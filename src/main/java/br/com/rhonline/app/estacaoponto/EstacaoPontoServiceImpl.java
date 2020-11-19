package br.com.rhonline.app.estacaoponto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstacaoPontoServiceImpl implements EstacaoPontoService {

    private final EstacaoPontoRepository repository;

    @Autowired
    public EstacaoPontoServiceImpl(EstacaoPontoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void salvarEstacaoPonto(EstacaoPonto estacaoPonto) {
        repository.save(estacaoPonto);
    }

    @Override
    public void excluirEstacaoPonto(long id) {
        repository.deleteById(id);
    }

    @Override
    public void alterarEstacaoPonto(long id, EstacaoPonto from) {
        EstacaoPonto estacaoPontoSalva = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estacao ponto nao encontrada."));

        estacaoPontoSalva.setLatitude(from.getLatitude());
        estacaoPontoSalva.setLongitude(from.getLongitude());
        estacaoPontoSalva.setNomeEstacao(from.getNomeEstacao());

        repository.save(estacaoPontoSalva);
    }
}
