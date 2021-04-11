package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ActivityShowDetilsShekayat extends AppCompatActivity {
    ProgressBar progressBar;
    TextView txvOfficeName, txvManagerName, txvProductType, txvAddress, txvPhone, txvFullName, txvDate, txvSubject, txvDescribtion;
    ImageView imageView;

    String url = Urls.urlReadShekayat;
    String id = "";
    String officeName = "";
    String managerName = "";
    String productType = "";
    String address = "";
    String phone = "";
    String fullName = "";
    String date = "";
    String subject = "";
    String describtion = "";
    String img = "";
    String image = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detils_shekayat);
        progressBar = findViewById(R.id.progressbarActivityShowDtilsShekayat);
        txvOfficeName = findViewById(R.id.txv_activity_show_detils_shekayat_officeName);
        txvManagerName = findViewById(R.id.txv_activity_show_detils_shekayat_managerName);
        txvProductType = findViewById(R.id.txv_activity_show_detils_shekayat_productType);
        txvAddress = findViewById(R.id.txv_activity_show_detils_shekayat_address);
        txvPhone = findViewById(R.id.txv_activity_show_detils_shekayat_phone);
        txvFullName = findViewById(R.id.txv_activity_show_detils_shekayat_fullName);
        txvDate = findViewById(R.id.txv_activity_show_detils_shekayat_date);
        txvSubject = findViewById(R.id.txv_activity_show_detils_shekayat_subject);
        txvDescribtion = findViewById(R.id.txv_activity_show_detils_shekayat_describtion);
        imageView = findViewById(R.id.img_activity_show_detils_shekayat_image);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        getData();

    }

    private void getData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    officeName = jsonObject.getString("officeName");
                    managerName = jsonObject.getString("managerName");
                    productType = jsonObject.getString("productType");
                    address = jsonObject.getString("address");
                    phone = jsonObject.getString("phone");
                    fullName = jsonObject.getString("fullName");
                    date = jsonObject.getString("date");
                    subject = jsonObject.getString("subject");
                    describtion = jsonObject.getString("describtion");
                    img = jsonObject.getString("img");
                    image=Urls.urlPic+img;
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                txvOfficeName.setText(officeName);
                txvManagerName.setText(managerName);
                txvProductType.setText(productType);
                txvAddress.setText(address);
                txvPhone.setText(phone);
                txvFullName.setText(fullName);
                txvDate.setText(date);
                txvSubject.setText(subject);
                txvDescribtion.setText(describtion);
                Picasso.get().load(image).into(imageView);
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
                params.put("id", id);
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