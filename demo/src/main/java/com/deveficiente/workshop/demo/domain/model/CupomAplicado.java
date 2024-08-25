package com.deveficiente.workshop.demo.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class CupomAplicado {

    @ManyToOne
    private Cupom cupom;

    @Positive
    @NotNull
    private BigDecimal percentualDescontoMomento;

    @Future
    @NotNull
    private LocalDate validadeMomento;


    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDescontoMomento = cupom.getPercentualDesconto();
        this.validadeMomento = cupom.getDataValidade();
    }

    @Deprecated
    public CupomAplicado() {
    }
}
