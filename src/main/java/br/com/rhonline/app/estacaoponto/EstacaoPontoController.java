package br.com.rhonline.app.estacaoponto;

import br.com.rhonline.app.estacaoponto.dto.EstacaoPontoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacaoPonto")
public class EstacaoPontoController {

    private final EstacaoPontoService estacaoPontoService;

    @Autowired
    public EstacaoPontoController(EstacaoPontoService estacaoPontoService) {
        this.estacaoPontoService = estacaoPontoService;
    }

    @PostMapping("")
    public ResponseEntity salvarEstacaoPonto(EstacaoPontoDTO estacaoPontoDTO) {
        estacaoPontoService.salvarEstacaoPonto(estacaoPontoDTO.from());
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity alterarEstacaoPonto(@PathVariable long id,
                                              @RequestBody EstacaoPontoDTO estacaoPontoDTO) {
        estacaoPontoService.alterarEstacaoPonto(id, estacaoPontoDTO.from());
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirEstacaoPonto(@PathVariable long id) {
        estacaoPontoService.excluirEstacaoPonto(id);
        return ResponseEntity.ok(null);
    }
}
