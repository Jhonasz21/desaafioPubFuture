package com.controledefinancapessoal.repository;

import com.controledefinancapessoal.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa,Long > {
}
