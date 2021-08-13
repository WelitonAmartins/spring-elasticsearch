package br.com.well.springelasticsearch.service;

import br.com.well.springelasticsearch.entity.Operacao;
import br.com.well.springelasticsearch.repository.OperacaoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.index.IndexOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.index.Settings;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class OperacaoServiceImpl implements OperacaoService{

    private final OperacaoRepository operacaoRepository;

    private final org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate elasticsearchRestTemplate;

    private final ElasticsearchOperations elasticsearchOperations;


    @Override
    public Iterable<Operacao> findAll() {
        return this.operacaoRepository.findAll();
    }

    @Override
    public Operacao save(Operacao entity) {
        entity.setId(UUID.randomUUID().toString());




        return this.operacaoRepository.save(entity);
    }


    @Override
    public  List<IndexInformation> saveAndCreateIndex(Operacao entity) {
        entity.setId(UUID.randomUUID().toString());

        Calendar calendar = Calendar.getInstance();
        int mes = calendar.get(GregorianCalendar.MONTH);

        int ano = calendar.get(GregorianCalendar.YEAR);
        String idIndex = mes+"-"+ano+"_"+entity.getUser();
        IndexQuery indexQuery = new IndexQueryBuilder().withId(entity.getId()).withObject(entity).build();

        elasticsearchOperations.index(indexQuery,IndexCoordinates.of(idIndex));




        List<IndexInformation> information = elasticsearchOperations.indexOps(IndexCoordinates.of(idIndex)).getInformation();
        Map<String, Object> mapping = elasticsearchOperations.indexOps(IndexCoordinates.of(idIndex)).getMapping();

        final Settings settings = elasticsearchOperations.indexOps(IndexCoordinates.of(idIndex)).getSettings();



        return information;
    }

    @Override
    public void deleteById(String id) {
        this.operacaoRepository.deleteById(id);
    }

    @Override
    public List<Operacao> findAllByUser(String user) {
        return this.operacaoRepository.findAllByUser(user);
    }

    @Override
    public SearchHits<Operacao> findAllByIndex(String index) {

        //NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build();


        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .build();

        SearchHits<Operacao> productHits =
                elasticsearchOperations
                        .search(searchQuery,
                                Operacao.class,
                                IndexCoordinates.of(index));

//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(matchAllQuery())
//                .withIndices("idx-foo", "idx-bar")
//                .build();

       // List<ElasticEntity> elasticEntities = template.queryForList(searchQuery, ElasticEntity.class);
//        logger.trace(elasticEntities.toString());



        return productHits;
    }

    @Override
    public void deleteByUserAndDateStartAndDateEnd(String user, Date dataInicio, Date dataFim) {
        this.operacaoRepository.deleteByUserAndDateStartAndDateEnd(user, dataInicio, dataFim);
    }
}
