package com.deveficiente.workshop.demo.controller.dto;

import com.deveficiente.workshop.demo.domain.model.Categoria;
import com.deveficiente.workshop.demo.domain.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroResponseDetail {

    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Long numeroPaginas;
    private String isbn;
    private LocalDate dataPublicacao;
    private Categoria categoria;
    private DetalheSiteAutorResponse autor;


    public LivroResponseDetail(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao();
        this.categoria = livro.getCategoria();
        this.autor = new DetalheSiteAutorResponse(livro.getAutor());
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Long getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public DetalheSiteAutorResponse getAutor() {
        return autor;
    }
}
