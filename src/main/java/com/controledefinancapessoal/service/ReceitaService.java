package com.controledefinancapessoal.service;

import com.controledefinancapessoal.model.Receita;
import com.controledefinancapessoal.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReceitaService {


    @Autowired
    private ReceitaRepository receitaRepository;

    public Receita salvarReceita(Receita receita) {
        Receita receitaSalva = receitaRepository.save(receita);
        return receitaSalva;
    }


    public List<Receita> obterReceitas() {
        List<Receita>receitas = receitaRepository.findAll();
        return receitas;
    }

    public void deleteById(Long id) {
        receitaRepository.deleteById(id);
    }
}
