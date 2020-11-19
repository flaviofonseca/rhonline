package br.com.rhonline.app.bairro;

import br.com.rhonline.app.cidade.Cidade;
import br.com.rhonline.core.model.Entidade;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Bairro extends Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 150)
    private String nomeBairro;

    @JoinColumn(nullable = false, name = "cidade_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Cidade cidade;
}
