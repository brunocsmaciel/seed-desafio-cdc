package com.deveficiente.workshop.demo.controller;

import com.deveficiente.workshop.demo.controller.dto.CupomRequest;
import com.deveficiente.workshop.demo.domain.model.Cupom;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cupons")
public class CupomController {

    private final EntityManager manager;

    public CupomController(EntityManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> criaCupom(@RequestBody @Valid CupomRequest request) {

        Cupom cupom = request.toModel(manager);
        manager.persist(cupom);

        return ResponseEntity.ok(cupom.getCodigo().toString());
    }
}
