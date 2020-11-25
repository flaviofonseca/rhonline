package br.com.rhonline.app.pessoa;

import br.com.rhonline.app.pessoa.dto.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/pessoaByCpf")
    public PessoaDTO pessoaBYCpf(String cpf) {
        Pessoa pessoa = pessoaService.obterPessoaPeloCpf(cpf);
        return PessoaDTO.of(pessoa);
    }

}
