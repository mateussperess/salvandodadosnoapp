package com.example.salvandodadosnoapp;

import android.content.Context;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity2 extends AppCompatActivity {

    String NOME_DO_ARQUIVO = "arquivo";
    String textoParaSalvar = "";
    EditText txtSalvar;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtSalvar = findViewById(R.id.txtSalvar);
        btnSalvar = findViewById(R.id.btnSalvar);

        String textoDoArquivo = lerTxt();
        if (textoDoArquivo == null) {
            txtSalvar.setText("Arquivo não gerado ainda");
        } else {
            txtSalvar.setText(textoDoArquivo);
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarTxt();
            }
        });
    }

    private void salvarTxt() {
        FileOutputStream fos;
        try {
            fos = openFileOutput(NOME_DO_ARQUIVO, Context.MODE_PRIVATE);
            textoParaSalvar = txtSalvar.getText().toString();
            fos.write(textoParaSalvar.getBytes());
            fos.close();
            Toast.makeText(this, "Texto salvo com sucesso!",  Toast.LENGTH_LONG);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String lerTxt() {
        String eol = System.getProperty("line.separator");
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(openFileInput(NOME_DO_ARQUIVO)));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = input.readLine()) != null) {
                buffer.append(line + eol);
            }
            input.close();
            return buffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}