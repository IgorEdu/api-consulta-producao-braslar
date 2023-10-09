package br.com.braslar.api.domain.etiqueta;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.braslar.api.domain.DTO.CadastroEtiquetaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListaEtiquetas {
  @JsonProperty("ListaInformacoesEtiqueta")
  private List<CadastroEtiquetaDTO> etiquetas;

  public int getSize() {
    return etiquetas.size();
  }

}