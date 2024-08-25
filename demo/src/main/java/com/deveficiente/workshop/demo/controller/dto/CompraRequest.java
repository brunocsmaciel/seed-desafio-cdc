package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.controller.Documento;
import com.deveficiente.workshop.demo.domain.model.*;
import com.deveficiente.workshop.demo.repository.CupomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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
                            //1
                            @NotNull @Valid PedidoRequest pedido,
                            String codigoCupom) {

    //1 cupomRepository
    public Compra toModel(EntityManager manager, CupomRepository cupomRepository) {

        //1 pais
        Pais pais = manager.find(Pais.class, paisId);

        if (pais == null) {
            throw new EntityNotFoundException(String.format("entidade não encontrada. Pais %s", pais));
        }

        //1 compra
        //1 pedido
        Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(manager);

        //1 passar funcao como argumento conta 1 ponto
        Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, pais, telefone, cep, funcaoCriacaoPedido);

        //1
        if (estadoId != null) {
            compra.setEstado(manager.find(Estado.class, estadoId));
        }

//        // TODO criar repositorio, aumentar a carga intrinseca porém se fazer necessário
//        TypedQuery<Cupom> cupomQuery = manager.createQuery("select c from Cupom c where c.codigo = :codigo", Cupom.class);
//        cupomQuery.setParameter("codigo", codigoCupom);
//        Cupom cupom = cupomQuery.getSingleResult();

        //1
        if(StringUtils.hasText(codigoCupom)) {
            Cupom cupom = cupomRepository.findByCodigo(codigoCupom).orElseThrow(() -> new EntityNotFoundException("cupom não encontrado"));
            compra.aplicaCupom(cupom);
        }

        return compra;
    }
}
