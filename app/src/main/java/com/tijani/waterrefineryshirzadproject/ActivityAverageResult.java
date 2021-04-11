package com.tijani.waterrefineryshirzadproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
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

public class ActivityAverageResult extends AppCompatActivity {

    String url = Urls.urlSabtAverageResult;


    ProgressBar progressBar;
    ImageView imgCalendar;
    TextView txvDate;
    EditText edt_debi_baravordi, edt_bod_vorodi, edt_bod_khoroji, edt_cod_vorodi, edt_cod_khoroji, edt_tss_vorodi, edt_tss_khoroji, edt_tkn_vorodi, edt_tkn_khoroji;
    EditText edt_tp_vorodi, edt_tp_khoroji, edt_mlss_havaDehi_1, edt_mlss_havaDehi_2, edt_mlss_havaDehi_3, edt_mlss_havaDehi_4, edt_mlss_havaDehi_5, edt_mlss_havaDehi_6;
    EditText edt_do_havaDehi_1, edt_do_havaDehi_2, edt_do_havaDehi_3, edt_do_havaDehi_4, edt_do_havaDehi_5, edt_do_havaDehi_6, edt_fikal_kliform, edt_kliform;
    Button btnSabt;

    Date myDate;


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
        setContentView(R.layout.activity_average_result);
        findViews();

        imgCalendar.setOnClickListener(new View.OnClickListener() {
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

                date = txvDate.getText().toString();
                debi_baravordi = edt_debi_baravordi.getText().toString();
                bod_vorodi = edt_bod_vorodi.getText().toString();
                bod_khoroji = edt_bod_khoroji.getText().toString();
                cod_vorodi = edt_cod_vorodi.getText().toString();
                cod_khoroji = edt_cod_khoroji.getText().toString();
                tss_vorodi = edt_tss_vorodi.getText().toString();
                tss_khoroji = edt_tss_khoroji.getText().toString();
                tkn_vorodi = edt_tkn_vorodi.getText().toString();
                tkn_khoroji = edt_tkn_khoroji.getText().toString();
                tp_vorodi = edt_tp_vorodi.getText().toString();
                tp_khoroji = edt_tp_khoroji.getText().toString();
                mlss_havaDehi_1 = edt_mlss_havaDehi_1.getText().toString();
                mlss_havaDehi_2 = edt_mlss_havaDehi_2.getText().toString();
                mlss_havaDehi_3 = edt_mlss_havaDehi_3.getText().toString();
                mlss_havaDehi_4 = edt_mlss_havaDehi_4.getText().toString();
                mlss_havaDehi_5 = edt_mlss_havaDehi_5.getText().toString();
                mlss_havaDehi_6 = edt_mlss_havaDehi_6.getText().toString();
                do_havaDehi_1 = edt_do_havaDehi_1.getText().toString();
                do_havaDehi_2 = edt_do_havaDehi_2.getText().toString();
                do_havaDehi_3 = edt_do_havaDehi_3.getText().toString();
                do_havaDehi_4 = edt_do_havaDehi_4.getText().toString();
                do_havaDehi_5 = edt_do_havaDehi_5.getText().toString();
                do_havaDehi_6 = edt_do_havaDehi_6.getText().toString();
                fikal_kliform = edt_fikal_kliform.getText().toString();
                kliform = edt_kliform.getText().toString();

                if (date.isEmpty()) {
                    Toast.makeText(ActivityAverageResult.this, "تاریخ انتخاب نشده است!", Toast.LENGTH_SHORT).show();
                } else {

                    if (debi_baravordi.isEmpty()) {
                        debi_baravordi = "_";
                    }
                    if (bod_vorodi.isEmpty()) {
                        bod_vorodi = "_";
                    }
                    if (bod_khoroji.isEmpty()) {
                        bod_khoroji = "_";
                    }
                    if (cod_vorodi.isEmpty()) {
                        cod_vorodi = "_";
                    }
                    if (cod_khoroji.isEmpty()) {
                        cod_khoroji = "_";
                    }
                    if (tss_vorodi.isEmpty()) {
                        tss_vorodi = "_";
                    }
                    if (tss_khoroji.isEmpty()) {
                        tss_khoroji = "_";
                    }
                    if (tkn_vorodi.isEmpty()) {
                        tkn_vorodi = "_";
                    }
                    if (tkn_khoroji.isEmpty()) {
                        tkn_khoroji = "_";
                    }
                    if (tp_vorodi.isEmpty()) {
                        tp_vorodi = "_";
                    }
                    if (tp_khoroji.isEmpty()) {
                        tp_khoroji = "_";
                    }
                    if (mlss_havaDehi_1.isEmpty()) {
                        mlss_havaDehi_1 = "_";
                    }
                    if (mlss_havaDehi_2.isEmpty()) {
                        mlss_havaDehi_2 = "_";
                    }
                    if (mlss_havaDehi_3.isEmpty()) {
                        mlss_havaDehi_3 = "_";
                    }
                    if (mlss_havaDehi_4.isEmpty()) {
                        mlss_havaDehi_4 = "_";
                    }
                    if (mlss_havaDehi_5.isEmpty()) {
                        mlss_havaDehi_5 = "_";
                    }
                    if (mlss_havaDehi_6.isEmpty()) {
                        mlss_havaDehi_6 = "_";
                    }
                    if (do_havaDehi_1.isEmpty()) {
                        do_havaDehi_1 = "_";
                    }
                    if (do_havaDehi_2.isEmpty()) {
                        do_havaDehi_2 = "_";
                    }
                    if (do_havaDehi_3.isEmpty()) {
                        do_havaDehi_3 = "_";
                    }
                    if (do_havaDehi_4.isEmpty()) {
                        do_havaDehi_4 = "_";
                    }
                    if (do_havaDehi_5.isEmpty()) {
                        do_havaDehi_5 = "_";
                    }
                    if (do_havaDehi_6.isEmpty()) {
                        do_havaDehi_6 = "_";
                    }
                    if (fikal_kliform.isEmpty()) {
                        fikal_kliform = "_";
                    }
                    if (kliform.isEmpty()) {
                        kliform = "_";
                    }

                    sendData(date,debi_baravordi,bod_vorodi,bod_khoroji,cod_vorodi,cod_khoroji,tss_vorodi,tss_khoroji,tkn_vorodi,tkn_khoroji,tp_vorodi,tp_khoroji,mlss_havaDehi_1,mlss_havaDehi_2,mlss_havaDehi_3,mlss_havaDehi_4,mlss_havaDehi_5,mlss_havaDehi_6,do_havaDehi_1,do_havaDehi_2,do_havaDehi_3,do_havaDehi_4,do_havaDehi_5,do_havaDehi_6,fikal_kliform,kliform);


                }


            }
        });
    }

    private void findViews() {

        progressBar = findViewById(R.id.progressbarActivityAverageResult);
        imgCalendar = findViewById(R.id.img_activityAverageResult_calendar);
        txvDate = findViewById(R.id.txv_activityAverageResult_date);

        edt_debi_baravordi = findViewById(R.id.edt_activityAverageResult_debi_baravordi);
        edt_bod_vorodi = findViewById(R.id.edt_activityAverageResult_bod_vorodi);
        edt_bod_khoroji = findViewById(R.id.edt_activityAverageResult_bod_khoroji);
        edt_cod_vorodi = findViewById(R.id.edt_activityAverageResult_cod_vorodi);
        edt_cod_khoroji = findViewById(R.id.edt_activityAverageResult_cod_khoroji);
        edt_tss_vorodi = findViewById(R.id.edt_activityAverageResult_tss_vorodi);
        edt_tss_khoroji = findViewById(R.id.edt_activityAverageResult_tss_khoroji);
        edt_tkn_vorodi = findViewById(R.id.edt_activityAverageResult_tkn_vorodi);
        edt_tkn_khoroji = findViewById(R.id.edt_activityAverageResult_tkn_khoroji);
        edt_tp_vorodi = findViewById(R.id.edt_activityAverageResult_tp_vorodi);
        edt_tp_khoroji = findViewById(R.id.edt_activityAverageResult_tp_khoroji);
        edt_mlss_havaDehi_1 = findViewById(R.id.edt_activityAverageResult_mlss_havaDehi_1);
        edt_mlss_havaDehi_2 = findViewById(R.id.edt_activityAverageResult_mlss_havaDehi_2);
        edt_mlss_havaDehi_3 = findViewById(R.id.edt_activityAverageResult_mlss_havaDehi_3);
        edt_mlss_havaDehi_4 = findViewById(R.id.edt_activityAverageResult_mlss_havaDehi_4);
        edt_mlss_havaDehi_5 = findViewById(R.id.edt_activityAverageResult_mlss_havaDehi_5);
        edt_mlss_havaDehi_6 = findViewById(R.id.edt_activityAverageResult_mlss_havaDehi_6);
        edt_do_havaDehi_1 = findViewById(R.id.edt_activityAverageResult_do_havaDehi_1);
        edt_do_havaDehi_2 = findViewById(R.id.edt_activityAverageResult_do_havaDehi_2);
        edt_do_havaDehi_3 = findViewById(R.id.edt_activityAverageResult_do_havaDehi_3);
        edt_do_havaDehi_4 = findViewById(R.id.edt_activityAverageResult_do_havaDehi_4);
        edt_do_havaDehi_5 = findViewById(R.id.edt_activityAverageResult_do_havaDehi_5);
        edt_do_havaDehi_6 = findViewById(R.id.edt_activityAverageResult_do_havaDehi_6);
        edt_fikal_kliform = findViewById(R.id.edt_activityAverageResult_fikal_kliform);
        edt_kliform = findViewById(R.id.edt_activityAverageResult_kliform);
        btnSabt = findViewById(R.id.btn_activity_averageResult_sabt);
    }

    public void sendData(final String date, final String debi_baravordi, final String bod_vorodi, final String bod_khoroji, final String cod_vorodi, final String cod_khoroji, final String tss_vorodi, final String tss_khoroji,
                         final String tkn_vorodi, final String tkn_khoroji, final String tp_vorodi, final String tp_khoroji, final String mlss_havaDehi_1, final String mlss_havaDehi_2, final String mlss_havaDehi_3,
                         final String mlss_havaDehi_4, final String mlss_havaDehi_5, final String mlss_havaDehi_6, final String do_havaDehi_1, final String do_havaDehi_2, final String do_havaDehi_3, final String do_havaDehi_4,
                         final String do_havaDehi_5, final String do_havaDehi_6, final String fikal_kliform, final String kliform) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivityAverageResult.this, response.trim().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(G.context, ActivityHosnBahrehBardary.class);
                startActivity(intent);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ActivityAverageResult.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            Map<String, String> params = new HashMap<>();

            @Override
            public Map<String, String> getParams() {

                params.put("date", date);
                params.put("debi_baravordi", debi_baravordi);
                params.put("bod_vorodi", bod_vorodi);
                params.put("bod_khoroji", bod_khoroji);
                params.put("cod_vorodi", cod_vorodi);
                params.put("cod_khoroji", cod_khoroji);
                params.put("tss_vorodi", tss_vorodi);
                params.put("tss_khoroji", tss_khoroji);
                params.put("tkn_vorodi", tkn_vorodi);
                params.put("tkn_khoroji", tkn_khoroji);
                params.put("tp_vorodi", tp_vorodi);
                params.put("tp_khoroji", tp_khoroji);
                params.put("mlss_havaDehi_1", mlss_havaDehi_1);
                params.put("mlss_havaDehi_2", mlss_havaDehi_2);
                params.put("mlss_havaDehi_3", mlss_havaDehi_3);
                params.put("mlss_havaDehi_4", mlss_havaDehi_4);
                params.put("mlss_havaDehi_5", mlss_havaDehi_5);
                params.put("mlss_havaDehi_6", mlss_havaDehi_6);
                params.put("do_havaDehi_1", do_havaDehi_1);
                params.put("do_havaDehi_2", do_havaDehi_2);
                params.put("do_havaDehi_3", do_havaDehi_3);
                params.put("do_havaDehi_4", do_havaDehi_4);
                params.put("do_havaDehi_5", do_havaDehi_5);
                params.put("do_havaDehi_6", do_havaDehi_6);
                params.put("fikal_kliform", fikal_kliform);
                params.put("kliform", kliform);

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