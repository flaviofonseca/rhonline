package br.com.rhonline.app.funcionario.dto;

import br.com.rhonline.app.cargo.dto.CargoDTO;
import br.com.rhonline.app.funcionario.Funcionario;
import br.com.rhonline.app.pessoa.dto.PessoaDTO;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class FuncionarioDTO {

    private Long id;
    @NotNull(message = "Data admissão preenchimento obrigatório")
    private LocalDate dataAdmissao;
    @NotEmpty(message = "Matrícula preenchimento obrigatório")
    private String matricula;

    private CargoDTO cargo;
    private PessoaDTO pessoa;

    public Funcionario from() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(getId());
        funcionario.setMatricula(getMatricula());
        funcionario.setDataAdmissao(getDataAdmissao());
        funcionario.setPessoa(getPessoa().from());
        funcionario.setCargo(getCargo().from());
        return funcionario;
    }

    public static FuncionarioDTO of(Funcionario funcionario) {
        if (funcionario == null) return FuncionarioDTO.builder().build();
        return FuncionarioDTO.builder()
                .id(funcionario.getId())
                .dataAdmissao(funcionario.getDataAdmissao())
                .matricula(funcionario.getMatricula())
                .pessoa(PessoaDTO.of(funcionario.getPessoa()))
                .cargo(CargoDTO.of(funcionario.getCargo()))
                .build();
    }
}
