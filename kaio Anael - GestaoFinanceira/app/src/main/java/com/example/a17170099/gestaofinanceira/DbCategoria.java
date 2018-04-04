package com.example.a17170099.gestaofinanceira;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 17170099 on 21/03/2018.
 */

public class DbCategoria extends SQLiteOpenHelper{

    //NOME DO BANCO
    private static String DB_NAME = "categoria.db";

    //Versão do banco
    private static int DB_VERSION = 1;

    //Criando banco
    public DbCategoria(Context ctx){
        super(ctx,DB_NAME,null,DB_VERSION);


    }
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


    @Override
    public void onUpgrade(SQLiteDatabase  db, int oldVersion, int newVersion) {
        db.execSQL("drop table tbl_categorias");
        onCreate(db);

    }
}
