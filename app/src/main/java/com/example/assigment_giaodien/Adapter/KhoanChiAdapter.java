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
import com.example.assigment_giaodien.Model.KhoanChi;
import com.example.assigment_giaodien.Model.KhoanThu;
import com.example.assigment_giaodien.R;

import java.util.List;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.Holder> {
    private List<KhoanChi> khoanChiList;
    private DatabaseSQL databaseSQL;
    private Context context;

    public KhoanChiAdapter(Context context, List<KhoanChi> khoanChiList, DatabaseSQL databaseSQL) {
        this.context = context;
        this.khoanChiList = khoanChiList;
        this.databaseSQL = databaseSQL;
    }


    @NonNull
    @Override
    public KhoanChiAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.khoanchi_adapter, viewGroup, false);
        return new KhoanChiAdapter.Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiAdapter.Holder holder, final int i) {
        final KhoanChi khoanChi = khoanChiList.get(i);
        holder.tvid.setText(String.valueOf(i));
        holder.tvname.setText(khoanChi.getName() + "");
        holder.tvgia.setText(khoanChi.getMoney() + "");
        holder.tvngay.setText(khoanChi.getDay() + "");
        holder.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(khoanChiList.get(i).getName());
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
                        KhoanChi khoanChi1=new KhoanChi();
                        String name = edname.getText().toString().trim();
                        long gia = Long.parseLong(edgia.getText().toString().trim());
                        String ngay = edngay.getText().toString().trim();

                        databaseSQL.updateKhoanchi(khoanChi1);
                        khoanChiList.get(i).setName(name);
                        khoanChiList.get(i).setMoney(gia);
                        khoanChiList.get(i).setDay(ngay);
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
        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseSQL.deleteKhoanchi(khoanChiList.get(i).getId());
                khoanChiList.get(i);
                khoanChiList.remove(khoanChi);
                notifyDataSetChanged();
                Toast.makeText(context, "Đã Xóa", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return khoanChiList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView tvid, tvname, tvgia, tvngay;
        ImageView sua, xoa;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.id_khoanchi);
            tvname = itemView.findViewById(R.id.name_khoanchi);
            tvgia = itemView.findViewById(R.id.tvgia_khoanchi);
            tvngay = itemView.findViewById(R.id.tvngay_khoanchi);
            sua = itemView.findViewById(R.id.sua_khoanchi);
            xoa = itemView.findViewById(R.id.xoa_khoanchi);

        }
    }
}
