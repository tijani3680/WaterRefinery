package com.tijani.waterrefineryshirzadproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.waterrefineryshirzadproject.R;
import com.tijani.waterrefineryshirzadproject.adapters.RecyclerAdapterTabBazsaziVaNosazi;
import com.tijani.waterrefineryshirzadproject.models.AverageResultProperties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BazsaziVaNosaziFragment extends Fragment {

    public static JSONArray data=null;
    View v;
    private RecyclerView recyclerView;
    private List<AverageResultProperties> bazsaziList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.bazsazi_nosazi_fragment_layout,container,false);
        recyclerView=v.findViewById(R.id.bazsaziVaNosazi_recyclerView);

        RecyclerAdapterTabBazsaziVaNosazi recyclerAdapterTabBazsaziVaNosazi = new RecyclerAdapterTabBazsaziVaNosazi(getContext(),bazsaziList);

///
        recyclerView.setHasFixedSize(true);
        ///
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapterTabBazsaziVaNosazi);
        recyclerAdapterTabBazsaziVaNosazi.notifyDataSetChanged();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showBazsaziVaNosazi();


    }

    private void showBazsaziVaNosazi(){
        bazsaziList=new ArrayList<>();

        for (int i=0;i<data.length();i++){


            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String id=jsonObject.getString("id");
                String startDate=jsonObject.getString("startDate");

                bazsaziList.add(new AverageResultProperties(id,startDate));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }


}
