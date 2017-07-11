package com.example.cicctuser.pokedex;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by cicctuser on 7/6/2017.
 */

public class RequestQueueSingleton {
    private static RequestQueue requestQueue;

    private RequestQueueSingleton() {

    }

    public static RequestQueue getInstance(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }
}
