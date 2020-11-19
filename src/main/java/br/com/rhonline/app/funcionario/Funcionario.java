package br.com.rhonline.app.funcionario;

import br.com.rhonline.app.bairro.Bairro;
import br.com.rhonline.app.dependente.Dependente;
import br.com.rhonline.core.model.Entidade;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Funcionario extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 150, nullable = false)
    private String nomeFuncionario;

    @Column
    private LocalDate dataNascimento;

    @Column(length = 150)
    private String nomePai;

    @Column(length = 150)
    private String nomeMae;

    @Enumerated
    private Sexo sexo;

    @Column
    private String numeroRG;

    @Column
    private String cpf;

    @Column
    private String logradouro;

    @ManyToOne
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
