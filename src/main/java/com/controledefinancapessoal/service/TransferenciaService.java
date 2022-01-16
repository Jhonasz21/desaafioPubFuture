package com.controledefinancapessoal.service;

import com.controledefinancapessoal.model.Conta;
import com.controledefinancapessoal.vo.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferenciaService {


    @Autowired
    ContaService contaService;

    public String fazerTransferencia(Transferencia transferencia){

        Optional<Conta> contaOrigem = contaService.findById(transferencia.getIdContaOrigem());
        Optional<Conta> contaDestino = contaService.findById(transferencia.getIdContaDestino());
        double novoSaldoContaOrigem;
        double novoSaldoContaDestino;

        if (transferencia.getValor() == 0) {
            return "O Valor de transferencia tem que ser maior do que zero";
        }

        if (!contaOrigem.isPresent()){
            return "Conta de origem nao existe";
        }

        if (!contaDestino.isPresent()){
            return "Conta de destino nao existe";
        }

        if (transferencia.getValor() > contaOrigem.get().getSaldo() ){
            return  "Saldo insuficiente";
        }

        novoSaldoContaOrigem = contaOrigem.get().getSaldo() - transferencia.getValor();
        novoSaldoContaDestino = contaDestino.get().getSaldo() + transferencia.getValor();

        contaOrigem.get().setSaldo(novoSaldoContaOrigem);
        contaDestino.get().setSaldo(novoSaldoContaDestino);

        try {
            contaService.salvarConta(contaOrigem.get());
            contaService.salvarConta(contaDestino.get());
        }catch (Exception e){
            return "Falha na transferencia, por favor tente novamente mais tarde";
        }

        return "Transferencia realizada com sucesso";
    }
}
