package br.com.braslar.api.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public record CadastroOdfDTO(
                @NotBlank @JsonProperty("Odf") String odf,
                @NotBlank @JsonProperty("QuantidadePedido") String quantidadePedido,
                @NotBlank @JsonProperty("QuantidadeEntrada") String quantidadeEntrada) {
}
