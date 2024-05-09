package com.emprestafacil.apiempresta.repositories;

import com.emprestafacil.apiempresta.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long> {

}
