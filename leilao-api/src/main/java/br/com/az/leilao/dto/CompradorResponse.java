package br.com.az.leilao.dto;

public class CompradorResponse {

    private Integer empresaId;
    private String razaoSocialEmpresa;
    private Integer leilaoId;
    private Integer codigoLeilao;

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getRazaoSocialEmpresa() {
        return razaoSocialEmpresa;
    }

    public void setRazaoSocialEmpresa(String razaoSocialEmpresa) {
        this.razaoSocialEmpresa = razaoSocialEmpresa;
    }

    public Integer getLeilaoId() {
        return leilaoId;
    }

    public void setLeilaoId(Integer leilaoId) {
        this.leilaoId = leilaoId;
    }

    public Integer getCodigoLeilao() {
        return codigoLeilao;
    }

    public void setCodigoLeilao(Integer codigoLeilao) {
        this.codigoLeilao = codigoLeilao;
    }
}

