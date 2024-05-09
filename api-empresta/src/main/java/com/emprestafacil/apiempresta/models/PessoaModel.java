package com.emprestafacil.apiempresta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "pessoas_db")
@Data
public class PessoaModel extends RepresentationModel<PessoaModel> implements Serializable {
    private static final long serialVersionUID = 1L;


    @OneToMany(mappedBy = "pessoaModel")
    @JsonManagedReference
    private List<ItemModel> listItens;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;
    @Column(nullable = false, length = 180)
    private String nome;

}
