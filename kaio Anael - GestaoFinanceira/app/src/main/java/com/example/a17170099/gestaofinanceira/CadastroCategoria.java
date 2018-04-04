package com.example.a17170099.gestaofinanceira;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class CadastroCategoria extends AppCompatActivity {
    Categorias categorias;
    EditText nome;
    CategoriasAdapter adapter;
    ListView lstViewCategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);
        lstViewCategorias = (ListView)findViewById(R.id.lst_view_categorias);
        adapter = new CategoriasAdapter(this,new ArrayList<Categorias>());
        lstViewCategorias.setAdapter(adapter);

    }
    @Override
    protected  void onResume(){
        super.onResume();
        ArrayList<Categorias> lstCategorias;
        lstCategorias = CategoriaDAO.selecionarTodos(this);
        adapter.clear();
        adapter.addAll(lstCategorias);
    }



    public void btnCategoria(View view) {
        categorias = new Categorias();
        nome = (EditText)findViewById(R.id.txt_nome_categoria);
        categorias.setNome(nome.getText().toString());
        CategoriaDAO.inserir(this,categorias);
        finish();

    }




}
