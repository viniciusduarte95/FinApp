package com.example.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sair(View view) {
        finish();
    }

    public void cadastro(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
        finish();
    }

    public void extrato(View view) {
        Intent intent = new Intent(this, ExtratoActivity.class);
        startActivity(intent);
        finish();
    }

    public void pesquisar(View view) {
        Intent intent = new Intent(this, PesquisarActivity.class);
        startActivity(intent);
        finish();
    }

    public void lista(View view) {
        Intent intent = new Intent(this, ListaClassificadaActivity.class);
        startActivity(intent);
        finish();
    }

}