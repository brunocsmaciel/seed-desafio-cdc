package com.deveficiente.workshop.demo.repository;

import com.deveficiente.workshop.demo.domain.model.Cupom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CupomRepository extends CrudRepository<Cupom, Long> {

    Optional<Cupom> findByCodigo(String codigo);
}
