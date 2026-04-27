package br.com.az.leilao.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class CompradorRequest {

    @NotNull
    @Schema(example = "1")
    private Integer empresaId;

    @NotNull
    @Schema(example = "10")
    private Integer leilaoId;

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Integer getLeilaoId() {
        return leilaoId;
    }

    public void setLeilaoId(Integer leilaoId) {
        this.leilaoId = leilaoId;
    }
}
