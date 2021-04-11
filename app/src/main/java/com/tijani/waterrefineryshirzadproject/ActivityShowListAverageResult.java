package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tijani.waterrefineryshirzadproject.adapters.AverageResultAdapter;
import com.tijani.waterrefineryshirzadproject.adapters.EdaryLaterAdapter;
import com.tijani.waterrefineryshirzadproject.models.AverageResultProperties;
import com.tijani.waterrefineryshirzadproject.models.EdaryLaterProperties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityShowListAverageResult extends AppCompatActivity {
    public static JSONArray data=null;
    AverageResultAdapter averageResultAdapter;
    ArrayList<AverageResultProperties> averageResultPropertiesArrayList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_average_result);
        recyclerView=findViewById(R.id.recycler_averageResult);

        showAverageResult();


    }

    private void showAverageResult(){
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

        averageResultAdapter =  new AverageResultAdapter(ActivityShowListAverageResult.this,averageResultPropertiesArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(ActivityShowListAverageResult.this,1));
        recyclerView.setAdapter(averageResultAdapter);
        recyclerView.setHasFixedSize(true);
        averageResultAdapter.notifyDataSetChanged();

        averageResultAdapter.setOnItemClickListener(new AverageResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
              String id=  averageResultPropertiesArrayList.get(position).getId();
                Intent intent = new Intent(G.context, ActivityShowDetilsAverageResult.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });



    }

}