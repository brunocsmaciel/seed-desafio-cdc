package com.deveficiente.workshop.demo.controller;

import com.deveficiente.workshop.demo.controller.dto.CompraRequest;
import com.deveficiente.workshop.demo.domain.model.Compra;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final EntityManager manager;

    private final EstadoPertenceAPaisValidator pertenceAPaisValidator;

    public PedidoController(EntityManager manager, EstadoPertenceAPaisValidator pertenceAPaisValidator) {
        this.manager = manager;
        this.pertenceAPaisValidator = pertenceAPaisValidator;
    }

    @PostMapping("/novo")
    @Transactional
    public ResponseEntity<String> novoPedido(@RequestBody @Valid CompraRequest request) {

        Compra compra = request.toModel(manager);
        manager.persist(compra);
        return ResponseEntity.ok(compra.toString());
    }
}
