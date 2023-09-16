package io.github.felipesilva15.bookservice.api.controller;

import io.github.felipesilva15.bookservice.api.client.CambioClient;
import io.github.felipesilva15.bookservice.api.dto.BookDTO;
import io.github.felipesilva15.bookservice.domain.entity.Book;
import io.github.felipesilva15.bookservice.domain.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {
    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioClient cambioClient;

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping("/{id}/{currency}")
    public ResponseEntity<BookDTO> findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        Book book = repository.getById(id);

        if(book == null) {
            throw new RuntimeException("Book not found");
        }

        var cambio = cambioClient.getCambio(book.getPrice(), "USD", currency);
        String port = environment.getProperty("local.server.port");
        String environment = "Book port: " + port + " Cambio port: " + cambio.environment();

        BookDTO bookDTO = new BookDTO(book.getId(), book.getAuthor(), book.getLaunchDate(), cambio.convertedValue(), book.getTitle(), currency, environment);

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}
