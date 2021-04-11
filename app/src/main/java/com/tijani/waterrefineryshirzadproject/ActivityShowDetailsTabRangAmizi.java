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

public class ActivityShowDetailsTabRangAmizi extends AppCompatActivity {

    String url = Urls.urlReadRangAmizi;

    ProgressBar progressBar;
    TextView txvStartDate, txvEndDate, txvQesmatRangamiziShodeh, txvTypeColor, txvRangMasrafi, txvHazineh, txvPishnahad;

    String id = "";
    String startDate = "";
    String endDate = "";
    String qesmatRangamiziShodeh = "";
    String typeColor = "";
    String rangMasrafi = "";
    String hazineh = "";
    String pishnahad = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_tab_rang_amizi);
        progressBar = findViewById(R.id.progressbarActivityShowDtilsTabRangAmizi);
        txvStartDate = findViewById(R.id.txv_activityShowDtilsTabRangAmizi_startDate);
        txvEndDate = findViewById(R.id.txv_activityShowDtilsTabRangAmizi_endDate);
        txvQesmatRangamiziShodeh = findViewById(R.id.txv_activityShowDtilsTabRangAmizi_qesmatRangAmiziShodeh);
        txvTypeColor = findViewById(R.id.txv_activityShowDtilsTabRangAmizi_typeColor);
        txvRangMasrafi = findViewById(R.id.txv_activityShowDtilsTabRangAmizi_mizanRangMasrafi);
        txvHazineh = findViewById(R.id.txv_activityShowDtilsTabRangAmizi_hazinehRangAmizi);
        txvPishnahad = findViewById(R.id.txv_activityShowDtilsTabRangAmizi_pishnahad);


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
                    qesmatRangamiziShodeh = jsonObject.getString("qesmatRangamiziShodeh");
                    typeColor = jsonObject.getString("typeColor");
                    rangMasrafi = jsonObject.getString("rangMasrafi");
                    hazineh = jsonObject.getString("hazineh");
                    pishnahad = jsonObject.getString("pishnahad");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                txvStartDate.setText(startDate);
                txvEndDate.setText(endDate);
                txvQesmatRangamiziShodeh.setText(qesmatRangamiziShodeh);
                txvTypeColor.setText(typeColor);
                txvRangMasrafi.setText(rangMasrafi);
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