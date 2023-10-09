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

import br.com.braslar.api.domain.DTO.CadastroEtiquetaDTO;
import br.com.braslar.api.domain.etiqueta.DadosDetalhamentoEtiqueta;
import br.com.braslar.api.domain.etiqueta.DadosListagemEtiqueta;
import br.com.braslar.api.domain.etiqueta.Etiqueta;
import br.com.braslar.api.domain.etiqueta.EtiquetaRepository;
import br.com.braslar.api.domain.etiqueta.ListaEtiquetas;
import jakarta.validation.Valid;

@RestController
@RequestMapping("etiquetas")
// @SecurityRequirement(name = "bearer-key")
public class EtiquetaController {

    @Autowired
    private EtiquetaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid String dados, UriComponentsBuilder uriBuilder)
            throws JsonMappingException, JsonProcessingException {
        // var etiqueta = new Etiqueta(dados);
        // repository.save(etiqueta);

        // var uri =
        // uriBuilder.path("/etiqueta/{id}").buildAndExpand(etiqueta.getId()).toUri();
        // return ResponseEntity.created(uri).body(new
        // DadosDetalhamentoEtiqueta(etiqueta));

        ObjectMapper mapper = new ObjectMapper();
        List<ResponseEntity<DadosDetalhamentoEtiqueta>> retornos = new ArrayList<>();

        @SuppressWarnings("unchecked")
        ListaEtiquetas etiquetas = mapper.readValue(dados, ListaEtiquetas.class);

        for (int i = 0; i < etiquetas.getSize(); i++) {
            String idEtiqueta = repository.findIdBySerie(etiquetas.getEtiquetas().get(i).serie());
            if (idEtiqueta != null) {
                retornos.add(atualizar(etiquetas.getEtiquetas().get(i), idEtiqueta));
            } else {

                var etiqueta = new Etiqueta(etiquetas.getEtiquetas().get(i));
                repository.save(etiqueta);

                // var uri =
                // uriBuilder.path("/etiqueta/{id}").buildAndExpand(etiqueta.getId()).toUri();
                // retornos.add(ResponseEntity.created(uri).body(new
                // DadosDetalhamentoEtiqueta(etiqueta)));
                retornos.add(ResponseEntity.created(null).body(new DadosDetalhamentoEtiqueta(etiqueta)));
            }
        }

        return ResponseEntity.ok().body(retornos);
    }

    private ResponseEntity<DadosDetalhamentoEtiqueta> atualizar(CadastroEtiquetaDTO dados,
            String idEtiqueta) {
        var etiquetaAtualizar = repository.getReferenceById(idEtiqueta);
        etiquetaAtualizar.setBip(dados.bip());
        etiquetaAtualizar.setQuantidadeUtilizada(new BigDecimal(dados.quantidadeUtilizada()));
        repository.save(etiquetaAtualizar);

        return ResponseEntity.ok(new DadosDetalhamentoEtiqueta(etiquetaAtualizar));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemEtiqueta>> listar(
            @PageableDefault(size = 10, sort = { "dataApontamento" }) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemEtiqueta::new);
        return ResponseEntity.ok(page);
    }

    // public ResponseEntity atualizar(CadastroEtiquetaDTO dados) {
    // var etiquetaAtualizar = repository.getReferenceById(null);
    // etiquetaAtualizar.setBip(dados.bip());
    // repository.save(etiquetaAtualizar);

    // return ResponseEntity.ok(new DadosDetalhamentoEtiqueta(etiquetaAtualizar));
    // }

    // @PutMapping
    // @Transactional
    // public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProduto
    // dados) {
    // var paciente = repository.getReferenceById(dados.id());
    // paciente.atualizarInformacoes(dados);

    // return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    // }

    // @DeleteMapping("/{id}")
    // @Transactional
    // public ResponseEntity excluir(@PathVariable Long id) {
    // var etiqueta = repository.getReferenceById(id);
    // etiqueta.excluir();

    // return ResponseEntity.noContent().build();
    // }

    @GetMapping("/{serie}")
    public ResponseEntity detalhar(@PathVariable String serie) {
        Etiqueta etiqueta = repository.getReferenceBySerie(serie);
        return ResponseEntity.ok(new DadosDetalhamentoEtiqueta(etiqueta));
    }

}
