package br.com.az.leilao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "leilao")
@SequenceGenerator(name = "seq_leilao", sequenceName = "seq_leilao", allocationSize = 1)
public class Leilao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_leilao")
    @Column(name = "id")
    private Integer id;

    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "descricao", nullable = false, length = 60)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendedor", nullable = false)
    private Empresa vendedor;

    @Column(name = "inicio_previsto", nullable = false)
    private LocalDateTime inicioPrevisto;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

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

    public Empresa getVendedor() {
        return vendedor;
    }

    public void setVendedor(Empresa vendedor) {
        this.vendedor = vendedor;
    }

    public LocalDateTime getInicioPrevisto() {
        return inicioPrevisto;
    }

    public void setInicioPrevisto(LocalDateTime inicioPrevisto) {
        this.inicioPrevisto = inicioPrevisto;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
