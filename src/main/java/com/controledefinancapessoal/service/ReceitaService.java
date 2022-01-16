package com.controledefinancapessoal.service;



import com.controledefinancapessoal.enums.TipoReceita;
import com.controledefinancapessoal.model.Despesa;
import com.controledefinancapessoal.model.Receita;
import com.controledefinancapessoal.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReceitaService<receitas> {



    @Autowired
    private ReceitaRepository receitaRepository;


    public Receita salvarReceita(Receita receita) {
        Receita receitaSalva = receitaRepository.save(receita);
        return receitaSalva;
    }


    public void deleteById(Long id) {
        receitaRepository.deleteById(id);
    }

    public Receita update(Receita newReceita, Long id) throws NoSuchElementException {
        Optional<Receita>updatedReceita=receitaRepository.findById(id)
                .map(receita -> {
                    receita.setTipoReceita(newReceita.getTipoReceita());
                    receita.setConta(newReceita.getConta());
                    receita.setDataRecebimento(newReceita.getDataRecebimento());
                    receita.setDescricao(newReceita.getDescricao());
                    receita.setDataRecebimentoEsperado(newReceita.getDataRecebimentoEsperado());
                    receita.setValor(newReceita.getValor());
                    return receita;
                });
        if (updatedReceita.isPresent()){
            receitaRepository.save(updatedReceita.get());

        }else
            throw new NoSuchElementException();
        return updatedReceita.get();
    }


    public List<Receita>obterReceitas(Date startDate, Date endDate, TipoReceita tipoReceita){
        if (tipoReceita != null) {
            List<Receita> receita =  receitaRepository.findAllByTipoReceita(tipoReceita);
            return receita;
        }
        if (startDate != null && endDate != null){
            List<Receita>receitas = receitaRepository
                    .findAllByDataRecebimentoBetween(startDate,endDate);
            return receitas;
        }
        List<Receita>receitas= receitaRepository.findAll();
        return receitas;
    }


    public double calcularTotalReceita() {
        List<Receita> receitas =  receitaRepository.findAll();
        double total = 0;

        for (Receita receita: receitas){
            total += receita.getValor();
        }

        return total;
    }
}
