package com.controledefinancapessoal.repository;

import com.controledefinancapessoal.model.Receita;
import com.fasterxml.jackson.databind.DatabindException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita,Long> {
    List<Receita> findAllByDateRecebimentoBetween(Date startDate, Date endDate);
}
