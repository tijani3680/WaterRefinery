package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import org.json.JSONArray;

public class ActivitySplashForGetGandZodaye extends AppCompatActivity {

    String url = Urls.urlReadGandzodayes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_for_get_gand_zodaye);
        getAllGandZodaye();

    }

    private void getAllGandZodaye(){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                ActivityShowListGandZodae.data =response;
                if (ActivityShowListGandZodae.data !=null){

                    Intent intent = new Intent(G.context, ActivityShowListGandZodae.class);
                    startActivity(intent);
                    finish();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(7000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(G.context);
        jsonArrayRequest.setShouldCache(false);
        queue.getCache().clear();
        queue.add(jsonArrayRequest);

    }

}