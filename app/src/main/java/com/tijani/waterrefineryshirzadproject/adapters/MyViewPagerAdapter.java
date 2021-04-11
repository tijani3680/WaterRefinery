package com.tijani.waterrefineryshirzadproject.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList;
    ArrayList<String> titles;

    public MyViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

        fragmentArrayList=new ArrayList<>();
        titles=new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void   addFragment(Fragment fragment,String title){
        fragmentArrayList.add(fragment);
        titles.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
