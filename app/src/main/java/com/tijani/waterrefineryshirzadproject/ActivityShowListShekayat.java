package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tijani.waterrefineryshirzadproject.adapters.EdaryLaterAdapter;
import com.tijani.waterrefineryshirzadproject.adapters.ShekayatAdapter;
import com.tijani.waterrefineryshirzadproject.models.EdaryLaterProperties;
import com.tijani.waterrefineryshirzadproject.models.ShekayatProperties;
import com.tijani.waterrefineryshirzadproject.models.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityShowListShekayat extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static JSONArray data=null;


    ShekayatAdapter shekayatAdapter;
    ArrayList<ShekayatProperties> shekayatPropertiesArrayList;
    RecyclerView recyclerView;

    ////
    Toolbar toolbar;

    ////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_shekayat);

        toolbar=findViewById(R.id.toolbar_showListShekayat);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recycler_shekayat);
        showShekayat();
    }

    private void showShekayat(){
        shekayatPropertiesArrayList=new ArrayList<>();

        for (int i=0;i<data.length();i++){


            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String id=jsonObject.getString("id");
                String officeName=jsonObject.getString("officeName");
                String date=jsonObject.getString("date");
                String subject = jsonObject.getString("subject");
                String img = jsonObject.getString("img");
                String urlPic= Urls.urlPic +img;


                shekayatPropertiesArrayList.add(new ShekayatProperties(id,officeName,date,subject,urlPic));



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        shekayatAdapter = new ShekayatAdapter(ActivityShowListShekayat.this,shekayatPropertiesArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(ActivityShowListShekayat.this,1));
        recyclerView.setAdapter(shekayatAdapter);
        recyclerView.setHasFixedSize(true);
        shekayatAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        newText=persianToEnglish(newText).toLowerCase();
        ArrayList<ShekayatProperties> newArrayList = new ArrayList<>();
        for (ShekayatProperties shekayatProperties : shekayatPropertiesArrayList){
            String officeName = shekayatProperties.getOfficeName();
            String shekayatSubject = shekayatProperties.getSubject();
            String shekayatDate = shekayatProperties.getDate();

            if (officeName.contains(newText) | shekayatSubject.contains(newText) | shekayatDate.contains(newText)){
                newArrayList.add(shekayatProperties);
            }
        }

        shekayatAdapter.setFilter(newArrayList);

        return true;
    }


    public static String persianToEnglish(String number) {
        char[] chars = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }

}