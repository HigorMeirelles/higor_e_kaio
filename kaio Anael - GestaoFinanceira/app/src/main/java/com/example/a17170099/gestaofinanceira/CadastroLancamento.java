package com.example.a17170099.gestaofinanceira;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CadastroLancamento extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_lancamento);
        Spinner comboBox;
        comboBox = (Spinner) findViewById(R.id.spCategoria);
        ArrayList<Categorias> opcoes = CategoriaDAO.selecionarTodos(this);
        ArrayAdapter<Categorias> adaptercb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);
        adaptercb.setDropDownViewResource(android.R.layout.simple_spinner_item);
        comboBox.setAdapter(adaptercb);


    }

    public void btnAdicionar(View view) {

        EditText nome = (EditText)findViewById(R.id.txtnome);
        EditText valor = (EditText)findViewById(R.id.txtvalor);
        EditText data = (EditText)findViewById(R.id.txtdata);
        Spinner spinner = (Spinner)findViewById(R.id.spCategoria);

        Lancamentos lancamentos = new Lancamentos();
            lancamentos.setNome(nome.getText().toString());
            lancamentos.setValor(Double.parseDouble(valor.getText().toString()));
            lancamentos.setData(data.getText().toString());
            lancamentos.setCategoria(spinner.getSelectedItem().toString());

            LancamentosDAO.inserir(this,lancamentos);
            finish();
    }


}
