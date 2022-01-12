package com.controledefinancapessoal.repository;

import com.controledefinancapessoal.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita,Long> {
}
