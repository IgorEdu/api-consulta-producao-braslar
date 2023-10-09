package br.com.braslar.api.domain.produto;

import java.math.BigDecimal;
import java.math.BigInteger;

import br.com.braslar.api.domain.DTO.CadastroProdutoDTO;
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

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status_produto")
    private String status;
    private int categoria;
    private int familia;

    @Column(name = "local_estoque")
    private BigInteger local;

    private BigInteger empresa;
    private BigInteger codigo;
    @Setter
    private String descricao;
    @Setter
    private BigDecimal quantidade;
    @Column(name = "estoque_minimo")
    @Setter
    private BigDecimal estoqueMinimo;
    @Column(name = "estoque_maximo")
    @Setter
    private BigDecimal estoqueMaximo;

    public Produto(CadastroProdutoDTO dados) {
        this.empresa = new BigInteger(dados.empresa());
        this.codigo = new BigInteger(dados.codigo());
        this.descricao = dados.descricao();
        this.local = new BigInteger(dados.local());
        this.quantidade = new BigDecimal(dados.quantidade());
        this.estoqueMinimo = new BigDecimal(dados.estoqueMinimo());
        this.estoqueMaximo = new BigDecimal(dados.estoqueMaximo());
    }

    public void atualizarInformacoes(CadastroProdutoDTO dados) {
        if (!new BigDecimal(dados.quantidade()).equals(0.0)) {
            this.quantidade = new BigDecimal(dados.quantidade());
        }
        if (!new BigDecimal(dados.estoqueMinimo()).equals(0.0)) {
            this.estoqueMinimo = new BigDecimal(dados.estoqueMinimo());
        }
        if (!new BigDecimal(dados.estoqueMaximo()).equals(0.0)) {
            this.estoqueMaximo = new BigDecimal(dados.estoqueMaximo());
        }

    }

    // public void excluir() {
    // this.ativo = false;
    // }
}
