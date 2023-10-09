package br.com.braslar.api.domain.odf;

public record DadosDetalhamentoOdf(String odf, String quantidadePedido, String quantidadeEntrada) {

    public DadosDetalhamentoOdf(Odf odf) {
        this(
                odf.getOdf(), odf.getQuantidadePedido(),
                odf.getQuantidadeEntrada());
    }

}
