package com.example.a17170132.meusdinheiros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CategoriasDAO {
    private static CategoriasDAO instance;

    public static CategoriasDAO getInstance() {
        if (instance == null) {
            instance = new CategoriasDAO();
        }
        return instance;
    }

    public static boolean  inserir(Context context, String nome) {

        //Acessando o Banco no modo escrita
        SQLiteDatabase db = new DbCategorias(context).getWritableDatabase();

        //Montando os valores vara inserir no banco
        ContentValues valores = new ContentValues();
        valores.put("nome", nome);

        //Inserindo os valores na tabela (nome da tabela, coluna que recebera
        // valor nulo(caso tenha valor nulo), valores)
        Long id = db.insert("tbl_categorias", null, valores);
        if (id != -1) {
            return true;
        } else {
            return false;
        }

    }
    public static boolean  atualizar(Context context, String nomeNovo, String nomeVelho) {

        //Acessando o Banco no modo escrita
        SQLiteDatabase db = new DbCategorias(context).getWritableDatabase();

        //Montando os valores vara inserir no banco
        ContentValues valores = new ContentValues();
        valores.put("nome", nomeNovo);
        //Inserindo os valores na tabela (nome da tabela, coluna que recebera
        // valor nulo(caso tenha valor nulo), valores)
        int id = db.update("tbl_categorias", valores,"nome = ?", new String[]{nomeVelho});
        if (id != -1) {
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList<Categoria> selecionarTodos(Context context) {
        //Criando a lista que sera retornada
        ArrayList<Categoria> listaCategorias = new ArrayList<>();

        //Acessando o Banco no modo leitura
        SQLiteDatabase db = new DbCategorias(context).getReadableDatabase();

        String sql = "select * from tbl_categorias";

        //Executando no Banco e retornado o resultado em forma de colunas
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Categoria categoria = new Categoria();
            //Valor dentro dos gets é o indice da coluna que retorna no cursor
            categoria.setId(cursor.getInt(0));
            categoria.setNome(cursor.getString(1));
            listaCategorias.add(categoria);
        }
        cursor.close();
        return listaCategorias;
    }

    public static Categoria selecionarUm(Context context, int id) {
        SQLiteDatabase db = new DbCategorias(context).getReadableDatabase();
        String sql = "SELECT * FROM tbl_categorias WHERE _id = " + id;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Categoria categoria = new Categoria();
            categoria.setId(cursor.getInt(0));
            categoria.setNome(cursor.getString(1));
            cursor.close();
            return categoria;
        }
        return null;
    }

    public static boolean remover(Context context, Integer id) {
        SQLiteDatabase db = new DbLancamentos(context).getWritableDatabase();
        db.delete("tbl_categorias", "_id = ?", new String[]{id.toString()});
        return true;
    }
    public static boolean consultar(Context context, String nome){
        SQLiteDatabase db = new DbCategorias(context).getReadableDatabase();
        String sql = "SELECT nome FROM tbl_categorias WHERE nome = '" + nome + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            return false;
        }else{
            return true;
        }
    }

}