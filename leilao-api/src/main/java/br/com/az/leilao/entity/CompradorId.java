package br.com.az.leilao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompradorId implements Serializable {

    @Column(name = "empresa")
    private Integer empresa;

    @Column(name = "leilao")
    private Integer leilao;

    public CompradorId() {
    }

    public CompradorId(Integer empresa, Integer leilao) {
        this.empresa = empresa;
        this.leilao = leilao;
    }

    public Integer getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Integer empresa) {
        this.empresa = empresa;
    }

    public Integer getLeilao() {
        return leilao;
    }

    public void setLeilao(Integer leilao) {
        this.leilao = leilao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CompradorId)) {
            return false;
        }
        CompradorId that = (CompradorId) o;
        return Objects.equals(empresa, that.empresa) && Objects.equals(leilao, that.leilao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empresa, leilao);
    }
}

