package com.example.assigment_giaodien;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.assigment_giaodien.Adapter.ThongKeAdapter;
import com.example.assigment_giaodien.Adapter.ThongkeKhoanchiAdapter;
import com.example.assigment_giaodien.Database.DatabaseSQL;
import com.example.assigment_giaodien.Model.KhoanChi;
import com.example.assigment_giaodien.Model.KhoanThu;

import java.util.ArrayList;
import java.util.List;

public class ThongKeFragment extends Fragment {
    TextView textView, textView22,ketqua;
    DatabaseSQL databaseSQL;
    RecyclerView recyclerView, recyclerView22;
    List<KhoanThu> khoanThuList;
    List<KhoanChi> khoanChiList;
    ThongKeAdapter thongKeAdapter;
    ThongkeKhoanchiAdapter thongkeKhoanchiAdapter;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongkefragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_thongke);
        recyclerView22 = view.findViewById(R.id.recyclerview_thongke2);
        textView = view.findViewById(R.id.texttong);
        textView22 = view.findViewById(R.id.texttonh22);
        ketqua=view.findViewById(R.id.ketqua);
        ///thong ke khoan thu
        khoanThuList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        databaseSQL = new DatabaseSQL(getActivity());
        khoanThuList = databaseSQL.getthongkeKhoanThu();

        thongKeAdapter = new ThongKeAdapter(getActivity(), khoanThuList, databaseSQL);
        recyclerView.setAdapter(thongKeAdapter);

        khoanThuList = databaseSQL.getKhoanthu();
        float tienthu = 0;
        for (int i = 0; i < khoanThuList.size(); i++) {
            KhoanThu khoanThu = new KhoanThu();
            khoanThu = khoanThuList.get(i);

            tienthu += khoanThu.getMoney();
            textView.setText(tienthu + " ");
        }


///thong ke khoan chi
        khoanChiList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager22 = new LinearLayoutManager(getActivity());
        recyclerView22.setLayoutManager(layoutManager22);

        databaseSQL = new DatabaseSQL(getActivity());
        khoanChiList = databaseSQL.getthongkeKhoanchi();

        thongkeKhoanchiAdapter = new ThongkeKhoanchiAdapter(getActivity(), khoanChiList, databaseSQL);
        recyclerView22.setAdapter(thongkeKhoanchiAdapter);

        khoanChiList = databaseSQL.getKhoanchi();
        float tienchi = 0;
        for (int i = 0; i < khoanChiList.size(); i++) {
            KhoanChi khoanChi = new KhoanChi();
            khoanChi = khoanChiList.get(i);

            tienchi += khoanChi.getMoney();

            textView22.setText(tienchi + " ");
        }

        String tong = tienthu + -tienchi + "";
        ketqua.setText(tong);


    }

}


