package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.tijani.waterrefineryshirzadproject.adapters.AverageResultAdapter;
import com.tijani.waterrefineryshirzadproject.models.AverageResultProperties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityShowListGandZodae extends AppCompatActivity {

    RecyclerView recyclerView;
    public static JSONArray data=null;
    AverageResultAdapter averageResultAdapter;
    ArrayList<AverageResultProperties> averageResultPropertiesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_gand_zodae);
        recyclerView=findViewById(R.id.recycler_gandZodaye);

        showGandZodaye();

    }

    private void showGandZodaye(){
        averageResultPropertiesArrayList=new ArrayList<>();

        for (int i=0;i<data.length();i++){


            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String id=jsonObject.getString("id");
                String date=jsonObject.getString("date");

                averageResultPropertiesArrayList.add(new AverageResultProperties(id,date));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        averageResultAdapter =  new AverageResultAdapter(ActivityShowListGandZodae.this,averageResultPropertiesArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(ActivityShowListGandZodae.this,1));
        recyclerView.setAdapter(averageResultAdapter);
        recyclerView.setHasFixedSize(true);
        averageResultAdapter.notifyDataSetChanged();

        averageResultAdapter.setOnItemClickListener(new AverageResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String id=  averageResultPropertiesArrayList.get(position).getId();

                Intent intent = new Intent(G.context, ActivityShowDetilsGandZodaye.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });



    }

}