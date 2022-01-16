package com.controledefinancapessoal.controller;

import com.controledefinancapessoal.model.Despesa;
import com.controledefinancapessoal.service.DespesaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/despesa")
public class DespesaController {

    public DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    /*cadastrar despesa*/
    @PostMapping
    public ResponseEntity<Despesa> save(@RequestBody Despesa despesa) {
        despesaService.salvarDespesa(despesa);
        return new ResponseEntity<>(despesa, HttpStatus.OK);
    }


    /*editar despesa*/
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
    @GetMapping
    public ResponseEntity<List<Despesa>> getAll(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate

    ) {
        List<Despesa> despesas = new ArrayList<>();
        despesas = despesaService.obterDespesa(startDate, endDate);
        return new ResponseEntity<>(despesas, HttpStatus.OK);
    }


    /*listar total de despesa*/

}