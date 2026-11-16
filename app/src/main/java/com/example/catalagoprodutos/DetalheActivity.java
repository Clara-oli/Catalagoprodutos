package com.example.catalagoprodutos;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class DetalheActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        TextView txtCodigo = findViewById(R.id.txtCodigoProduto);
        TextView txtNome = findViewById(R.id.txtNomeProduto);
        TextView txtPreco = findViewById(R.id.txtPrecoProduto);
        Button btnFavoritar = findViewById(R.id.btnFavoritar);
        // 1. Recupera os dados que vieram na Intent da MainActivity
        int id = getIntent().getIntExtra("EXTRA_ID", 0);
        String nome = getIntent().getStringExtra("EXTRA_NOME");
        double preco = getIntent().getDoubleExtra("EXTRA_PRECO", 0.0);
        // 2. Mostra nos TextViews
        txtCodigo.setText("Código: " + id);
        txtNome.setText("Nome: " + nome);
        txtPreco.setText(String.format("Preço: R$ %.2f", preco));
        // 3. Devolve a resposta para a MainActivity
        btnFavoritar.setOnClickListener(v -> {
            Intent resultado = new Intent();
            resultado.putExtra("EXTRA_FAVORITADO", true);
            setResult(RESULT_OK, resultado);
            finish();
        });
    }
}