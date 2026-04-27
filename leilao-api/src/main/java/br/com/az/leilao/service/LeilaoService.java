package br.com.az.leilao.service;

import br.com.az.leilao.business.LeilaoBO;
import br.com.az.leilao.dto.LeilaoListResponse;
import br.com.az.leilao.dto.LeilaoRequest;
import br.com.az.leilao.dto.LeilaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/leiloes")
@Tag(name = "Leiloes", description = "CRUD de leiloes")
public class LeilaoService {

    private final LeilaoBO leilaoBO;

    public LeilaoService(LeilaoBO leilaoBO) {
        this.leilaoBO = leilaoBO;
    }

    @GetMapping
    @Operation(summary = "Listar leiloes")
    public List<LeilaoListResponse> buscarTodos() {
        return leilaoBO.buscarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar leilao por ID")
    public LeilaoResponse buscarPorId(@PathVariable Integer id) {
        return leilaoBO.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar leilao")
    public LeilaoResponse criar(@Valid @RequestBody LeilaoRequest request) {
        return leilaoBO.criar(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar leilao")
    public LeilaoResponse atualizar(@PathVariable Integer id, @Valid @RequestBody LeilaoRequest request) {
        return leilaoBO.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover leilao")
    public void remover(@PathVariable Integer id) {
        leilaoBO.remover(id);
    }
}
