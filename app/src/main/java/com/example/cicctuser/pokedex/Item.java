package com.example.cicctuser.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by cicctuser on 7/6/2017.
 */

public class Item extends AppCompatActivity{
    private ListView gridViewMove;
    String JsonURLItem = "http://pokeapi.co/api/v2/item/";
    private ArrayAdapter<String> listAdapter;

    Button poke, item, moves, location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        poke = (Button) findViewById(R.id.btnPoke);
        item = (Button) findViewById(R.id.btnItems);
        moves = (Button) findViewById(R.id.btnMove);
        location = (Button) findViewById(R.id.btnLocation);

        gridViewMove = (ListView) findViewById(R.id.gridView_item);
        ArrayList<String> moveList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this, R.layout.items, moveList);
        sendRequest();

        poke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Item.this, MainActivity.class);
                Item.this.startActivity(intent);
            }
        });

        moves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Item.this, Moves.class);
                Item.this.startActivity(intent);
            }
        });

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Item.this, Item.class);
                Item.this.startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Item.this, Location.class);
                Item.this.startActivity(intent);
            }
        });
    }

    private void sendRequest(){
        RequestQueueSingleton.getInstance(this).add(obrequest);
        Log.d("Inside sendRequest", "true");
    }

    JsonObjectRequest obrequest = new JsonObjectRequest(Request.Method.GET, JsonURLItem, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Inside on response", "true");
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
                            listAdapter.add(tempObj.getString("name"));
                        }

                        gridViewMove.setAdapter(listAdapter);
                        listAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }
    );
}
