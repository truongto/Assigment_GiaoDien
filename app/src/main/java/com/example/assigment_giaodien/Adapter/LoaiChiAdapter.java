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
import com.example.assigment_giaodien.Model.LoaiChi;
import com.example.assigment_giaodien.Model.LoaiThu;
import com.example.assigment_giaodien.R;

import java.util.List;

public class LoaiChiAdapter extends RecyclerView.Adapter<LoaiChiAdapter.Holder> {
    List<LoaiChi> loaiChiList;
    Context context;
    DatabaseSQL databaseSQL;

    public LoaiChiAdapter(Context context, List<LoaiChi> loaiChiList, DatabaseSQL databaseSQL) {
        this.context=context;
        this.loaiChiList=loaiChiList;
        this.databaseSQL=databaseSQL;
    }

    @NonNull
    @Override
    public LoaiChiAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.loaichi_adapter, viewGroup, false);
        return new LoaiChiAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiAdapter.Holder holder, final int i) {

        final LoaiChi loaiChi = loaiChiList.get(i);
        holder.tvid.setText(String.valueOf(i));
        holder.tvname.setText(loaiChi.getName() + "");


        holder.imgsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(loaiChiList.get(i).getName());
                dialog.setContentView(R.layout.dialog_sua_loaithu);

                final EditText edsualoaithu;
                final Button huy;
                final Button sua;
                edsualoaithu = dialog.findViewById(R.id.edit_loaithu);
                huy = dialog.findViewById(R.id.huy_dialogthem);
                sua = dialog.findViewById(R.id.them_dialogthem);
                sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      LoaiChi loaiChi1 =new LoaiChi();
                        String edsua = edsualoaithu.getText().toString().trim();

                        databaseSQL.updateLoaichi(loaiChi1);
                        loaiChiList.get(i).setName(edsua);
                        notifyDataSetChanged();
                        dialog.dismiss();
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
        holder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseSQL.deleteLoaichi(loaiChiList.get(i).getId());
                loaiChiList.get(i);
                loaiChiList.remove(loaiChi);
                notifyDataSetChanged();
                Toast.makeText(context, "Đã Xóa", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return loaiChiList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvid, tvname;
        private ImageView imgsua, imgxoa;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.id_loaichi);
            tvname = itemView.findViewById(R.id.name_loaichi);
            imgsua = itemView.findViewById(R.id.sua_loaichi);
            imgxoa = itemView.findViewById(R.id.xoa_loaichi);
        }
    }
}
