package br.com.braslar.api.domain.odf;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.braslar.api.domain.DTO.CadastroOdfDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListaOdfs {
  @JsonProperty("ListaInformacoesOrdemProducao")
  private List<CadastroOdfDTO> odfs;

  public int getSize() {
    return odfs.size();
  }

}