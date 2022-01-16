package com.controledefinancapessoal.repository;

import com.controledefinancapessoal.enums.TipoDespesa;
import com.controledefinancapessoal.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface DespesaRepository extends JpaRepository<Despesa,Long > {
    List<Despesa> findAllByDataPagamentoBetween(Date startDate, Date endDate);

    List<Despesa> findAllByTipoDespesa(TipoDespesa tipoDespesa);
}
