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
import com.squareup.picasso.Picasso;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityShowDetilsGozareshat extends AppCompatActivity {
    ProgressBar progressBar;
    TextView txvDate, txvNemonehLocation, txvNemonehNumber, txvParametrType, txvMeasuredValue, txvAllowedAmount, txvDescribtion;

    String url = Urls.urlReadGozaresh;
    String id = "";
    String date = "";
    String nemonehLocation = "";
    String nemonehNumber = "";
    String parametrType = "";
    String measuredValue = "";
    String allowedAmount = "";
    String describtion = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detils_gozareshat);
        progressBar = findViewById(R.id.progressbarActivityShowDtilsGozareshat);
        txvDate = findViewById(R.id.txv_activity_show_detils_gozareshat_date);
        txvNemonehLocation = findViewById(R.id.txv_activity_show_detils_gozareshat_nemonehLocation);
        txvParametrType = findViewById(R.id.txv_activity_show_detils_gozareshat_parametrType);
        txvMeasuredValue = findViewById(R.id.txv_activity_show_detils_gozareshat_measuredValue);
        txvAllowedAmount = findViewById(R.id.txv_activity_show_detils_gozareshat_allowedAmount);
        txvDescribtion = findViewById(R.id.txv_activity_show_detils_gozareshat_describtion);
        txvNemonehNumber = findViewById(R.id.txv_activity_show_detils_gozareshat_nemonehNumber);

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
                    parametrType = jsonObject.getString("parametrType");
                    nemonehLocation = jsonObject.getString("nemonehLocation");
                    nemonehNumber = jsonObject.getString("nemonehNumber");
                    measuredValue = jsonObject.getString("measuredValue");
                    allowedAmount = jsonObject.getString("allowedAmount");
                    describtion = jsonObject.getString("describtion");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                txvDate.setText(date);
                txvNemonehLocation.setText(nemonehLocation);
                txvNemonehNumber.setText(nemonehNumber);
                txvParametrType.setText(parametrType);
                txvMeasuredValue.setText(measuredValue);
                txvAllowedAmount.setText(allowedAmount);
                txvDescribtion.setText(describtion);

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