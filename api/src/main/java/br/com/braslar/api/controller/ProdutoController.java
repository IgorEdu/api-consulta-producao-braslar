package br.com.braslar.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.braslar.api.domain.DTO.CadastroProdutoDTO;
import br.com.braslar.api.domain.produto.DadosDetalhamentoProduto;
import br.com.braslar.api.domain.produto.DadosListagemProduto;
import br.com.braslar.api.domain.produto.ListaProdutos;
import br.com.braslar.api.domain.produto.Produto;
import br.com.braslar.api.domain.produto.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<List<ResponseEntity<DadosDetalhamentoProduto>>> cadastrar(
            @RequestBody @Valid String dados,
            UriComponentsBuilder uriBuilder) throws JsonMappingException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        List<ResponseEntity<DadosDetalhamentoProduto>> retornos = new ArrayList<>();

        ListaProdutos produtos = mapper.readValue(dados, ListaProdutos.class);

        for (int i = 0; i < produtos.getSize(); i++) {
            String idProduto = repository.findIdByCodigoAndEmpresaAndLocal(
                    produtos.getProdutos().get(i).codigo(),
                    produtos.getProdutos().get(i).empresa(), produtos.getProdutos().get(i).local());
            if (idProduto != null) {
                retornos.add(atualizar(produtos.getProdutos().get(i), idProduto));
            } else {
                var produto = new Produto(produtos.getProdutos().get(i));
                repository.save(produto);
                retornos.add(ResponseEntity.created(null).body(new DadosDetalhamentoProduto(produto)));

            }
        }

        return ResponseEntity.ok().body(retornos);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProduto>> listar(
            @PageableDefault(size = 10, sort = { "codigo" }) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemProduto::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity<DadosDetalhamentoProduto> atualizar(CadastroProdutoDTO cadastroProdutoDTO, String id) {

        var produtoAtualizar = repository.getReferenceById(id);
        produtoAtualizar.setDescricao(cadastroProdutoDTO.descricao());
        // produtoAtualizar.setQuantidade(new
        // BigDecimal(cadastroProdutoDTO.quantidade()));
        // produtoAtualizar.setEstoqueMinimo(new
        // BigDecimal(cadastroProdutoDTO.estoqueMinimo()));
        // produtoAtualizar.setEstoqueMaximo(new
        // BigDecimal(cadastroProdutoDTO.estoqueMaximo()));

        if (!cadastroProdutoDTO.quantidade().isBlank()) {
            produtoAtualizar.setQuantidade(new BigDecimal(cadastroProdutoDTO.quantidade()));
        } else {
            produtoAtualizar.setQuantidade(new BigDecimal(0));
        }

        if (!cadastroProdutoDTO.estoqueMinimo().isBlank()) {
            produtoAtualizar.setEstoqueMinimo(new BigDecimal(cadastroProdutoDTO.estoqueMinimo()));
        } else {
            produtoAtualizar.setEstoqueMinimo(new BigDecimal(0));
        }

        if (!cadastroProdutoDTO.estoqueMaximo().isBlank()) {
            produtoAtualizar.setEstoqueMaximo(new BigDecimal(cadastroProdutoDTO.estoqueMaximo()));
        } else {
            produtoAtualizar.setEstoqueMaximo(new BigDecimal(0));
        }

        repository.save(produtoAtualizar);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produtoAtualizar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProduto> detalhar(@PathVariable String id) {
        Produto produto = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
    }

}
