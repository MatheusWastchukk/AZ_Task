package br.com.az.leilao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LeilaoListResponse {

    private Integer id;
    private String razaoSocialVendedor;
    private LocalDateTime inicioPrevisto;
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

