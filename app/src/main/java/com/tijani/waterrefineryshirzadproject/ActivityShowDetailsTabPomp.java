package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityShowDetailsTabPomp extends AppCompatActivity {

    String url = Urls.urlReadKarkardPomp;

    ProgressBar progressBar;
    TextView txvPomp, txvKarkardQabli, txvKarkardFeli, txvSaateKarkard, txvServisDowrehe, txvSalamateKarkard;



    String id = "";
    String pomp = "";
    String karkardQabli = "";
    String karkardFeli = "";
    String saateKarkard = "";
    String servisDowrehe = "";
    String salamateKarkard = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_tab_pomp);

        progressBar=findViewById(R.id.progressbarActivityShowDtilsTabPomp);
        txvPomp=findViewById(R.id.txv_activityShowDtilsTabPomp_pomp);
        txvKarkardQabli=findViewById(R.id.txv_activityShowDtilsTabPomp_karkardQabli);
        txvKarkardFeli=findViewById(R.id.txv_activityShowDtilsTabPomp_karkardFeli);
        txvSaateKarkard=findViewById(R.id.txv_activityShowDtilsTabPomp_saateKarkard);
        txvServisDowrehe=findViewById(R.id.txv_activityShowDtilsTabPomp_servisDowrehe);
        txvSalamateKarkard=findViewById(R.id.txv_activityShowDtilsTabPomp_salamateKarkard);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        getData();
    }

    private void getData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    pomp = jsonObject.getString("pomp");
                    karkardQabli = jsonObject.getString("karkardQabli");
                    karkardFeli = jsonObject.getString("karkardFeli");
                    saateKarkard = jsonObject.getString("saateKarkard");
                    servisDowrehe = jsonObject.getString("servisDowrehe");
                    salamateKarkard = jsonObject.getString("salamateKarkard");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                txvPomp.setText(pomp);
                txvKarkardQabli.setText(karkardQabli);
                txvKarkardFeli.setText(karkardFeli);
                txvSaateKarkard.setText(saateKarkard);
                txvServisDowrehe.setText(servisDowrehe);
                txvSalamateKarkard.setText(salamateKarkard);
                progressBar.setVisibility(View.GONE);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            Map<String, String> params = new HashMap<>();

            @Override
            public Map<String, String> getParams() {
                params.put("id", id);
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