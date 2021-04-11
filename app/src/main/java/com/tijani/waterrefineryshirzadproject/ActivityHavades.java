package com.tijani.waterrefineryshirzadproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alirezaafkar.sundatepicker.DatePicker;
import com.alirezaafkar.sundatepicker.interfaces.DateSetListener;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tijani.waterrefineryshirzadproject.models.Date;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityHavades extends AppCompatActivity {

    String url = Urls.urlSabtHavades;

    ProgressBar progressBar;
    ImageView imgCalander;
    EditText edtLocationVoqoeHadeseh,edtDescribtionHadeseh,edtEllateHadeseh,edtEqdamateAnjamShodeh,edtKhesarateJani,edtKhesarateMali,edtPishnahad;
    TextView txvDate;
    Button btnSabt;

    Date myDate;


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
        setContentView(R.layout.activity_havades);

        progressBar=findViewById(R.id.progressbarActivityHavades);
        edtLocationVoqoeHadeseh=findViewById(R.id.edt_activityHavades_locationVoqoeHadeseh);
        edtDescribtionHadeseh=findViewById(R.id.edt_activityHavades_describtionHadeseh);
        edtEllateHadeseh=findViewById(R.id.edt_activityHavades_ellateVoqoeHadeseh);
        edtEqdamateAnjamShodeh=findViewById(R.id.edt_activityHavades_eqdamateAnjamShodeh);
        edtKhesarateJani=findViewById(R.id.edt_activityHavades_khesarateJani);
        edtKhesarateMali=findViewById(R.id.edt_activityHavades_khesarateMali);
        edtPishnahad=findViewById(R.id.edt_activityHavades_pishnahad);

        imgCalander=findViewById(R.id.img_activityHavades_calendar);
        txvDate=findViewById(R.id.txv_activityHavades_date);
        btnSabt=findViewById(R.id.btn_activityHavades_sabt);


        imgCalander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker.Builder builder = new DatePicker.Builder();
                builder.theme(R.style.DialogTheme);
                myDate = new Date();
                builder.date(myDate.getDay(), myDate.getMonth(), myDate.getYear());
                builder.build(new DateSetListener() {
                    @Override
                    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
                        myDate.setDate(day, month, year);
                        txvDate.setText(myDate.getDate());

                    }
                }).show(getSupportFragmentManager(), "");
            }
        });

        btnSabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=txvDate.getText().toString();
                locationVoqoeHadeseh=edtLocationVoqoeHadeseh.getText().toString();
                describtionHadeseh=edtDescribtionHadeseh.getText().toString();
                ellateHadeseh=edtEllateHadeseh.getText().toString();
                eqdamateAnjamShodeh=edtEqdamateAnjamShodeh.getText().toString();
                khesarateJani=edtKhesarateJani.getText().toString();
                khesarateMali=edtKhesarateMali.getText().toString();
                pishnahad=edtPishnahad.getText().toString();

                if (date.isEmpty()) {
                    Toast.makeText(G.context, "تاریخ انتخاب نشده است!", Toast.LENGTH_SHORT).show();
                } else{

                    if (locationVoqoeHadeseh.isEmpty()) {
                        locationVoqoeHadeseh = "_";
                    }

                    if (describtionHadeseh.isEmpty()) {
                        describtionHadeseh = "_";
                    }

                    if (ellateHadeseh.isEmpty()) {
                        ellateHadeseh = "_";
                    }
                    if (eqdamateAnjamShodeh.isEmpty()) {
                        eqdamateAnjamShodeh = "_";
                    }
                    if (khesarateJani.isEmpty()) {
                        khesarateJani = "_";
                    }
                    if (khesarateMali.isEmpty()) {
                        khesarateMali = "_";
                    }
                    if (pishnahad.isEmpty()) {
                        pishnahad = "_";
                    }

                    sendData(date,locationVoqoeHadeseh,describtionHadeseh,ellateHadeseh,eqdamateAnjamShodeh,khesarateJani,khesarateMali,pishnahad);
                }

            }
        });

    }


    public void sendData(final String date, final String locationVoqoeHadeseh, final String describtionHadeseh, final String ellateHadeseh, final String eqdamateAnjamShodeh, final String khesarateJani, final String khesarateMali, final String pishnahad) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(G.context, response.trim().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(G.context, ActivityHavadesVaQateeBarq.class);
                startActivity(intent);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            Map<String, String> params = new HashMap<>();

            @Override
            public Map<String, String> getParams() {


                params.put("date", date);
                params.put("locationVoqoeHadeseh", locationVoqoeHadeseh);
                params.put("describtionHadeseh", describtionHadeseh);
                params.put("ellateHadeseh", ellateHadeseh);
                params.put("eqdamateAnjamShodeh", eqdamateAnjamShodeh);
                params.put("khesarateJani", khesarateJani);
                params.put("khesarateMali", khesarateMali);
                params.put("pishnahad", pishnahad);

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