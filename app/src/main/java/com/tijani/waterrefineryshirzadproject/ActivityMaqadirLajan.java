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

public class ActivityMaqadirLajan extends AppCompatActivity {
    String url = Urls.urlSabtMaqadirLajan;
    ProgressBar progressBar;
    ImageView imgCalendar;
    TextView txvDate;
    EditText edt_lajanBargashti_meqdar, edt_lajanBargashti_qelzat, edt_lajanMazad_meqdar, edt_lajanMazad_qelzat, edt_lajanVorodi_meqdar, edt_lajanVorodi_qelzat, edt_lajanKhoroji_meqdar, edt_lajanKhoroji_qelzat, edt_meqdarAshqalJamAvarishodeh,edt_meqdarDanehJamAvarishodeh;
    Button btnSabt;

    Date myDate;


    String date = "";
    String lajanBargashti_meqdar = "";
    String lajanBargashti_qelza = "";
    String lajanMazad_meqdar = "";
    String lajanMazad_qelzat = "";
    String lajanVorodi_meqdar = "";
    String lajanVorodi_qelzat = "";
    String lajanKhoroji_meqdar = "";
    String lajanKhoroji_qelzat = "";
    String meqdarAshqalJamAvarishodeh = "";
    String meqdarDanehJamAvarishodeh = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maqadir_lajan);

        progressBar=findViewById(R.id.progressbarActivityMaqadirLajan);
        imgCalendar=findViewById(R.id.img_activityMaqadirLajan_calendar);
        txvDate=findViewById(R.id.txv_activityMaqadirLajan_date);

        edt_lajanBargashti_meqdar=findViewById(R.id.edt_activityMaqadirLajan_lajanBargashti_meqdar);
        edt_lajanBargashti_qelzat=findViewById(R.id.edt_activityMaqadirLajan_lajanBargashti_qelzat);
        edt_lajanMazad_meqdar=findViewById(R.id.edt_activityMaqadirLajan_lajanMazadDafee_meqdar);
        edt_lajanMazad_qelzat=findViewById(R.id.edt_activityMaqadirLajan_lajanMazadDafee_qelzat);
        edt_lajanVorodi_meqdar=findViewById(R.id.edt_activityMaqadirLajan_lajanVorodiBeVahedAbgiri_meqdar);
        edt_lajanVorodi_qelzat=findViewById(R.id.edt_activityMaqadirLajan_lajanVorodiBeVahedAbgiri_qelzat);
        edt_lajanKhoroji_meqdar=findViewById(R.id.edt_activityMaqadirLajan_lajanKhorojiAzTasfieKhaneh_meqdar);
        edt_lajanKhoroji_qelzat=findViewById(R.id.edt_activityMaqadirLajan_lajanKhorojiAzTasfieKhaneh_qelzat);
        edt_meqdarAshqalJamAvarishodeh=findViewById(R.id.edt_activityMaqadirLajan_ashqalJamAvariShodeh);
        edt_meqdarDanehJamAvarishodeh=findViewById(R.id.edt_activityMaqadirLajan_danehJamAvariShodeh);

        btnSabt=findViewById(R.id.btn_activityMaqadirLajan_sabt);


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
                date=txvDate.getText().toString();
                lajanBargashti_meqdar=edt_lajanBargashti_meqdar.getText().toString();
                lajanBargashti_qelza=edt_lajanBargashti_qelzat.getText().toString();
                lajanMazad_meqdar=edt_lajanMazad_meqdar.getText().toString();
                lajanMazad_qelzat=edt_lajanMazad_qelzat.getText().toString();
                lajanVorodi_meqdar=edt_lajanVorodi_meqdar.getText().toString();
                lajanVorodi_qelzat=edt_lajanVorodi_qelzat.getText().toString();
                lajanKhoroji_meqdar=edt_lajanKhoroji_meqdar.getText().toString();
                lajanKhoroji_qelzat=edt_lajanKhoroji_qelzat.getText().toString();
                meqdarAshqalJamAvarishodeh=edt_meqdarAshqalJamAvarishodeh.getText().toString();
                meqdarDanehJamAvarishodeh=edt_meqdarDanehJamAvarishodeh.getText().toString();

                if (date.isEmpty()) {
                    Toast.makeText(ActivityMaqadirLajan.this, "تاریخ انتخاب نشده است!", Toast.LENGTH_SHORT).show();
                } else{

                    if (lajanBargashti_meqdar.isEmpty()) {
                        lajanBargashti_meqdar = "_";
                    }

                    if (lajanBargashti_qelza.isEmpty()) {
                        lajanBargashti_qelza = "_";
                    }

                    if (lajanMazad_meqdar.isEmpty()) {
                        lajanMazad_meqdar = "_";
                    }

                    if (lajanMazad_qelzat.isEmpty()) {
                        lajanMazad_qelzat = "_";
                    }

                    if (lajanVorodi_meqdar.isEmpty()) {
                        lajanVorodi_meqdar = "_";
                    }

                    if (lajanVorodi_qelzat.isEmpty()) {
                        lajanVorodi_qelzat = "_";
                    }

                    if (lajanKhoroji_meqdar.isEmpty()) {
                        lajanKhoroji_meqdar = "_";
                    }

                    if (lajanKhoroji_qelzat.isEmpty()) {
                        lajanKhoroji_qelzat = "_";
                    }

                    if (meqdarAshqalJamAvarishodeh.isEmpty()) {
                        meqdarAshqalJamAvarishodeh = "_";
                    }

                    if (meqdarDanehJamAvarishodeh.isEmpty()) {
                        meqdarDanehJamAvarishodeh = "_";
                    }

                    sendData(date,lajanBargashti_meqdar,lajanBargashti_qelza,lajanMazad_meqdar,lajanMazad_qelzat,lajanVorodi_meqdar,lajanVorodi_qelzat,lajanKhoroji_meqdar,lajanKhoroji_qelzat,meqdarAshqalJamAvarishodeh,meqdarDanehJamAvarishodeh);






                }








            }
        });




    }


    public void sendData(final String date, final String lajanBargashti_meqdar, final String lajanBargashti_qelza, final String lajanMazad_meqdar, final String lajanMazad_qelzat, final String lajanVorodi_meqdar, final String lajanVorodi_qelzat, final String lajanKhoroji_meqdar, final String lajanKhoroji_qelzat, final String meqdarAshqalJamAvarishodeh , final String meqdarDanehJamAvarishodeh) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivityMaqadirLajan.this, response.trim().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(G.context, ActivityHosnBahrehBardary.class);
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
                params.put("lajanBargashti_meqdar", lajanBargashti_meqdar);
                params.put("lajanBargashti_qelza", lajanBargashti_qelza);
                params.put("lajanMazad_meqdar", lajanMazad_meqdar);
                params.put("lajanMazad_qelzat", lajanMazad_qelzat);
                params.put("lajanVorodi_meqdar", lajanVorodi_meqdar);
                params.put("lajanVorodi_qelzat", lajanVorodi_qelzat);
                params.put("lajanKhoroji_meqdar", lajanKhoroji_meqdar);
                params.put("lajanKhoroji_qelzat", lajanKhoroji_qelzat);
                params.put("meqdarAshqalJamAvarishodeh", meqdarAshqalJamAvarishodeh);
                params.put("meqdarDanehJamAvarishodeh", meqdarDanehJamAvarishodeh);
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