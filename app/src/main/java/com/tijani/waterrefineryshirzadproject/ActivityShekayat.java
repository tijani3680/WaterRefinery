package com.tijani.waterrefineryshirzadproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityShekayat extends AppCompatActivity {
    EditText edtOfficeName, edtManagerName, edtProductType, edtAddress, edtPhone, edtSabtKonandehFullName, edtSubject, edtDescribtion;
    TextView txvDate;
    ImageView imgCalendar, imgPicGallery;
    Button btnSelectPic, btnSend;
    ProgressBar progressBar;
    final int REQUEST_SELECT_PIC = 100;
    Bitmap bitmap;
    Date myDate;
    String url = Urls.urlSabtShekayat;
    boolean emptyImagView=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shekayat);
        progressBar = findViewById(R.id.progressbarActivityShekayat);
        edtOfficeName = findViewById(R.id.edt_activity_shekayat_officeName);
        edtManagerName = findViewById(R.id.edt_activity_shekayat_managerName);
        edtProductType = findViewById(R.id.edt_activity_shekayat_productionType);
        edtAddress = findViewById(R.id.edt_activity_shekayat_adderess);
        edtPhone = findViewById(R.id.edt_activity_shekayat_phone);
        edtSabtKonandehFullName = findViewById(R.id.edt_activity_shekayat_sabtKonandehShekayat);
        txvDate = findViewById(R.id.txv_activityShekayat_date);
        edtSubject = findViewById(R.id.edt_activity_shekayat_shekayatSubject);
        edtDescribtion = findViewById(R.id.edt_activity_shekayat_describtion);
        imgCalendar = findViewById(R.id.img_activityShekayat_calendar);
        imgPicGallery = findViewById(R.id.img_activityShekayat_gallery);

        btnSelectPic = findViewById(R.id.btn_activity_shekayat_selectPic);
        btnSend = findViewById(R.id.btn_activity_shekayat_sabt);
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

        btnSelectPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String officeName = edtOfficeName.getText().toString();
                String managerName = edtManagerName.getText().toString();
                String productType = edtProductType.getText().toString();
                String address = edtAddress.getText().toString();
                String phone = edtPhone.getText().toString();
                String fullName = edtSabtKonandehFullName.getText().toString();
                String date = txvDate.getText().toString();
                String subject = edtSubject.getText().toString();
                String describtion = edtDescribtion.getText().toString();

                if (officeName.isEmpty() || managerName.isEmpty() || productType.isEmpty() || address.isEmpty() || phone.isEmpty() || fullName.isEmpty() || date.isEmpty() || subject.isEmpty() || describtion.isEmpty()) {

                    Toast.makeText(ActivityShekayat.this, "فیلد های مربوطه وارد نشده است!", Toast.LENGTH_SHORT).show();
                } else {
                    if (emptyImagView) {
                        sendData(officeName, managerName, productType, address, phone, fullName, date, subject, describtion, bitmap);

                    } else {
                        Toast.makeText(ActivityShekayat.this, "عکس انتخاب نشده است!", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_PIC && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imgPicGallery.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            emptyImagView=true;

        }
    }

    private void selectPicture() {
        Intent mIntent = new Intent();
        mIntent.setType("image/*");
        mIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(mIntent, REQUEST_SELECT_PIC);
    }


    public String uploadImageString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte bytes[] = byteArrayOutputStream.toByteArray();
        String strImage = Base64.encodeToString(bytes, Base64.DEFAULT);
        return strImage;
    }

    public void sendData(final String officeName, final String managerName, final String productType, final String address, final String phone, final String fullName, final String date, final String subject, final String describtion, final Bitmap bitmap) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ActivityShekayat.this, response.trim().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(G.context, MainActivity.class);
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

                String img = uploadImageString(bitmap);
                params.put("officeName", officeName);
                params.put("managerName", managerName);
                params.put("productType", productType);
                params.put("address", address);
                params.put("phone", phone);
                params.put("fullName", fullName);
                params.put("date", date);
                params.put("subject", subject);
                params.put("describtion", describtion);
                params.put("img", img);
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