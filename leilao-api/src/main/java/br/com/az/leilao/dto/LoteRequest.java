package br.com.az.leilao.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LoteRequest {

    private Integer numeroLote;

    @NotNull
    @Length(max = 60)
    private String descricao;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal quantidade;

    @DecimalMin(value = "0.01")
    private BigDecimal valorInicial;

    @NotNull
    @Length(max = 128)
    private String unidade;

    @NotNull
    private Integer leilaoId;

    public Integer getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(Integer numeroLote) {
        this.numeroLote = numeroLote;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getLeilaoId() {
        return leilaoId;
    }

    public void setLeilaoId(Integer leilaoId) {
        this.leilaoId = leilaoId;
    }
}
