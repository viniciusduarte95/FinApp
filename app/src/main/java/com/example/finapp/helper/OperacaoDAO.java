package com.example.finapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.finapp.Utils;
import com.example.finapp.model.Categoria;
import com.example.finapp.model.Operacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperacaoDAO {

    private SQLiteDatabase read;
    private SQLiteDatabase write;

    public OperacaoDAO(Context context){
        DBHelper db = new DBHelper(context);
        read = db.getReadableDatabase();
        write = db.getWritableDatabase();
    }

    public List<Operacao> getAllOperacoes() {
        List<Operacao> operacaoList = new ArrayList<>();
        try{
            String sql = "SELECT o.id, o.valor, o.data, o.categoria, c.id, c.descricao, c.debito "+
                    "FROM " + DBHelper.TABLE1_NAME + " o JOIN " + DBHelper.TABLE2_NAME +" c " +
                    "ON(o.categoria=c.id) ";
            Cursor cursor = read.rawQuery(sql,null);
            while(cursor.moveToNext()){
                Operacao op = new Operacao();
                Long id = cursor.getLong(0);
                Double valor = cursor.getDouble(1);
                op.setId(id);
                op.setValor(valor);
                op.setData(Utils.miliToDate(cursor.getLong(2)));
                Categoria categoria = new Categoria();
                Long idCat = cursor.getLong(4);
                String descricao = cursor.getString(5);
                int debito = cursor.getInt(6);
                categoria.setId(idCat);
                categoria.setDescricao(descricao);
                categoria.setDebito(debito);
                op.setCategoria(categoria);
                operacaoList.add(op);
            }
            return operacaoList;
        }catch (Exception e){
            return null;
        }
    }

    public List<Operacao> get15Operacoes() {
        List<Operacao> operacaoList = new ArrayList<>();
        try{
            String sql = "SELECT o.id, o.valor, o.data, o.categoria, c.id, c.descricao, c.debito "+
                    "FROM " + DBHelper.TABLE1_NAME + " o JOIN " + DBHelper.TABLE2_NAME +" c " +
                    "ON(o.categoria=c.id) ORDER BY o.data DESC LIMIT 15 ";
            Cursor cursor = read.rawQuery(sql,null);
            while(cursor.moveToNext()){
                Operacao op = new Operacao();
                Long id = cursor.getLong(0);
                Double valor = cursor.getDouble(1);
                op.setId(id);
                op.setValor(valor);
                op.setData(Utils.miliToDate(cursor.getLong(2)));
                Categoria categoria = new Categoria();
                Long idCat = cursor.getLong(4);
                String descricao = cursor.getString(5);
                int debito = cursor.getInt(6);
                categoria.setId(idCat);
                categoria.setDescricao(descricao);
                categoria.setDebito(debito);
                op.setCategoria(categoria);
                operacaoList.add(op);
            }
            return operacaoList;
        }catch (Exception e){
            return null;
        }
    }

    public List<Operacao> pesquisar(Date d1, Date d2, String categoria) {
        List<Operacao> operacaoList = new ArrayList<>();
        try{
            String sql = "SELECT o.id, o.valor, o.data, o.categoria, c.id, c.descricao, c.debito "+
                    "FROM " + DBHelper.TABLE1_NAME + " o JOIN " + DBHelper.TABLE2_NAME +" c " +
                    "ON(o.categoria=c.id) WHERE o.data >= ? AND o.data <= ? ";
            if(categoria.equals("Débito")){
                 sql += "AND c.debito = 1 ";
            }else if(categoria.equals("Crédito")){
                sql += " AND c.debito = 0 ";
            }
            sql += " ORDER BY o.data DESC ";
            long md1 = Utils.dateToMili(d1);
            long md2 = Utils.dateToMili(d2);
            Cursor cursor = read.rawQuery(sql,new String[]{String.valueOf(md1),String.valueOf(md2)});
            while(cursor.moveToNext()){
                Operacao op = new Operacao();
                Long id = cursor.getLong(0);
                Double valor = cursor.getDouble(1);
                op.setId(id);
                op.setValor(valor);
                op.setData(Utils.miliToDate(cursor.getLong(2)));
                Categoria cat = new Categoria();
                Long idCat = cursor.getLong(4);
                String descricao = cursor.getString(5);
                int debito = cursor.getInt(6);
                cat.setId(idCat);
                cat.setDescricao(descricao);
                cat.setDebito(debito);
                op.setCategoria(cat);
                operacaoList.add(op);
            }
            return operacaoList;
        }catch (Exception e){
            return null;
        }
    }

    public boolean insertOperacao(Operacao operacao){
        ContentValues values = new ContentValues();
        values.put("data",Utils.dateToMili(operacao.getData()));
        values.put("valor",operacao.getValor());
        values.put("categoria",operacao.getCategoria().getId());
        try{
            write.insert(DBHelper.TABLE1_NAME,null,values);
            Log.i("INFO", "Tarefa salva.");
        }catch (Exception e){
            Log.e("INFO","Erro ao salvar" + e.getMessage());
            return false;
        }
        return true;
    }
}
