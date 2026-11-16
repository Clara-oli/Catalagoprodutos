package com.example.catalagoprodutos;

import android.content.Context;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import java.util.Locale;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private final Context context;

    private final List<Produto> listaProdutos;

    public ProdutoAdapter(Context context, List<Produto> listaProdutos) {

        this.context = context;

        this.listaProdutos = listaProdutos;

    }

    @NonNull

    @Override

    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false);

        return new ProdutoViewHolder(view);

    }

    @Override

    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {

        Produto p = listaProdutos.get(position);

        if (holder.txtNome != null) {

            holder.txtNome.setText(p.getNome());

        }

        if (holder.txtPreco != null) {

            holder.txtPreco.setText(String.format(Locale.getDefault(), "R$ %.2f", p.getPreco()));

        }

        if (holder.imgProduto != null) {

            Glide.with(context)

                    .load(p.getImagemUrl())

                    .placeholder(android.R.drawable.ic_menu_gallery)

                    .error(android.R.drawable.stat_notify_error)

                    .override(300, 300)

                    .centerCrop()

                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                    .into(holder.imgProduto);

        }

    }

    @Override

    public int getItemCount() {

        return listaProdutos != null ? listaProdutos.size() : 0;

    }

    // Mantendo apenas UMA classe ViewHolder e buscando pelos IDs exatos do primeiro erro

    public static class ProdutoViewHolder extends RecyclerView.ViewHolder {

        TextView txtNome, txtPreco;

        ImageView imgProduto;

        public ProdutoViewHolder(@NonNull View itemView) {

            super(itemView);

            txtNome = itemView.findViewById(R.id.textNomeProduto);

            txtPreco = itemView.findViewById(R.id.textPrecoProduto);

            imgProduto = itemView.findViewById(R.id.imgProduto);

        }

    }

}
