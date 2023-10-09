package br.com.braslar.api.domain.odf;

import br.com.braslar.api.domain.DTO.CadastroOdfDTO;
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

@Table(name = "odfs")
@Entity(name = "Odf")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Odf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String odf;
    @Column(name = "quantidade_pedido")
    @Setter
    private String quantidadePedido;
    @Column(name = "quantidade_entrada")
    @Setter
    private String quantidadeEntrada;

    public Odf(CadastroOdfDTO dados) {
        this.odf = dados.odf();
        this.quantidadePedido = dados.quantidadePedido();
        this.quantidadeEntrada = dados.quantidadeEntrada();
    }

    public void atualizarInformacoes(CadastroOdfDTO dados) {
        if (dados.quantidadePedido() != null) {
            this.quantidadePedido = dados.quantidadePedido();
        }
        if (dados.quantidadeEntrada() != null) {
            this.quantidadeEntrada = dados.quantidadeEntrada();
        }

    }

    // public void excluir() {
    // this.ativo = false;
    // }
}
