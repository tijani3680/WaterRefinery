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

public class ActivityGozareshat extends AppCompatActivity {

    String url = Urls.urlSabtGozaresh;

    ProgressBar progressBar;
    ImageView imgCalander;
    EditText edtParametrType, edtNemonehLocation, edtNemonehNumber, edtMeasuredValue, edtAllowedAmount, edtDescribtion;
    TextView txvDate;
    Button btnSend;

    Date myDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gozareshat);
        progressBar=findViewById(R.id.progressbarActivityGozareshat);
        imgCalander = findViewById(R.id.img_activityGozareshat_calendar);
        txvDate = findViewById(R.id.txv_activityGozareshat_date);
        edtParametrType = findViewById(R.id.edt_activity_gozareshat_parametrType);
        edtNemonehLocation = findViewById(R.id.edt_activity_gozareshat_nemonehLocation);
        edtNemonehNumber = findViewById(R.id.edt_activity_gozareshat_nemonehNumber);
        edtMeasuredValue = findViewById(R.id.edt_activity_gozareshat_measuredValue);
        edtAllowedAmount = findViewById(R.id.edt_activity_gozareshat_allowedAmount);
        edtDescribtion = findViewById(R.id.edt_activity_gozareshat_describtion);
        btnSend = findViewById(R.id.btn_activity_gozareshat_sabt);

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


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date =txvDate.getText().toString();
                String parametrType = edtParametrType.getText().toString();
                String nemonehLocation =edtNemonehLocation.getText().toString();
                String nemonehNumber = edtNemonehNumber.getText().toString();
                String measuredValue = edtMeasuredValue.getText().toString();
                String allowedAmount = edtAllowedAmount.getText().toString();
                String describtion = edtDescribtion.getText().toString();
                if (date.isEmpty() || parametrType.isEmpty() || nemonehLocation.isEmpty() || nemonehNumber.isEmpty() || measuredValue.isEmpty() || allowedAmount.isEmpty() || describtion.isEmpty()){
                    Toast.makeText(G.context, "فیلد های مربوطه وارد نشده است!", Toast.LENGTH_SHORT).show();

                }else {
                    sendData(date,parametrType,nemonehLocation,nemonehNumber,measuredValue,allowedAmount,describtion);
                }
            }
        });


    }

    public void sendData(final String  date, final String parametrType, final String nemonehLocation, final String nemonehNumber, final String measuredValue, final String allowedAmount, final String describtion) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response.trim().equals("ok")){
                    Toast.makeText(G.context, "اطلاعات با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(G.context, MainActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(G.context, "متاسفانه ارتباط با سرور برقرار نشد!", Toast.LENGTH_SHORT).show();

                }



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
                params.put("parametrType", parametrType);
                params.put("nemonehLocation", nemonehLocation);
                params.put("nemonehNumber", nemonehNumber);
                params.put("measuredValue", measuredValue);
                params.put("allowedAmount", allowedAmount);
                params.put("describtion", describtion);
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