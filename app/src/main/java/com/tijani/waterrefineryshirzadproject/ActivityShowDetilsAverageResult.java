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

public class ActivityShowDetilsAverageResult extends AppCompatActivity {
    String url = Urls.urlReadAverageResult;
    String id = "";

    ProgressBar progressBar;

    TextView txv_date, txv_debi_baravordi, txv_bod_vorodi, txv_bod_khoroji, txv_cod_vorodi, txv_cod_khoroji, txv_tss_vorodi, txv_tss_khoroji, txv_tkn_vorodi, txv_tkn_khoroji;
    TextView txv_tp_vorodi, txv_tp_khoroji, txv_mlss_havaDehi_1, txv_mlss_havaDehi_2, txv_mlss_havaDehi_3, txv_mlss_havaDehi_4, txv_mlss_havaDehi_5, txv_mlss_havaDehi_6;
    TextView txv_do_havaDehi_1, txv_do_havaDehi_2, txv_do_havaDehi_3, txv_do_havaDehi_4, txv_do_havaDehi_5, txv_do_havaDehi_6, txv_fikal_kliform, txv_kliform;

    String date = "";
    String debi_baravordi = "";
    String bod_vorodi = "";
    String bod_khoroji = "";
    String cod_vorodi = "";
    String cod_khoroji = "";
    String tss_vorodi = "";
    String tss_khoroji = "";
    String tkn_vorodi = "";
    String tkn_khoroji = "";
    String tp_vorodi = "";
    String tp_khoroji = "";
    String mlss_havaDehi_1 = "";
    String mlss_havaDehi_2 = "";
    String mlss_havaDehi_3 = "";
    String mlss_havaDehi_4 = "";
    String mlss_havaDehi_5 = "";
    String mlss_havaDehi_6 = "";
    String do_havaDehi_1 = "";
    String do_havaDehi_2 = "";
    String do_havaDehi_3 = "";
    String do_havaDehi_4 = "";
    String do_havaDehi_5 = "";
    String do_havaDehi_6 = "";
    String fikal_kliform = "";
    String kliform = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detils_average_result);

    findViews();


        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");


        getData();

    }

    private void findViews() {

        progressBar=findViewById(R.id.progressbarActivityShowDtilsAverageResult);
        txv_date=findViewById(R.id.txv_activity_show_detils_averageResult_date);
        txv_debi_baravordi=findViewById(R.id.txv_activity_show_detils_averageResult_debiBaravordi);
        txv_bod_vorodi=findViewById(R.id.txv_activity_show_detils_averageResult_bod_vorodi);
        txv_bod_khoroji=findViewById(R.id.txv_activity_show_detils_averageResult_bod_khoroji);
        txv_cod_vorodi=findViewById(R.id.txv_activity_show_detils_averageResult_cod_vorodi);
        txv_cod_khoroji=findViewById(R.id.txv_activity_show_detils_averageResult_cod_khoroji);
        txv_tss_vorodi=findViewById(R.id.txv_activity_show_detils_averageResult_tss_vorodi);
        txv_tss_khoroji=findViewById(R.id.txv_activity_show_detils_averageResult_tss_khoroji);
        txv_tkn_vorodi=findViewById(R.id.txv_activity_show_detils_averageResult_tkn_vorodi);
        txv_tkn_khoroji=findViewById(R.id.txv_activity_show_detils_averageResult_tkn_khoroji);
        txv_tp_vorodi=findViewById(R.id.txv_activity_show_detils_averageResult_tp_vorodi);
        txv_tp_khoroji=findViewById(R.id.txv_activity_show_detils_averageResult_tp_khoroji);
        txv_mlss_havaDehi_1=findViewById(R.id.txv_activity_show_detils_averageResult_mlss_h1);
        txv_mlss_havaDehi_2=findViewById(R.id.txv_activity_show_detils_averageResult_mlss_h2);
        txv_mlss_havaDehi_3=findViewById(R.id.txv_activity_show_detils_averageResult_mlss_h3);
        txv_mlss_havaDehi_4=findViewById(R.id.txv_activity_show_detils_averageResult_mlss_h4);
        txv_mlss_havaDehi_5=findViewById(R.id.txv_activity_show_detils_averageResult_mlss_h5);
        txv_mlss_havaDehi_6=findViewById(R.id.txv_activity_show_detils_averageResult_mlss_h6);
        txv_do_havaDehi_1=findViewById(R.id.txv_activity_show_detils_averageResult_do_h1);
        txv_do_havaDehi_2=findViewById(R.id.txv_activity_show_detils_averageResult_do_h2);
        txv_do_havaDehi_3=findViewById(R.id.txv_activity_show_detils_averageResult_do_h3);
        txv_do_havaDehi_4=findViewById(R.id.txv_activity_show_detils_averageResult_do_h4);
        txv_do_havaDehi_5=findViewById(R.id.txv_activity_show_detils_averageResult_do_h5);
        txv_do_havaDehi_6=findViewById(R.id.txv_activity_show_detils_averageResult_do_h6);
        txv_fikal_kliform=findViewById(R.id.txv_activity_show_detils_averageResult_fikal_kliform);
        txv_kliform=findViewById(R.id.txv_activity_show_detils_averageResult_kliform);
    }

    private void getData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    date = jsonObject.getString("date");
                    debi_baravordi = jsonObject.getString("debi_baravordi");
                    bod_vorodi = jsonObject.getString("bod_vorodi");
                    bod_khoroji = jsonObject.getString("bod_khoroji");
                    cod_vorodi = jsonObject.getString("cod_vorodi");
                    cod_khoroji = jsonObject.getString("cod_khoroji");
                    tss_vorodi = jsonObject.getString("tss_vorodi");
                    tss_khoroji = jsonObject.getString("tss_khoroji");
                    tkn_vorodi = jsonObject.getString("tkn_vorodi");
                    tkn_khoroji = jsonObject.getString("tkn_khoroji");
                    tp_vorodi = jsonObject.getString("tp_vorodi");
                    tp_khoroji = jsonObject.getString("tp_khoroji");
                    mlss_havaDehi_1 = jsonObject.getString("mlss_havaDehi_1");
                    mlss_havaDehi_2 = jsonObject.getString("mlss_havaDehi_2");
                    mlss_havaDehi_3 = jsonObject.getString("mlss_havaDehi_3");
                    mlss_havaDehi_4 = jsonObject.getString("mlss_havaDehi_4");
                    mlss_havaDehi_5 = jsonObject.getString("mlss_havaDehi_5");
                    mlss_havaDehi_6 = jsonObject.getString("mlss_havaDehi_6");
                    do_havaDehi_1 = jsonObject.getString("do_havaDehi_1");
                    do_havaDehi_2 = jsonObject.getString("do_havaDehi_2");
                    do_havaDehi_3 = jsonObject.getString("do_havaDehi_3");
                    do_havaDehi_4 = jsonObject.getString("do_havaDehi_4");
                    do_havaDehi_5 = jsonObject.getString("do_havaDehi_5");
                    do_havaDehi_6 = jsonObject.getString("do_havaDehi_6");
                    fikal_kliform = jsonObject.getString("fikal_kliform");
                    kliform = jsonObject.getString("kliform");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                txv_date.setText(date);
                txv_debi_baravordi.setText(debi_baravordi);
                txv_bod_vorodi.setText(bod_vorodi);
                txv_bod_khoroji.setText(bod_khoroji);
                txv_cod_vorodi.setText(cod_vorodi);
                txv_cod_khoroji.setText(cod_khoroji);
                txv_tss_vorodi.setText(tss_vorodi);
                txv_tss_khoroji.setText(tss_khoroji);
                txv_tkn_vorodi.setText(tkn_vorodi);
                txv_tkn_khoroji.setText(tkn_khoroji);
                txv_tp_vorodi.setText(tp_vorodi);
                txv_tp_khoroji.setText(tp_khoroji);
                txv_mlss_havaDehi_1.setText(mlss_havaDehi_1);
                txv_mlss_havaDehi_2.setText(mlss_havaDehi_2);
                txv_mlss_havaDehi_3.setText(mlss_havaDehi_3);
                txv_mlss_havaDehi_4.setText(mlss_havaDehi_4);
                txv_mlss_havaDehi_5.setText(mlss_havaDehi_5);
                txv_mlss_havaDehi_6.setText(mlss_havaDehi_6);
                txv_do_havaDehi_1.setText(do_havaDehi_1);
                txv_do_havaDehi_2.setText(do_havaDehi_2);
                txv_do_havaDehi_3.setText(do_havaDehi_3);
                txv_do_havaDehi_4.setText(do_havaDehi_4);
                txv_do_havaDehi_5.setText(do_havaDehi_5);
                txv_do_havaDehi_6.setText(do_havaDehi_6);
                txv_fikal_kliform.setText(fikal_kliform);
                txv_kliform.setText(kliform);

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