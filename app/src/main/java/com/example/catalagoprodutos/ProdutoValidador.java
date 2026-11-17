package com.example.catalagoprodutos;

public class ProdutoValidador {
    public static boolean isValido(Produto produto) {
        if (produto == null) {
            return false;
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            return false;
        }
        double preco = produto.getPreco();
        return Double.isFinite(preco) && preco > 0;
    }
}
