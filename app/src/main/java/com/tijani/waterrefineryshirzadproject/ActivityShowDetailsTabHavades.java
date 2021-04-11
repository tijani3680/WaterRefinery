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

public class ActivityShowDetailsTabHavades extends AppCompatActivity {

    String url = Urls.urlReadHavades;

    ProgressBar progressBar;
    TextView txvDate, txvLocationVoqoeHadeseh, txvDescribtionHadeseh, txvEllateHadeseh, txvEqdamatAnjamShodeh, txvKhesaratJani, txvKhesaratMali, txvPishnahad;

    String id = "";
    String date = "";
    String locationVoqoeHadeseh = "";
    String describtionHadeseh = "";
    String ellateHadeseh = "";
    String eqdamateAnjamShodeh = "";
    String khesarateJani = "";
    String khesarateMali = "";
    String pishnahad = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_tab_havades);

        progressBar=findViewById(R.id.progressbarActivityShowDtilsTabHavades);
        txvDate=findViewById(R.id.txv_activity_show_detils_tabHavades_date);
        txvLocationVoqoeHadeseh=findViewById(R.id.txv_activity_show_detils_tabHavades_locationHadeseh);
        txvDescribtionHadeseh=findViewById(R.id.txv_activity_show_detils_tabHavades_describtionHadeseh);
        txvEllateHadeseh=findViewById(R.id.txv_activity_show_detils_tabHavades_ellateHadeseh);
        txvEqdamatAnjamShodeh=findViewById(R.id.txv_activity_show_detils_tabHavades_eqdamateAnjamShodeh);
        txvKhesaratJani=findViewById(R.id.txv_activity_show_detils_tabHavades_khesaratJani);
        txvKhesaratMali=findViewById(R.id.txv_activity_show_detils_tabHavades_khesaratMali);
        txvPishnahad=findViewById(R.id.txv_activity_show_detils_tabHavades_pishnahad);


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
                    date = jsonObject.getString("date");
                    locationVoqoeHadeseh = jsonObject.getString("locationVoqoeHadeseh");
                    describtionHadeseh = jsonObject.getString("describtionHadeseh");
                    ellateHadeseh = jsonObject.getString("ellateHadeseh");
                    eqdamateAnjamShodeh = jsonObject.getString("eqdamateAnjamShodeh");
                    khesarateJani = jsonObject.getString("khesarateJani");
                    khesarateMali = jsonObject.getString("khesarateMali");
                    pishnahad = jsonObject.getString("pishnahad");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                txvDate.setText(date);
                txvLocationVoqoeHadeseh.setText(locationVoqoeHadeseh);
                txvDescribtionHadeseh.setText(describtionHadeseh);
                txvEllateHadeseh.setText(ellateHadeseh);
                txvEqdamatAnjamShodeh.setText(eqdamateAnjamShodeh);
                txvKhesaratJani.setText(khesarateJani);
                txvKhesaratMali.setText(khesarateMali);
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