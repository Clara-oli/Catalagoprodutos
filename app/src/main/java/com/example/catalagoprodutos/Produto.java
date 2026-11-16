package com.example.catalagoprodutos;
import com.google.gson.annotations.SerializedName;


public class Produto {
    private long id;
    private String nome;
    private double preco;

    // Construtor usado ao ler do banco (com id)
    public Produto(long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Construtor usado ao criar um novo produto (sem id ainda)
    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @SerializedName("imagemUrl")

    private String imagemUrl;

    public String getImagemUrl() {
        return imagemUrl;
    }
}

