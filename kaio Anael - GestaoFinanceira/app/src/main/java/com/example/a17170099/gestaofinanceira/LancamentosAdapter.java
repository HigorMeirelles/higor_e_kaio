package com.example.a17170099.gestaofinanceira;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by 17170099 on 14/03/2018.
 */

public class LancamentosAdapter extends ArrayAdapter<Lancamentos> {

    public LancamentosAdapter(Context ctx, ArrayList<Lancamentos> lst){
        super(ctx, 0, lst);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null){

            v = LayoutInflater.from(getContext())
                    .inflate(R.layout.itens_lista_main, null);
        }

        //pegar o contato que esta sendo montado
        Lancamentos item = getItem(position);
        TextView txt_item_nome = v.findViewById(R.id.txt_item_nome);
        TextView txt_item_valor = v.findViewById(R.id.txt_item_valor);
        TextView txt_item_data = v.findViewById(R.id.txt_item_data);
        TextView txt_item_categoria = v.findViewById(R.id.txt_item_spinner);



        Locale ptBr = new Locale("pt","BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);
        txt_item_nome.setText(item.getNome());
        txt_item_valor.setText(nf.format(item.getValor()));
        txt_item_data.setText(item.getData());
        txt_item_categoria.setText(item.getCategoria());



        return v;
    }
}
