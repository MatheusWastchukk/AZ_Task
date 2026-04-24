package br.com.az.leilao.service;

import br.com.az.leilao.business.EmpresaBO;
import br.com.az.leilao.dto.EmpresaRequest;
import br.com.az.leilao.dto.EmpresaResponse;
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
@RequestMapping("/empresas")
public class EmpresaService {

    private final EmpresaBO empresaBO;

    public EmpresaService(EmpresaBO empresaBO) {
        this.empresaBO = empresaBO;
    }

    @GetMapping
    public List<EmpresaResponse> buscarTodos() {
        return empresaBO.buscarTodos();
    }

    @GetMapping("/{id}")
    public EmpresaResponse buscarPorId(@PathVariable Integer id) {
        return empresaBO.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaResponse criar(@Valid @RequestBody EmpresaRequest request) {
        return empresaBO.criar(request);
    }

    @PutMapping("/{id}")
    public EmpresaResponse atualizar(@PathVariable Integer id, @Valid @RequestBody EmpresaRequest request) {
        return empresaBO.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id) {
        empresaBO.remover(id);
    }
}

