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

public class ActivityShowDetilsNegahdariVaTamirTajhizatMekaniki extends AppCompatActivity {

    String url = Urls.urlReadNegahdariVaTamirTajhizatMekaniki;

    ProgressBar progressBar;
    TextView txvDate, txvNameTajhiz, txvlocationEsteqrar, txvMadool, txveeybeVaEllateEybe, txvTypeActivity, txvDescribtionActivity, txvQeteMasrafi, txvNumberQeteMasrafi, txvAfradYaSherekatAnjamDahandeh, txvNumberAfrad, txvPriceQete, txvPriceNiroyeEnsani, txvPriceTamirKharejAzKargah;
    String id = "";
    String date = "";
    String nameTajhiz = "";
    String locationEsteqrar = "";
    String madool = "";
    String eeybeVaEllateEybe = "";
    String typeActivity = "";
    String describtionActivity = "";
    String qeteMasrafi = "";
    String numberQeteMasrafi = "";
    String afradYaSherekatAnjamDahandeh = "";
    String numberAfrad = "";
    String priceQate = "";
    String priceNiroyeEnsani = "";
    String priceTamirKharejAzKargah = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detils_negahdari_va_tamir_tajhizat_mekaniki);
        progressBar = findViewById(R.id.progressbarActivityShowDtilsNegahdariVaTamirTajhizatMekaniki);
        txvDate = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_date);
        txvNameTajhiz = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_nameTajhiz);
        txvlocationEsteqrar = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_locationEsteqrar);
        txvMadool = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_madool);
        txveeybeVaEllateEybe = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_eeybeVaEllateEybe);
        txvTypeActivity = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_typeActivity);
        txvDescribtionActivity = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_describtionActivity);
        txvQeteMasrafi = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_qeteMasrafi);
        txvNumberQeteMasrafi = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_numberQeteMasrafi);
        txvAfradYaSherekatAnjamDahandeh = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_afradYaSherekatAnjamDahandeh);
        txvNumberAfrad = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_numberAfrad);
        txvPriceQete = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_priceQete);
        txvPriceNiroyeEnsani = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_priceNiroyeEnsani);
        txvPriceTamirKharejAzKargah = findViewById(R.id.txv_activity_show_detils_negahdariVaTamirTajhizatMekaniki_priceTamirKharejAzKargah);


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
                    nameTajhiz = jsonObject.getString("nameTajhiz");
                    locationEsteqrar = jsonObject.getString("locationEsteqrar");
                    madool = jsonObject.getString("madool");
                    eeybeVaEllateEybe = jsonObject.getString("eeybeVaEllateEybe");
                    typeActivity = jsonObject.getString("typeActivity");
                    describtionActivity = jsonObject.getString("describtionActivity");
                    qeteMasrafi = jsonObject.getString("qeteMasrafi");
                    numberQeteMasrafi = jsonObject.getString("numberQeteMasrafi");
                    afradYaSherekatAnjamDahandeh = jsonObject.getString("afradYaSherekatAnjamDahandeh");
                    numberAfrad = jsonObject.getString("numberAfrad");
                    priceQate = jsonObject.getString("priceQate");
                    priceNiroyeEnsani = jsonObject.getString("priceNiroyeEnsani");
                    priceTamirKharejAzKargah = jsonObject.getString("priceTamirKharejAzKargah");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                txvDate.setText(date);
                txvNameTajhiz.setText(nameTajhiz);
                txvlocationEsteqrar.setText(locationEsteqrar);
                txvMadool.setText(madool);
                txveeybeVaEllateEybe.setText(eeybeVaEllateEybe);
                txvTypeActivity.setText(typeActivity);
                txvDescribtionActivity.setText(describtionActivity);
                txvQeteMasrafi.setText(qeteMasrafi);
                txvNumberQeteMasrafi.setText(numberQeteMasrafi);
                txvAfradYaSherekatAnjamDahandeh.setText(afradYaSherekatAnjamDahandeh);
                txvNumberAfrad.setText(numberAfrad);
                txvPriceQete.setText(priceQate);
                txvPriceNiroyeEnsani.setText(priceNiroyeEnsani);
                txvPriceTamirKharejAzKargah.setText(priceTamirKharejAzKargah);
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