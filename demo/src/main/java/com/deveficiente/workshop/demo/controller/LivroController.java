package com.deveficiente.workshop.demo.controller;

import com.deveficiente.workshop.demo.domain.model.Livro;
import com.deveficiente.workshop.demo.controller.dto.LivroRequest;
import com.deveficiente.workshop.demo.controller.dto.LivroResponse;
import com.deveficiente.workshop.demo.controller.dto.LivroResponseDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    //1
    private final EntityManager manager;

    public LivroController(EntityManager manager) {
        this.manager = manager;
    }

    @GetMapping
    //2
    public ResponseEntity<List<LivroResponse>> listarTodos() {
        //3
        TypedQuery<Livro> typedQuery = manager.createQuery("select l from Livro l", Livro.class);
        List<Livro> livros = typedQuery.getResultList();
        //4
        List<LivroResponse> livrosReponse = livros
                .stream()
                .map(Livro::toDto)
                .toList();

        return ResponseEntity.ok(livrosReponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDetail> detalharLivro(@PathVariable Long id) {

        TypedQuery<Livro> typedQuery = manager.createQuery("select l from Livro l where l.id = :livroId", Livro.class);
        typedQuery.setParameter("livroId", id);

        Livro livroRetornado = typedQuery.getResultList().stream().findFirst().orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        Assert.state(livroRetornado != null, String.format("Livro de id %s não encontrado", id));

        LivroResponseDetail livroResponseDetail = new LivroResponseDetail(livroRetornado);
        return ResponseEntity.ok(livroResponseDetail);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> criarLivro(@RequestBody @Valid LivroRequest request) {

        //5
        Livro novoLivro = request.toModel(manager);
        manager.persist(novoLivro);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
