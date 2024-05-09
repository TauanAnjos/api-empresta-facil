package com.emprestafacil.apiempresta.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Entity
@Table(name = "itens_db")
@Data
public class ItemModel extends RepresentationModel<ItemModel> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    @JsonBackReference
    private PessoaModel pessoaModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false)
    private Boolean disponivel;


}
