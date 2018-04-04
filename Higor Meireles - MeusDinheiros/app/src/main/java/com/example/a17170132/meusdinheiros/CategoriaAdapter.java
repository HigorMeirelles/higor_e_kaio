package com.example.a17170132.meusdinheiros;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriaAdapter  extends ArrayAdapter<Categoria> {
    TextView itemCategoria;
    public CategoriaAdapter(Context contexto, ArrayList<Categoria> listaCategorias){
        super(contexto, 0, listaCategorias);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {

            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.itens_lista_categorias, null);
        }
        Categoria item = getItem(position);

        itemCategoria = (TextView) view.findViewById(R.id.itemCategoria);
        itemCategoria.setText(item.getNome());
        return view;
    }
}
