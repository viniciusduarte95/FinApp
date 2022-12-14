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
import com.example.finapp.adapter.ListaAdapter;
import com.example.finapp.helper.ListaDAO;
import com.example.finapp.model.ItemLista;

import java.util.ArrayList;
import java.util.List;

public class ListaClassificadaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaAdapter listaAdapter;
    private List<ItemLista> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_classificada);

        recyclerView = findViewById(R.id.recyclerViewLista);

        ListaDAO listaDAO = new ListaDAO(getApplicationContext());
        itemList = listaDAO.get15Itens();

        listaAdapter = new ListaAdapter(itemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(listaAdapter);
    }

    public void voltar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}