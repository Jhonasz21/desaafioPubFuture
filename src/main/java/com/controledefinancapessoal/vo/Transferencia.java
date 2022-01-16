package com.controledefinancapessoal.vo;

import com.sun.istack.NotNull;

public class Transferencia {
    @NotNull
    private double valor;
    @NotNull
    private long idContaOrigem;
    @NotNull
    private long idContaDestino;

    public Transferencia(double valor, long idContaOrigem, long idContaDestino) {
        this.valor = valor;
        this.idContaOrigem = idContaOrigem;
        this.idContaDestino = idContaDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public long getIdContaOrigem() {
        return idContaOrigem;
    }

    public void setIdContaOrigem(long idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public long getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(long idContaDestino) {
        this.idContaDestino = idContaDestino;
    }
}
