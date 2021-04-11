package com.tijani.waterrefineryshirzadproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tijani.waterrefineryshirzadproject.ActivityShowDetilsShekayat;
import com.tijani.waterrefineryshirzadproject.R;
import com.tijani.waterrefineryshirzadproject.models.EdaryLaterProperties;
import com.tijani.waterrefineryshirzadproject.models.ShekayatProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ShekayatAdapter extends RecyclerView.Adapter<ShekayatAdapter.ShekayatViewHolder> {
    Context context;
    List<ShekayatProperties> shekayatPropertiesList;
    ShekayatProperties shekayatProperties;
    ShekayatViewHolder shekayatViewHolder;


    public ShekayatAdapter(Context context, List<ShekayatProperties> shekayatPropertiesList) {
        this.context = context;
        this.shekayatPropertiesList = shekayatPropertiesList;
    }

    @NonNull
    @Override
    public ShekayatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_shekayat,parent,false);
        shekayatViewHolder = new ShekayatViewHolder(view);
        return shekayatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShekayatViewHolder holder, int position) {
        shekayatProperties = shekayatPropertiesList.get(position);

        holder.officeName.setText(shekayatProperties.getOfficeName());
        holder.date.setText(shekayatProperties.getDate());
        holder.subject.setText(shekayatProperties.getSubject());
        Picasso.get().load(shekayatProperties.getImg()).placeholder(R.drawable.ic_img_placeholder).into(holder.img);


        final String id = shekayatProperties.getId();

        holder.showDetils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityShowDetilsShekayat.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return shekayatPropertiesList.size();
    }


    public class ShekayatViewHolder extends RecyclerView.ViewHolder{
        TextView officeName;
        TextView date;
        TextView subject;
        TextView showDetils;
        ImageView img;

        public ShekayatViewHolder(@NonNull View itemView) {
            super(itemView);

            officeName=itemView.findViewById(R.id.txv_officeName_recycler_shekayat);
            date=itemView.findViewById(R.id.txv_date_recycler_shekayat);
            subject=itemView.findViewById(R.id.txv_subject_recycler_shekayat);
            showDetils=itemView.findViewById(R.id.txvDetilsItemRecyclerShekayat);
            img=itemView.findViewById(R.id.img_item_recycler_shekayat);

        }
    }

    public void setFilter(ArrayList<ShekayatProperties> arrayList){


        shekayatPropertiesList=new ArrayList<>();
        shekayatPropertiesList.addAll(arrayList);
        notifyDataSetChanged();

    }







}
