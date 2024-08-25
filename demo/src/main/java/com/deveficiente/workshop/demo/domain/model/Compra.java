package com.deveficiente.workshop.demo.domain.model;

import com.deveficiente.workshop.demo.controller.Documento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.function.Function;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String email;
    private final String nome;
    private final String sobrenome;
    private final String documento;
    private final String endereco;
    private final String complemento;
    @ManyToOne
    private final Pais pais;
    @ManyToOne
    private Estado estado;
    private final String telefone;
    private final String cep;
    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private final Pedido pedido;
    @Embedded
    private CupomAplicado cupomAplicado;

    public Compra(@NotBlank @Email String email,
                  @NotBlank String nome,
                  @NotBlank String sobrenome,
                  @NotBlank @Documento String documento,
                  @NotBlank String endereco,
                  @NotBlank String complemento,
                  @NotNull Pais pais,
                  @NotBlank String telefone,
                  @NotBlank String cep,
                  Function<Compra, Pedido> funcaoCriacaoPedido) {

        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }


    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", pais=" + pais +
                ", estado=" + estado +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", pedido=" + pedido +
                ", cupom=" + cupomAplicado +
                '}';
    }

    public void aplicaCupom(Cupom cupom) {
        if (this.cupomAplicado != null) {
            throw new UnsupportedOperationException("não é possível associar outro cupom a essa compra");
        }

        if (!cupom.ehValido()) {
            throw new UnsupportedOperationException("cupom aplicado não é mais valido");
        }
        this.cupomAplicado = new CupomAplicado(cupom);
    }
}
