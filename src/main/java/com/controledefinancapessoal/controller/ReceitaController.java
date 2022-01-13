package com.controledefinancapessoal.controller;


import com.controledefinancapessoal.model.Receita;
import com.controledefinancapessoal.service.ReceitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/receita")
public class ReceitaController {

    private ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    /*cadastrar receita*/
    @PostMapping
    public ResponseEntity<Receita> save(@RequestBody Receita receita) {
        receitaService.salvarReceita(receita);
        return new ResponseEntity<>(receita, HttpStatus.OK);
    }
    /*editar receita*/



    /*remover receita*/
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Receita>> deleteById(@PathVariable Long id) {
        try {
            receitaService.deleteById(id);
            return new ResponseEntity<Optional<Receita>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Receita>>(HttpStatus.NOT_FOUND);

        }
    }

    /*listar receita por periodo datainicial-datafinal*/

    /*filtro por tipo de receita*/



    /*listar total de receita*/
    @GetMapping
    public ResponseEntity<List<Receita>> getAll() {
        List<Receita> receitas = new ArrayList<>();
        receitas = receitaService.obterReceitas();
        return new ResponseEntity<>(receitas, HttpStatus.OK);
    }



}
