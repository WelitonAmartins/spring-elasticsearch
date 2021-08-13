package br.com.well.springelasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "well-valemobi")
public class Operacao {

    private String id;
    private String user;
    private String operacao;
    private Double valor;
    private String categoria;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date data;
}
