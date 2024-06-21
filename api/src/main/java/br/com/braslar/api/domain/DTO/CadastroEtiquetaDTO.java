package br.com.braslar.api.domain.DTO;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record CadastroEtiquetaDTO(
    @JsonProperty("Serie") String serie,
    @NotBlank @JsonProperty("Odf") String odf,
    @JsonProperty("DataApontamento") LocalDateTime dataApontamento,
    @NotBlank @JsonProperty("Bip") String bip,
    @NotBlank @JsonProperty("QuantidadeOdf") String quantidadeOdf,
    @NotBlank @JsonProperty("QuantidadeUtilizada") String quantidadeUtilizada) {
}
