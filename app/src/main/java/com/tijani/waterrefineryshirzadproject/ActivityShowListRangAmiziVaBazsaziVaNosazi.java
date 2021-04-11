package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.tijani.waterrefineryshirzadproject.adapters.MyViewPagerAdapter;
import com.tijani.waterrefineryshirzadproject.fragments.BazsaziVaNosaziFragment;
import com.tijani.waterrefineryshirzadproject.fragments.HavadesFragment;
import com.tijani.waterrefineryshirzadproject.fragments.RangAmiziFragment;

public class ActivityShowListRangAmiziVaBazsaziVaNosazi extends AppCompatActivity {

    SmartTabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_rang_amizi_va_bazsazi_va_nosazi);

        tabLayout=findViewById(R.id.tabLayout_rangAmizi_bazsaziVaNosazi);
        viewPager=findViewById(R.id.viewPager_rangAmizi_bazsaziVaNosazi);
        MyViewPagerAdapter myViewPagerAdapter =new MyViewPagerAdapter(getSupportFragmentManager());

        myViewPagerAdapter.addFragment(new RangAmiziFragment()," رنگ آمیزی");
        myViewPagerAdapter.addFragment(new BazsaziVaNosaziFragment(),"بازسازی و نوسازی");


        viewPager.setAdapter(myViewPagerAdapter);
        tabLayout.setViewPager(viewPager);

    }
}