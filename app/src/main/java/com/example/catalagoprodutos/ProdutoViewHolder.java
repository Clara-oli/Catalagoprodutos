package com.example.catalagoprodutos;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class ProdutoViewHolder extends RecyclerView.ViewHolder {
    TextView textNome;
    TextView textPreco;
    public ProdutoViewHolder(@NonNull View itemView) {
        super(itemView);
        // O findViewById é executado apenas na criação do ViewHolder
        textNome = itemView.findViewById(R.id.textNomeProduto);
        textPreco = itemView.findViewById(R.id.textPrecoProduto);
    }
}
