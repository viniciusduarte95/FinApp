package com.example.finapp.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;
import com.example.finapp.adapter.PesquisaAdapter;

public class DadosPesquisaActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private PesquisaAdapter pesquisaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pesquisa);

        pesquisaAdapter = new PesquisaAdapter(PesquisarActivity.operacoes);

        recyclerView = findViewById(R.id.recyclerViewPesquisa);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(pesquisaAdapter);
    }
}