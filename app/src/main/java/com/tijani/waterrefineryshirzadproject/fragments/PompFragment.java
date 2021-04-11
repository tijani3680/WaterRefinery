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
import com.tijani.waterrefineryshirzadproject.adapters.RecyclerAdapterTabPomp;
import com.tijani.waterrefineryshirzadproject.adapters.RecyclerAdapterTabRangAmizi;
import com.tijani.waterrefineryshirzadproject.models.TitleKarkardBoloerYaPomp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PompFragment extends Fragment {

    public static JSONArray data=null;
    View v;
    private RecyclerView recyclerView;
    List<TitleKarkardBoloerYaPomp> titleList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.pomp_fragment_layout,container,false);
        recyclerView=v.findViewById(R.id.pomp_recyclerView);

        RecyclerAdapterTabPomp recyclerAdapterTabPomp = new RecyclerAdapterTabPomp(getContext(),titleList);

///
        recyclerView.setHasFixedSize(true);
        ///
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapterTabPomp);
        recyclerAdapterTabPomp.notifyDataSetChanged();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showPomp();


    }


    private void showPomp(){
        titleList=new ArrayList<>();

        for (int i=0;i<data.length();i++){


            try {
                JSONObject jsonObject = data.getJSONObject(i);
                String id=jsonObject.getString("id");
                String title=jsonObject.getString("pomp");

                titleList.add(new TitleKarkardBoloerYaPomp(id,title));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }

}
