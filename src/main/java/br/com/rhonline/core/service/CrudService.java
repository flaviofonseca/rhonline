package br.com.rhonline.core.service;

import br.com.rhonline.core.model.Entidade;

public interface CrudService<T extends Entidade> {

    T salvar(T entidade);

    T alterar(long id, T entidade);

    void excluir(long id);
}
