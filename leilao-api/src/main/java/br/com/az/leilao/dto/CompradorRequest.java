package br.com.az.leilao.dto;

import javax.validation.constraints.NotNull;

public class CompradorRequest {

    @NotNull
    private Integer empresaId;

    @NotNull
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

