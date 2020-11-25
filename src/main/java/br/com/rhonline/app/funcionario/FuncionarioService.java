package br.com.rhonline.app.funcionario;

import br.com.rhonline.core.service.CrudService;

import java.util.Set;

public interface FuncionarioService extends CrudService<Funcionario> {
    Funcionario obterPeloId(long id);

    Set<Funcionario> listarTodos();

}
