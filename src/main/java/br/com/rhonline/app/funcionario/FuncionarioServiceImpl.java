package br.com.rhonline.app.funcionario;

import br.com.rhonline.app.pessoa.PessoaService;
import br.com.rhonline.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final PessoaService pessoaService;
    private final FuncionarioRepository repository;

    @Autowired
    public FuncionarioServiceImpl(PessoaService pessoaService, FuncionarioRepository repository) {
        this.pessoaService = pessoaService;
        this.repository = repository;
    }

    @Override
    public Funcionario obterPeloId(long id) {
        return repository.findById(id).orElse(new Funcionario());
    }

    @Override
    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        validarSalvar(funcionario);
        validarMatriculaJaCadastrada(funcionario);

        funcionario.setPessoa(pessoaService.salvarPessoa(funcionario.getPessoa()));

        return repository.save(funcionario);
    }

    private void validarMatriculaJaCadastrada(Funcionario funcionario) {
        if (repository.matriculaExists(funcionario.getMatricula(), Optional.ofNullable(funcionario.getId()).orElse(0l)) > 0) {
            throw new BusinessException("Já existe um funcionário com essa matrícula.");
        }
    }

    private void validarSalvar(Funcionario funcionario) {
        if (StringUtils.isEmpty(funcionario.getMatricula())) {
            throw new BusinessException("Matrícula preenchimento obrigatório");
        }

        if (funcionario.getDataAdmissao() == null) {
            throw new BusinessException("Data de adminissão preenchimento obrigatório");
        }

        if(!funcionario.getPessoa().getCidade().registroSalvo()) {
            throw new BusinessException("Cidade preenchimento obrigatório");
        }
    }

    @Override
    public Set<Funcionario> listarTodos() {
        return repository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void excluir(long id) {
        repository.deleteById(id);
    }
}
