package com.tijani.waterrefineryshirzadproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.waterrefineryshirzadproject.R;
import com.tijani.waterrefineryshirzadproject.models.AverageResultProperties;

import java.util.List;

public class AverageResultAdapter extends RecyclerView.Adapter<AverageResultAdapter.AverageResultViewHolder> {
    

    Context context;
    List<AverageResultProperties> averageResultPropertiesList;
    AverageResultProperties averageResultProperties;

    AverageResultViewHolder averageResultViewHolder;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mListener=onItemClickListener;
    }


    public AverageResultAdapter(Context context, List<AverageResultProperties> averageResultPropertiesList) {
        this.context = context;
        this.averageResultPropertiesList = averageResultPropertiesList;
    }

    @NonNull
    @Override
    public AverageResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_average_result,parent,false);
        averageResultViewHolder= new AverageResultViewHolder(view,mListener);
        return averageResultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AverageResultViewHolder holder, int position) {
        averageResultProperties= averageResultPropertiesList.get(position);
        holder.txvDate.setText(averageResultProperties.getDate());



    }

    @Override
    public int getItemCount() {
        return averageResultPropertiesList.size();
    }

    public class AverageResultViewHolder extends RecyclerView.ViewHolder {

        TextView txvDate;

        public AverageResultViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            txvDate=itemView.findViewById(R.id.txv_date_recycler_averageResult);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener !=null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
