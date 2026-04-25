package br.com.az.leilao.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmpresaRequest {

    @NotBlank
    @Length(max = 64)
    private String razaoSocial;

    @NotBlank
    @Length(max = 32)
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$", message = "deve seguir o formato 00.000.000/0000-00")
    private String cnpj;

    @Length(max = 64)
    private String logradouro;

    @Length(max = 64)
    private String municipio;

    @Length(max = 10)
    @Pattern(regexp = "^$|^\\d+$", message = "deve conter apenas numeros")
    private String numero;

    @Length(max = 64)
    private String complemento;

    @Length(max = 64)
    private String bairro;

    @Length(max = 16)
    @Pattern(regexp = "^$|^\\d{5}-\\d{3}$", message = "deve seguir o formato 00000-000")
    private String cep;

    @Length(max = 32)
    @Pattern(regexp = "^$|^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$", message = "deve seguir o formato (00) 0000-0000 ou (00) 00000-0000")
    private String telefone;

    @Email
    @Length(max = 254)
    private String email;

    @Pattern(regexp = "^$|^(https?://).+$", message = "deve iniciar com http:// ou https://")
    @Length(max = 254)
    private String site;

    @NotBlank
    @Length(max = 20)
    private String usuario;

    @Length(max = 128)
    private String senha;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
