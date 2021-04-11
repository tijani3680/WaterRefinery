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
import com.tijani.waterrefineryshirzadproject.fragments.HavadesFragment;
import com.tijani.waterrefineryshirzadproject.fragments.QateeBarqFragment;
import com.tijani.waterrefineryshirzadproject.fragments.TajhizatMotavaqefFragment;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import org.json.JSONArray;

public class ActivitySplashForGetDataTabsHavadesVaQateeBarq extends AppCompatActivity {

    String url = Urls.urlReadHavadess;
    String url2 = Urls.urlReadQateeBarqs;
    String url3 = Urls.urlReadTajhizatMotavaqefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_for_get_data_tabs_havades_va_qatee_barq);
        getAllHavades();
        getAllQateeBarq();
        getAllTajhizatMotavaqef();

    }

    private void getAllHavades(){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                HavadesFragment.data =response;


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
    private void getAllQateeBarq(){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                QateeBarqFragment.data =response;


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

    private void getAllTajhizatMotavaqef(){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url3, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                TajhizatMotavaqefFragment.data =response;
                if ( HavadesFragment.data !=null  || QateeBarqFragment.data !=null || TajhizatMotavaqefFragment.data !=null){

                    Intent intent = new Intent(G.context, ActivityShowListHavadesVaQateeBarq.class);
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