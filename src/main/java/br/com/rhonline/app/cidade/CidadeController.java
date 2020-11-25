package br.com.rhonline.app.cidade;

import br.com.rhonline.app.cidade.dto.CidadeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("cidade")
public class CidadeController {

    private final CidadeService cidadeService;

    @Autowired
    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping("/obterPeloTermo")
    public List<CidadeDTO> obterPeloTermo(String termo) {
        return cidadeService.obterPeloTermo(termo).stream().map(CidadeDTO::of).collect(Collectors.toList());
    }
}
