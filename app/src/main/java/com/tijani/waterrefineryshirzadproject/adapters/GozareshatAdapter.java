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

import com.tijani.waterrefineryshirzadproject.ActivityShowDetilsGozareshat;
import com.tijani.waterrefineryshirzadproject.R;
import com.tijani.waterrefineryshirzadproject.models.EdaryLaterProperties;
import com.tijani.waterrefineryshirzadproject.models.GozareshatProperties;

import java.util.ArrayList;
import java.util.List;

public class GozareshatAdapter extends RecyclerView.Adapter<GozareshatAdapter.GozareshatViewHolder> {
    Context context;
    List<GozareshatProperties> gozareshatPropertiesList;
    GozareshatProperties gozareshatProperties;
    GozareshatViewHolder gozareshatViewHolder;

    public GozareshatAdapter(Context context, List<GozareshatProperties> gozareshatPropertiesList) {
        this.context = context;
        this.gozareshatPropertiesList = gozareshatPropertiesList;
    }

    @NonNull
    @Override
    public GozareshatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_gozareshat,parent,false);
        gozareshatViewHolder= new GozareshatViewHolder(view);
        return gozareshatViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull GozareshatViewHolder holder, int position) {
        gozareshatProperties=gozareshatPropertiesList.get(position);
        holder.date.setText(gozareshatProperties.getDate());
        holder.nemonehLocation.setText(gozareshatProperties.getNemonehLocation());
        holder.parametrType.setText(gozareshatProperties.getParametrType());
        holder.nemonehNumber.setText(gozareshatProperties.getNemonehNumber());

        final String id = gozareshatProperties.getId();
        holder.detils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityShowDetilsGozareshat.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return gozareshatPropertiesList.size();
    }


    public class GozareshatViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView nemonehLocation;
        TextView parametrType;
        TextView nemonehNumber;
        TextView detils;


        public GozareshatViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.txv_date_recycler_gozareshat);
            nemonehLocation=itemView.findViewById(R.id.txv_nemonehLocation_recycler_gozareshat);
            parametrType=itemView.findViewById(R.id.txv_parametrType_recycler_gozareshat);
            nemonehNumber=itemView.findViewById(R.id.txv_nemonehNumber_recycler_gozareshat);
            detils=itemView.findViewById(R.id.txvDetilsItemRecyclerGozareshat);
        }
    }


    public void setFilter(ArrayList<GozareshatProperties> arrayList){
        gozareshatPropertiesList=new ArrayList<>();
        gozareshatPropertiesList.addAll(arrayList);
        notifyDataSetChanged();

    }

}
