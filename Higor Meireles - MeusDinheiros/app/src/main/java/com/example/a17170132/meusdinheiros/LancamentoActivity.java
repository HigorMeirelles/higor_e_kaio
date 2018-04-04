package com.example.a17170132.meusdinheiros;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LancamentoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    CheckBox chkItemLancamento;
    LancamentoAdapter adapter;
    TextView txtValorTotal;
    Float valorTotal = 0f;
    ListView listViewLancamentos;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamentos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewLancamentos = (ListView) findViewById(R.id.listViewLancamentos);

        //criando o adaptador vazio
        adapter = new LancamentoAdapter(this, new ArrayList<Lancamento>());

        //plugando(conectando) o adaptador na lista
        listViewLancamentos.setAdapter(adapter);

        listViewLancamentos.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        spinner();

        //Pegando os contatos do banco
        ArrayList<Lancamento> listLancamentos;
        listLancamentos =  LancamentosDAO.selecionarTodos(this);

        //limpar o conteudo
        adapter.clear();

        txtValorTotal = (TextView) findViewById(R.id.txtValorTotal);

        for (Lancamento lancamento : listLancamentos) {
            valorTotal = valorTotal + lancamento.getValor();
        }
        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);
        txtValorTotal.setText("Valor Total: " + nf.format(valorTotal));

        //preenchendo o adaptador
        adapter.addAll(listLancamentos);
    }

    //tratar o click da ListView
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view,
                            int i, long l) {

        //pegando o contato da posição i da lista
        Lancamento item = adapter.getItem(i);

        //criando o objeto de intenção
        intent = new Intent(this,
                LancamentoVisualizarActivity.class);

        //pasando o id do contato
        intent.putExtra("idLancamento", item.getId());

        //abrindo a tela de visualizar
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuMarcar) {
            chkItemLancamento = (CheckBox) findViewById(R.id.chkItemLancamento);
            for (CheckBox checkItem : adapter.listaCheck) {
                checkItem.setVisibility(View.VISIBLE);
            }
        }
        if (item.getItemId() == R.id.menuExcluir) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Excluir");
            builder.setMessage("Tem certeza que deseja excluir esse lançamento?");
            final Context context = this;
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int id = intent.getIntExtra("idLancamento", 0);
                    LancamentosDAO.getInstance().remover(context, id);
                }
            });

            builder.setNegativeButton("NÃO", null);
            builder.create().show();
        }
        if (item.getItemId() == R.id.menuMarcarTodos) {
            chkItemLancamento = (CheckBox) findViewById(R.id.chkItemLancamento);
            for (CheckBox checkItem : adapter.listaCheck) {
                checkItem.setVisibility(View.VISIBLE);
                checkItem.setChecked(true);
            }
        }
        if (item.getItemId() == R.id.menuDesmarcarTodos) {
            chkItemLancamento = (CheckBox) findViewById(R.id.chkItemLancamento);
            for (CheckBox checkItem : adapter.listaCheck) {
                checkItem.setChecked(false);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void limparCheck(View view) {
        chkItemLancamento = (CheckBox) findViewById(R.id.chkItemLancamento);
        for (CheckBox checkItem : adapter.listaCheck) {
            checkItem.setChecked(false);
            checkItem.setVisibility(View.INVISIBLE);
        }
    }

    public void novoLancamento(View view) {
        Intent intent = new Intent(this, LancamentoCadastroActivity.class);
        startActivity(intent);
    }
    public void categorias(View view){
        Intent intent = new Intent(this, CategoriasActivity.class);
        startActivity(intent);
    }

    private void spinner(){
        Spinner comboBox;
        comboBox = (Spinner) findViewById(R.id.cbCategoriaLancamento);
        ArrayList<Categoria> opcoes = CategoriasDAO.selecionarTodos(this);
        ArrayAdapter<Categoria> adaptercb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);
        adaptercb.setDropDownViewResource(android.R.layout.simple_spinner_item);
        comboBox.setAdapter(adaptercb);
    }

}
