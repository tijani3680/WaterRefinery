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

public class ActivityTajhizatMotavaqef extends AppCompatActivity {

    String url = Urls.urlSabtTajhizatMotavaqef;

    ProgressBar progressBar;
    ImageView imgCalander,imgStartDateTavaqof,imgEndDateTavaqof;
    EditText edtTjhizName,edtLocationEsteqrar,edtMaadool,edtEllateTavaqof,edtEqdamatAnjamShodeh,edtPishnahad;
    TextView txvDate,txvStartDate,txvEndDate;
    Button btnSabt;

    Date myDate;
    Date myDate2;
    Date myDate3;


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
        setContentView(R.layout.activity_tajhizat_motavaqef);

        progressBar=findViewById(R.id.progressbarActivityTajhizatMotavaqef);
        imgCalander=findViewById(R.id.img_activityTajhizatMotavaqef_calendar);
        imgStartDateTavaqof=findViewById(R.id.img_activityTajhizatMotavaqef_dateStartTavaqof);
        imgEndDateTavaqof=findViewById(R.id.img_activityTajhizatMotavaqef_dateEndTavaqof);

        txvDate=findViewById(R.id.txv_activityTajhizatMotavaqef_date);
        txvStartDate=findViewById(R.id.txv_activityTajhizatMotavaqef_dateStartTavaqof);
        txvEndDate=findViewById(R.id.txv_activityTajhizatMotavaqef_dateEndTavaqof);
        edtTjhizName=findViewById(R.id.edt_activityTajhizatMotavaqef_tajhizName);
        edtLocationEsteqrar=findViewById(R.id.edt_activityTajhizatMotavaqef_locationEsteqrar);
        edtMaadool=findViewById(R.id.edt_activityTajhizatMotavaqef_maadool);
        edtEllateTavaqof=findViewById(R.id.edt_activityTajhizatMotavaqef_ellateTavaqof);
        edtEqdamatAnjamShodeh=findViewById(R.id.edt_activityTajhizatMotavaqef_eqdamateAnjamShodeh);
        edtPishnahad=findViewById(R.id.edt_activityTajhizatMotavaqef_pishnahad);
        btnSabt=findViewById(R.id.btn_activityTajhizatMotavaqef_sabt);


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

        imgStartDateTavaqof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePicker.Builder builder = new DatePicker.Builder();
                builder.theme(R.style.DialogTheme);
                myDate2 = new Date();
                builder.date(myDate2.getDay(), myDate2.getMonth(), myDate2.getYear());
                builder.build(new DateSetListener() {
                    @Override
                    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
                        myDate2.setDate(day, month, year);
                        txvStartDate.setText(myDate2.getDate());

                    }
                }).show(getSupportFragmentManager(), "");

            }
        });

        imgEndDateTavaqof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker.Builder builder = new DatePicker.Builder();
                builder.theme(R.style.DialogTheme);
                myDate3 = new Date();
                builder.date(myDate3.getDay(), myDate3.getMonth(), myDate3.getYear());
                builder.build(new DateSetListener() {
                    @Override
                    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
                        myDate3.setDate(day, month, year);
                        txvEndDate.setText(myDate3.getDate());

                    }
                }).show(getSupportFragmentManager(), "");
            }
        });


        btnSabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=txvDate.getText().toString();
                startDate=txvStartDate.getText().toString();
                endDate=txvEndDate.getText().toString();
                tjhizName=edtTjhizName.getText().toString();
                locationEsteqrar=edtLocationEsteqrar.getText().toString();
                maadool=edtMaadool.getText().toString();
                ellateTavaqof=edtEllateTavaqof.getText().toString();
                eqdamatAnjamShodeh=edtEqdamatAnjamShodeh.getText().toString();
                pishnahad=edtPishnahad.getText().toString();


                if (date.isEmpty()) {
                    Toast.makeText(G.context, "تاریخ انتخاب نشده است!", Toast.LENGTH_SHORT).show();
                } else{


                    if (tjhizName.isEmpty()) {
                        tjhizName = "_";
                    }
                    if (locationEsteqrar.isEmpty()) {
                        locationEsteqrar = "_";
                    }
                    if (maadool.isEmpty()) {
                        maadool = "_";
                    }
                    if (startDate.isEmpty()) {
                        startDate = "_";
                    }
                    if (endDate.isEmpty()) {
                        endDate = "_";
                    }
                    if (ellateTavaqof.isEmpty()) {
                        ellateTavaqof = "_";
                    }
                    if (eqdamatAnjamShodeh.isEmpty()) {
                        eqdamatAnjamShodeh = "_";
                    }
                    if (pishnahad.isEmpty()) {
                        pishnahad = "_";
                    }

                    sendData(date,startDate,endDate,tjhizName,locationEsteqrar,maadool,ellateTavaqof,eqdamatAnjamShodeh,pishnahad);
                }

            }
        });




    }


    public void sendData(final String date, final String startDate , final String endDate, final String tjhizName, final String locationEsteqrar, final String maadool, final String ellateTavaqof, final String eqdamatAnjamShodeh,final String pishnahad) {

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
                params.put("startDate", startDate);
                params.put("endDate", endDate);
                params.put("tjhizName", tjhizName);
                params.put("locationEsteqrar", locationEsteqrar);
                params.put("maadool", maadool);
                params.put("ellateTavaqof", ellateTavaqof);
                params.put("eqdamatAnjamShodeh", eqdamatAnjamShodeh);
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