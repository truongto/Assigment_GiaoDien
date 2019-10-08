package com.example.assigment_giaodien.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment_giaodien.Database.DatabaseSQL;
import com.example.assigment_giaodien.LoaiThuFragment;
import com.example.assigment_giaodien.Model.LoaiThu;
import com.example.assigment_giaodien.R;

import java.util.List;

public class LoaithuAdapter extends RecyclerView.Adapter<LoaithuAdapter.Holder> {
    List<LoaiThu> loaiThuList;
    Context context;
    DatabaseSQL databaseSQL;

    public LoaithuAdapter(Context context, List<LoaiThu> loaiThuList, DatabaseSQL databaseSQL) {
        this.context = context;
        this.loaiThuList = loaiThuList;
        this.databaseSQL = databaseSQL;
    }



    @NonNull
    @Override
    public LoaithuAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.loaithu_adapter, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LoaithuAdapter.Holder holder, final int i) {
        final LoaiThu loaiThu = loaiThuList.get(i);
        holder.tvid.setText(String.valueOf(i));
        holder.tvname.setText(loaiThu.getName() + "");


        holder.imgsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(loaiThuList.get(i).getName());
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
                        LoaiThu loaiThu1 = new LoaiThu();
                        String edsua = edsualoaithu.getText().toString().trim();

                        databaseSQL.updateLoaithu(loaiThu1);
                        loaiThuList.get(i).setName(edsua);
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
                databaseSQL.deleteLoaithu(loaiThuList.get(i).getId());
                loaiThuList.get(i);
                loaiThuList.remove(loaiThu);
                notifyDataSetChanged();
                Toast.makeText(context, "Đã Xóa", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return loaiThuList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvid, tvname;
        private ImageView imgsua, imgxoa;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvid = itemView.findViewById(R.id.id_loaithu);
            tvname = itemView.findViewById(R.id.name_loaithu);
            imgsua = itemView.findViewById(R.id.sua_loaithu);
            imgxoa = itemView.findViewById(R.id.xoa_loaithu);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String name);
    }


    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
