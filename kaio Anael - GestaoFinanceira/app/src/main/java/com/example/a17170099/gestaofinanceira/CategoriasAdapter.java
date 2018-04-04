package com.example.a17170099.gestaofinanceira;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by 17170099 on 28/03/2018.
 */

public class CategoriasAdapter extends ArrayAdapter<Categorias> {
    public CategoriasAdapter(Context ctx, ArrayList<Categorias> lst){
        super(ctx, 0, lst);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null){

            v = LayoutInflater.from(getContext())
                    .inflate(R.layout.itens_lista_categorias, null);
        }

        //pegar o contato que esta sendo montado
        Categorias item = getItem(position);

        TextView txt_item_nome2 = v.findViewById(R.id.txt_item_nome2);




        txt_item_nome2.setText(item.getNome());




        return v;
    }
}



