package br.com.rhonline.app.pessoa;

public interface PessoaService {
    Pessoa obterPessoaPeloCpf(String cpf);

    Pessoa salvarPessoa(Pessoa pessoa);
}
