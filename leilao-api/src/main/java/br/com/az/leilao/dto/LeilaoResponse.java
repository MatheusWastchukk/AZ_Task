package br.com.az.leilao.dto;

import java.time.LocalDateTime;

public class LeilaoResponse {

    private Integer id;
    private Integer codigo;
    private String descricao;
    private Integer vendedorId;
    private String razaoSocialVendedor;
    private LocalDateTime inicioPrevisto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getRazaoSocialVendedor() {
        return razaoSocialVendedor;
    }

    public void setRazaoSocialVendedor(String razaoSocialVendedor) {
        this.razaoSocialVendedor = razaoSocialVendedor;
    }

    public LocalDateTime getInicioPrevisto() {
        return inicioPrevisto;
    }

    public void setInicioPrevisto(LocalDateTime inicioPrevisto) {
        this.inicioPrevisto = inicioPrevisto;
    }
}

