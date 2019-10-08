package com.example.assigment_giaodien.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assigment_giaodien.Database.DatabaseSQL;
import com.example.assigment_giaodien.Model.KhoanThu;
import com.example.assigment_giaodien.Model.LoaiThu;
import com.example.assigment_giaodien.R;

import java.util.List;

public class ThongKeAdapter extends RecyclerView.Adapter<ThongKeAdapter.Holder> {
    List<KhoanThu> khoanThuList;
    Context context;
    DatabaseSQL databaseSQL;

    public ThongKeAdapter(Context context, List<KhoanThu> khoanThuList, DatabaseSQL databaseSQL) {
        this.context = context;
        this.khoanThuList = khoanThuList;
        this.databaseSQL=databaseSQL;
    }

    @NonNull
    @Override
    public ThongKeAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.thongke_adapter, viewGroup, false);
        return new ThongKeAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongKeAdapter.Holder holder, int i) {
        KhoanThu khoanThu = khoanThuList.get(i);
        holder.id.setText(String.valueOf(i));
        holder.gia.setText(khoanThu.getMoney() + "");
    }

    @Override
    public int getItemCount() {
        return khoanThuList.size();
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
