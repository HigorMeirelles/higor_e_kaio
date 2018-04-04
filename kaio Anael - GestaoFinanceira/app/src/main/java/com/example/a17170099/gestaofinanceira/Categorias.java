package com.example.a17170099.gestaofinanceira;

/**
 * Created by 17170099 on 21/03/2018.
 */

public class Categorias {
    private String nome;
    private int id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
       return nome;
    }
}
