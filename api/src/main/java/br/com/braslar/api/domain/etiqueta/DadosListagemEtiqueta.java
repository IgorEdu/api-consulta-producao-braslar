package br.com.braslar.api.domain.etiqueta;

import java.time.LocalDateTime;

public record DadosListagemEtiqueta(String odf, LocalDateTime dataApontamento, String bip) {

    public DadosListagemEtiqueta(Etiqueta etiqueta) {
        this(etiqueta.getOdf(), etiqueta.getDataApontamento(),
                etiqueta.getBip());
    }

}
