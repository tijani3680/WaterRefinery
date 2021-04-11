package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.tijani.waterrefineryshirzadproject.adapters.MyViewPagerAdapter;
import com.tijani.waterrefineryshirzadproject.fragments.BoloerFragment;
import com.tijani.waterrefineryshirzadproject.fragments.PompFragment;

public class ActivityShowListKarkardBoloerVaPomp extends AppCompatActivity {

    SmartTabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_karkard_boloer_va_pomp);
        tabLayout=findViewById(R.id.tabLayout_boloerVaPomp);
        viewPager=findViewById(R.id.viewPager_boloerVaPomp);

        MyViewPagerAdapter myViewPagerAdapter =new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.addFragment(new BoloerFragment(),"کارکرد بلوئرها");
        myViewPagerAdapter.addFragment(new PompFragment(),"کارکرد پمپ ها");
        viewPager.setAdapter(myViewPagerAdapter);
        tabLayout.setViewPager(viewPager);


    }
}