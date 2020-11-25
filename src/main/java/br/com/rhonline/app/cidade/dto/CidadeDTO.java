package br.com.rhonline.app.cidade.dto;

import br.com.rhonline.app.cidade.Cidade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CidadeDTO {
    private Long id;
    private String nomeCidade;
    private String uf;

    public static CidadeDTO of(Cidade cidade) {
        if(cidade == null) return CidadeDTO.builder().build();
        return CidadeDTO.builder()
                .id(cidade.getId())
                .nomeCidade(cidade.getNomeCidade())
                .uf(cidade.getUf())
                .build();
    }

    public Cidade from() {
        Cidade cidade = new Cidade();
        cidade.setId(getId());
        cidade.setNomeCidade(getNomeCidade());
        cidade.setUf(getUf());
        return cidade;
    }
}
