package com.controledefinancapessoal.controller;

import com.controledefinancapessoal.model.Conta;
import com.controledefinancapessoal.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Api
@RestController
@RequestMapping(path = "/contas")
public class ContaController {

    private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    /* Cadastra conta */
    @ApiOperation("Cadastramento de contas")
    @PostMapping
    public ResponseEntity<Conta> save(@RequestBody Conta conta) {
        contaService.salvarConta(conta);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }


    /* Consulta conta retorna dodas em uma lista*/
    @ApiOperation("Consulta conta retornando todas em uma lista")
    @GetMapping
    public ResponseEntity<List<Conta>> getAll() {
        List<Conta> contas = new ArrayList<>();
        contas = contaService.obterContas();
        return new ResponseEntity<>(contas, HttpStatus.OK);
    }


    /*Consulta Conta pelo id*/
    @ApiOperation("Consulta Conta pelo id")
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

    /*Deleta conta por id*/
    @ApiOperation("Deleta conta por id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Conta>> deleteById(@PathVariable Long id) {
        try {
            contaService.deleteById(id);
            return new ResponseEntity<Optional<Conta>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Conta>>(HttpStatus.NOT_FOUND);

        }
    }
    @ApiOperation("Edita conta por id")
    @PutMapping(path = "/{id}")
   public ResponseEntity<Conta>update(@PathVariable Long id,@RequestBody Conta updatedConta){
    try {
    Conta conta = contaService.update(updatedConta,id);
    return new ResponseEntity<Conta>(conta,HttpStatus.OK);
    }catch (NoSuchElementException nsee){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
       }

       /*Listar saldo total*/
    @ApiOperation("lista saldo total de conta")
    @GetMapping(path = "/total")
    public ResponseEntity<Double> saldoTotal(){
        double totalSaldo = contaService.calcularTotalSaldo();
        return new ResponseEntity<>(totalSaldo, HttpStatus.OK);
    }

    }