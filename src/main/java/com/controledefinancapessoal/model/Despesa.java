package com.controledefinancapessoal.model;


import com.controledefinancapessoal.enums.TipoDespesa;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double valor;
    private Date dataPagamento;
    private Date dataPagamentoEsperado;

    @Enumerated(EnumType.STRING)
    private TipoDespesa tipoDespesa;

    @ManyToOne
    @JoinColumn(name = "ContaId")
    private Conta conta;

    public Despesa() {

    }

    public Despesa(Integer id, double valor, Date dataPagamento, Date dataPagamentoEsperado, TipoDespesa tipoDespesa,
                    Conta conta) {
        this.id = id;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.dataPagamentoEsperado = dataPagamentoEsperado;
        this.tipoDespesa = tipoDespesa;
        this.conta = conta;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataPagamentoEsperado() {
        return dataPagamentoEsperado;
    }

    public void setDataPagamentoEsperado(Date dataPagamentoEsperado) {
        this.dataPagamentoEsperado = dataPagamentoEsperado;
    }

    public TipoDespesa getTipoDespesa() {
        return tipoDespesa;
    }

    public void setTipoDespesa(TipoDespesa tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }



}