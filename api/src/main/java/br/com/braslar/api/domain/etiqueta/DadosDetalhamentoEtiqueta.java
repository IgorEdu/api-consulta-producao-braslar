package br.com.braslar.api.domain.etiqueta;

import java.math.BigDecimal;

public record DadosDetalhamentoEtiqueta(String serie, String odf, String dataApontamento, String bip,
        BigDecimal quantidadeOdf,
        BigDecimal quanitdadeUtilizada) {

    public DadosDetalhamentoEtiqueta(Etiqueta etiqueta) {
        this(
                etiqueta.getSerie(), etiqueta.getOdf(), etiqueta.getDataApontamento(), etiqueta.getBip(),
                etiqueta.getQuantidadeOdf(), etiqueta.getQuantidadeUtilizada());
    }

}
