package br.com.braslar.api.domain.etiqueta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.braslar.api.domain.DTO.CadastroEtiquetaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "etiquetas")
@Entity(name = "Etiqueta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serie;

    private String odf;

    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_apontamento")
    private LocalDateTime dataApontamento;

    @Setter
    @JsonProperty("Bip")
    private String bip;

    @Column(name = "quantidade_odf")
    private BigDecimal quantidadeOdf;

    @Column(name = "quanitdade_utilizada")
    @Setter
    private BigDecimal quantidadeUtilizada;

    public Etiqueta(CadastroEtiquetaDTO dados) {
        this.serie = dados.serie();
        this.odf = dados.odf();
        this.dataApontamento = dados.dataApontamento();
        this.bip = dados.bip();
        this.quantidadeOdf = new BigDecimal(dados.quantidadeOdf());
        this.quantidadeUtilizada = new BigDecimal(dados.quantidadeUtilizada());
    }

    public void atualizarInformacoes(CadastroEtiquetaDTO dados) {
        if (dados.dataApontamento() != null) {
            this.dataApontamento = dados.dataApontamento();
        }
        if (dados.bip() != null) {
            this.bip = dados.bip();
        }
        if (!new BigDecimal(dados.quantidadeOdf()).equals(0.0)) {
            this.quantidadeOdf = new BigDecimal(dados.quantidadeOdf());
        }
        if (!new BigDecimal(dados.quantidadeUtilizada()).equals(0.0)) {
            this.quantidadeUtilizada = new BigDecimal(dados.quantidadeUtilizada());
        }

    }

    // public void excluir() {
    // this.ativo = false;
    // }
}
