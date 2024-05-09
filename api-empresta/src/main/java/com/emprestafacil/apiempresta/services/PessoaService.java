package com.emprestafacil.apiempresta.services;

import com.emprestafacil.apiempresta.controllers.PessoaController;
import com.emprestafacil.apiempresta.models.PessoaModel;
import com.emprestafacil.apiempresta.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public PessoaModel cadastrarPessoa(PessoaModel pessoa){
        return pessoaRepository.save(pessoa);
    }

    public List<PessoaModel> listaDePessoas(){
       List<PessoaModel> list = pessoaRepository.findAll();
       if (list.isEmpty()){
           return list;
       }
       else {
           for (PessoaModel pessoas : list){
               Long id = pessoas.getIdPessoa();
               pessoas.add(linkTo(methodOn(PessoaController.class).buscarPessoa(id)).withSelfRel());
           }
       }
       return list;
    }

    public ResponseEntity<Object> buscarPessoa(Long id) {
        Optional<PessoaModel> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()){
            pessoa.get().add(linkTo(methodOn(PessoaController.class).listarPessoas()).withRel("Lista de pessoas"));
            return ResponseEntity.status(HttpStatus.OK).body(pessoa);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
