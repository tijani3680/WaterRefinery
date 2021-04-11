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
import com.tijani.waterrefineryshirzadproject.fragments.BazsaziVaNosaziFragment;
import com.tijani.waterrefineryshirzadproject.fragments.HavadesFragment;
import com.tijani.waterrefineryshirzadproject.fragments.QateeBarqFragment;
import com.tijani.waterrefineryshirzadproject.fragments.RangAmiziFragment;
import com.tijani.waterrefineryshirzadproject.fragments.TajhizatMotavaqefFragment;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import org.json.JSONArray;

public class ActivitySplashForGetDataTabsRangAmiziVaBazsaziVaNosazi extends AppCompatActivity {

    String url = Urls.urlReadRangAmizis;
    String url2 = Urls.urlReadBazsazis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_for_get_data_tabs_rang_amizi_va_bazsazi_va_nosazi);
        getAllRangAmizi();
        getAllBazsaziVaNosazi();
    }

    private void getAllRangAmizi(){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                RangAmiziFragment.data =response;


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
    private void getAllBazsaziVaNosazi(){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                BazsaziVaNosaziFragment.data =response;

                if ( RangAmiziFragment.data !=null ||  BazsaziVaNosaziFragment.data !=null){

                    Intent intent = new Intent(G.context, ActivityShowListRangAmiziVaBazsaziVaNosazi.class);
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