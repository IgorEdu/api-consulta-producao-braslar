package br.com.braslar.api.controller;

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

import br.com.braslar.api.domain.DTO.CadastroOdfDTO;
import br.com.braslar.api.domain.odf.DadosDetalhamentoOdf;
import br.com.braslar.api.domain.odf.DadosListagemOdf;
import br.com.braslar.api.domain.odf.ListaOdfs;
import br.com.braslar.api.domain.odf.Odf;
import br.com.braslar.api.domain.odf.OdfRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("odfs")
public class OdfController {

    @Autowired
    private OdfRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<List<ResponseEntity<DadosDetalhamentoOdf>>> cadastrar(@RequestBody @Valid String dados,
            UriComponentsBuilder uriBuilder) throws JsonMappingException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        List<ResponseEntity<DadosDetalhamentoOdf>> retornos = new ArrayList<>();

        ListaOdfs odfs = mapper.readValue(dados, ListaOdfs.class);

        for (int i = 0; i < odfs.getSize(); i++) {
            String idOdf = repository.findIdByOdf(odfs.getOdfs().get(i).odf());
            if (idOdf != null) {
                retornos.add(atualizar(odfs.getOdfs().get(i), idOdf));
            } else {

                var odf = new Odf(odfs.getOdfs().get(i));
                repository.save(odf);

                retornos.add(ResponseEntity.created(null).body(new DadosDetalhamentoOdf(odf)));
            }
        }

        return ResponseEntity.ok().body(retornos);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemOdf>> listar(
            @PageableDefault(size = 10, sort = { "odf" }) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemOdf::new);

        return ResponseEntity.ok(page);
    }

    private ResponseEntity<DadosDetalhamentoOdf> atualizar(CadastroOdfDTO dados, String idOdf) {
        var odfAtualizar = repository.getReferenceById(idOdf);
        odfAtualizar.setQuantidadeEntrada(dados.quantidadeEntrada());
        odfAtualizar.setQuantidadePedido(dados.quantidadePedido());
        repository.save(odfAtualizar);

        return ResponseEntity.ok(new DadosDetalhamentoOdf(odfAtualizar));
    }

    @GetMapping("/{odf}")
    public ResponseEntity<DadosDetalhamentoOdf> detalhar(@PathVariable String numOdf) {
        Odf odf = repository.getReferenceByOdf(numOdf);
        return ResponseEntity.ok(new DadosDetalhamentoOdf(odf));
    }

}
