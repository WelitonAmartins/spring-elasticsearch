package br.com.well.springelasticsearch.controller;

import br.com.well.springelasticsearch.entity.Operacao;
import br.com.well.springelasticsearch.service.OperacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/operacao")
public class OperacaoController {

    private final OperacaoService operacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Operacao save(@RequestBody Operacao entity) {
        return this.operacaoService.save(entity);
    }

    @PostMapping("/index")
    @ResponseStatus(HttpStatus.CREATED)
    public List<IndexInformation> saveAndCreateIndex(@RequestBody Operacao entity) {
        return this.operacaoService.saveAndCreateIndex(entity);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Operacao> findAll() {
        return this.operacaoService.findAll();
    }

    @GetMapping("index")
    @ResponseStatus(HttpStatus.OK)
    public List<Operacao> findAllByIndex(@RequestParam("index") String index) {
        return this.operacaoService.findAllByIndex(index);
    }

    @GetMapping("/{user}")
    @ResponseStatus(HttpStatus.OK)
    public List<Operacao> findByUser(@PathVariable String user) {
        return this.operacaoService.findAllByUser(user);
    }

    @DeleteMapping("/delete-teste")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUserAndDateStartAndDateEnd(@RequestParam(value = "user") String user,
                                                             @RequestParam(value = "dataInicio") @DateTimeFormat(pattern = "dd-MM-yyyy")Date dataInicio,
                                                             @RequestParam(value = "dataFim") @DateTimeFormat(pattern = "dd-MM-yyyy")Date dataFim) {
         this.operacaoService.deleteByUserAndDateStartAndDateEnd(user, dataInicio, dataFim);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) {
         this.operacaoService.deleteById(id);
    }
}
