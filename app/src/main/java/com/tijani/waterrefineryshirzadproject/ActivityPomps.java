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

public class ActivityPomps extends AppCompatActivity {

    String url = Urls.urlSabtKarkardPomp;
    ProgressBar progressBar;
    EditText edtPomp,edtKarkardQabli,edtKarkardFeli,edtSaateKarkard,edtServisDowrehe,edtSalamateKarkard;
    Button btnSabt;


    String pomp = "";
    String karkardQabli = "";
    String karkardFeli = "";
    String saateKarkard = "";
    String servisDowrehe = "";
    String salamateKarkard = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomps);

        progressBar=findViewById(R.id.progressbarActivityPomps);
        edtPomp=findViewById(R.id.edt_activityPomps_numberPomps);
        edtKarkardQabli=findViewById(R.id.edt_activityPomps_karkardQabli);
        edtKarkardFeli=findViewById(R.id.edt_activityPomps_karkardFeli);
        edtSaateKarkard=findViewById(R.id.edt_activityPomps_saateKarkard);
        edtServisDowrehe=findViewById(R.id.edt_activityPomps_servisDowrehee);
        edtSalamateKarkard=findViewById(R.id.edt_activityPomps_vazeyatSalamatKarKard);

        btnSabt=findViewById(R.id.btn_activityPomps_sabt);

        btnSabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pomp=edtPomp.getText().toString();
                karkardQabli=edtKarkardQabli.getText().toString();
                karkardFeli=edtKarkardFeli.getText().toString();
                saateKarkard=edtSaateKarkard.getText().toString();
                servisDowrehe=edtServisDowrehe.getText().toString();
                salamateKarkard=edtSalamateKarkard.getText().toString();

                if (pomp.isEmpty()) {
                    Toast.makeText(G.context, "شماره پمپ مشخص نشده است!", Toast.LENGTH_SHORT).show();
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
                    if (servisDowrehe.isEmpty()) {
                        servisDowrehe = "_";
                    }
                    if (salamateKarkard.isEmpty()) {
                        salamateKarkard = "_";
                    }

                    sendData(pomp,karkardQabli,karkardFeli,saateKarkard,servisDowrehe,salamateKarkard);



                }
            }
        });
    }



    public void sendData(final String pomp, final String karkardQabli, final String karkardFeli, final String saateKarkard, final String servisDowrehe, final String salamateKarkard) {

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

                params.put("pomp", pomp);
                params.put("karkardQabli", karkardQabli);
                params.put("karkardFeli", karkardFeli);
                params.put("saateKarkard", saateKarkard);
                params.put("servisDowrehe", servisDowrehe);
                params.put("salamateKarkard", salamateKarkard);

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