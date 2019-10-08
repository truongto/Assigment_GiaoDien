package com.example.assigment_giaodien;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assigment_giaodien.Adapter.ViewPagerChiAdapter;

public class ChiFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chi_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout=view.findViewById(R.id.tablayoutChi);
        viewPager=view.findViewById(R.id.pagerChi);
        ViewPagerChiAdapter viewPagerChiAdapter =new ViewPagerChiAdapter(getFragmentManager());
        viewPager.setAdapter(viewPagerChiAdapter);
        tabLayout.setupWithViewPager(viewPager);













    }
}
