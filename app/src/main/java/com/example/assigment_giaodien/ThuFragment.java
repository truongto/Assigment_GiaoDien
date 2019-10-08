package com.example.assigment_giaodien;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assigment_giaodien.Adapter.LoaithuAdapter;
import com.example.assigment_giaodien.Adapter.ViewPagerThuAdapter;
import com.example.assigment_giaodien.Database.DatabaseSQL;
import com.example.assigment_giaodien.Model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class ThuFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thufragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.pager);
        ViewPagerThuAdapter viewPagerThuAdapter = new ViewPagerThuAdapter(getFragmentManager());
        viewPager.setAdapter(viewPagerThuAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
