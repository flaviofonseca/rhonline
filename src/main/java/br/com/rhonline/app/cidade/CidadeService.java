package br.com.rhonline.app.cidade;

import java.util.List;

public interface CidadeService {
    List<Cidade> obterPeloTermo(String termo);
}
