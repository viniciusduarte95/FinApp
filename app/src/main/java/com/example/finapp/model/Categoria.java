package com.example.finapp.model;

import java.io.Serializable;

public class Categoria implements Serializable {
    long id;
    String descricao;
    int debito;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int isDebito() {
        return debito;
    }

    public void setDebito(int debito) {
        this.debito = debito;
    }

    @Override
    public String toString() {
        return this.descricao;
    }
}
