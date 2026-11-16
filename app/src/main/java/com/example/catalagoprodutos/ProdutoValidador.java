package com.example.catalagoprodutos;

public class ProdutoValidador {
    public static boolean isValido(Produto produto) {
        if (produto == null) {
            return false;
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            return false;
        }
        return produto.getPreco() > 0;
    }
}
