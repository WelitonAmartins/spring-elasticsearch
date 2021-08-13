package br.com.well.springelasticsearch.repository;

import br.com.well.springelasticsearch.entity.Operacao;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface OperacaoRepository extends ElasticsearchRepository <Operacao, String> {

    public List<Operacao> findAllByUser(String usuario);



    @Transactional
    @Query("delete from Operacao o where o.user = :user and o.data = :dataInicio")
    public void deleteByUserAndDateStartAndDateEnd(@Param("user") String user, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}
