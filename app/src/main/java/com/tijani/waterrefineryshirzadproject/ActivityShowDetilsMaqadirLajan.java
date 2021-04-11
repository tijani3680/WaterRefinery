package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class ActivityShowDetilsMaqadirLajan extends AppCompatActivity {
    String url = Urls.urlReadMaqadirLajan;
    String id = "";

    ProgressBar progressBar;
    TextView txv_date, txv_lajanBargashtiMeqdar, txv_lajanBargashtiQelzat, txv_lajanMazadDafeeMeqdar, txv_lajanMazadDafeeQelzat, txv_lajanVorodiMeqdar, txv_lajanVorodiQelzat, txv_lajanKhorojiMeqdar, txv_lajanKhorojiQelzat, txv_ashqalJamAvariShodeh, txv_danehJamAvariShodeh;


    String date = "";
    String lajanBargashtiMeqdar = "";
    String lajanBargashtiQelzat = "";
    String lajanMazadDafeeMeqdar = "";
    String lajanMazadDafeeQelzat = "";
    String lajanVorodiMeqdar = "";
    String lajanVorodiQelzat = "";
    String lajanKhorojiMeqdar = "";
    String lajanKhorojiQelzat = "";
    String ashqalJamAvariShodeh = "";
    String danehJamAvariShodeh = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detils_maqadir_lajan);

        progressBar = findViewById(R.id.progressbarActivityShowDtilsMaqadirLajan);
        txv_date = findViewById(R.id.txv_activity_show_detils_maqadirLajan_date);
        txv_lajanBargashtiMeqdar = findViewById(R.id.txv_activity_show_detils_maqadirLajan_lajanBargashti_meqdar);
        txv_lajanBargashtiQelzat = findViewById(R.id.txv_activity_show_detils_maqadirLajan_lajanBargashti_qelzat);
        txv_lajanMazadDafeeMeqdar = findViewById(R.id.txv_activity_show_detils_maqadirLajan_lajanMazadDafee_meqdar);
        txv_lajanMazadDafeeQelzat = findViewById(R.id.txv_activity_show_detils_maqadirLajan_lajanMazadDafee_qelzat);
        txv_lajanVorodiMeqdar = findViewById(R.id.txv_activity_show_detils_maqadirLajan_lajanVorodi_meqdar);
        txv_lajanVorodiQelzat = findViewById(R.id.txv_activity_show_detils_maqadirLajan_lajanVorodi_qelzat);
        txv_lajanKhorojiMeqdar = findViewById(R.id.txv_activity_show_detils_maqadirLajan_lajanKhoroji_meqdar);
        txv_lajanKhorojiQelzat = findViewById(R.id.txv_activity_show_detils_maqadirLajan_lajanKhoroji_qelzat);
        txv_ashqalJamAvariShodeh = findViewById(R.id.txv_activity_show_detils_maqadirLajan_ashqalJamAvariShodeh);
        txv_danehJamAvariShodeh = findViewById(R.id.txv_activity_show_detils_maqadirLajan_danehJamAvariShodeh);

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
                    lajanBargashtiMeqdar = jsonObject.getString("lajanBargashti_meqdar");
                    lajanBargashtiQelzat = jsonObject.getString("lajanBargashti_qelza");
                    lajanMazadDafeeMeqdar = jsonObject.getString("lajanMazad_meqdar");
                    lajanMazadDafeeQelzat = jsonObject.getString("lajanMazad_qelzat");
                    lajanVorodiMeqdar = jsonObject.getString("lajanVorodi_meqdar");
                    lajanVorodiQelzat = jsonObject.getString("lajanVorodi_qelzat");
                    lajanKhorojiMeqdar = jsonObject.getString("lajanKhoroji_meqdar");
                    lajanKhorojiQelzat = jsonObject.getString("lajanKhoroji_qelzat");
                    ashqalJamAvariShodeh = jsonObject.getString("meqdarAshqalJamAvarishodeh");
                    danehJamAvariShodeh = jsonObject.getString("meqdarDanehJamAvarishodeh");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                txv_date.setText(date);
                txv_lajanBargashtiMeqdar.setText(lajanBargashtiMeqdar);
                txv_lajanBargashtiQelzat.setText(lajanBargashtiQelzat);
                txv_lajanMazadDafeeMeqdar.setText(lajanMazadDafeeMeqdar);
                txv_lajanMazadDafeeQelzat.setText(lajanMazadDafeeQelzat);
                txv_lajanVorodiMeqdar.setText(lajanVorodiMeqdar);
                txv_lajanVorodiQelzat.setText(lajanVorodiQelzat);
                txv_lajanKhorojiMeqdar.setText(lajanKhorojiMeqdar);
                txv_lajanKhorojiQelzat.setText(lajanKhorojiQelzat);
                txv_ashqalJamAvariShodeh.setText(ashqalJamAvariShodeh);
                txv_danehJamAvariShodeh.setText(danehJamAvariShodeh);
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