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

public class ActivityShowDetilsGandZodaye extends AppCompatActivity {

    String url = Urls.urlReadGandzodaye;

    ProgressBar progressBar;
    TextView txvDate, txvColorTazriqi, txvDebiFazelab, txvColorBaghimandeh, txvKliformKol, txvKliformGarmapay, txvTimeQateTazrigh, txvEllateQateTazrigh, txvPeygiryHayeAnjamShodeh, txvShosteshoyeMakhzan;
    String id = "";
    String date = "";
    String colorTazriqi = "";
    String debiFazelab = "";
    String colorBaghimandeh = "";
    String kliformKol = "";
    String kliformGarmapay = "";
    String timeQateTazrigh = "";
    String ellateQateTazrigh = "";
    String peygiryHayeAnjamShodeh = "";
    String shosteshoyeMakhzan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detils_gand_zodaye);

        progressBar = findViewById(R.id.progressbarActivityShowDtilsGandZodaye);
        txvDate = findViewById(R.id.txv_activity_show_detils_GandZodaye_date);
        txvColorTazriqi = findViewById(R.id.txv_activity_show_detils_GandZodaye_colorTazriqi);
        txvDebiFazelab = findViewById(R.id.txv_activity_show_detils_GandZodaye_debiFazelab);
        txvColorBaghimandeh = findViewById(R.id.txv_activity_show_detils_GandZodaye_colorBaqimandeh);
        txvKliformKol = findViewById(R.id.txv_activity_show_detils_GandZodaye_kliformKol);
        txvKliformGarmapay = findViewById(R.id.txv_activity_show_detils_GandZodaye_kliformGarmapay);
        txvTimeQateTazrigh = findViewById(R.id.txv_activity_show_detils_GandZodaye_timeQateTazrigh);
        txvEllateQateTazrigh = findViewById(R.id.txv_activity_show_detils_GandZodaye_elleteQateTazrigh);
        txvPeygiryHayeAnjamShodeh = findViewById(R.id.txv_activity_show_detils_GandZodaye_peygiriHayeAnjamShodeh);
        txvShosteshoyeMakhzan = findViewById(R.id.txv_activity_show_detils_GandZodaye_shosteshoyeMakhzan);

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
                    colorTazriqi = jsonObject.getString("dozColorTazriqi");
                    debiFazelab = jsonObject.getString("debiFazelab");
                    colorBaghimandeh = jsonObject.getString("colorBaqimandeh");
                    kliformKol = jsonObject.getString("kliformKol");
                    kliformGarmapay = jsonObject.getString("kliformGarmapay");
                    timeQateTazrigh = jsonObject.getString("timeQateTazrigh");
                    ellateQateTazrigh = jsonObject.getString("ellateQateTazrigh");
                    peygiryHayeAnjamShodeh = jsonObject.getString("peygiriHayeAnjamShodeh");
                    shosteshoyeMakhzan = jsonObject.getString("shosteshoyeMakhzan");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                txvDate.setText(date);
                txvColorTazriqi.setText(colorTazriqi);
                txvDebiFazelab.setText(debiFazelab);
                txvColorBaghimandeh.setText(colorBaghimandeh);
                txvKliformKol.setText(kliformKol);
                txvKliformGarmapay.setText(kliformGarmapay);
                txvTimeQateTazrigh.setText(timeQateTazrigh);
                txvEllateQateTazrigh.setText(ellateQateTazrigh);
                txvPeygiryHayeAnjamShodeh.setText(peygiryHayeAnjamShodeh);
                txvShosteshoyeMakhzan.setText(shosteshoyeMakhzan);
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