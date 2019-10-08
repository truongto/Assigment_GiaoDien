package com.example.assigment_giaodien;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.assigment_giaodien.Adapter.KhoanthuAdapter;
import com.example.assigment_giaodien.Adapter.LoaithuAdapter;
import com.example.assigment_giaodien.Database.DatabaseSQL;
import com.example.assigment_giaodien.Model.KhoanThu;
import com.example.assigment_giaodien.Model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private List<KhoanThu> khoanThuList;
    private DatabaseSQL databaseSQL;
    private RecyclerView recyclerView;
    private KhoanthuAdapter khoanthuAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.khoanthufragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);

        recyclerView = view.findViewById(R.id.recyclerview_khoanthu);

        khoanThuList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        databaseSQL = new DatabaseSQL(getActivity());
        khoanThuList = databaseSQL.getKhoanthu();

        khoanthuAdapter = new KhoanthuAdapter(getActivity(), khoanThuList, databaseSQL);
        recyclerView.setAdapter(khoanthuAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final View alert = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_them_khoanthu, null);
                builder.setView(alert);
                final Dialog dialog = builder.show();

                final EditText edname, edgia, edngay;
                final Button huy;
                final Button them;
                edname = alert.findViewById(R.id.edit_name_khoanthu);
                edgia = alert.findViewById(R.id.edit_gia_khoanthu);
                edngay = alert.findViewById(R.id.edit_ngay_khoanthu);

                huy = alert.findViewById(R.id.huy_dialog_khoanthu);
                them = alert.findViewById(R.id.them_dialog_khanthu);
                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        String nhapid = edloaithuID.getText().toString();
                        String nhapname = edname.getText().toString().trim();
                        long nhapgia = Long.parseLong(edgia.getText().toString());
                        String nhapngay = edngay.getText().toString().trim();


                        KhoanThu khoanThu = new KhoanThu();
                        khoanThu.setName(nhapname);
                        khoanThu.setMoney(nhapgia);
                        khoanThu.setDay(nhapngay);
                        khoanThuList.add(khoanThu);
                        long result = databaseSQL.inserKhoanthu(khoanThu);
                        if (nhapname.isEmpty()) {
                            edname.setError("không được bỏ trống");
                        } else if (result > 0) {

                            databaseSQL = new DatabaseSQL(getActivity());
                            khoanthuAdapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(getActivity(), "Thêm Thất Bại ", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }
}
