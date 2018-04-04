package com.example.a17170132.meusdinheiros;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class DbLancamentos extends SQLiteOpenHelper{

    //Nome do banco
    private static String DB_NAME = "lancamentos.db";

    //Versão do Banco
    private static int  DB_VERSION = 1;

    //Construtor da Classe para criação do Banco
    public DbLancamentos(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    //Criação do Banco, é igual criar tabelas ou qualquer estrutura inicial
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbl_lancamentos(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "categoria TEXT," +
                "descricao TEXT," +
                "detalhes TEXT," +
                "dataCadastro TEXT," +
                "valor TEXT)";
        db.execSQL(sql);
    }

    //Upgrade no Banco
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table tbl_lancamentos;");
        onCreate(db);
    }

}
