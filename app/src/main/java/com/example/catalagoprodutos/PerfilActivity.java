package com.example.catalagoprodutos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private TextView textBoasVindas;
    private EditText editNome;
    private Button botaoSalvar, botaoSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        textBoasVindas = findViewById(R.id.textBoasVindas);
        editNome = findViewById(R.id.editNome);
        botaoSalvar = findViewById(R.id.botaoSalvar);
        botaoSair = findViewById(R.id.botaoSair);

        prefs = getSharedPreferences("MeuAppPrefs", MODE_PRIVATE);
        carregarPreferencias();

        botaoSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            if (!nome.isEmpty()) {
                prefs.edit().putString("nome_usuario", nome).apply();
                Toast.makeText(this, "Nome salvo com sucesso!", Toast.LENGTH_SHORT).show();
                carregarPreferencias();
            }
        });

        botaoSair.setOnClickListener(v -> {
            prefs.edit().clear().apply();
            textBoasVindas.setText("Bem-vindo, Visitante!");
            editNome.setText("");
            Toast.makeText(this, "Sessão encerrada.", Toast.LENGTH_SHORT).show();
        });
    }

    private void carregarPreferencias() {
        String nomeSalvo = prefs.getString("nome_usuario", "");
        if (!nomeSalvo.isEmpty()) {
            textBoasVindas.setText("Bem-vindo de volta, " + nomeSalvo + "!");
        } else {
            textBoasVindas.setText("Bem-vindo, Visitante!");
        }
    }
}