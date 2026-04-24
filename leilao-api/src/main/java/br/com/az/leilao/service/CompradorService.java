package br.com.az.leilao.service;

import br.com.az.leilao.business.CompradorBO;
import br.com.az.leilao.dto.CompradorRequest;
import br.com.az.leilao.dto.CompradorResponse;
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
@RequestMapping("/compradores")
public class CompradorService {

    private final CompradorBO compradorBO;

    public CompradorService(CompradorBO compradorBO) {
        this.compradorBO = compradorBO;
    }

    @GetMapping
    public List<CompradorResponse> buscarTodos() {
        return compradorBO.buscarTodos();
    }

    @GetMapping("/{empresaId}/{leilaoId}")
    public CompradorResponse buscarPorId(@PathVariable Integer empresaId, @PathVariable Integer leilaoId) {
        return compradorBO.buscarPorId(empresaId, leilaoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompradorResponse criar(@Valid @RequestBody CompradorRequest request) {
        return compradorBO.criar(request);
    }

    @PutMapping("/{empresaId}/{leilaoId}")
    public CompradorResponse atualizar(@PathVariable Integer empresaId,
                                       @PathVariable Integer leilaoId,
                                       @Valid @RequestBody CompradorRequest request) {
        return compradorBO.atualizar(empresaId, leilaoId, request);
    }

    @DeleteMapping("/{empresaId}/{leilaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer empresaId, @PathVariable Integer leilaoId) {
        compradorBO.remover(empresaId, leilaoId);
    }
}

