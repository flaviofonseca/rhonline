package br.com.rhonline.app.cargo;

import br.com.rhonline.app.cargo.dto.CargoDTO;
import br.com.rhonline.app.cidade.dto.CidadeDTO;
import br.com.rhonline.app.funcionario.dto.FuncionarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/cargo")
public class CargoController {

    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping("")
    public ResponseEntity<CargoDTO> salvar(@RequestBody @Valid CargoDTO cargoDTO) {
        Cargo cargo = cargoService.salvar(cargoDTO.from());
        return ResponseEntity.ok(CargoDTO.of(cargo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> obterPeloId(@PathVariable("id") long id) {
        return ResponseEntity.ok(CargoDTO.of(cargoService.obterPeloId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") long id) {
        cargoService.excluir(id);
        return ResponseEntity.ok("");
    }

    @GetMapping("")
    public ResponseEntity<Set<CargoDTO>> listarTodos() {
        return ResponseEntity.ok(cargoService.listarTodos()
                .stream()
                .map(CargoDTO::of)
                .collect(Collectors.toSet()));
    }

    @GetMapping("/obterPeloTermo")
    public List<CargoDTO> obterPeloTermo(String termo) {
        return cargoService.obterPeloTermo(termo).stream().map(CargoDTO::of).collect(Collectors.toList());
    }
}
