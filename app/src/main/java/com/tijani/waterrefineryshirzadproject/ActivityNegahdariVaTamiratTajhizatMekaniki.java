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

public class ActivityNegahdariVaTamiratTajhizatMekaniki extends AppCompatActivity {

    String url = Urls.urlSabtNegahdariVaTamiratTajhizatMekaniki;

    ProgressBar progressBar;
    ImageView imgCalander;
    TextView txvDate;
    Button btnSabt;

    EditText edtNameTajhiz, edtLocationEsteqrar, edtMadool, edtEeybeVaEllateEybe, edtTypeActivity, edtDescribtionActivity, edtQeteMasrafi, edtNumberQeteMasrafi, edtAfradYaSherekatAnjamDahandeh, edtNumberAfrad, edtPriceQate, edtPriceNiroyeEnsani, edtPriceTamirKharejAzKargah;

    Date myDate;

    String date = "";
    String nameTajhiz = "";
    String locationEsteqrar = "";
    String madool = "";
    String eeybeVaEllateEybe = "";
    String typeActivity = "";
    String describtionActivity = "";
    String qeteMasrafi = "";
    String numberQeteMasrafi = "";
    String afradYaSherekatAnjamDahandeh = "";
    String numberAfrad = "";
    String priceQate = "";
    String priceNiroyeEnsani = "";
    String priceTamirKharejAzKargah = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_negahdari_va_tamirat_tajhizat_mekaniki);

        progressBar = findViewById(R.id.progressbarActivityNegahdariVaTamirTajhizatMekaniki);
        imgCalander = findViewById(R.id.img_activityNegahdariVaTamirTajhizatMekaniki_calendar);
        txvDate = findViewById(R.id.txv_activityNegahdariVaTamirTajhizatMekaniki_date);
        btnSabt = findViewById(R.id.btn_activityNegahdariVaTamirTajhizatMekaniki_sabt);
        edtNameTajhiz = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_nameTajhiz);
        edtLocationEsteqrar = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_locationEsteqrar);
        edtMadool = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_maadool);
        edtEeybeVaEllateEybe = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_eeybVaEllateEeybe);
        edtTypeActivity = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_typeActivity);
        edtDescribtionActivity = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_describtionActivity);
        edtQeteMasrafi = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_qateeMasrafi);
        edtNumberQeteMasrafi = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_numberQateeMasrafi);
        edtAfradYaSherekatAnjamDahandeh = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_afradYaSherekatAnjamDahandeh);
        edtNumberAfrad = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_numberNafarat);
        edtPriceQate = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_priceQatee);
        edtPriceNiroyeEnsani = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_priceNiroyeEnsani);
        edtPriceTamirKharejAzKargah = findViewById(R.id.edt_activityNegahdariVaTamirTajhizatMekaniki_priceTamirKharejAzKargah);

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

                date = txvDate.getText().toString();
                nameTajhiz = edtNameTajhiz.getText().toString();
                locationEsteqrar = edtLocationEsteqrar.getText().toString();
                madool = edtMadool.getText().toString();
                eeybeVaEllateEybe = edtEeybeVaEllateEybe.getText().toString();
                typeActivity = edtTypeActivity.getText().toString();
                describtionActivity = edtDescribtionActivity.getText().toString();
                qeteMasrafi = edtQeteMasrafi.getText().toString();
                numberQeteMasrafi=edtNumberQeteMasrafi.getText().toString();
                afradYaSherekatAnjamDahandeh=edtAfradYaSherekatAnjamDahandeh.getText().toString();
                numberAfrad=edtNumberAfrad.getText().toString();
                priceQate=edtPriceQate.getText().toString();
                priceNiroyeEnsani=edtPriceNiroyeEnsani.getText().toString();
                priceTamirKharejAzKargah=edtPriceTamirKharejAzKargah.getText().toString();


                if (date.isEmpty()) {
                    Toast.makeText(ActivityNegahdariVaTamiratTajhizatMekaniki.this, "تاریخ انتخاب نشده است!", Toast.LENGTH_SHORT).show();
                } else{

                    if (nameTajhiz.isEmpty()) {
                        nameTajhiz = "_";
                    }
                    if (locationEsteqrar.isEmpty()) {
                        locationEsteqrar = "_";
                    }
                    if (madool.isEmpty()) {
                        madool = "_";
                    }
                    if (eeybeVaEllateEybe.isEmpty()) {
                        eeybeVaEllateEybe = "_";
                    }
                    if (typeActivity.isEmpty()) {
                        typeActivity = "_";
                    }
                    if (describtionActivity.isEmpty()) {
                        describtionActivity = "_";
                    }
                    if (qeteMasrafi.isEmpty()) {
                        qeteMasrafi = "_";
                    }
                    if (numberQeteMasrafi.isEmpty()) {
                        numberQeteMasrafi = "_";
                    }
                    if (afradYaSherekatAnjamDahandeh.isEmpty()) {
                        afradYaSherekatAnjamDahandeh = "_";
                    }
                    if (numberAfrad.isEmpty()) {
                        numberAfrad = "_";
                    }
                    if (priceQate.isEmpty()) {
                        priceQate = "_";
                    }
                    if (priceNiroyeEnsani.isEmpty()) {
                        priceNiroyeEnsani = "_";
                    }
                    if (priceTamirKharejAzKargah.isEmpty()) {
                        priceTamirKharejAzKargah = "_";
                    }


                    sendData(date,nameTajhiz,locationEsteqrar,madool,eeybeVaEllateEybe,typeActivity,describtionActivity,qeteMasrafi,numberQeteMasrafi,afradYaSherekatAnjamDahandeh,numberAfrad,priceQate,priceNiroyeEnsani,priceTamirKharejAzKargah);

                }


            }
        });




    }
    public void sendData(final String date, final String nameTajhiz, final String locationEsteqrar, final String madool, final String eeybeVaEllateEybe, final String typeActivity, final String describtionActivity, final String qeteMasrafi, final String numberQeteMasrafi, final String afradYaSherekatAnjamDahandeh, final String numberAfrad, final String priceQate, final String priceNiroyeEnsani, final String priceTamirKharejAzKargah) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivityNegahdariVaTamiratTajhizatMekaniki.this, response.trim().toString(), Toast.LENGTH_SHORT).show();
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
                params.put("nameTajhiz", nameTajhiz);
                params.put("locationEsteqrar", locationEsteqrar);
                params.put("madool", madool);
                params.put("eeybeVaEllateEybe", eeybeVaEllateEybe);
                params.put("typeActivity", typeActivity);
                params.put("describtionActivity", describtionActivity);
                params.put("qeteMasrafi", qeteMasrafi);
                params.put("numberQeteMasrafi", numberQeteMasrafi);
                params.put("afradYaSherekatAnjamDahandeh", afradYaSherekatAnjamDahandeh);
                params.put("numberAfrad", numberAfrad);
                params.put("priceQate", priceQate);
                params.put("priceNiroyeEnsani", priceNiroyeEnsani);
                params.put("priceTamirKharejAzKargah", priceTamirKharejAzKargah);
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