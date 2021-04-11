package com.tijani.waterrefineryshirzadproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tijani.waterrefineryshirzadproject.R;
import com.tijani.waterrefineryshirzadproject.models.MenueListItem;

import java.util.ArrayList;
import java.util.List;

public class MenueAdapter extends ArrayAdapter {


    public Activity context;
    public int resourceId;
    public ArrayList<MenueListItem> objects;

    public MenueAdapter(@NonNull Activity context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        this.context=context;
        this.resourceId=resource;
        this.objects=objects;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = this.context.getLayoutInflater().inflate(this.resourceId, null);
        TextView txvTitleMenuList = view.findViewById(R.id.txvTitleMenuList);
        ImageView imgMenuList = view.findViewById(R.id.imgMenuList);
        MenueListItem menueListItem = objects.get(position);
        txvTitleMenuList.setText(menueListItem.title);
        imgMenuList.setImageResource(menueListItem.icon);
        return view;

    }
}
