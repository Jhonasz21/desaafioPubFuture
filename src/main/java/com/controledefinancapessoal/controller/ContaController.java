package com.controledefinancapessoal.controller;

import com.controledefinancapessoal.model.Conta;
import com.controledefinancapessoal.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/contas")
public class ContaController {

    private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    /* Cadastra conta */
    @PostMapping
    public ResponseEntity<Conta> save(@RequestBody Conta conta) {
        contaService.salvarConta(conta);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }


    /* Consulta conta retorna dodas em uma lista*/
    @GetMapping
    public ResponseEntity<List<Conta>> getAll() {
        List<Conta> contas = new ArrayList<>();
        contas = contaService.obterContas();
        return new ResponseEntity<>(contas, HttpStatus.OK);
    }

    /*Consulta Conta pelo id*/
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Conta>> getById(@PathVariable Long id) {
        Optional<Conta> conta;
        try {
            conta = contaService.findById(id);
            return new ResponseEntity<Optional<Conta>>(conta, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Conta>>(HttpStatus.NOT_FOUND);

        }

    }

    /*Deleta conta pelo id*/
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Conta>> deleteById(@PathVariable Long id) {
        try {
            contaService.deleteById(id);
            return new ResponseEntity<Optional<Conta>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Conta>>(HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody Conta  newConta) {
        return contaService.findById(id)
                .map(conta -> {
                    conta.setSaldo(newConta.getSaldo());
                    conta.setTipoConta(newConta.getTipoConta());
                    conta.setInstituicaoFinanceira(newConta.getInstituicaoFinanceira());
                    Conta contaUpdated = contaService.save(conta);
                    return ResponseEntity.ok().body(contaUpdated);
                }).orElse(ResponseEntity.notFound().build());


    }
}