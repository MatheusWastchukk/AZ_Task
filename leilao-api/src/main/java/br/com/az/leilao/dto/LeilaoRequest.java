package br.com.az.leilao.dto;

import br.com.az.leilao.validation.SafeText;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class LeilaoRequest {

    @Schema(example = "1001")
    private Integer codigo;

    @Length(max = 60)
    @NotNull
    @SafeText
    @Schema(example = "Leilao de graos")
    private String descricao;

    @NotNull
    @Schema(example = "1")
    private Integer vendedorId;

    @NotNull
    @Schema(example = "2026-05-01T10:00:00")
    private LocalDateTime inicioPrevisto;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Integer vendedorId) {
        this.vendedorId = vendedorId;
    }

    public LocalDateTime getInicioPrevisto() {
        return inicioPrevisto;
    }

    public void setInicioPrevisto(LocalDateTime inicioPrevisto) {
        this.inicioPrevisto = inicioPrevisto;
    }
}
