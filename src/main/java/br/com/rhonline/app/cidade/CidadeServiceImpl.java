package br.com.rhonline.app.cidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository repository;

    @Autowired
    public CidadeServiceImpl(CidadeRepository repository) {
        this.repository = repository;
    }
}
