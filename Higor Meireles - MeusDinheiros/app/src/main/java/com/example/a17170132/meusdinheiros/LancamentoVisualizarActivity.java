package com.example.a17170132.meusdinheiros;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LancamentoVisualizarActivity extends AppCompatActivity {
    EditText edtNomeLcmt;
    EditText edtDescricaoLcmt;
    EditText edtDetalhesLcmt;
    EditText edtDataLcmt;
    EditText edtValorLcmt;
    Spinner comboBox;
    Button btnSalvarLcmt;
    Button btnCancelarLcmt;
    int idLancamento;
    ArrayAdapter<Categoria> adaptercb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento_visualizar);

        //acessar o ID passado por Intent
        Intent intent = getIntent();
        idLancamento = intent.getIntExtra("idLancamento", 0);

        //Pegando os elementos
        edtNomeLcmt = (EditText) findViewById(R.id.edtNomeLcmt);
        edtDescricaoLcmt = (EditText) findViewById(R.id.edtDescricaoLcmt);
        edtDetalhesLcmt = (EditText) findViewById(R.id.edtDetalhesLcmt);
        edtDataLcmt = (EditText) findViewById(R.id.edtDataLcmt);
        edtValorLcmt = (EditText) findViewById(R.id.edtValorLcmt);
        btnSalvarLcmt = (Button) findViewById(R.id.btnSalvarLcmt);
        btnCancelarLcmt = (Button) findViewById(R.id.btnCancelarLcmt);
        comboBox = (Spinner) findViewById(R.id.cbCategoriaLcmt);

        Lancamento lancamento = LancamentosDAO.selecionarUm(this, idLancamento);
        edtNomeLcmt.setText(lancamento.getNome());
        edtDescricaoLcmt.setText(lancamento.getDescricao());
        edtDetalhesLcmt.setText(lancamento.getDetalhes());
        edtDataLcmt.setText(lancamento.getDataCadastro());

        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);
        edtValorLcmt.setText(nf.format(lancamento.getValor()));


        ArrayList<Categoria> opcoes = CategoriasDAO.selecionarTodos(this);
        adaptercb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);
        adaptercb.setDropDownViewResource(android.R.layout.simple_spinner_item);
        comboBox.setAdapter(adaptercb);
        int tamanho = adaptercb.getCount();
        int spinnerPostion=0;
        if (!lancamento.getCategoria().equals(null)) {
            for(int i = 0; i < tamanho;  i++){
                String cat = adaptercb.getItem(i).getNome();
                if(cat.equals(lancamento.getCategoria())){
                    spinnerPostion = adaptercb.getPosition(adaptercb.getItem(i));
                    break;
                }
            }

            comboBox.setSelection(spinnerPostion);
            comboBox.setEnabled(false);
            spinnerPostion = 0;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lancamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuEditar) {
            edtNomeLcmt.setEnabled(true);
            edtDescricaoLcmt.setEnabled(true);
            edtDetalhesLcmt.setEnabled(true);
            edtDataLcmt.setEnabled(true);
            edtValorLcmt.setEnabled(true);
            comboBox.setEnabled(true);
            btnSalvarLcmt.setVisibility(View.VISIBLE);
            btnCancelarLcmt.setVisibility(View.VISIBLE);
        }
        if (item.getItemId() == R.id.menuExcluir) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Excluir");
            builder.setMessage("Tem certeza que deseja excluir esse lançamento?");
            final Context context = this;
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LancamentosDAO.getInstance().remover(context, idLancamento);
                    finish();
                }
            });

            builder.setNegativeButton("NÃO", null);
            builder.create().show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void salvarEdicaoLancamento(View view) {
        Lancamento lancamento = new Lancamento();
        lancamento.setId(idLancamento);
        lancamento.setNome(edtNomeLcmt.getText().toString());
        lancamento.setDescricao(edtDescricaoLcmt.getText().toString());
        lancamento.setDetalhes(edtDetalhesLcmt.getText().toString());
        lancamento.setDataCadastro(edtDataLcmt.getText().toString());
        String valor = edtValorLcmt.getText().toString().replace("R$", "");
        valor = valor.replace(",", ".");
        lancamento.setValor(Float.parseFloat(valor));
        lancamento.setCategoria(comboBox.getSelectedItem().toString());

        LancamentosDAO.editar(this,lancamento);
        finish();
    }

    public void cancelarEdicaoLancamento(View view) {
        finish();
    }
}
