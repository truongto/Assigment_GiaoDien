package com.example.assigment_giaodien.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment_giaodien.Database.DatabaseSQL;
import com.example.assigment_giaodien.Model.KhoanThu;
import com.example.assigment_giaodien.Model.LoaiThu;
import com.example.assigment_giaodien.R;

import java.util.List;

public class KhoanthuAdapter extends RecyclerView.Adapter<KhoanthuAdapter.HolDer> {
    Context context;
    List<KhoanThu> khoanThuList;
    DatabaseSQL databaseSQL;

    public KhoanthuAdapter(Context context, List<KhoanThu> khoanThuList, DatabaseSQL databaseSQL) {
        this.context = context;
        this.khoanThuList = khoanThuList;
        this.databaseSQL = databaseSQL;
    }

    @NonNull
    @Override
    public KhoanthuAdapter.HolDer onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.khoanthu_adapter, viewGroup, false);
        return new HolDer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanthuAdapter.HolDer holDer, final int i) {
        final KhoanThu khoanThu = khoanThuList.get(i);
        holDer.tvid.setText(String.valueOf(i));
        holDer.tvname.setText(khoanThu.getName() + "");
        holDer.tvgia.setText(khoanThu.getMoney() + "");
        holDer.tvngay.setText(khoanThu.getDay() + "");
        holDer.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseSQL.deleteKhoanthu(khoanThuList.get(i).getId());
                khoanThuList.get(i);
                khoanThuList.remove(khoanThu);
                notifyDataSetChanged();
                Toast.makeText(context, "Đã Xóa", Toast.LENGTH_SHORT).show();
            }
        });
        holDer.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(khoanThuList.get(i).getName());
                dialog.setContentView(R.layout.dialog_sua_khoanthu);

                final EditText edname, edgia, edngay;
                final Button bthuy;
                final Button btsua;
                edname = dialog.findViewById(R.id.edit_suaname_khoanthu);
                edgia = dialog.findViewById(R.id.edit_suagia_khoanthu);
                edngay = dialog.findViewById(R.id.edit_suangay_khoanthu);
                bthuy = dialog.findViewById(R.id.huy_dialogsua_khoanthu);
                btsua = dialog.findViewById(R.id.sua_dialogsua_khoanthu);
                btsua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        KhoanThu khoanThu1 = new KhoanThu();
                        String name = edname.getText().toString().trim();
                        long gia = Long.parseLong(edgia.getText().toString().trim());
                        String ngay =edngay.getText().toString().trim();

                        databaseSQL.updateKhoanthu(khoanThu1);
                        khoanThuList.get(i).setName(name);
                        khoanThuList.get(i).setMoney(gia);
                        khoanThuList.get(i).setDay(ngay);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                bthuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return khoanThuList.size();
    }

    public class HolDer extends RecyclerView.ViewHolder {
        TextView tvid, tvname, tvgia, tvngay;
        ImageView sua, xoa;

        public HolDer(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.id_khoanthu);
            tvname = itemView.findViewById(R.id.name_khoanthu);
            tvgia = itemView.findViewById(R.id.tvgia_khoanthu);
            tvngay = itemView.findViewById(R.id.tvngay_khoanthu);
            sua = itemView.findViewById(R.id.sua_khoanthu);
            xoa = itemView.findViewById(R.id.xoa_khoanthu);

        }
    }
}
