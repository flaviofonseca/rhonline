package br.com.rhonline.app.funcionario;

import br.com.rhonline.app.funcionario.dto.FuncionarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("")
    public ResponseEntity<FuncionarioDTO> salvar(@RequestBody FuncionarioDTO funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.salvar(funcionario.from());
        return ResponseEntity.ok(FuncionarioDTO.of(funcionarioSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity alterar(@PathVariable("id") long id, @RequestBody FuncionarioDTO funcionario) {
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
