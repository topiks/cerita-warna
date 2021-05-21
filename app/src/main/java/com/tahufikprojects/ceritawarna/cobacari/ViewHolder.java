package com.tahufikprojects.ceritawarna.cobacari;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tahufikprojects.ceritawarna.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mview;

    public ViewHolder(@NonNull View itemView)
    {
        super(itemView);
        mview = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context ctx, String namaJurusan, String namaKampus, String inisialisasi, String judulArtikel, String isiArtikel, String namaSaya, String pesanSaya)
    {
        if(inisialisasi.equals("jurusan") || inisialisasi.equals("kampus"))
        {
            TextView mJurusan = mview.findViewById(R.id.nama_jurusan_hasil);
            TextView mKampus = mview.findViewById(R.id.list_kampus_hasil);

            mJurusan.setText(namaJurusan);
            mKampus.setText(namaKampus);
        }
        else if(inisialisasi.equals("artikel"))
        {
            TextView mJudul = mview.findViewById(R.id.judul_artikel_dari_list);
            TextView mIsi = mview.findViewById(R.id.isi_artikel_dari_list);

            mJudul.setText(judulArtikel);
            mIsi.setText(isiArtikel);
        }
        else if(inisialisasi.equals("forum_saya"))
        {
            TextView mNama = mview.findViewById(R.id.nama_jurusan_pesan_saya);
            TextView mMsg = mview.findViewById(R.id.list_kampus_pesan_saya);

            mNama.setText(namaSaya);
            mMsg.setText(pesanSaya);
        }

    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener)
    {
        mClickListener = clickListener;
    }

}
