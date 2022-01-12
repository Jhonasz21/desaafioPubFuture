package com.controledefinancapessoal.model;

import com.controledefinancapessoal.enums.TipoReceita;

import javax.persistence.*;
import java.util.Date;


    @Entity
    public class Receita {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private double valor;
        private Date dataRecebimento;
        private Date dataRecebimentoEsperado;
        private String descricao;

        @Enumerated(EnumType.STRING)
        private TipoReceita tipoReceita;

        @ManyToOne
        @JoinColumn(name = "contaId")
        private Conta conta;

        public Receita() {

        }



        public Receita(Long id, double valor, Date dataRecebimento, Date dataRecebimentoEsperado, String descricao,
                        TipoReceita tipoReceita, Conta conta) {

            this.id = id;
            this.valor = valor;
            this.dataRecebimento = dataRecebimento;
            this.dataRecebimentoEsperado = dataRecebimentoEsperado;
            this.descricao = descricao;
            this.tipoReceita = tipoReceita;
            this.conta = conta;
        }



        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }

        public Date getDataRecebimento() {
            return dataRecebimento;
        }

        public void setDataRecebimento(Date dataRecebimento) {
            this.dataRecebimento = dataRecebimento;
        }

        public Date getDataRecebimentoEsperado() {
            return dataRecebimentoEsperado;
        }

        public void setDataRecebimentoEsperado(Date dataRecebimentoEsperado) {
            this.dataRecebimentoEsperado = dataRecebimentoEsperado;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

        public Conta getConta() {
            return conta;
        }

        public void setConta(Conta conta) {
            this.conta = conta;
        }

        public TipoReceita getTipoReceita() {
            return tipoReceita;
        }

        public void setTipoReceita(TipoReceita tipoReceita) {
            this.tipoReceita = tipoReceita;
        }


    }


