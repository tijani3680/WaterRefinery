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

public class ActivityQateeBarq extends AppCompatActivity {

    String url = Urls.urlSabtQateeBarq;

    ProgressBar progressBar;
    ImageView imgCalander;
    EditText edtEllateQatee,edtStartTime,edtEndTime,edtTimeKhamoshi,edtEqdamatAnjamShodeh,edtPishnahad;
    TextView txvDate;
    Button btnSabt;

    Date myDate;


    String date = "";
    String ellateQatee = "";
    String startTime = "";
    String endTime = "";
    String timeKhamoshi = "";
    String eqdamatAnjamShodeh = "";
    String pishnahad = "";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qatee_barq);
        progressBar=findViewById(R.id.progressbarActivityQateeBargh);
        imgCalander=findViewById(R.id.img_activityQateeBargh_calendar);
        txvDate=findViewById(R.id.txv_activityQateeBargh_date);
        edtEllateQatee=findViewById(R.id.edt_activityQateeBargh_ellateQatee);
        edtStartTime=findViewById(R.id.edt_activityQateeBargh_startTime);
        edtEndTime=findViewById(R.id.edt_activityQateeBargh_endTime);
        edtTimeKhamoshi=findViewById(R.id.edt_activityQateeBargh_timeKhamoshi);
        edtEqdamatAnjamShodeh=findViewById(R.id.edt_activityQateeBargh_eqdamateAnjamShodeh);
        edtPishnahad=findViewById(R.id.edt_activityQateeBargh_pishnahad);
        btnSabt=findViewById(R.id.btn_activityQateeBargh_sabt);

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
                ellateQatee=edtEllateQatee.getText().toString();
                startTime=edtStartTime.getText().toString();
                endTime=edtEndTime.getText().toString();
                timeKhamoshi=edtTimeKhamoshi.getText().toString();
                eqdamatAnjamShodeh=edtEqdamatAnjamShodeh.getText().toString();
                pishnahad=edtPishnahad.getText().toString();

                if (date.isEmpty()) {
                    Toast.makeText(G.context, "تاریخ انتخاب نشده است!", Toast.LENGTH_SHORT).show();
                } else{

                    if (ellateQatee.isEmpty()) {
                        ellateQatee = "_";
                    }
                    if (startTime.isEmpty()) {
                        startTime = "_";
                    }
                    if (endTime.isEmpty()) {
                        endTime = "_";
                    }
                    if (timeKhamoshi.isEmpty()) {
                        timeKhamoshi = "_";
                    }
                    if (eqdamatAnjamShodeh.isEmpty()) {
                        eqdamatAnjamShodeh = "_";
                    }
                    if (pishnahad.isEmpty()) {
                        pishnahad = "_";
                    }


                    sendData(date,ellateQatee,startTime,endTime,timeKhamoshi,eqdamatAnjamShodeh,pishnahad);


                }

            }
        });




    }



    public void sendData(final String date, final String ellateQatee, final String startTime, final String endTime, final String timeKhamoshi, final String eqdamatAnjamShodeh, final String pishnahad) {

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
                params.put("ellateQatee", ellateQatee);
                params.put("startTime", startTime);
                params.put("endTime", endTime);
                params.put("timeKhamoshi", timeKhamoshi);
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