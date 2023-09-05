package io.github.felipesilva15.bookservice.domain.repository;

import io.github.felipesilva15.bookservice.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
