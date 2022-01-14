package com.controledefinancapessoal.service;



import com.controledefinancapessoal.model.Receita;
import com.controledefinancapessoal.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ReceitaService {



    @Autowired
    private ReceitaRepository receitaRepository;


    public Receita salvarReceita(Receita receita) {
        Receita receitaSalva = receitaRepository.save(receita);
        return receitaSalva;
    }


    public List<Receita> obterReceitas() {
        List<Receita> receitas = receitaRepository.findAll();
        return receitas;
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
                    receita.setValor((double) newReceita.getValor());
                    return receita;
                });
        if (updatedReceita.isPresent()){
            receitaRepository.save(updatedReceita.get());

        }else
            throw new NoSuchElementException();
        return updatedReceita.get();
    }


    public List<Receita>obterReceitas(Date startDate, Date endDate){
        if(startDate != null && endDate != null){
            List<Receita>receitas = receitaRepository
                    .findAllByDateRecebimentoBetween(startDate,endDate);
            return receitas;
        }
        List<Receita>receitas = receitaRepository.findAll();
        return receitas;
    }
}
