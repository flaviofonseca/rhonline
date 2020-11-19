package br.com.rhonline.app.dependente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DependenteServiceImpl implements DependenteService {

    private final DependenteRepository repository;

    @Autowired
    public DependenteServiceImpl(DependenteRepository repository) {
        this.repository = repository;
    }
}
