package br.com.braslar.api.domain.produto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.braslar.api.domain.DTO.CadastroProdutoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListaProdutos {
  @JsonProperty("ListaInformacoesEstoque")
  private List<CadastroProdutoDTO> produtos;

  public int getSize() {
    return produtos.size();
  }

}