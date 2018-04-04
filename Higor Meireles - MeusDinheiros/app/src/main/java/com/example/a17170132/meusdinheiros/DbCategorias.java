package com.example.a17170132.meusdinheiros;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCategorias extends SQLiteOpenHelper {
    //Nome do banco
    private static String DB_NAME = "categorias.db";

    //Versão do Banco
    private static int  DB_VERSION = 1;

    //Construtor da Classe para criação do Banco
    public DbCategorias(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    //Criação do Banco, é igual criar tabelas ou qualquer estrutura inicial
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE tbl_categorias( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT);";
        db.execSQL(sql);
        sql = ("INSERT INTO tbl_categorias(nome) values ('Comida');");
        db.execSQL(sql);
        sql = ("INSERT INTO tbl_categorias(nome) values ('Lazer');");
        db.execSQL(sql);
        sql = ("INSERT INTO tbl_categorias(nome) values ('Moradia');");
        db.execSQL(sql);
        sql = ("INSERT INTO tbl_categorias(nome) values ('Saúde');");
        db.execSQL(sql);
        sql = ("INSERT INTO tbl_categorias(nome) values ('Transporte');");
        db.execSQL(sql);
    }

    //Upgrade no Banco
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table tbl_categorias;");
        onCreate(db);
    }

}