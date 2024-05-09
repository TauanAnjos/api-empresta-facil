package com.emprestafacil.apiempresta.controllers;

import com.emprestafacil.apiempresta.models.ItemModel;
import com.emprestafacil.apiempresta.services.ItemService;
import com.emprestafacil.apiempresta.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<ItemModel> criarItem(@RequestBody ItemModel item){
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.salvarItem(item));
    }

    @GetMapping
    public List<ItemModel> listarItens(){
        return itemService.listaItens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarItem(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.itemId(id));
    }

    @PutMapping("/associar/{idItem}/{idPessoa}")
    public ResponseEntity updateItem(@PathVariable(name = "idItem") Long idItem, @PathVariable(name = "idPessoa") Long idPessoa){
        return itemService.associar(idItem, idPessoa);
    }
}
