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

public class ActivityShowDetailsTabBazsaziVaNosazi extends AppCompatActivity {
    String url = Urls.urlReadBazsazi;

    ProgressBar progressBar;
    TextView txvStartDate, txvEndDate, txvLocation, txvEqdamAnjamShodeh, txvMojri, txvHazineh, txvPishnahad;

    String id = "";
    String startDate = "";
    String endDate = "";
    String location = "";
    String eqdamAnjamShodeh = "";
    String mojri = "";
    String hazineh = "";
    String pishnahad = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_tab_bazsazi_va_nosazi);

        progressBar = findViewById(R.id.progressbarActivityShowDtilsTabBazsaziVaNoSazi);
        txvStartDate = findViewById(R.id.txv_activityShowDtilsTabBazsaziVaNoSazi_startDate);
        txvEndDate = findViewById(R.id.txv_activityShowDtilsTabBazsaziVaNoSazi_endDate);
        txvLocation = findViewById(R.id.txv_activityShowDtilsTabBazsaziVaNoSazi_location);
        txvEqdamAnjamShodeh = findViewById(R.id.txv_activityShowDtilsTabBazsaziVaNoSazi_eqdamatAnjamShodeh);
        txvMojri = findViewById(R.id.txv_activityShowDtilsTabBazsaziVaNoSazi_mojri);
        txvHazineh = findViewById(R.id.txv_activityShowDtilsTabBazsaziVaNoSazi_hazineh);
        txvPishnahad = findViewById(R.id.txv_activityShowDtilsTabBazsaziVaNoSazi_pishnahad);

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
                    startDate = jsonObject.getString("startDate");
                    endDate = jsonObject.getString("endDate");
                    location = jsonObject.getString("location");
                    eqdamAnjamShodeh = jsonObject.getString("eqdamAnjamShodeh");
                    mojri = jsonObject.getString("mojri");
                    hazineh = jsonObject.getString("hazineh");
                    pishnahad = jsonObject.getString("pishnahad");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                txvStartDate.setText(startDate);
                txvEndDate.setText(endDate);
                txvLocation.setText(location);
                txvEqdamAnjamShodeh.setText(eqdamAnjamShodeh);
                txvMojri.setText(mojri);
                txvHazineh.setText(hazineh);
                txvPishnahad.setText(pishnahad);
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