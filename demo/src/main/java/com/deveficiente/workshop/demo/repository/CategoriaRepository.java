package com.deveficiente.workshop.demo.repository;

import com.deveficiente.workshop.demo.domain.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nome);
}
