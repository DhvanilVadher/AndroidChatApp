package com.example.dhvanil.authi;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class adapter extends FragmentPagerAdapter {
    public ArrayList<Fragment>fregment1=new ArrayList<>();
    public ArrayList<String>titles = new ArrayList<>();

    public adapter( FragmentManager fm) {
        super( fm );
    }


    @Override
    public Fragment getItem( int i ) {return fregment1.get(i);
    }

    @Override
    public int getCount() {
        return fregment1.size();
    }
    public void addFregment(Fragment fragment,String s){

        fregment1.add(fragment);
        titles.add(s);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle( int position ) {
        return titles.get(position);
    }
}
