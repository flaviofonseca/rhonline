package br.com.rhonline.app.funcionario;

import br.com.rhonline.app.funcionario.dto.FuncionarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> obterPeloId(@PathVariable("id") long id) {
        return ResponseEntity.ok(FuncionarioDTO.of(funcionarioService.obterPeloId(id)));
    }

    @GetMapping("")
    public ResponseEntity<Set<FuncionarioDTO>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos()
                .stream()
                .map(FuncionarioDTO::of)
                .collect(Collectors.toSet()));
    }

    @PostMapping("/")
    public ResponseEntity<FuncionarioDTO> salvar(@RequestBody @Valid FuncionarioDTO funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.salvar(funcionario.from());
        return ResponseEntity.ok(FuncionarioDTO.of(funcionarioSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable("id") long id, @RequestBody @Valid FuncionarioDTO funcionario) {
        // TODO buscar funcionario
        Funcionario funcionarioSalvo = funcionarioService.alterar(id, funcionario.from());
        return ResponseEntity.ok(funcionarioSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.ok("");
    }

}
