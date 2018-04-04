package com.example.a17170132.meusdinheiros;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class LancamentosDAO {

    private static LancamentosDAO instance;

    public static LancamentosDAO getInstance() {
        if (instance == null) {
            instance = new LancamentosDAO();
        }
        return instance;
    }

    public static boolean inserir(Context context, Lancamento lancamento) {

        //Acessando o Banco no modo escrita
        SQLiteDatabase db = new DbLancamentos(context).getWritableDatabase();

        //Montando os valores vara inserir no banco
        ContentValues valores = new ContentValues();
        valores.put("nome", lancamento.getNome());
        valores.put("categoria", lancamento.getCategoria());
        valores.put("detalhes", lancamento.getDetalhes());
        valores.put("descricao", lancamento.getDescricao());
        valores.put("dataCadastro", lancamento.getDataCadastro());
        valores.put("valor", lancamento.getValor());

        //Inserindo os valores na tabela (nome da tabela, coluna que recebera
        // valor nulo(caso tenha valor nulo), valores)
        Long id = db.insert("tbl_lancamentos", null, valores);
        if (id != -1) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean  atualizar(Context context, String nomeNovo, String nomeVelho) {

        //Acessando o Banco no modo escrita
        SQLiteDatabase db = new DbLancamentos(context).getWritableDatabase();

        //Montando os valores vara inserir no banco
        ContentValues valores = new ContentValues();
        valores.put("categoria", nomeNovo);
        //Inserindo os valores na tabela (nome da tabela, coluna que recebera
        // valor nulo(caso tenha valor nulo), valores)
        int id = db.update("tbl_lancamentos", valores,"categoria = ?", new String[]{nomeVelho});
        if (id != -1) {
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList<Lancamento> selecionarTodos(Context context) {
        //Criando a lista que sera retornada
        ArrayList<Lancamento> listaLancamentos = new ArrayList<>();

        //Acessando o Banco no modo leitura
        SQLiteDatabase db = new DbLancamentos(context).getReadableDatabase();

        String sql = "select * from tbl_lancamentos";

        //Executando no Banco (comando e null (os where caso tenha))e retornado o resultado
        //em forma de colunas
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Lancamento lancamento = new Lancamento();

            //Valor dentro dos gets é o indice da coluna que retorna no cursor
            lancamento.setId(cursor.getInt(0));
            lancamento.setNome(cursor.getString(1));
            lancamento.setCategoria(cursor.getString(2));
            lancamento.setDescricao(cursor.getString(3));
            lancamento.setDetalhes(cursor.getString(4));
            lancamento.setDataCadastro(cursor.getString(5));
            lancamento.setValor(Float.parseFloat(cursor.getString(6)));
            listaLancamentos.add(lancamento);
        }
        return listaLancamentos;
    }

    public static Lancamento selecionarUm(Context context, int id) {
        SQLiteDatabase db = new DbLancamentos(context).getReadableDatabase();
        String sql = "select * from tbl_lancamentos where _id = " + id;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Lancamento lancamento = new Lancamento();
            lancamento.setId(cursor.getInt(0));
            lancamento.setNome(cursor.getString(1));
            lancamento.setCategoria(cursor.getString(2));
            lancamento.setDescricao(cursor.getString(3));
            lancamento.setDetalhes(cursor.getString(4));
            lancamento.setDataCadastro(cursor.getString(5));
            lancamento.setValor(cursor.getFloat(6));
            cursor.close();
            return lancamento;
            }
        return null;
        }

    public static boolean remover(Context context, Integer id) {
        SQLiteDatabase db = new DbLancamentos(context).getWritableDatabase();
        db.delete("tbl_lancamentos", "_id = ?", new String[]{id.toString()});
        return true;
    }

    public static boolean consultar(Context context, String nome){
        SQLiteDatabase db = new DbLancamentos(context).getReadableDatabase();
        String sql = "SELECT categoria FROM tbl_lancamentos WHERE categoria = '" + nome + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            return false;
        }else{
            return true;
        }
    }
    public static boolean editar(Context context, Lancamento lancamento) {
        //Acessando o Banco no modo escrita
        SQLiteDatabase db = new DbLancamentos(context).getWritableDatabase();
        //Montando os valores vara inserir no banco
        ContentValues valores = new ContentValues();
        valores.put("nome", lancamento.getNome());
        valores.put("categoria", lancamento.getCategoria());
        valores.put("detalhes", lancamento.getDetalhes());
        valores.put("descricao", lancamento.getDescricao());
        valores.put("dataCadastro", lancamento.getDataCadastro());
        valores.put("valor", lancamento.getValor());
        //Inserindo os valores na tabela (nome da tabela, coluna que recebera
        // valor nulo(caso tenha valor nulo), valores)
        int id = db.update("tbl_lancamentos", valores,"_id = ?", new String[]{lancamento.getId().toString()});
        if (id != -1) {
            return true;
        } else {
            return false;
        }
    }

}
