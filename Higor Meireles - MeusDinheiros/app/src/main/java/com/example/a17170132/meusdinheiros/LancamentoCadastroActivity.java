package com.example.a17170132.meusdinheiros;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class LancamentoCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_cadastro);

        Spinner comboBox;
        comboBox = (Spinner) findViewById(R.id.cbCadCategoriaLancamento);
        ArrayList<String> opcoes = new ArrayList<String>();
        opcoes.add("Lazer");
        opcoes.add("Transporte");
        opcoes.add("Saúde");
        opcoes.add("Moradia");
        opcoes.add("Saúde");
        opcoes.add("Comida");
        opcoes.add("Outros");

        ArrayAdapter<String> adaptercb = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoes);
        adaptercb.setDropDownViewResource(android.R.layout.simple_spinner_item);
        comboBox.setAdapter(adaptercb);
        // TODO: trocar isso
    }

    public void gravarLancamento(View view) {
        //Recuperando valores
        EditText edtCadNomeLanc = (EditText) findViewById(R.id.edtCadNomeLanc);
        EditText edtCadValorLanc = (EditText) findViewById(R.id.edtCadValorLanc);
        EditText edtCadDescricaoLanc = (EditText) findViewById(R.id.edtCadDescricaoLanc);
        EditText edtCadDetalhesLanc = (EditText) findViewById(R.id.edtCadDetalhesLanc);
        EditText edtCadDataLanc = (EditText) findViewById(R.id.edtCadDataLanc);
        Spinner cbCadCategoriaLancamento = (Spinner) findViewById(R.id.cbCadCategoriaLancamento);
        //Setando valores
        Lancamento lancamento = new Lancamento(
                edtCadNomeLanc.getText().toString(),
                cbCadCategoriaLancamento.getSelectedItem().toString(),
                edtCadDescricaoLanc.getText().toString(),
                edtCadDetalhesLanc.getText().toString(),
                edtCadDataLanc.getText().toString(),
                Float.parseFloat(edtCadValorLanc.getText().toString())
        );
        //Gravando novo valor
        LancamentosDAO.getInstance().inserir(this, lancamento);
        //fechando tela
        finish();
    }
}
