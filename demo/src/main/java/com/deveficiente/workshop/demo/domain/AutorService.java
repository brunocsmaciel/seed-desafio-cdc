package com.deveficiente.workshop.demo.domain;

import com.deveficiente.workshop.demo.domain.model.Autor;
import com.deveficiente.workshop.demo.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void criaAutor(Autor autor) {

        autorRepository.save(autor);
    }
}
