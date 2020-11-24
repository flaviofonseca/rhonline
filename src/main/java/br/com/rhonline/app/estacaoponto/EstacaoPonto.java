package br.com.rhonline.app.estacaoponto;

import br.com.rhonline.core.model.Entidade;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class EstacaoPonto extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome da estacao nao pode ser vazia")
    @Column(length = 150)
    private String nomeEstacao;

    @Column
    private double longitude;

    @Column
    private double latitude;
}
