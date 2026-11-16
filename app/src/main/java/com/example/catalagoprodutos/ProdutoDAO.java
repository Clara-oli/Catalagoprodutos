package com.example.catalagoprodutos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private BancoHelper helper;

    public ProdutoDAO(Context context) {
        helper = new BancoHelper(context);
    }

    public long inserir(Produto produto) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", produto.getNome());
        valores.put("preco", produto.getPreco());
        return db.insert("produtos", null, valores);
    }

    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("produtos", null, null, null, null, null, "nome ASC");
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
                double preco = cursor.getDouble(cursor.getColumnIndexOrThrow("preco"));
                lista.add(new Produto(id, nome, preco));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public int atualizar(Produto produto) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", produto.getNome());
        valores.put("preco", produto.getPreco());
        return db.update("produtos", valores, "id = ?", new String[]{String.valueOf(produto.getId())});
    }

    public int excluir(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        return db.delete("produtos", "id = ?", new String[]{String.valueOf(id)});
    }
}
