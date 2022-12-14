package com.example.finapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finapp.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private SQLiteDatabase read;

    public CategoriaDAO(Context context){
        DBHelper db = new DBHelper(context);
        read = db.getReadableDatabase();
    }

    public List<Categoria> getAllCategorias(){
        List<Categoria> categoriaList = new ArrayList<>();
        Cursor cursor = read.query(DBHelper.TABLE2_NAME, new String[]{"id","descricao","debito"},
                null,null,null,null,"debito, descricao");
        while(cursor.moveToNext()){
            Categoria categoria = new Categoria();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            int debito = cursor.getInt(cursor.getColumnIndex("debito"));
            categoria.setId(id);
            categoria.setDescricao(descricao);
            categoria.setDebito(debito);
            categoriaList.add(categoria);
        }
        return categoriaList;
    }
}
