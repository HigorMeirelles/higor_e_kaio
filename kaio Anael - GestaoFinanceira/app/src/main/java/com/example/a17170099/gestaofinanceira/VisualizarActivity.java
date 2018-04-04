package com.example.a17170099.gestaofinanceira;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class VisualizarActivity extends AppCompatActivity {

    Integer id;

    EditText txt_nome, txt_valor, txt_categoria, txt_data;
    Spinner comboBox;
    ArrayAdapter<Categorias> adaptercb;

    Lancamentos lancamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        txt_nome = (EditText) findViewById(R.id.txt_nome1);
        txt_valor = (EditText) findViewById(R.id.txt_valor1);
        //txt_categoria = (EditText) findViewById(R.id.txt_categoria1);
        txt_data = (EditText) findViewById(R.id.txt_data1);
        Intent intent = getIntent();
        id = intent.getIntExtra("idLancamento", 0);
    }
    @Override
    protected void onResume() {
        super.onResume();

        //selecionar contato do banco
        lancamentos = LancamentosDAO.selecionarUm(this, id);
        Locale ptBr = new Locale("pt","BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);
        txt_nome.setText(lancamentos.getNome());
        txt_valor.setText(nf.format(lancamentos.getValor()));
        //txt_categoria.setText(lancamentos.getCategoria());
        txt_data.setText(lancamentos.getData());

        comboBox = (Spinner) findViewById(R.id.combobox1);
        ArrayList<Categorias> opcoes = CategoriaDAO.selecionarTodos(this);
        adaptercb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);
        adaptercb.setDropDownViewResource(android.R.layout.simple_spinner_item);
        comboBox.setAdapter(adaptercb);
        int tamanho = adaptercb.getCount();
        int spinnerPostion=0;
        if (!lancamentos.getCategoria().equals(null)) {
            for(int i = 0; i < tamanho;  i++){
                String cat = adaptercb.getItem(i).getNome();
                if(cat.equals(lancamentos.getCategoria())){
                    spinnerPostion = adaptercb.getPosition(adaptercb.getItem(i));
                    break;
                }
            }
            comboBox.setSelection(spinnerPostion);
            spinnerPostion = 0;
        }
    }

    public void editar(View view){
        Lancamentos lancamentos = new Lancamentos();
        lancamentos.setId(id);
        lancamentos.setNome(txt_nome.getText().toString());
        String valor = txt_valor.getText().toString().replace("R$", "");
        valor = valor.replace(",", ".");
        lancamentos.setValor(Double.parseDouble(valor));
        lancamentos.setCategoria(comboBox.getSelectedItem().toString());
        LancamentosDAO.editar(this,lancamentos);
        finish();
    }

    public void excluir(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir");
        builder.setMessage("Tem certeza que deseja excluir esse lançamento?");
        final Context context = this;
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LancamentosDAO.getInstance().remover(context, id);
                finish();
            }
        });

        builder.setNegativeButton("NÃO", null);
        builder.create().show();
    }

}
