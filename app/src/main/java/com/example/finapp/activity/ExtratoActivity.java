package com.example.finapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;
import com.example.finapp.adapter.ExtratoAdapter;
import com.example.finapp.helper.OperacaoDAO;
import com.example.finapp.model.Operacao;

import java.util.ArrayList;
import java.util.List;

public class ExtratoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExtratoAdapter extratoAdapter;
    private List<Operacao> operacaoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        recyclerView = findViewById(R.id.recyclerViewExtrato);

        OperacaoDAO operacaoDAO = new OperacaoDAO(getApplicationContext());
        operacaoList = operacaoDAO.get15Operacoes();

        extratoAdapter = new ExtratoAdapter(operacaoList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(extratoAdapter);

    }

    public void voltar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}