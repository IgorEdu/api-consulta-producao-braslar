package br.com.braslar.api.domain.etiqueta;

public record DadosListagemEtiqueta(String odf, String dataApontamento, String bip) {

    public DadosListagemEtiqueta(Etiqueta etiqueta) {
        this(etiqueta.getOdf(), etiqueta.getDataApontamento(),
                etiqueta.getBip());
    }

}
