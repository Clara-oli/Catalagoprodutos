package com.example.catalagoprodutos;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.example.catalagoprodutos.ProdutoValidador;
import com.example.catalagoprodutos.Produto;
import org.junit.Test;

public class ProdutoValidadorTest {
    //
        @Test
        public void produtoCriadoComNomeCorreto_deveRetornarMesmoNome() {
            Produto produto = new Produto("Mouse", 45.0);
            assertEquals("Mouse", produto.getNome());
        }
        @Test
        public void produtoComNomeEPrecoValidos_deveSerValido() {
            Produto produto = new Produto("Mouse", 45.0);
            assertTrue(ProdutoValidador.isValido(produto));
        }
        @Test
        public void produtoComNomeVazio_naoDeveSerValido() {
            Produto produto = new Produto("", 45.0);
            assertFalse(ProdutoValidador.isValido(produto));
        }
        @Test
        public void produtoComNomeApenasEspacos_naoDeveSerValido() {
            Produto produto = new Produto(" ", 45.0);
            assertFalse(ProdutoValidador.isValido(produto));
        }
        @Test
        public void produtoComPrecoZero_naoDeveSerValido() {
            Produto produto = new Produto("Mouse", 0.0);
            assertFalse(ProdutoValidador.isValido(produto));
        }
        @Test
        public void produtoComPrecoNegativo_naoDeveSerValido() {
            Produto produto = new Produto("Mouse", -10.0);
            assertFalse(ProdutoValidador.isValido(produto));
        }
}
