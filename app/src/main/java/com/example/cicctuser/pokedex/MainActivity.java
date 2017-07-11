package com.example.cicctuser.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String JsonURLPokemon = "http://pokeapi.co/api/v2/pokemon/";

    Button poke, item, moves, location;
    private ListView gridViewPokemon;
    private PokemonList pokemonListAdapter;
    private List<Poke> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewPokemon = (ListView)findViewById(R.id.gridView_poke);

        pokemonList = new ArrayList<Poke>();
        pokemonListAdapter = new PokemonList(MainActivity.this, pokemonList);
        gridViewPokemon.setAdapter(pokemonListAdapter);

        poke = (Button) findViewById(R.id.btnPoke);
        item = (Button) findViewById(R.id.btnItems);
        location = (Button) findViewById(R.id.btnLocation);
        moves = (Button) findViewById(R.id.btnMove);

        sendRequest();

        poke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        moves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Moves.class);
                MainActivity.this.startActivity(intent);
            }
        });

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Item.class);
                MainActivity.this.startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Location.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void sendRequest() {
        RequestQueueSingleton.getInstance(this).add(obrequest);
    }
    JsonObjectRequest obrequest = new JsonObjectRequest(Request.Method.GET, JsonURLPokemon, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
                            Poke poke = new Poke();
                            poke.setPokeName(tempObj.getString("name"));
                            pokemonList.add(poke);
                        }

                        pokemonListAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }
    );
}
