package com.example.a17170099.gestaofinanceira;
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

    public static boolean inserir(Context context, Lancamentos lancamentos) {

        //Acessando o Banco no modo escrita
        SQLiteDatabase db = new DbLancamentos(context).getWritableDatabase();

        //Montando os valores vara inserir no banco
        ContentValues valores = new ContentValues();
        valores.put("nome", lancamentos.getNome());
        valores.put("categoria", lancamentos.getCategoria());


        /*String data = new SimpleDateFormat("dd/MM/yyyy")
                .format(lancamentos.getData());*/

        valores.put("data", lancamentos.getData());
        valores.put("valor", lancamentos.getValor());

        //Inserindo os valores na tabela (nome da tabela, coluna que recebera
        // valor nulo(caso tenha valor nulo), valores)
        Long id = db.insert("tbl_lancamentos", null, valores);
        if (id != -1) {
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList<Lancamentos> selecionarTodos(Context context) {
        //Criando a lista que sera retornada
        ArrayList<Lancamentos> listaLancamentos = new ArrayList<>();

        //Acessando o Banco no modo leitura
        SQLiteDatabase db = new DbLancamentos(context).getReadableDatabase();

        String sql = "select * from tbl_lancamentos";

        //Executando no Banco (comando e null (os where caso tenha))e retornado o resultado
        //em forma de colunas
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Lancamentos lancamentos = new Lancamentos();

            //Valor dentro dos gets é o indice da coluna que retorna no cursor
            lancamentos.setId(cursor.getInt(0));
            lancamentos.setNome(cursor.getString(1));
            lancamentos.setCategoria(cursor.getString(2));
            lancamentos.setData(cursor.getString(3));
            lancamentos.setValor(Double.parseDouble(cursor.getString(4)));
            listaLancamentos.add(lancamentos);
        }
        return listaLancamentos;
    }

    public static Lancamentos selecionarUm(Context context, int id) {
        SQLiteDatabase db = new DbLancamentos(context).getReadableDatabase();
        String sql = "select * from tbl_lancamentos where _id = " + id;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Lancamentos lancamentos = new Lancamentos();
            lancamentos.setId(cursor.getInt(0));
            lancamentos.setNome(cursor.getString(1));
            lancamentos.setCategoria(cursor.getString(2));
            lancamentos.setData(cursor.getString(3));
            lancamentos.setValor(cursor.getDouble(4));
            cursor.close();
            return lancamentos;
        }
        return null;
    }

    public static boolean remover(Context context, Integer id) {
        SQLiteDatabase db = new DbLancamentos(context).getWritableDatabase();
        db.delete("tbl_lancamentos", "_id = ?", new String[]{id.toString()});
        return true;
    }

    public static boolean editar(Context context, Lancamentos lancamento) {
        //Acessando o Banco no modo escrita
        SQLiteDatabase db = new DbLancamentos(context).getWritableDatabase();
        //Montando os valores vara inserir no banco
        ContentValues valores = new ContentValues();
        valores.put("nome", lancamento.getNome());
        valores.put("categoria", lancamento.getCategoria());
        valores.put("data", lancamento.getData());
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
