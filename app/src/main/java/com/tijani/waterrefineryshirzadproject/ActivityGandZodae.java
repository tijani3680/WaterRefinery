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

public class ActivityGandZodae extends AppCompatActivity {

    String url = Urls.urlSabtGandzodaye;

    ProgressBar progressBar;
    ImageView imgCalander;
    EditText edtDozColorTazriqi,edtDebiFazelab,edtColorBaqimandeh,edtKliformKol,edtKliformGarmapay,edtTimeQateTazrigh,edtEllateQateTazrigh,edtPeygiriHayeAnjamShodeh,edtShosteshoyeMakhzan;
    TextView txvDate;
    Button btnSabt;

    Date myDate;


    String date = "";
    String dozColorTazriqi = "";
    String debiFazelab = "";
    String colorBaqimandeh = "";
    String kliformKol = "";
    String kliformGarmapay = "";
    String timeQateTazrigh = "";
    String ellateQateTazrigh = "";
    String peygiriHayeAnjamShodeh = "";
    String shosteshoyeMakhzan = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gand_zodae);
        progressBar=findViewById(R.id.progressbarActivityGandZodaee);
        imgCalander=findViewById(R.id.img_activityGandZodaee_calendar);
        txvDate=findViewById(R.id.txv_activityGandZodaee_date);
        edtDozColorTazriqi=findViewById(R.id.edt_activityGandZodaee_dozColorTazriqi);
        edtDebiFazelab=findViewById(R.id.edt_activityGandZodaee_debiFazelab);
        edtColorBaqimandeh=findViewById(R.id.edt_activityGandZodaee_colorBaqimandeh);
        edtKliformKol=findViewById(R.id.edt_activityGandZodaee_kliformKol);
        edtKliformGarmapay=findViewById(R.id.edt_activityGandZodaee_kliformGarmapay);
        edtTimeQateTazrigh=findViewById(R.id.edt_activityGandZodaee_timeQateTazrigh);
        edtEllateQateTazrigh=findViewById(R.id.edt_activityGandZodaee_ellateQateTazrigh);
        edtPeygiriHayeAnjamShodeh=findViewById(R.id.edt_activityGandZodaee_peygiryhayeAnjamShodeh);
        edtShosteshoyeMakhzan=findViewById(R.id.edt_activityGandZodaee_shosteshoyeMakhzan);
        btnSabt=findViewById(R.id.btn_activityGandZodaee_sabt);

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
                dozColorTazriqi=edtDozColorTazriqi.getText().toString();
                debiFazelab=edtDebiFazelab.getText().toString();
                colorBaqimandeh=edtColorBaqimandeh.getText().toString();
                kliformKol=edtKliformKol.getText().toString();
                kliformGarmapay=edtKliformGarmapay.getText().toString();
                timeQateTazrigh=edtTimeQateTazrigh.getText().toString();
                ellateQateTazrigh=edtEllateQateTazrigh.getText().toString();
                peygiriHayeAnjamShodeh=edtPeygiriHayeAnjamShodeh.getText().toString();
                shosteshoyeMakhzan=edtShosteshoyeMakhzan.getText().toString();


                if (date.isEmpty()) {
                    Toast.makeText(ActivityGandZodae.this, "تاریخ انتخاب نشده است!", Toast.LENGTH_SHORT).show();
                } else{

                    if (dozColorTazriqi.isEmpty()) {
                        dozColorTazriqi = "_";
                    }

                    if (debiFazelab.isEmpty()) {
                        debiFazelab = "_";
                    }

                    if (colorBaqimandeh.isEmpty()) {
                        colorBaqimandeh = "_";
                    }

                    if (kliformKol.isEmpty()) {
                        kliformKol = "_";
                    }

                    if (kliformGarmapay.isEmpty()) {
                        kliformGarmapay = "_";
                    }

                    if (timeQateTazrigh.isEmpty()) {
                        timeQateTazrigh = "_";
                    }

                    if (ellateQateTazrigh.isEmpty()) {
                        ellateQateTazrigh = "_";
                    }

                    if (peygiriHayeAnjamShodeh.isEmpty()) {
                        peygiriHayeAnjamShodeh = "_";
                    }

                    if (shosteshoyeMakhzan.isEmpty()) {
                        shosteshoyeMakhzan = "_";
                    }

                    sendData(date,dozColorTazriqi,debiFazelab,colorBaqimandeh,kliformKol,kliformGarmapay,timeQateTazrigh,ellateQateTazrigh,peygiriHayeAnjamShodeh,shosteshoyeMakhzan);



                }




            }
        });
    }


    public void sendData(final String date, final String dozColorTazriqi, final String debiFazelab, final String colorBaqimandeh, final String kliformKol, final String kliformGarmapay, final String timeQateTazrigh, final String ellateQateTazrigh, final String peygiriHayeAnjamShodeh, final String shosteshoyeMakhzan) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivityGandZodae.this, response.trim().toString(), Toast.LENGTH_SHORT).show();
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
                params.put("dozColorTazriqi", dozColorTazriqi);
                params.put("debiFazelab", debiFazelab);
                params.put("colorBaqimandeh", colorBaqimandeh);
                params.put("kliformKol", kliformKol);
                params.put("kliformGarmapay", kliformGarmapay);
                params.put("timeQateTazrigh", timeQateTazrigh);
                params.put("ellateQateTazrigh", ellateQateTazrigh);
                params.put("peygiriHayeAnjamShodeh", peygiriHayeAnjamShodeh);
                params.put("shosteshoyeMakhzan", shosteshoyeMakhzan);
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