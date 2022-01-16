package com.controledefinancapessoal;

import com.controledefinancapessoal.enums.TipoConta;
import com.controledefinancapessoal.model.Conta;
import com.controledefinancapessoal.repository.ContaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DesafioPubFutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPubFutureApplication.class, args);
	}
	@Bean
	public static Object iniciarContas(ContaRepository contaRepository){
		Conta conta1 = new Conta();
		conta1.setSaldo(50);
		conta1.setTipoConta(TipoConta.CONTA_CORRENTE);
		conta1.setInstituicaoFinanceira("Itau");

		Conta conta2 = new Conta();
		conta2.setSaldo(50);
		conta2.setTipoConta(TipoConta.CONTA_CORRENTE);
		conta2.setInstituicaoFinanceira("Nubank");

		List contas = new ArrayList();
		contas.add(conta1);
		contas.add(conta2);

		contaRepository.saveAll(contas);

		return null;

	}

}
