package io.github.felipesilva15.cambioservice.domain.repository;

import io.github.felipesilva15.cambioservice.domain.entity.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
    Cambio findByFromAndTo(String from, String to);
}
