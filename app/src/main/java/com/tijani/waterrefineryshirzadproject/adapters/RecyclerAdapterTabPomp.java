package com.tijani.waterrefineryshirzadproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.waterrefineryshirzadproject.ActivityShowDetailsTabBoloer;
import com.tijani.waterrefineryshirzadproject.ActivityShowDetailsTabPomp;
import com.tijani.waterrefineryshirzadproject.R;
import com.tijani.waterrefineryshirzadproject.models.TitleKarkardBoloerYaPomp;

import java.util.List;

public class RecyclerAdapterTabPomp extends RecyclerView.Adapter<RecyclerAdapterTabPomp.MyViewHolder> {

    Context context;
    List<TitleKarkardBoloerYaPomp> mData;

    public RecyclerAdapterTabPomp(Context context, List<TitleKarkardBoloerYaPomp> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.item_recycler_tab_pomp,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txvTitle.setText(mData.get(position).getTitle());

        final String id= mData.get(position).getId();

        holder.imgGoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityShowDetailsTabPomp.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txvTitle;
        ImageView imgGoDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txvTitle=itemView.findViewById(R.id.txv_boloer_recycler_item_tab_pomp);
            imgGoDetails=itemView.findViewById(R.id.img_goDetails_recycler_item_tab_pomp);

        }
    }
}
