package br.com.rhonline.app.funcionario;

import br.com.rhonline.app.cargo.Cargo;
import br.com.rhonline.app.pessoa.Pessoa;
import br.com.rhonline.core.model.Entidade;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table
@Data
public class Funcionario extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Matrícula deve ser preenchida")
    @Column(length = 15, nullable = false)
    private String matricula;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    @JoinColumn(name = "id_pessoa", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Pessoa pessoa;

    @JoinColumn(name = "id_cargo", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cargo cargo;

}
