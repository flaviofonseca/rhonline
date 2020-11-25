package br.com.rhonline.app.dependente;

import br.com.rhonline.app.funcionario.Funcionario;
import br.com.rhonline.core.model.Entidade;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Dependente extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String nomeDependente;

    @Column
    private LocalDate dataNascimento;

    @JoinColumn(name = "id_funcionario", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcionario funcionario;
}
