package com.example.catalagoprodutos;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private TextView txtStatusFavorito;
    private Button btnVerProduto;
    // CORREÇÃO 1: Declarando as variáveis globais da classe corretamente
    private RecyclerView recycler;
    private ProdutoDAO dao;
    private final ActivityResultLauncher<Intent> launcherDetalhe = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            resultado -> {
                if (resultado.getResultCode() == RESULT_OK && resultado.getData() != null) {
                    boolean favoritado = resultado.getData().getBooleanExtra("EXTRA_FAVORITADO", false);
                    if (favoritado) {
                        txtStatusFavorito.setText("Status: Produto marcado como FAVORITO!");
                        txtStatusFavorito.setTextColor(0xFF2E7D32);
                        Toast.makeText(MainActivity.this, "Retorno recebido com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    txtStatusFavorito.setText("Status: Retorno cancelado.");
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. Vincula os componentes do XML
        txtStatusFavorito = findViewById(R.id.txtStatusFavorito);
        btnVerProduto = findViewById(R.id.btnVerProduto);
        recycler = findViewById(R.id.recyclerProdutos);
        // 2. Inicializa o Banco de Dados (DAO)
        dao = new ProdutoDAO(this);
        // 3. Configura o layout manager da lista
        recycler.setLayoutManager(new LinearLayoutManager(this));
        // 4. CORREÇÃO 2: Inserindo com exatamente 3 parâmetros (0, "Nome", Preco)
        if (dao.listarTodos().isEmpty()) {
            dao.inserir(new Produto(0, "Monitor Gamer 29''", 1299.90));
            dao.inserir(new Produto(0, "Teclado Mecânico RGB", 250.00));
            dao.inserir(new Produto(0, "Mouse Vertical Ergonômico", 120.50));
        }
        // 5. Configura e define o adaptador
        List<Produto> listaDeProdutos = dao.listarTodos();
        ProdutoAdapter adapter = new ProdutoAdapter(this, listaDeProdutos);
        recycler.setAdapter(adapter);
        Toast.makeText(this, "Total de produtos: " + listaDeProdutos.size(), Toast.LENGTH_SHORT).show();
        // 6. Clique do botão
        btnVerProduto.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetalheActivity.class);
            intent.putExtra("EXTRA_ID", 101);
            intent.putExtra("EXTRA_NOME", "Monitor UltraWide 29'' IPS");
            intent.putExtra("EXTRA_PRECO", 1250.50);
            launcherDetalhe.launch(intent);
        });
    }
}