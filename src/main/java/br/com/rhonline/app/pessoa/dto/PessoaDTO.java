package br.com.rhonline.app.pessoa.dto;

import br.com.rhonline.app.cidade.dto.CidadeDTO;
import br.com.rhonline.app.funcionario.Sexo;
import br.com.rhonline.app.pessoa.Pessoa;
import lombok.Builder;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class PessoaDTO {

    private long id;
    @NotEmpty(message = "Nome preenchimento obrigatório")
    private String nome;
    @NotEmpty(message = "CPF preenchimento obrigatório")
    private String cpf;
    @NotNull(message = "Data nascimento preenchimento obrigatório")
    private LocalDate dataNascimento;
    @NotEmpty(message = "Sexo  preenchimento obrigatório")
    private String sexo;
    @NotEmpty(message = "Logradouro preenchimento obrigatório")
    private String logradouro;
    @NotEmpty(message = "Bairro preenchimento obrigatório")
    private String bairro;
    private String complemento;
    @NotEmpty(message = "CEP preenchimento obrigatório")
    private String cep;
    @NotNull(message = "Cidade preenchimento obrigatório")
    private CidadeDTO cidade;

    public static PessoaDTO of(Pessoa pessoa) {
        if (pessoa == null) return PessoaDTO.builder().build();
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .cpf(pessoa.getCpf())
                .dataNascimento(pessoa.getDataNascimento())
                .sexo(pessoa.getSexo().getValor())
                .logradouro(pessoa.getLogradouro())
                .bairro(pessoa.getBairro())
                .complemento(pessoa.getComplemento())
                .cep(pessoa.getCep())
                .cidade(CidadeDTO.of(pessoa.getCidade()))
                .build();
    }

    public Pessoa from() {
        ModelMapper modelMapper = new ModelMapper();
        Pessoa pessoa = modelMapper.map(this, Pessoa.class);
        pessoa.setSexo(Sexo.sexoPeloValor(getSexo()));
        return pessoa;
    }
}
