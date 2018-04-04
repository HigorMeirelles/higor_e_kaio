package com.example.a17170099.gestaofinanceira;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;

public class CategoriaDAO {

    private static CategoriaDAO instance;

    public static CategoriaDAO getInstance() {
        if (instance == null) {
            instance = new CategoriaDAO();
        }
        return instance;
    }

    public static boolean inserir(Context context, Categorias categorias) {

        //Acessando o Banco no modo escrita
        SQLiteDatabase db = new DbCategoria(context).getWritableDatabase();

        //Montando os valores vara inserir no banco
        ContentValues valores = new ContentValues();
        valores.put("nome", categorias.getNome());


        //Inserindo os valores na tabela (nome da tabela, coluna que recebera
        // valor nulo(caso tenha valor nulo), valores)
        Long id = db.insert("tbl_categorias", null, valores);
        if (id != -1) {
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList<Categorias> selecionarTodos(Context context) {
        //Criando a lista que sera retornada
        ArrayList<Categorias> listaCategorias = new ArrayList<>();

        //Acessando o Banco no modo leitura
        SQLiteDatabase db = new DbCategoria(context).getReadableDatabase();

        String sql = "select * from tbl_categorias";

        //Executando no Banco (comando e null (os where caso tenha))e retornado o resultado
        //em forma de colunas
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Categorias categoria = new Categorias();
            //Valor dentro dos gets é o indice da coluna que retorna no cursor
            categoria.setId(cursor.getInt(0));
            categoria.setNome(cursor.getString(1));
            listaCategorias.add(categoria);
        }
        cursor.close();
        return listaCategorias;
    }

}
