package com.example.assigment_giaodien.Adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.assigment_giaodien.KhoanThuFragment;
import com.example.assigment_giaodien.LoaiThuFragment;

public class ViewPagerThuAdapter extends FragmentStatePagerAdapter {

    public ViewPagerThuAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {

            case 0:
                return "Khoản Thu";
            case 1:
                return "Loại Thu";

            default:
                return "Khoản Thu";
        }

    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i){
            case 0:
                fragment = new KhoanThuFragment();
                break;
            case 1:
                fragment = new LoaiThuFragment();
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return 2;
    }
}
