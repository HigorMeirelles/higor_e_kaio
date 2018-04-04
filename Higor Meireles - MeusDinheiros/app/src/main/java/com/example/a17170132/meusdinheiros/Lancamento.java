package com.example.a17170132.meusdinheiros;


public class Lancamento {

    private Integer id;
    private String nome;
    private String categoria;
    private String descricao;
    private String detalhes;
    private String dataCadastro;
    private Float valor;

    public Lancamento(){

    }
    public Lancamento(int id, String nome, String categoria, String descricao, String detalhes, String dataCadastro, Float valor) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.detalhes = detalhes;
        this.dataCadastro = dataCadastro;
        this.valor = valor;
    }
    public Lancamento(String nome, String categoria, String descricao, String detalhes, String dataCadastro, Float valor) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.detalhes = detalhes;
        this.dataCadastro = dataCadastro;
        this.valor = valor;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }


}
