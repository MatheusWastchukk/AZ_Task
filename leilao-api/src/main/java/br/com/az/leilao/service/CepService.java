package br.com.az.leilao.service;

import br.com.az.leilao.business.CepBO;
import br.com.az.leilao.dto.CepResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/ceps")
@Tag(name = "CEPs", description = "Consulta de CEP via ViaCEP")
public class CepService {

    private final CepBO cepBO;

    public CepService(CepBO cepBO) {
        this.cepBO = cepBO;
    }

    @GetMapping("/{cep}")
    @Operation(summary = "Consultar endereco por CEP")
    public CepResponse buscarPorCep(@PathVariable String cep) {
        return cepBO.buscarPorCep(cep);
    }
}
