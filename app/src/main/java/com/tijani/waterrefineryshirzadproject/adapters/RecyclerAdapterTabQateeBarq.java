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

import com.tijani.waterrefineryshirzadproject.ActivityShowDetailsTabHavades;
import com.tijani.waterrefineryshirzadproject.ActivityShowDetailsTabQateeBarq;
import com.tijani.waterrefineryshirzadproject.R;
import com.tijani.waterrefineryshirzadproject.models.AverageResultProperties;

import java.util.List;

public class RecyclerAdapterTabQateeBarq extends RecyclerView.Adapter<RecyclerAdapterTabQateeBarq.MyViewHolder> {

    Context context;
    List<AverageResultProperties> mData;

    public RecyclerAdapterTabQateeBarq(Context context, List<AverageResultProperties> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.item_recycler_tab_qatee_barq,parent,false);

        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txvDate.setText(mData.get(position).getDate());

        final String id= mData.get(position).getId();

        holder.imgGoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityShowDetailsTabQateeBarq.class);
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
        TextView txvDate;
        ImageView imgGoDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txvDate=itemView.findViewById(R.id.txv_date_recycler_item_tab_qateeBarq);
            imgGoDetails=itemView.findViewById(R.id.img_goDetails_recycler_item_tab_qateeBarq);

        }
    }



}
