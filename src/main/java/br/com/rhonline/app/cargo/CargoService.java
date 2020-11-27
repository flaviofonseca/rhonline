package br.com.rhonline.app.cargo;

import java.util.List;

public interface CargoService {
    Cargo salvar(Cargo cargo);

    void excluir(long id);

    List<Cargo> listarTodos();

    Cargo obterPeloId(long id);

    List<Cargo> obterPeloTermo(String termo);
}
