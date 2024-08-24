package com.deveficiente.workshop.demo.repository;

import com.deveficiente.workshop.demo.domain.model.Livro;
import org.springframework.data.repository.CrudRepository;

public interface LivroRepository extends CrudRepository<Livro, Long> {
}
