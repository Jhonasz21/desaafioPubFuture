package com.controledefinancapessoal.controller;

import com.controledefinancapessoal.model.Despesa;
import com.controledefinancapessoal.service.DespesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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


    /*listar total de despesa*/
    @GetMapping
    public ResponseEntity<List<Despesa>> getAll() {
        List<Despesa> despesas = new ArrayList<>();
        despesas = despesaService.obterDespesa();
        return new ResponseEntity<>(despesas, HttpStatus.OK);
    }
}
