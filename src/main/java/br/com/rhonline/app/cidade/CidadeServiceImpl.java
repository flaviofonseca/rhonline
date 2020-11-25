package br.com.rhonline.app.cidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository repository;

    @Autowired
    public CidadeServiceImpl(CidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cidade> obterPeloTermo(String termo) {
        return repository.findAll()
                .stream()
                .filter(c -> c.getNomeCidade().toLowerCase().contains(termo.toLowerCase()))
                .collect(Collectors.toList());
    }
}
