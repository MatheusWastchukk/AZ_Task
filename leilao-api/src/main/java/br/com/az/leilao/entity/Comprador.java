package br.com.az.leilao.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "comprador")
public class Comprador {

    @EmbeddedId
    private CompradorId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("empresa")
    @JoinColumn(name = "empresa", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("leilao")
    @JoinColumn(name = "leilao", nullable = false)
    private Leilao leilao;

    public CompradorId getId() {
        return id;
    }

    public void setId(CompradorId id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }
}

