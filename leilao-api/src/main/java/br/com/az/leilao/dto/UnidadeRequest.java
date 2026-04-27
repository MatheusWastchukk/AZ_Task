package br.com.az.leilao.dto;

import br.com.az.leilao.validation.SafeText;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UnidadeRequest {

    @NotBlank
    @Length(max = 128)
    @SafeText
    @Schema(example = "Kg")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
