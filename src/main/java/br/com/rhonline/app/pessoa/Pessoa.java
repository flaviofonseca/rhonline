package br.com.rhonline.app.pessoa;

import br.com.rhonline.app.cidade.Cidade;
import br.com.rhonline.app.funcionario.Sexo;
import br.com.rhonline.core.model.Entidade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@EqualsAndHashCode
@Data
@Entity
@Table
public class Pessoa extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(length = 150)
    private String nomePai;

    @Column(length = 150)
    private String nomeMae;

    @NotNull(message = "Sexo preenchimento obrigatório")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(length = 30)
    private String numeroRG;

    @Column
    private String logradouro;

    @Column(length = 30)
    private String bairro;

    @Column(length = 30)
    private String complemento;

    @Column(length = 30)
    private String cep;

    @JoinColumn(name = "id_cidade", nullable = false, foreignKey = @ForeignKey(name = "fk_cidade"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Cidade cidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return id.equals(pessoa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
