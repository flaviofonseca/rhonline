package br.com.rhonline.app.funcionario;

import br.com.rhonline.app.bairro.Bairro;
import br.com.rhonline.app.dependente.Dependente;
import br.com.rhonline.core.model.Entidade;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Funcionario extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome funcionario nao pode ser vazio.")
    @Column(length = 150, nullable = false)
    private String nomeFuncionario;

    @NotNull(message = "Data de nascimento deve ser preenchida")
    @Column
    private LocalDate dataNascimento;

    @Column(length = 150)
    private String nomePai;

    @Column(length = 150)
    private String nomeMae;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(length = 30)
    private String numeroRG;

    @NotEmpty(message = "CPF nao pode ser vazio")
    @Column(length = 20)
    private String cpf;

    @Column
    private String logradouro;

    @ManyToOne(fetch = FetchType.LAZY)
    private Bairro bairro;

    @OneToMany(mappedBy = "dependente", fetch = FetchType.LAZY)
    private Set<Dependente> dependentes;

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nomeFuncionario='" + nomeFuncionario + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
