package br.com.az.leilao.service;

import br.com.az.leilao.business.LoteBO;
import br.com.az.leilao.dto.LoteRequest;
import br.com.az.leilao.dto.LoteResponse;
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
@RequestMapping("/lotes")
public class LoteService {

    private final LoteBO loteBO;

    public LoteService(LoteBO loteBO) {
        this.loteBO = loteBO;
    }

    @GetMapping
    public List<LoteResponse> buscarTodos() {
        return loteBO.buscarTodos();
    }

    @GetMapping("/{id}")
    public LoteResponse buscarPorId(@PathVariable Integer id) {
        return loteBO.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoteResponse criar(@Valid @RequestBody LoteRequest request) {
        return loteBO.criar(request);
    }

    @PutMapping("/{id}")
    public LoteResponse atualizar(@PathVariable Integer id, @Valid @RequestBody LoteRequest request) {
        return loteBO.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id) {
        loteBO.remover(id);
    }
}

