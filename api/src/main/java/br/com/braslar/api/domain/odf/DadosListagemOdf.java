package br.com.braslar.api.domain.odf;

public record DadosListagemOdf(String odf, String quantidadePedido, String quantidadeEntrada) {

    public DadosListagemOdf(Odf odf) {
        this(odf.getOdf(), odf.getQuantidadePedido(),
                odf.getQuantidadeEntrada());
    }

}
