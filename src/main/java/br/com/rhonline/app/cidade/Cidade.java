package br.com.rhonline.app.cidade;

import br.com.rhonline.core.model.Entidade;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Cidade extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String nomeCidade;
}
