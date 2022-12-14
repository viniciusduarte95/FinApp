package com.example.finapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finapp.model.Categoria;
import com.example.finapp.model.ItemLista;

import java.util.ArrayList;
import java.util.List;

public class ListaDAO {

    private SQLiteDatabase read;

    public ListaDAO(Context context){
        DBHelper db = new DBHelper(context);
        read = db.getReadableDatabase();
    }

    public List<ItemLista> getAllItens() {
        List<ItemLista> itemList = new ArrayList<>();
        try{
            String sql = "SELECT o.id, SUM(o.valor), o.categoria, c.id, c.descricao, c.debito "+
                    "FROM " + DBHelper.TABLE1_NAME + " o JOIN " + DBHelper.TABLE2_NAME +" c " +
                    "ON(o.categoria=c.id) " +
                    "GROUP BY(o.categoria)" +
                    "ORDER BY o.valor";
            Cursor cursor = read.rawQuery(sql,null);
            while(cursor.moveToNext()){
                ItemLista op = new ItemLista();
                Long id = cursor.getLong(0);
                Double valor = cursor.getDouble(1);
                op.setId(id);
                op.setValor(valor);
                Categoria categoria = new Categoria();
                Long idCat = cursor.getLong(4);
                String descricao = cursor.getString(5);
                int debito = cursor.getInt(6);
                op.setCategoria(categoria);
                categoria.setId(idCat);
                categoria.setDescricao(descricao);
                categoria.setDebito(debito);
                itemList.add(op);
            }
            return itemList;
        }catch (Exception e){
            return null;
        }
    }

    public List<ItemLista> get15Itens() {
        List<ItemLista> itemList = new ArrayList<>();
        try {
            String sql = "SELECT o.id, SUM(o.valor) as valor, o.data, o.categoria, c.id, c.descricao, c.debito " +
                    "FROM " + DBHelper.TABLE1_NAME + " o JOIN " + DBHelper.TABLE2_NAME + " c " +
                    "ON(o.categoria=c.id) " +
                    "GROUP BY(o.categoria)" +
                    "ORDER BY valor DESC LIMIT 15 ";
            Cursor cursor = read.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                System.out.println("1");
                ItemLista lt = new ItemLista();
                Long id = cursor.getLong(0);
                Double valor = cursor.getDouble(1);
                lt.setId(id);
                lt.setValor(valor);
                Categoria categoria = new Categoria();
                Long idCat = cursor.getLong(4);
                String descricao = cursor.getString(5);
                int debito = cursor.getInt(6);
                lt.setCategoria(categoria);
                categoria.setId(idCat);
                categoria.setDescricao(descricao);
                categoria.setDebito(debito);
                itemList.add(lt);
            }
            return itemList;
        } catch (Exception e) {
            return null;
        }
    }
}
