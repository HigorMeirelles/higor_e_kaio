package com.example.a17170099.gestaofinanceira;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 17170099 on 21/03/2018.
 */

public class DbLancamentos extends SQLiteOpenHelper{

    //NOME DO BANCO
    private static String DB_NAME = "lancamentos.db";

    //Versão do banco
    private static int DB_VERSION = 1;

    //Criando banco
    public DbLancamentos(Context ctx){
        super(ctx,DB_NAME,null,DB_VERSION);


    }
   @Override
     public void onCreate(SQLiteDatabase db){
        String sql ="CREATE TABLE tbl_lancamentos("+
                "_id integer primary key autoincrement," +
                "nome text," +
                "categoria text," +
                "data text," +
                "valor text)";
            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase  db, int oldVersion, int newVersion) {
         db.execSQL("drop table tbl_lancamentos");
         onCreate(db);

    }
}
