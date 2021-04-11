package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import java.util.HashMap;
import java.util.Map;

public class ActivityBoloers extends AppCompatActivity {
    String url = Urls.urlSabtKarkardBoloer;
    ProgressBar progressBar;
    EditText edtBoloer,edtKarkardQabli,edtKarkardFeli,edtSaateKarkard,edtAnjameTavizRowqan,edtNextTavizRowqan;
    Button btnSabt;


    String boloer = "";
    String karkardQabli = "";
    String karkardFeli = "";
    String saateKarkard = "";
    String anjameTavizRowqan = "";
    String nextTavizRowqan = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boloers);

        progressBar=findViewById(R.id.progressbarActivityBoloerss);
        edtBoloer=findViewById(R.id.edt_activityBoloerss_numberBoloers);
        edtKarkardQabli=findViewById(R.id.edt_activityBoloerss_karkardQabli);
        edtKarkardFeli=findViewById(R.id.edt_activityBoloerss_karkardFeli);
        edtSaateKarkard=findViewById(R.id.edt_activityBoloerss_saateKarkard);
        edtAnjameTavizRowqan=findViewById(R.id.edt_activityBoloerss_anjamTavizRowqan);
        edtNextTavizRowqan=findViewById(R.id.edt_activityBoloerss_nextTavizRowqan);

        btnSabt=findViewById(R.id.btn_activityBoloerss_sabt);
        btnSabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boloer=edtBoloer.getText().toString();
                karkardQabli=edtKarkardQabli.getText().toString();
                karkardFeli=edtKarkardFeli.getText().toString();
                saateKarkard=edtSaateKarkard.getText().toString();
                anjameTavizRowqan=edtAnjameTavizRowqan.getText().toString();
                nextTavizRowqan=edtNextTavizRowqan.getText().toString();

                if (boloer.isEmpty()) {
                    Toast.makeText(G.context, "شماره بلوئر مشخص نشده است!", Toast.LENGTH_SHORT).show();
                }else {

                    if (karkardQabli.isEmpty()) {
                        karkardQabli = "_";
                    }
                    if (karkardFeli.isEmpty()) {
                        karkardFeli = "_";
                    }
                    if (saateKarkard.isEmpty()) {
                        saateKarkard = "_";
                    }
                    if (anjameTavizRowqan.isEmpty()) {
                        anjameTavizRowqan = "_";
                    }
                    if (nextTavizRowqan.isEmpty()) {
                        nextTavizRowqan = "_";
                    }

                    sendData(boloer,karkardQabli,karkardFeli,saateKarkard,anjameTavizRowqan,nextTavizRowqan);



                }
            }
        });
    }




    public void sendData(final String boloer, final String karkardQabli, final String karkardFeli, final String saateKarkard, final String anjameTavizRowqan, final String nextTavizRowqan) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(G.context, response.trim().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(G.context, ActivityKarkardMahanehBoloersVaIstgahPomPazh.class);
                startActivity(intent);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            Map<String, String> params = new HashMap<>();

            @Override
            public Map<String, String> getParams() {

                params.put("boloer", boloer);
                params.put("karkardQabli", karkardQabli);
                params.put("karkardFeli", karkardFeli);
                params.put("saateKarkard", saateKarkard);
                params.put("anjameTavizRowqan", anjameTavizRowqan);
                params.put("nextTavizRowqan", nextTavizRowqan);

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(7000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(G.context);
        stringRequest.setShouldCache(false);
        queue.getCache().clear();
        queue.add(stringRequest);
        progressBar.setVisibility(View.VISIBLE);


    }

}