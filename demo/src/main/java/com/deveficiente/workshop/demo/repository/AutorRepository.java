package com.deveficiente.workshop.demo.repository;

import com.deveficiente.workshop.demo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByEmail(String email);

    Optional<Autor> findByNome(String nome);
}
