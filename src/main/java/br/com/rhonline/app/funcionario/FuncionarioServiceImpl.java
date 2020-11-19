package br.com.rhonline.app.funcionario;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository repository;

    @Autowired
    public FuncionarioServiceImpl(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        log.info(funcionario.toString());
        return repository.save(funcionario);
    }

    @Override
    @Transactional
    public Funcionario alterar(long id, Funcionario funcionario) {
        Funcionario funcionarioSalvo = repository.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        funcionarioSalvo.setNomeFuncionario(funcionario.getNomeFuncionario());
        funcionarioSalvo.setDataNascimento(funcionario.getDataNascimento());
        funcionarioSalvo.setCpf(funcionario.getCpf());

        return repository.save(funcionarioSalvo);
    }

    @Override
    @Transactional
    public void excluir(long id) {
        repository.deleteById(id);
    }
}
