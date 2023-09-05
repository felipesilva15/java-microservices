package io.github.felipesilva15.bookservice.api.controller;

import io.github.felipesilva15.bookservice.api.dto.BookDTO;
import io.github.felipesilva15.bookservice.domain.entity.Book;
import io.github.felipesilva15.bookservice.domain.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("book-service")
public class BookController {
    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @GetMapping("/{id}/{currency}")
    public ResponseEntity<BookDTO> findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        Book book = repository.getById(id);

        if(book == null) {
            throw new RuntimeException("Book not found");
        }

        String port = environment.getProperty("local.server.port");

        BookDTO bookDTO = new BookDTO(book.getId(), book.getAuthor(), book.getLaunchDate(), book.getPrice(), book.getTitle(), currency, port);

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }
}
