package com.example.finapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;
import com.example.finapp.Utils;
import com.example.finapp.model.ItemLista;

import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.MyViewHolder> {
    private List<ItemLista> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoria, valor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoria = itemView.findViewById(R.id.textViewCategoriaLista);
            valor = itemView.findViewById(R.id.textViewValorLista);
        }
    }

    public ListaAdapter(List<ItemLista> list){this.list = list; System.out.println(list.size()); }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listaItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cell, parent, false);
        return new MyViewHolder(listaItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemLista itemLista = list.get(position);
        holder.categoria.setText(itemLista.getCategoria().getDescricao());
        if(itemLista.getCategoria().isDebito()==1){
            holder.valor.setText("- " + Utils.formatValor(itemLista.getValor()));
            holder.valor.setTextColor(Color.parseColor("#ff0000"));
        }else{
            holder.valor.setText("+ " + Utils.formatValor(itemLista.getValor()));
            holder.valor.setTextColor(Color.parseColor("#00ff00"));
        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
