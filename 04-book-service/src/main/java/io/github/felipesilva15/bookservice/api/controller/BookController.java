package io.github.felipesilva15.bookservice.api.controller;

import io.github.felipesilva15.bookservice.api.client.CambioClient;
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

@RestController
@RequestMapping("book-service")
public class BookController {
    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository repository;

    @Autowired
    private CambioClient cambioClient;

    @GetMapping("/{id}/{currency}")
    public ResponseEntity<BookDTO> findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        Book book = repository.getById(id);

        if(book == null) {
            throw new RuntimeException("Book not found");
        }

        var cambio = cambioClient.getCambio(book.getPrice(), "USD", currency);
        String port = environment.getProperty("local.server.port") + " FEIGN";

        BookDTO bookDTO = new BookDTO(book.getId(), book.getAuthor(), book.getLaunchDate(), cambio.convertedValue(), book.getTitle(), currency, port);

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    /**
    @GetMapping("/{id}/{currency}")
    public ResponseEntity<BookDTO> findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        Book book = repository.getById(id);

        if(book == null) {
            throw new RuntimeException("Book not found");
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response =  new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", CambioDTO.class, params);

        var cambio = response.getBody();
        String port = environment.getProperty("local.server.port");

        BookDTO bookDTO = new BookDTO(book.getId(), book.getAuthor(), book.getLaunchDate(), cambio.convertedValue(), book.getTitle(), currency, port);

        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }*/
}
