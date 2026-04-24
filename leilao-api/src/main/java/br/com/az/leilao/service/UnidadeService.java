package br.com.az.leilao.service;

import br.com.az.leilao.business.UnidadeBO;
import br.com.az.leilao.dto.UnidadeRequest;
import br.com.az.leilao.entity.Unidade;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/unidades")
public class UnidadeService {

    private final UnidadeBO unidadeBO;

    public UnidadeService(UnidadeBO unidadeBO) {
        this.unidadeBO = unidadeBO;
    }

    @GetMapping
    public List<Unidade> buscarTodos() {
        return unidadeBO.buscarTodos();
    }

    @GetMapping("/{id}")
    public Unidade buscarPorId(@PathVariable Integer id) {
        return unidadeBO.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Unidade criar(@Valid @RequestBody UnidadeRequest request) {
        return unidadeBO.criar(request);
    }

    @PutMapping("/{id}")
    public Unidade atualizar(@PathVariable Integer id, @Valid @RequestBody UnidadeRequest request) {
        return unidadeBO.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id) {
        unidadeBO.remover(id);
    }
}

