package br.com.well.springelasticsearch.service;

import br.com.well.springelasticsearch.entity.Operacao;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.data.elasticsearch.core.SearchHits;


import java.util.Date;
import java.util.List;

public interface OperacaoService {

    public Iterable<Operacao> findAll();

    public List<Operacao> findAllByUser(String user);

    public SearchHits<Operacao> findAllByIndex(String index);

    public Operacao save(Operacao entity);

   public  List<IndexInformation> saveAndCreateIndex(Operacao entity);

    public void deleteById(String id);

    public void deleteByUserAndDateStartAndDateEnd(String user, Date dataInicio, Date dataFim);
}
