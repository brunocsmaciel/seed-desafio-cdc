package com.deveficiente.workshop.demo.controller;

import com.deveficiente.workshop.demo.controller.dto.CompraRequest;
import com.deveficiente.workshop.demo.domain.model.Estado;
import com.deveficiente.workshop.demo.domain.model.Pais;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoPertenceAPaisValidator implements Validator {


    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CompraRequest pedido = (CompraRequest) target;

        Pais pais = manager.find(Pais.class, pedido.paisId());
        Estado estado = manager.find(Estado.class, pedido.estadoId());

        if (!estado.pertence(pais)) {
            errors.rejectValue("estadoId", null, "este estado não é do país selecionado");
        }

    }
}
