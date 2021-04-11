package com.tijani.waterrefineryshirzadproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
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

public class ActivityUserSignIn extends AppCompatActivity {
    EditText edtUserName,edtPassword;
    AppCompatCheckBox checkBox;
    Button btnSignIn,btnSignUp;
    public static SharedPreferences preferences;
    String url = Urls.urlChekLogin;
    private String userName="";
    private String password="";
    private final int REQUEST_CODE=1;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);
        edtUserName=findViewById(R.id.edtUserNameSignIn);
        edtPassword=findViewById(R.id.edtpassSignIn);
        checkBox=findViewById(R.id.chkShowPass);
        btnSignIn=findViewById(R.id.btnSignIn);
        btnSignUp=findViewById(R.id.btnSigup);
        progressBar=findViewById(R.id.progressbarActivityUserSignIn);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()){
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                }else {
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

                }
            }
        });


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=edtUserName.getText().toString();
                password=edtPassword.getText().toString();
                if (userName.isEmpty() & password.isEmpty()){
                    Toast.makeText(ActivityUserSignIn.this,R.string.text2, Toast.LENGTH_SHORT).show();
                }else{
                    signIn();

                }


            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(G.context,ActivityUserSignUp.class);
                /*startActivityForResult(intent,REQUEST_CODE);*/

                startActivity(intent);
            }
        });
    }



    private void signIn() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("not exist")) {
                    Toast.makeText(G.context, "نام کاربری یا کلمه عبور اشتباه است", Toast.LENGTH_SHORT).show();

                } else {

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String userNameS = jsonObject.getString("userName");
                        Intent intent = new Intent(G.context, MainActivity.class);
                        intent.putExtra("userName", userNameS);
                        setResult(RESULT_OK, intent);
                        finish();

                        creatCustomToast(G.context,userNameS,Toast.LENGTH_SHORT);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


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
                params.put("userName", userName);
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

    private void creatCustomToast(Context context,String text,int time){

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


  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String registerUserDon = bundle.getString("do");

            preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("do", registerUserDon);
            editor.commit();
            Intent intent = new Intent(G.context, MainActivity.class);
            startActivity(intent);
        }
    }*/
}