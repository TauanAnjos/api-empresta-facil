package com.emprestafacil.apiempresta.services;

import com.emprestafacil.apiempresta.models.PessoaModel;
import com.emprestafacil.apiempresta.repositories.PessoaRepository;
import com.emprestafacil.apiempresta.controllers.ItemController;
import com.emprestafacil.apiempresta.models.ItemModel;
import com.emprestafacil.apiempresta.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    PessoaRepository pessoaRepository;
    public ItemModel salvarItem(ItemModel item) {
        return itemRepository.save(item);
    }

    public List<ItemModel> listaItens() {
        List<ItemModel> itens = itemRepository.findAll();
        if (itens.isEmpty()){
            return itens;
        }
        else {
            for(ItemModel listId : itens){
                Long id = listId.getIdItem();
                listId.add(linkTo(methodOn(ItemController.class).buscarItem(id)).withSelfRel());
            }
        }
        return itens;
    }

    public ResponseEntity<Object> itemId(Long id) {
        Optional<ItemModel> item = itemRepository.findById(id);
        if (item.isPresent()){
            item.get().add(linkTo(methodOn(ItemController.class).listarItens()).withRel("Lista de itens"));
            return ResponseEntity.status(HttpStatus.OK).body(item);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    public ResponseEntity associar(Long idItem, Long idPessoa) {
        var item = itemRepository.findById(idItem);
        var pessoa = pessoaRepository.findById(idPessoa);
        if(item.isPresent() && pessoa.isPresent()){
            ItemModel itemOptional = item.get();
            PessoaModel pessoaOptional = pessoa.get();
            if(itemOptional.getDisponivel() && itemOptional.getPessoaModel() == null){
                itemOptional.setDisponivel(false);
                itemOptional.setPessoaModel(pessoaOptional);
                itemRepository.save(itemOptional);
                return ResponseEntity.status(HttpStatus.OK).body("Item associado com sucesso");
            }
        }else if (!item.isPresent() && pessoa.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item não disponivel");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item ou pessoa não encontrado");
    }
}
