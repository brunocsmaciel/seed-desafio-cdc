package com.deveficiente.workshop.demo.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long autorId;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String descricao;

    @Column(name = "instante_criacao")
    private LocalDateTime instanteCriacao;

    @Deprecated
    public Autor() {
    }

    public Autor(String nome, String email, String descricao) {
        // validar campos
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        instanteCriacao = LocalDateTime.now();

    }

    public Long getAutorId() {
        return autorId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                ", instanteCriacao=" + instanteCriacao +
                '}';
    }
}
