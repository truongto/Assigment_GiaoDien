package com.example.assigment_giaodien.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.assigment_giaodien.KhoancChiFragment;
import com.example.assigment_giaodien.LoaiChiFragment;

public class ViewPagerChiAdapter extends FragmentStatePagerAdapter {

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {

            case 0:
                return "Khoản Chi";
            case 1:
                return "Loại Chi";

            default:
                return "Khoản Chi";
        }
    }

    public ViewPagerChiAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i){
            case 0:
                fragment = new KhoancChiFragment();
                break;
            case 1:
                fragment = new LoaiChiFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
