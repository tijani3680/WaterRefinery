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

public class ActivityRangAmizi extends AppCompatActivity {

    String url = Urls.urlSabtRangAmizi;

    ProgressBar progressBar;

    ImageView imgStartDate,imgEndDate;
    TextView txvStartDate,txvEndDate;
    EditText edtQesmatRangamiziShodeh,edtTypeColor,edtRangMasrafi,edtHazineh,edtPishnahad;
    Button btnSabt;

    Date myDate;
    Date myDate2;

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
        setContentView(R.layout.activity_rang_amizi);
        progressBar=findViewById(R.id.progressbarActivityRangAmizi);
        imgStartDate=findViewById(R.id.img_activityRangAmizi_startDate);
        imgEndDate=findViewById(R.id.img_activityRangAmizi_endDate);
        txvStartDate=findViewById(R.id.txv_activityRangAmizi_startDate);
        txvEndDate=findViewById(R.id.txv_activityRangAmizi_endDate);
        edtQesmatRangamiziShodeh =findViewById(R.id.edt_activityRangAmizi_vahedRangAmiziShodeh);
        edtTypeColor=findViewById(R.id.edt_activityRangAmizi_typeColor);
        edtRangMasrafi=findViewById(R.id.edt_activityRangAmizi_mizanRangMasrafi);
        edtHazineh=findViewById(R.id.edt_activityRangAmizi_hazinehRangAmizi);
        edtPishnahad=findViewById(R.id.edt_activityRangAmizi_pishnahad);
        btnSabt=findViewById(R.id.btn_activityRangAmizi_sabt);

        imgStartDate.setOnClickListener(new View.OnClickListener() {
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
                        txvStartDate.setText(myDate.getDate());

                    }
                }).show(getSupportFragmentManager(), "");
            }
        });


        imgEndDate.setOnClickListener(new View.OnClickListener() {
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
                        txvEndDate.setText(myDate2.getDate());

                    }
                }).show(getSupportFragmentManager(), "");

            }
        });


        btnSabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDate=txvStartDate.getText().toString();
                endDate=txvEndDate.getText().toString();
                qesmatRangamiziShodeh=edtQesmatRangamiziShodeh.getText().toString();
                typeColor=edtTypeColor.getText().toString();
                rangMasrafi=edtRangMasrafi.getText().toString();
                hazineh=edtHazineh.getText().toString();
                pishnahad=edtPishnahad.getText().toString();


                if (startDate.isEmpty()) {
                    Toast.makeText(G.context, "تاریخ شروع رنگ آمیزی مشخص نشده است!", Toast.LENGTH_SHORT).show();
                } else if (endDate.isEmpty()){

                    Toast.makeText(G.context, "تاریخ پایان رنگ آمیزی مشخص نشده است!", Toast.LENGTH_SHORT).show();
                }else {

                    if (qesmatRangamiziShodeh.isEmpty()) {
                        qesmatRangamiziShodeh = "_";
                    }
                    if (typeColor.isEmpty()) {
                        typeColor = "_";
                    }
                    if (rangMasrafi.isEmpty()) {
                        rangMasrafi = "_";
                    }
                    if (hazineh.isEmpty()) {
                        hazineh = "_";
                    }
                    if (pishnahad.isEmpty()) {
                        pishnahad = "_";
                    }

                    sendData(startDate,endDate,qesmatRangamiziShodeh,typeColor,rangMasrafi,hazineh,pishnahad);


                }

            }
        });





    }




    public void sendData(final String startDate, final String endDate, final String qesmatRangamiziShodeh, final String typeColor, final String rangMasrafi, final String hazineh, final String pishnahad) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(G.context, response.trim().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(G.context, ActivityBazsaziVaRangAmizi.class);
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

                params.put("startDate", startDate);
                params.put("endDate", endDate);
                params.put("qesmatRangamiziShodeh", qesmatRangamiziShodeh);
                params.put("typeColor", typeColor);
                params.put("rangMasrafi", rangMasrafi);
                params.put("hazineh", hazineh);
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