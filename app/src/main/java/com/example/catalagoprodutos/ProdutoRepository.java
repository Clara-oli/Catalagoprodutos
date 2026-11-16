package com.example.catalagoprodutos;

import java.util.List;

import retrofit2.Call;

import retrofit2.Callback;

import retrofit2.Response;

public class ProdutoRepository {

    public interface OnResultadoApiListener<T> {

        void onSuccess(T resultado);

        void onError(String mensagem, boolean sessaoExpirada);

    }

    public static void listarProdutos(OnResultadoApiListener<List<Produto>> listener) {

        ApiService api = RetrofitClient.getApiService();

        api.listarProdutos().enqueue(new Callback<List<Produto>>() {

            @Override

            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    listener.onSuccess(response.body());

                } else {

                    listener.onError("Erro HTTP: " + response.code(), response.code() == 401);

                }

            }

            @Override

            public void onFailure(Call<List<Produto>> call, Throwable t) {

                listener.onError(MapeadorDeErros.interpretarExcecaoRede(t), false);

            }

        });

    }

    public static void criarProduto(String token, Produto p, OnResultadoApiListener<Produto> listener) {

        ApiService api = RetrofitClient.getApiService();

        api.criarProdutoAutenticado("Bearer " + token, p).enqueue(new Callback<Produto>() {

            @Override

            public void onResponse(Call<Produto> call, Response<Produto> response) {

                if (response.isSuccessful() && response.body() != null) {

                    listener.onSuccess(response.body());

                } else {

                    listener.onError("Erro ao cadastrar. Código: " + response.code(), response.code() == 401);

                }

            }

            @Override

            public void onFailure(Call<Produto> call, Throwable t) {

                listener.onError(MapeadorDeErros.interpretarExcecaoRede(t), false);

            }

        });

    }

}
