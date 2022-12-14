package com.example.finapp.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static int DB_VERSION = 6;
    public static String DB_NAME = "FINAPP.DB";
    public static String TABLE1_NAME = "operacoes";
    public static String TABLE2_NAME = "categorias";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE2_NAME +
                " ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " descricao varchar(100) NOT NULL, " +
                " debito boolean NOT NULL); ";
        String CREATE_SQL_2 = "CREATE TABLE IF NOT EXISTS " + TABLE1_NAME +
                " ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " valor double NOT NULL, " +
                " data int NOT NULL, " +
                " categoria integer, " +
                " foreign key (categoria) references " + TABLE2_NAME + "(id)); ";
        String CREATE_SQL_3 =
                " INSERT INTO "+TABLE2_NAME+"(descricao,debito) VALUES ('Educação',1),"+
                "('Lazer',1),('Moradia',1),('Saúde',1),('Outros',1),('Salário',0),"+
                "('Transferências',0);";
        try{
            sqLiteDatabase.execSQL(CREATE_SQL);
            sqLiteDatabase.execSQL(CREATE_SQL_2);
            sqLiteDatabase.execSQL(CREATE_SQL_3);
            Log.i("INFO DB", "Tabela Criada");
        }catch (Exception e){
            Log.i("INFO DB","Erro ao criar tabela." + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE1_NAME + " ; ";
        String sql2 = "DROP TABLE IF EXISTS " + TABLE2_NAME + " ; ";
        try{
            sqLiteDatabase.execSQL(sql);
            sqLiteDatabase.execSQL(sql2);
            onCreate(sqLiteDatabase);
            Log.i("INFO DB","Tabela Atualizada.");
        }catch (Exception e){
            Log.i("INFO DB","Erro ao atualizar tabela." + e.getMessage());
        }
    }
}
