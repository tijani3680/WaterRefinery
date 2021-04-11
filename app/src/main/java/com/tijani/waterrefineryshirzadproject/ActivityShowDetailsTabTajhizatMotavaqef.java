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

public class ActivityShowDetailsTabTajhizatMotavaqef extends AppCompatActivity {

    String url = Urls.urlReadTajhizatMotavaqef;

    ProgressBar progressBar;
    TextView txvDate, txvStartDate, txvEndDate, txvTjhizName, txvLocationEsteqrar,txvMaadool , txvEllateTavaqof, txvEqdamatAnjamShodeh,txvPishnahad;

    String id = "";
    String date = "";
    String startDate = "";
    String endDate = "";
    String tjhizName = "";
    String locationEsteqrar = "";
    String maadool = "";
    String ellateTavaqof = "";
    String eqdamatAnjamShodeh = "";
    String pishnahad = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_tab_tajhizat_motavaqef);
        progressBar=findViewById(R.id.progressbarActivityShowDtilsTabTajhizatMotavaqef);
        txvDate=findViewById(R.id.txv_activityShowDtilsTabTajhizatMotavaqef_date);
        txvStartDate=findViewById(R.id.txv_activityShowDtilsTabTajhizatMotavaqef_startTime);
        txvEndDate=findViewById(R.id.txv_activityShowDtilsTabTajhizatMotavaqef_endTime);
        txvTjhizName=findViewById(R.id.txv_activityShowDtilsTabTajhizatMotavaqef_tajhizName);
        txvLocationEsteqrar=findViewById(R.id.txv_activityShowDtilsTabTajhizatMotavaqef_locationEsteqrar);
        txvMaadool=findViewById(R.id.txv_activityShowDtilsTabTajhizatMotavaqef_maadool);
        txvEllateTavaqof=findViewById(R.id.txv_activityShowDtilsTabTajhizatMotavaqef_ellateTavaqof);
        txvEqdamatAnjamShodeh=findViewById(R.id.txv_activityShowDtilsTabTajhizatMotavaqef_eqdamatAnjamShodeh);
        txvPishnahad=findViewById(R.id.txv_activityShowDtilsTabTajhizatMotavaqef_pishnahad);

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
                    startDate = jsonObject.getString("startDate");
                    endDate = jsonObject.getString("endDate");
                    tjhizName = jsonObject.getString("tjhizName");
                    ellateTavaqof = jsonObject.getString("ellateTavaqof");
                    locationEsteqrar = jsonObject.getString("locationEsteqrar");
                    maadool = jsonObject.getString("maadool");
                    eqdamatAnjamShodeh = jsonObject.getString("eqdamatAnjamShodeh");
                    pishnahad = jsonObject.getString("pishnahad");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                txvDate.setText(date);
                txvStartDate.setText(startDate);
                txvEndDate.setText(endDate);
                txvTjhizName.setText(tjhizName);
                txvLocationEsteqrar.setText(locationEsteqrar);
                txvMaadool.setText(maadool);
                txvEllateTavaqof.setText(ellateTavaqof);
                txvEqdamatAnjamShodeh.setText(eqdamatAnjamShodeh);
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