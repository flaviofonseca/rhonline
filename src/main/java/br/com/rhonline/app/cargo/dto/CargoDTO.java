package br.com.rhonline.app.cargo.dto;

import br.com.rhonline.app.cargo.Cargo;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class CargoDTO {

    private Long id;

    @NotEmpty(message = "Nome do cargo")
    private String nomeCargo;

    public static CargoDTO of(Cargo cargo) {
        if (cargo == null) return CargoDTO.builder().build();
        return CargoDTO.builder()
                .id(cargo.getId())
                .nomeCargo(cargo.getNomeCargo())
                .build();
    }

    public Cargo from() {
        return new Cargo(id, nomeCargo);
    }
}
