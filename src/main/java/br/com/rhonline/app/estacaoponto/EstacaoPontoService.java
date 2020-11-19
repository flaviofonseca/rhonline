package br.com.rhonline.app.estacaoponto;

public interface EstacaoPontoService {
    void salvarEstacaoPonto(EstacaoPonto estacaoPonto);

    void excluirEstacaoPonto(long id);

    void alterarEstacaoPonto(long id, EstacaoPonto from);
}
