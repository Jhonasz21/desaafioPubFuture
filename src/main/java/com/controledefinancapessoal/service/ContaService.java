package com.controledefinancapessoal.service;


import com.controledefinancapessoal.model.Conta;
import com.controledefinancapessoal.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;



    public Conta salvarConta(Conta conta) {
        Conta contaSalva = contaRepository.save(conta);
        return contaSalva;

    }


    public List<Conta> obterContas() {
        List<Conta>contas = contaRepository.findAll();
        return contas;
    }

    public Optional<Conta> findById(Long id) {
        Optional<Conta>obterConta = contaRepository.findById(id);
        return obterConta;
    }


    public void deleteById(Long id) {
        contaRepository.deleteById(id);

    }






}

