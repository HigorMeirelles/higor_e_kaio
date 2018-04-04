package com.example.a17170132.meusdinheiros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CategoriasActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listViewCategorias;
    CategoriaAdapter adapter;
    String nomeCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        listViewCategorias = (ListView) findViewById(R.id.listaCategorias);
        adapter = new CategoriaAdapter(this, new ArrayList<Categoria>());
        listViewCategorias.setAdapter(adapter);

        listViewCategorias.setOnItemClickListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        atualizar();
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,
                            int i, long l) {
        //pegando o contato da posição i da lista
        Categoria item = adapter.getItem(i);
        EditText edtVerCategoria = (EditText) findViewById(R.id.edtVerCategoria);
        edtVerCategoria.setText(item.getNome());

        Button btnEdtCategoria = (Button)findViewById(R.id.btnEdtCategoria);
        btnEdtCategoria.setText("Editar");

        nomeCategoria =  item.getNome();
    }

    public void salvar(View view) {
        Button btnEdtCategoria = (Button)findViewById(R.id.btnEdtCategoria);
        EditText edtVerCategoria = (EditText) findViewById(R.id.edtVerCategoria);

        if(btnEdtCategoria.getText().toString() == "Salvar"){
            CategoriasDAO.inserir(this, edtVerCategoria.getText().toString());
            edtVerCategoria.setText("");
            atualizar();
        }else if(btnEdtCategoria.getText().toString() ==  "Editar"){

            CategoriasDAO.atualizar(this,edtVerCategoria.getText().toString(),nomeCategoria);
            if(LancamentosDAO.consultar(this,nomeCategoria)) {
                LancamentosDAO.atualizar(this, edtVerCategoria.getText().toString(), nomeCategoria);
            }
            btnEdtCategoria.setText("Salvar");
            edtVerCategoria.setText("");
            atualizar();
        }
    }

    public void excluir(View view) {

        EditText edtVerCategoria = (EditText) findViewById(R.id.edtVerCategoria);
        if(!CategoriasDAO.consultar(this,edtVerCategoria.getText().toString())) {

        }
    }

    private void atualizar(){
        //Pegando os contatos do banco
        ArrayList<Categoria> listCategorias;
        listCategorias =  CategoriasDAO.selecionarTodos(this);
        //limpar o conteudo
        adapter.clear();

        //preenchendo o adaptador
        adapter.addAll(listCategorias);
    }
}
