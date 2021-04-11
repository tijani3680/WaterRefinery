package com.tijani.waterrefineryshirzadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.tijani.waterrefineryshirzadproject.adapters.MyViewPagerAdapter;
import com.tijani.waterrefineryshirzadproject.fragments.HavadesFragment;
import com.tijani.waterrefineryshirzadproject.fragments.QateeBarqFragment;
import com.tijani.waterrefineryshirzadproject.fragments.TajhizatMotavaqefFragment;

public class ActivityShowListHavadesVaQateeBarq extends AppCompatActivity {

    SmartTabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_havades_va_qatee_barq);

        tabLayout=findViewById(R.id.tabLayout_havadesVaQateeBarq);
        viewPager=findViewById(R.id.viewPager_havadesVaQateeBarq);

        MyViewPagerAdapter myViewPagerAdapter =new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.addFragment(new HavadesFragment()," حوادث");
        myViewPagerAdapter.addFragment(new QateeBarqFragment()," قطعی برق");
        myViewPagerAdapter.addFragment(new TajhizatMotavaqefFragment()," ت متوقف");

        viewPager.setAdapter(myViewPagerAdapter);
        tabLayout.setViewPager(viewPager);



    }
}