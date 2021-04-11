package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class ActivityShowDetilsEdaryLaters extends AppCompatActivity {
    TextView txvShomarehName, txvDate, txvPeyvast, txvFrom, txvTo, txvSubject, txvDscribtion, txvSemat, txvFullName;
    String id = "";
    String url = Urls.urlReadEdaryLater;
    ProgressBar progressBar;

    String shomarehNameh = "";
    String date = "";
    String peyvast = "";
    String fromeName = "";
    String toName = "";
    String subject = "";
    String describtion = "";
    String semat = "";
    String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detils_edary_laters);
        txvShomarehName = findViewById(R.id.txv_activity_show_detils_edary_shomarehNameh);
        txvDate = findViewById(R.id.txv_activity_show_detils_edary_date);
        txvPeyvast = findViewById(R.id.txv_activity_show_detils_edary_peyvast);
        txvFrom = findViewById(R.id.txv_activity_show_detils_edary_from);
        txvTo = findViewById(R.id.txv_activity_show_detils_edary_to);
        txvSubject = findViewById(R.id.txv_activity_show_detils_edary_subject);
        txvDscribtion = findViewById(R.id.txv_activity_show_detils_edary_describtion);
        txvSemat = findViewById(R.id.txv_activity_show_detils_edary_semat);
        txvFullName = findViewById(R.id.txv_activity_show_detils_edary_fullname);
        progressBar = findViewById(R.id.progressbarActivityShowDtilsEdaryLaters);


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
                    shomarehNameh = jsonObject.getString("number");
                    date = jsonObject.getString("date");
                    peyvast = jsonObject.getString("peyvast");
                    fromeName = jsonObject.getString("fromeName");
                    toName = jsonObject.getString("toName");
                    subject = jsonObject.getString("subject");
                    describtion = jsonObject.getString("describtion");
                    semat = jsonObject.getString("semat");
                    name = jsonObject.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                txvShomarehName.setText(shomarehNameh);
                txvDate.setText(date);
                txvPeyvast.setText(peyvast);
                txvFrom.setText(fromeName);
                txvTo.setText(toName);
                txvSubject.setText(subject);
                txvDscribtion.setText(describtion);
                txvSemat.setText(semat);
                txvFullName.setText(name);

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