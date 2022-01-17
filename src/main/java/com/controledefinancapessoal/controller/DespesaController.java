package com.controledefinancapessoal.controller;

import com.controledefinancapessoal.enums.TipoDespesa;
import com.controledefinancapessoal.model.Despesa;
import com.controledefinancapessoal.service.DespesaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Api
@RestController
@RequestMapping(path = "/despesas")
public class DespesaController {

    public DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    /*cadastrar despesa*/
    @ApiOperation("Cadastra despesa")
    @PostMapping
    public ResponseEntity<Despesa> save(@RequestBody Despesa despesa) {
        despesaService.salvarDespesa(despesa);
        return new ResponseEntity<>(despesa, HttpStatus.OK);
    }


    /*editar despesa*/
    @ApiOperation("Edita despesa por id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<Despesa> update(@PathVariable Long id, @RequestBody Despesa updatedDespesa) {
        try {
            Despesa despesa = despesaService.update(updatedDespesa, id);
            return new ResponseEntity<Despesa>(despesa, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*remover despesa*/
    @ApiOperation("Remove despesas por id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Despesa>> deleteById(@PathVariable Long id) {
        try {
            despesaService.deleteById(id);
            return new ResponseEntity<Optional<Despesa>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Despesa>>(HttpStatus.NOT_FOUND);

        }
    }

    /*listar despesa
     * por periodo data inicial e data final
     *  por tipó de despesa
     */
    @ApiOperation("filtro por dataInicial e dataFinal / filtro por tipo de despesas")
    @GetMapping
    public ResponseEntity<List<Despesa>> getAll(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate,
            @RequestParam(required = false) TipoDespesa tipoDespesa
    ) {
        List<Despesa> despesas = new ArrayList<>();
        despesas = despesaService.obterDespesa(startDate, endDate,tipoDespesa);
        return new ResponseEntity<>(despesas, HttpStatus.OK);
    }


    /*listar total de despesa*/
    @ApiOperation("Lista total de Despesas")
    @GetMapping(path = "/total")
    public ResponseEntity<Double> listarTotalDespesa(){
        double totalDespesas = despesaService.calcularTotalDespesas();
        return new ResponseEntity<>(totalDespesas, HttpStatus.OK);
    }
}