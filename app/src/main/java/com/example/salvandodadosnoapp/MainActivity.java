package com.example.salvandodadosnoapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnSalvar;
    EditText etNome;
    EditText etSobrenome;

    public static String PREFS = "prefs";
    public static String CHAVE_NOME = "nome";
    public static String CHAVE_SOBRENOME = "sobrenome";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSalvar = findViewById(R.id.btnSalvar);
        etNome = findViewById(R.id.etNome);
        etSobrenome = findViewById(R.id.etSobrenome);

        lerPreferencias();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPreferencias();
            }
        });
    }

    private void lerPreferencias() {
        SharedPreferences preferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        String nome = preferences.getString(CHAVE_NOME, "NOME");
        String sobrenome = preferences.getString(CHAVE_SOBRENOME, "SOBRENOME");

        etNome.setText(nome);
        etSobrenome.setText(sobrenome);
    }

    private void salvarPreferencias() {
        SharedPreferences preferences = getSharedPreferences(PREFS, MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CHAVE_NOME, etNome.getText().toString());
        editor.putString(CHAVE_SOBRENOME, etSobrenome.getText().toString());
        editor.commit();

        Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
    }
}