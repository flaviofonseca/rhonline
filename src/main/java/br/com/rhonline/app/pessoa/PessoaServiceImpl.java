package br.com.rhonline.app.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa obterPessoaPeloCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    @Override
    @Transactional
    public Pessoa salvarPessoa(Pessoa pessoa) {
        validarCpfJaCadastrado(pessoa);
        return pessoaRepository.save(pessoa);
    }

    private void validarCpfJaCadastrado(Pessoa pessoa) {
        Pessoa pessoaSalvaMesmoCpf = obterPessoaPeloCpf(pessoa.getCpf());
        if (pessoaSalvaMesmoCpf == null) return;

        if (isCpfIguais(pessoa, pessoaSalvaMesmoCpf) && pessoaMesmoCpfDiferentePessoaSendoSalva(pessoa, pessoaSalvaMesmoCpf)) {
            throw new RuntimeException("Já existe uma pessoa com este CPF.");
        }
    }

    private boolean pessoaMesmoCpfDiferentePessoaSendoSalva(Pessoa pessoa, Pessoa pessoaSalva) {
        return !pessoa.equals(pessoaSalva);
    }

    private boolean isCpfIguais(Pessoa pessoa, Pessoa pessoaSalva) {
        return pessoaSalva.getCpf().equalsIgnoreCase(pessoa.getCpf());
    }

}
