package br.com.braslar.api.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record CadastroProdutoDTO(
        @JsonIgnore String id,
        @NotBlank @JsonProperty("Empresa") String empresa,
        @NotBlank @JsonProperty("Codigo") String codigo,
        @NotBlank @JsonProperty("Descricao") String descricao,
        @NotBlank @JsonProperty("Local") String local,
        @NotBlank @JsonProperty("Quantidade") String quantidade,
        @NotBlank @JsonProperty("EstMinimo") String estoqueMinimo,
        @NotBlank @JsonProperty("EstMaximo") String estoqueMaximo

) {

}
