package com.controledefinancapessoal.service;


import com.controledefinancapessoal.enums.TipoDespesa;
import com.controledefinancapessoal.model.Despesa;
import com.controledefinancapessoal.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;



    public Despesa salvarDespesa(Despesa despesa) {
        Despesa despesaSalva = despesaRepository.save(despesa);
        return despesaSalva;
    }


    public void deleteById(Long id) {
        despesaRepository.deleteById(id);
    }




    public Despesa update(Despesa newDespesa, Long id) throws NoSuchElementException {
        Optional<Despesa>updatedDespesa=despesaRepository.findById(id)
                .map(despesa -> {
                    despesa.setConta(newDespesa.getConta());
                    despesa.setDataPagamento(newDespesa.getDataPagamento());
                    despesa.setTipoDespesa(newDespesa.getTipoDespesa());
                    despesa.setDataPagamentoEsperado(newDespesa.getDataPagamentoEsperado());
                    despesa.setValor(newDespesa.getValor());
                    return despesa;
                });
        if (updatedDespesa.isPresent()){
            despesaRepository.save(updatedDespesa.get());

        }else
            throw new NoSuchElementException();
        return updatedDespesa.get();
    }

    public List<Despesa> obterDespesa(Date startDate, Date endDate, TipoDespesa tipoDespesa) {
        if (tipoDespesa != null) {
            List<Despesa> despesas =  despesaRepository.findAllByTipoDespesa(tipoDespesa);
            return despesas;
        }
        if (startDate != null && endDate != null){
            List<Despesa>despesas = despesaRepository
                    .findAllByDataPagamentoBetween(startDate,endDate);
            return despesas;
        }
        List<Despesa>despesas= despesaRepository.findAll();
        return despesas;
    }
}
