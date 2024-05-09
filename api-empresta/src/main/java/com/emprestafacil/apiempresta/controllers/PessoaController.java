package com.emprestafacil.apiempresta.controllers;

import com.emprestafacil.apiempresta.models.PessoaModel;
import com.emprestafacil.apiempresta.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoa")
public class PessoaController {
    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaModel> cadastrar(@RequestBody PessoaModel pessoa){
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.cadastrarPessoa(pessoa));
    }

    @GetMapping
    public List<PessoaModel> listarPessoas(){
        return pessoaService.listaDePessoas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPessoa(@PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarPessoa(id));
    }
}
