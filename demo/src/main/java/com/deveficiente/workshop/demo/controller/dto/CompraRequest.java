package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.controller.Documento;
import com.deveficiente.workshop.demo.domain.model.Compra;
import com.deveficiente.workshop.demo.domain.model.Estado;
import com.deveficiente.workshop.demo.domain.model.Pais;
import com.deveficiente.workshop.demo.domain.model.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import java.util.function.Function;

public record CompraRequest(@NotBlank @Email String email,
                            @NotBlank String nome,
                            @NotBlank String sobrenome,
                            @NotBlank @Documento String documento,
                            @NotBlank String endereco,
                            @NotBlank String complemento,
                            @NotBlank String cidade,
                            @NotNull Long paisId,
                            @NotNull Long estadoId,
                            @NotBlank String telefone,
                            @NotBlank String cep,
                            @NotNull @Valid PedidoRequest pedido) {

    public Compra toModel(EntityManager manager) {

        Pais pais = manager.find(Pais.class, paisId);

        if (pais == null) {
            throw new EntityNotFoundException(String.format("entidade não encontrada. Pais %s", pais));
        }

        // Lazy Value do Kotlin, quando temos uma entidade que depende de outra
        Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(manager);

        Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, pais, telefone, cep, funcaoCriacaoPedido);
        if (estadoId != null) {
            compra.setEstado(manager.find(Estado.class, estadoId));
        }

        return compra;
    }

    private boolean documentoValido() {
        Assert.hasLength(this.documento, "documento deve ser preenchido");
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
    }
}
