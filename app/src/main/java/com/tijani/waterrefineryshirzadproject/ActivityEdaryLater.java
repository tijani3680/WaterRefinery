package com.tijani.waterrefineryshirzadproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.tijani.waterrefineryshirzadproject.models.EdaryLaterProperties;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityEdaryLater extends AppCompatActivity  {
    TextView txvDate;
    EditText edtShomarehNameh, edtPeyvast, edtFrom, edtTo, edtSubject, edtDescribtion, edtSemat, edtName;
    Button btnSabt;
    Date myDate;
    String url = Urls.urlSabtEdaryLater;
    ProgressBar progressBar;

    String shomarehName = "";
    String peyvast = "";
    String from = "";
    String to = "";
    String subject = "";
    String describtion = "";
    String semat = "";
    String name = "";
    String date = "";

    String userName="";
    public static SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edary_later);
        txvDate = findViewById(R.id.txv_activity_edary_Date);
        edtShomarehNameh = findViewById(R.id.edt_activity_edary_shomarehNameh);
        edtPeyvast = findViewById(R.id.edt_activity_edary_peyvast);
        edtFrom = findViewById(R.id.edt_activity_edary_from);
        edtTo = findViewById(R.id.edt_activity_edary_to);
        edtSubject = findViewById(R.id.edt_activity_edary_subject);
        edtDescribtion = findViewById(R.id.edt_activity_edary_describtion);
        edtSemat = findViewById(R.id.edt_activity_edary_semat);
        edtName = findViewById(R.id.edt_activity_edary_name);
        btnSabt = findViewById(R.id.btn_activity_edary_sabt);
        progressBar = findViewById(R.id.progressbarActivityEdaryLater);

        preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
        userName = preferences.getString("userName", "");

        txvDate.setOnClickListener(new View.OnClickListener() {
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
                shomarehName = edtShomarehNameh.getText().toString();
                date = txvDate.getText().toString();
                peyvast = edtPeyvast.getText().toString();
                from = edtFrom.getText().toString();
                to = edtTo.getText().toString();
                subject = edtSubject.getText().toString();
                describtion = edtDescribtion.getText().toString();
                semat = edtSemat.getText().toString();
                name = edtName.getText().toString();

                if (shomarehName.isEmpty() || date.equals("تاریخ") || peyvast.isEmpty() || subject.isEmpty() || describtion.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    Toast.makeText(ActivityEdaryLater.this, "همه قسمت های نامه کامل وارد نشده است!", Toast.LENGTH_SHORT).show();
                } else {

                    sendLater();

                }
            }
        });
    }

    private void sendLater() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("exist")) {
                    Toast.makeText(ActivityEdaryLater.this, "این شماره نامه قبلا در سیستم ثبت شده است!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityEdaryLater.this, "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(G.context,MainActivity.class);
                    startActivity(intent);
                    finish();

                }


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
                params.put("shomarehName", shomarehName);
                params.put("date", date);
                params.put("peyvast", peyvast);
                params.put("from", from);
                params.put("to", to);
                params.put("subject", subject);
                params.put("describtion", describtion);
                params.put("semat", semat);
                params.put("name", name);
                params.put("userName", userName);
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