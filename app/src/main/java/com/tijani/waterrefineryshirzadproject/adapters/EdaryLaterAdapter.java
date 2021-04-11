package com.tijani.waterrefineryshirzadproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.waterrefineryshirzadproject.ActivityShowDetilsEdaryLaters;
import com.tijani.waterrefineryshirzadproject.G;
import com.tijani.waterrefineryshirzadproject.R;
import com.tijani.waterrefineryshirzadproject.models.EdaryLaterProperties;

import java.util.ArrayList;
import java.util.List;

public class EdaryLaterAdapter extends RecyclerView.Adapter<EdaryLaterAdapter.EdaryLaterViewHolder> {

    Context context;
    EdaryLaterProperties edaryLaterProperties;
    List<EdaryLaterProperties> edaryLaterPropertiesArrayList;
    EdaryLaterViewHolder edaryLaterViewHolder;

    public EdaryLaterAdapter(Context context, List<EdaryLaterProperties> edaryLaterPropertiesArrayList) {
        this.context = context;
        this.edaryLaterPropertiesArrayList = edaryLaterPropertiesArrayList;
    }

    @NonNull
    @Override
    public EdaryLaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_edary_laters, parent, false);
        edaryLaterViewHolder = new EdaryLaterViewHolder(view);
        return edaryLaterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EdaryLaterViewHolder holder, int position) {
        edaryLaterProperties = edaryLaterPropertiesArrayList.get(position);
        holder.shomarehName.setText(edaryLaterProperties.getShomarehName());
        holder.date.setText(edaryLaterProperties.getDate());
        holder.registrant.setText(edaryLaterProperties.getRegistrant());
        holder.subject.setText(edaryLaterProperties.getSubject());
        final String idLater = edaryLaterProperties.getId();

        holder.detils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityShowDetilsEdaryLaters.class);
                intent.putExtra("id",idLater);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return edaryLaterPropertiesArrayList.size();
    }


    public class EdaryLaterViewHolder extends RecyclerView.ViewHolder {
        TextView shomarehName;
        TextView date;
        TextView registrant;
        TextView subject;
        TextView detils;


        public EdaryLaterViewHolder(@NonNull View itemView) {
            super(itemView);
            shomarehName = itemView.findViewById(R.id.txv_shomarehNameh_recycler_edaryLater);
            date = itemView.findViewById(R.id.txv_date_recycler_edaryLater);
            registrant = itemView.findViewById(R.id.txv_registrant_recycler_edaryLater);
            subject = itemView.findViewById(R.id.txv_subject_recycler_edaryLater);
            detils=itemView.findViewById(R.id.txvDetilsItemRecyclerEdaryLaters);

        }
    }

    public void setFilter(ArrayList<EdaryLaterProperties> arrayList){


        edaryLaterPropertiesArrayList=new ArrayList<>();
        edaryLaterPropertiesArrayList.addAll(arrayList);
        notifyDataSetChanged();

    }



}
