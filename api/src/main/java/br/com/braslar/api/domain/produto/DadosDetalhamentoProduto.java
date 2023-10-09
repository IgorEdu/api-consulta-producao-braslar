package br.com.braslar.api.domain.produto;

import java.math.BigDecimal;
import java.math.BigInteger;

public record DadosDetalhamentoProduto(BigInteger empresa, BigInteger codigo, String descricao, BigInteger local,
        BigDecimal quantidade,
        BigDecimal estoqueMinimo,
        BigDecimal estoqueMaximo) {

    public DadosDetalhamentoProduto(Produto produto) {
        this(
                produto.getEmpresa(), produto.getCodigo(), produto.getDescricao(),
                produto.getLocal(), produto.getQuantidade(),
                produto.getEstoqueMinimo(), produto.getEstoqueMaximo());
    }

}
