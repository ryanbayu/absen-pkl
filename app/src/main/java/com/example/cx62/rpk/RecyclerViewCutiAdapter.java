package com.example.cx62.rpk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewCutiAdapter extends RecyclerView.Adapter<RecyclerViewCutiAdapter.ViewHolder> {

    List<CutiModel> listCuti;
    Context mContext;

    public RecyclerViewCutiAdapter(List<CutiModel> listCuti, Context mContext) {
        this.listCuti = listCuti;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // membuat view baru
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemcuti, viewGroup, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        RecyclerViewCutiAdapter.ViewHolder vh = new RecyclerViewCutiAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CutiModel data = listCuti.get(i);
        viewHolder.tglPengajuan.setText(data.getTanggalPengajuan());
        viewHolder.tglSelesai.setText(data.getTanggalSelesai());
        viewHolder.tglMulai.setText(data.getTanggalMulai());
        viewHolder.keterangan.setText(data.getKeterangan());
        viewHolder.status.setText (data.getStatus());
    }

    @Override
    public int getItemCount() {
        return listCuti.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView status, tglMulai, tglSelesai, tglPengajuan, keterangan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.tvStatusCuti);
            tglMulai = itemView.findViewById(R.id.tvTanggalMulai);
            tglSelesai = itemView.findViewById(R.id.tvTanggalSelesai);
            tglPengajuan = itemView.findViewById(R.id.tvTanggalPengajuan);
            keterangan = itemView.findViewById(R.id.tvKeterangan);

        }
    }
}
