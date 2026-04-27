package br.com.az.leilao.dto;

import br.com.az.leilao.validation.SafeText;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LoteRequest {

    @Schema(example = "1")
    private Integer numeroLote;

    @NotNull
    @Length(max = 60)
    @SafeText
    @Schema(example = "Milho amarelo")
    private String descricao;

    @NotNull
    @DecimalMin(value = "0.01")
    @Schema(example = "1000.00")
    private BigDecimal quantidade;

    @DecimalMin(value = "0.01")
    @Schema(example = "12.50")
    private BigDecimal valorInicial;

    @NotNull
    @Length(max = 128)
    @SafeText
    @Schema(example = "Kg")
    private String unidade;

    @NotNull
    @Schema(example = "1")
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
