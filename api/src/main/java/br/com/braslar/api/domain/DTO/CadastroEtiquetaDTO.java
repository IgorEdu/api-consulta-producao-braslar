package br.com.braslar.api.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record CadastroEtiquetaDTO(
                @JsonProperty("Serie") String serie,
                @NotBlank @JsonProperty("Odf") String odf,
                @NotBlank @JsonProperty("DataApontamento") String dataApontamento,
                @NotBlank @JsonProperty("Bip") String bip,
                @NotBlank @JsonProperty("QuantidadeOdf") String quantidadeOdf,
                @NotBlank @JsonProperty("QuantidadeUtilizada") String quantidadeUtilizada) {
}
