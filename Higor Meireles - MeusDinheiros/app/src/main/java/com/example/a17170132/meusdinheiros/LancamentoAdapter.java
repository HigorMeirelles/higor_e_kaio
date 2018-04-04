package com.example.a17170132.meusdinheiros;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LancamentoAdapter extends ArrayAdapter<Lancamento> {
    TextView txtNomeLancamento;
    TextView txtCategoriaLancamento;
    TextView txtDescricaoLancamento;
    TextView txtValorLancamento;
    CheckBox chkItemLancamento;

    ArrayList<CheckBox> listaCheck = new ArrayList<CheckBox>();
    public LancamentoAdapter(Context contexto, ArrayList<Lancamento> listaLancamentos){
        super(contexto, 0, listaLancamentos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {

            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.itens_lista_lancamentos, null);
        }

        //Pegaando o contato que esta sendo montado
        Lancamento item = getItem(position);

        txtNomeLancamento = view.findViewById(R.id.txtNomeLancamento);
        txtCategoriaLancamento = view.findViewById(R.id.txtCategoriaLancamento);
        txtDescricaoLancamento = view.findViewById(R.id.txtDescricaoLancamento);
        txtValorLancamento = view.findViewById(R.id.txtValorLancamento);
        chkItemLancamento = view.findViewById(R.id.chkItemLancamento);
        chkItemLancamento.setTag(item);

        listaCheck.add(chkItemLancamento);
        txtNomeLancamento.setText(item.getNome());
        txtCategoriaLancamento.setText(item.getCategoria());
        txtDescricaoLancamento.setText(item.getDescricao());

        Locale ptBr = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);
        txtValorLancamento.setText(nf.format(item.getValor()));

        chkItemLancamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox ch = (CheckBox) view;
                if(ch.isChecked()){
                    Lancamento lc = (Lancamento) ch.getTag();
                    Toast.makeText(getContext(),lc.getNome(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext()," ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}
