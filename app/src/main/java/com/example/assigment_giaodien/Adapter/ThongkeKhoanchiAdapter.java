package com.example.assigment_giaodien.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assigment_giaodien.Database.DatabaseSQL;
import com.example.assigment_giaodien.Model.KhoanChi;
import com.example.assigment_giaodien.Model.KhoanThu;
import com.example.assigment_giaodien.R;

import java.util.List;

public class ThongkeKhoanchiAdapter extends RecyclerView.Adapter<ThongkeKhoanchiAdapter.Holder> {
    List<KhoanChi> khoanChiList;
    Context context;
    DatabaseSQL databaseSQL;

    public ThongkeKhoanchiAdapter(Context context, List<KhoanChi> khoanChiList, DatabaseSQL databaseSQL) {
        this.context = context;
        this.khoanChiList = khoanChiList;
        this.databaseSQL = databaseSQL;
    }


    @NonNull
    @Override
    public ThongkeKhoanchiAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.thongke_adapter, viewGroup, false);
        return new ThongkeKhoanchiAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongkeKhoanchiAdapter.Holder holder, int i) {
        KhoanChi khoanChi = khoanChiList.get(i);
        holder.id.setText(String.valueOf(i));
        holder.gia.setText(khoanChi.getMoney() + "");
    }

    @Override
    public int getItemCount() {
        return khoanChiList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView id, gia;

        public Holder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idthongke);
            gia = itemView.findViewById(R.id.gia_thongke);
        }
    }
}
