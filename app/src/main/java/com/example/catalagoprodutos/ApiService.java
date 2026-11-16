package com.example.catalagoprodutos;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface ApiService {
    @GET("produtos")
    Call<List<Produto>> listarProdutos();
    @POST("produtos")
    Call<Produto> criarProdutoAutenticado(@Header("Authorization") String bearer, @Body Produto p);
    @PUT("produtos/{id}")
    Call<Produto> atualizarProduto(@Header("Authorization") String bearer,
                                   @Path("id") int id, @Body Produto p);
}
