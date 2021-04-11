package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityUserSignUp extends AppCompatActivity {

    ProgressBar progressBar;
    EditText edtUserNameSignUp,edtPasswordSignUp,edtRepasswordSignUp;
    Button btnRegister;
    String url = Urls.urlRegister;
    private String username="";
    private String password="";
    private String rePassword="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        progressBar=findViewById(R.id.progressbarActivityUserSignUp);
        edtUserNameSignUp=findViewById(R.id.edtUserNameSignUp);
        edtPasswordSignUp=findViewById(R.id.edtpassSignUp);
        edtRepasswordSignUp=findViewById(R.id.edtRePassSignUp);
        btnRegister=findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=edtUserNameSignUp.getText().toString();
                password=edtPasswordSignUp.getText().toString();
                rePassword=edtRepasswordSignUp.getText().toString();
                if (rePassword.equals(password)){

                    signUp();


                }else {
                    Toast.makeText(ActivityUserSignUp.this, R.string.text3, Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

    private void signUp() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("not ok")) {
                    Toast.makeText(G.context, R.string.text4, Toast.LENGTH_SHORT).show();
                } else if (response.trim().equals("exist")) {
                    Toast.makeText(G.context, R.string.text5, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(ActivityUserSignUp.this, "ثبت نام با موفقییت انجام شد", Toast.LENGTH_SHORT).show();
                    finish();

               /*     Intent intent = new Intent(G.context, ActivityUserSignIn.class);
                    intent.putExtra("do", response);
                    setResult(RESULT_OK, intent);
                    finish();
                    creatCustomToast(G.context,response,Toast.LENGTH_SHORT);*/

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
                params.put("userName", username);
                params.put("password", password);
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

    private void creatCustomToast(Context context, String text, int time){

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast,null);
        TextView txvUsName = view.findViewById(R.id.txvCustomToastUserName);
        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(time);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM,0,0);
        txvUsName.setText(text);
        toast.show();
    }

}