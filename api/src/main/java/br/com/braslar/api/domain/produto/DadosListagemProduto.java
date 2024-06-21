package br.com.braslar.api.domain.produto;

import java.math.BigDecimal;
import java.math.BigInteger;

public record DadosListagemProduto(Long id, BigInteger codigo, String descricao, BigDecimal quantidade) {

    public DadosListagemProduto(Produto produto) {
        this(produto.getId(), produto.getCodigo(), produto.getDescricao(),
                produto.getQuantidade());
    }

}
