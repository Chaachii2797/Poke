package com.example.cicctuser.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cicctuser on 7/6/2017.
 */

public class PokemonList extends BaseAdapter{
    private Context context;
    private List<Poke> pokemonList;
    private LayoutInflater mInflater;

    public PokemonList(Context context, List<Poke> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pokemonList.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.poke_item, null);

        TextView textView = (TextView) convertView.findViewById(R.id.tv_poke);
        textView.setText(pokemonList.get(position).getPokeName());
        return convertView;
    }
}
