package com.example.assigment_giaodien;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assigment_giaodien.Adapter.LoaiChiAdapter;
import com.example.assigment_giaodien.Adapter.LoaithuAdapter;
import com.example.assigment_giaodien.Database.DatabaseSQL;
import com.example.assigment_giaodien.Model.LoaiChi;
import com.example.assigment_giaodien.Model.LoaiThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    List<LoaiChi> loaiChiList;
    DatabaseSQL databaseSQL;
    LoaiChiAdapter loaiChiAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loaichifragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton = view.findViewById(R.id.floatingActionButton_loaichi);
        recyclerView = view.findViewById(R.id.recyclerview_loaichi);

        loaiChiList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        databaseSQL = new DatabaseSQL(getActivity());
        loaiChiList = databaseSQL.getLoaichi();

        loaiChiAdapter = new LoaiChiAdapter(getActivity(),loaiChiList, databaseSQL);
        recyclerView.setAdapter(loaiChiAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final View alert = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_them_loaithu, null);
                builder.setView(alert);
                final Dialog dialog = builder.show();

                final EditText edloaithu;
                final Button huy;
                final Button them;
                edloaithu = alert.findViewById(R.id.edit_loaithu);

                huy = alert.findViewById(R.id.huy_dialogthem);
                them = alert.findViewById(R.id.them_dialogthem);
                them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        String nhapid = edloaithuID.getText().toString();
                        String nhaploaithu = edloaithu.getText().toString().trim();


                        LoaiChi loaiChi = new LoaiChi();
                        loaiChi.setName(nhaploaithu);
                        loaiChiList.add(loaiChi);
                        databaseSQL = new DatabaseSQL(getActivity());
                        loaiChiAdapter.notifyDataSetChanged();

                        long result = databaseSQL.inserLoaichi(loaiChi);
                        if (nhaploaithu.isEmpty()) {
                            edloaithu.setError("không được bỏ trống");
//                            Toast.makeText(getActivity(), "không được bỏ trống", Toast.LENGTH_SHORT).show();

                        } else if (result > 0) {
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
