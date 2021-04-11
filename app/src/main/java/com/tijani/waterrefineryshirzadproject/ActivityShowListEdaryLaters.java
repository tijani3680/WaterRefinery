package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tijani.waterrefineryshirzadproject.adapters.EdaryLaterAdapter;
import com.tijani.waterrefineryshirzadproject.models.EdaryLaterProperties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityShowListEdaryLaters extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static JSONArray data=null;
    RecyclerView recyclerView;
    EdaryLaterAdapter edaryLaterAdapter;
    ArrayList<EdaryLaterProperties> edaryLaterPropertiesArrayList;

    ////
    Toolbar toolbar;

    ////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_edary_laters);

        toolbar=findViewById(R.id.toolbar_showListEdaryLater);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recycler_edaryLaters);
        showEdaryLaters();


    }

    private void showEdaryLaters(){
        edaryLaterPropertiesArrayList=new ArrayList<>();

        for (int i=0;i<data.length();i++){


            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String id=jsonObject.getString("id");
                String number=jsonObject.getString("number");
                String date=jsonObject.getString("date");
                String subject = jsonObject.getString("subject");
                String registrant = jsonObject.getString("registrant");


                edaryLaterPropertiesArrayList.add(new EdaryLaterProperties(id,number,date,registrant,subject));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        edaryLaterAdapter =  new EdaryLaterAdapter(ActivityShowListEdaryLaters.this,edaryLaterPropertiesArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(ActivityShowListEdaryLaters.this,1));
        recyclerView.setAdapter(edaryLaterAdapter);
        recyclerView.setHasFixedSize(true);
        edaryLaterAdapter.notifyDataSetChanged();


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
        ArrayList<EdaryLaterProperties> newArrayList = new ArrayList<>();
        for (EdaryLaterProperties edaryLaterProperties : edaryLaterPropertiesArrayList){
            String laterName = edaryLaterProperties.getShomarehName();
            String laterSubject = edaryLaterProperties.getSubject();
            String laterDate = edaryLaterProperties.getDate();

            if (laterName.contains(newText) | laterSubject.contains(newText) | laterDate.contains(newText)){
                newArrayList.add(edaryLaterProperties);
            }
        }

        edaryLaterAdapter.setFilter(newArrayList);

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