package com.example.a17170099.gestaofinanceira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    LancamentosAdapter adapter;
    ListView lstViewLancamentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstViewLancamentos = (ListView)findViewById(R.id.lst_view_lancamentos);
        adapter = new LancamentosAdapter(this,new ArrayList<Lancamentos>());
        lstViewLancamentos.setAdapter(adapter);
        lstViewLancamentos.setOnItemClickListener(this);
    }
    @Override
    protected  void onResume(){
        super.onResume();
        ArrayList<Lancamentos> lstLancamentos;
        lstLancamentos = LancamentosDAO.selecionarTodos(this);
        adapter.clear();
        adapter.addAll(lstLancamentos);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,
                            int i, long l) {

        //pegando o contato da posição i da lista
        Lancamentos item = adapter.getItem(i);

        //criando o objeto de intenção
        Intent intent = new Intent(this,
                VisualizarActivity.class);

        //pasando o id do contato
        intent.putExtra("idLancamento", item.getId());

        //abrindo a tela de visualizar
        startActivity(intent);

    }
    public void btnAdd(View view) {
        Intent intent = new Intent(this,CadastroLancamento.class);
        startActivity(intent);
    }

    public void btnCategoria(View view) {
        Intent intent = new Intent(this,CadastroCategoria.class);
        startActivity(intent);
    }
}
