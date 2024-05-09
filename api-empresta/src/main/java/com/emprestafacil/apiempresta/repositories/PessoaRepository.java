package com.emprestafacil.apiempresta.repositories;

import com.emprestafacil.apiempresta.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
}
