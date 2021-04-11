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

import com.tijani.waterrefineryshirzadproject.adapters.GozareshatAdapter;
import com.tijani.waterrefineryshirzadproject.adapters.ShekayatAdapter;
import com.tijani.waterrefineryshirzadproject.models.EdaryLaterProperties;
import com.tijani.waterrefineryshirzadproject.models.GozareshatProperties;
import com.tijani.waterrefineryshirzadproject.models.ShekayatProperties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityShowListGozareshat extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static JSONArray data=null;

    RecyclerView recyclerView;
    GozareshatAdapter gozareshatAdapter;
    ArrayList<GozareshatProperties> gozareshatPropertiesArrayList;

    ////
    Toolbar toolbar;

    ////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_gozareshat);

        toolbar=findViewById(R.id.toolbar_showListGozareshat);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recycler_gozareshat);

        showGozareshat();
    }

    private void showGozareshat(){
        gozareshatPropertiesArrayList=new ArrayList<>();

        for (int i=0;i<data.length();i++){


            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String id=jsonObject.getString("id");
                String date=jsonObject.getString("date");
                String parametrType=jsonObject.getString("parametrType");
                String nemonehLocation = jsonObject.getString("nemonehLocation");
                String nemonehNumber = jsonObject.getString("nemonehNumber");

                gozareshatPropertiesArrayList.add(new GozareshatProperties(id,date,nemonehLocation,parametrType,nemonehNumber));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        gozareshatAdapter= new GozareshatAdapter(ActivityShowListGozareshat.this,gozareshatPropertiesArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(ActivityShowListGozareshat.this,1));
        recyclerView.setAdapter(gozareshatAdapter);
        recyclerView.setHasFixedSize(true);
        gozareshatAdapter.notifyDataSetChanged();


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
        ArrayList<GozareshatProperties> newArrayList = new ArrayList<>();
        for (GozareshatProperties gozareshatProperties : gozareshatPropertiesArrayList){
            String nemonehNumber = gozareshatProperties.getNemonehNumber();
            String nemonehLocation = gozareshatProperties.getNemonehLocation();
            String nemonehDate = gozareshatProperties.getDate();
            String parameterType = gozareshatProperties.getParametrType();
            if (nemonehNumber.contains(newText) | nemonehLocation.contains(newText) | nemonehDate.contains(newText) | parameterType.contains(newText)){
                newArrayList.add(gozareshatProperties);
            }
        }

        gozareshatAdapter.setFilter(newArrayList);

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