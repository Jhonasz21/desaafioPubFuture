package com.controledefinancapessoal.service;


import com.controledefinancapessoal.model.Despesa;
import com.controledefinancapessoal.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;



    public Despesa salvarDespesa(Despesa despesa) {
        Despesa despesaSalva = despesaRepository.save(despesa);
        return despesaSalva;
    }

    public List<Despesa> obterDespesa() {
        List<Despesa>despesas = despesaRepository.findAll();
        return despesas;
    }

    public void deleteById(Long id) {
        despesaRepository.deleteById(id);
    }
}