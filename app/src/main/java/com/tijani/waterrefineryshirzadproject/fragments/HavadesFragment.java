package com.tijani.waterrefineryshirzadproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.waterrefineryshirzadproject.ActivityShowDetilsNegahdariVaTamirBarq;
import com.tijani.waterrefineryshirzadproject.ActivityShowListNegahdariVaTamiratBarq;
import com.tijani.waterrefineryshirzadproject.G;
import com.tijani.waterrefineryshirzadproject.R;
import com.tijani.waterrefineryshirzadproject.adapters.AverageResultAdapter;
import com.tijani.waterrefineryshirzadproject.adapters.RecyclerAdapterTabHavades;
import com.tijani.waterrefineryshirzadproject.models.AverageResultProperties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HavadesFragment extends Fragment {

    public static JSONArray data=null;
    View v;
    private RecyclerView recyclerView;
    private List<AverageResultProperties> havadesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.havades_fragment_layout,container,false);
        recyclerView=v.findViewById(R.id.havades_recyclerView);
        RecyclerAdapterTabHavades recyclerAdapterTabHavades =new RecyclerAdapterTabHavades(getContext(),havadesList);
///
        recyclerView.setHasFixedSize(true);
        ///
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapterTabHavades);
        recyclerAdapterTabHavades.notifyDataSetChanged();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHavades();


    }

    private void showHavades(){
        havadesList=new ArrayList<>();

        for (int i=0;i<data.length();i++){


            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String id=jsonObject.getString("id");
                String date=jsonObject.getString("date");

                havadesList.add(new AverageResultProperties(id,date));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }

}
