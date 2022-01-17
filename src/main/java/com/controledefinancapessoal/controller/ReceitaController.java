package com.controledefinancapessoal.controller;


import com.controledefinancapessoal.enums.TipoReceita;
import com.controledefinancapessoal.model.Receita;
import com.controledefinancapessoal.service.ReceitaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api
@RestController
@RequestMapping(path = "/receitas")
public class ReceitaController<list> {

    private ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    /*cadastrar receita*/
    @ApiOperation("Cadastra Receitas")
    @PostMapping
    public ResponseEntity<Receita> save(@RequestBody Receita receita) {
        receitaService.salvarReceita(receita);
        return new ResponseEntity<>(receita, HttpStatus.OK);
    }

    /*remover receita*/
    @ApiOperation("Remove Receitas")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Receita>> deleteById(@PathVariable Long id) {
        try {
            receitaService.deleteById(id);
            return new ResponseEntity<Optional<Receita>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Receita>>(HttpStatus.NOT_FOUND);

        }
    }

    /*editar receita*/
    @ApiOperation("Edita Receitas")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Receita> update(@PathVariable Long id, @RequestBody Receita updatedReceita) {
        try {
            Receita receita = receitaService.update(updatedReceita, id);
            return new ResponseEntity<Receita>(receita, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*listar receita por periodo datainicial-datafinal*/
    /*filtro por tipo de receita*/
    @ApiOperation("filtro por dataInicial e dataFinal / filtro por tipo de receita")
    @GetMapping
    public ResponseEntity<List<Receita>> getAll(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate,
            @RequestParam(required = false) TipoReceita tipoReceita
    ) {
        List<Receita> receitas = new ArrayList<>();
        receitas = receitaService.obterReceitas(startDate, endDate,tipoReceita);
        return new ResponseEntity<>(receitas, HttpStatus.OK);

    }

    /*listar total de receita*/
    @ApiOperation("Lista total de Receita")
    @GetMapping(path = "/total")
    public ResponseEntity<Double> listarTotalReceita(){
        double totalReceitas = receitaService.calcularTotalReceita();
        return new ResponseEntity<>(totalReceitas, HttpStatus.OK);
    }

}