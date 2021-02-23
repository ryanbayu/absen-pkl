package com.example.cx62.rpk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History_Cuti extends AppCompatActivity {

    private RecyclerView listCuti;
    private RecyclerViewCutiAdapter adapter;
    private List<CutiModel> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__cuti);

        dataSet = new ArrayList<>();

        Call<ResponBodyHistoryCuti> callHistoryCuti = RetrofitClient.getInstance().getAPI().getAllCuti(HasilLogin.ID_Karyawan);
        callHistoryCuti.enqueue(new Callback<ResponBodyHistoryCuti>() {
            @Override
            public void onResponse(Call<ResponBodyHistoryCuti> call, Response<ResponBodyHistoryCuti> response) {
                ResponBodyHistoryCuti respon = response.body();

                dataSet = respon.getListCuti();
                listCuti = (RecyclerView) findViewById(R.id.rvHistoryCuti);
                listCuti.setHasFixedSize(true);

                /**
                 * Kita menggunakan LinearLayoutManager untuk list standar
                 * yang hanya berisi daftar item
                 * disusun dari atas ke bawah
                 */
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(History_Cuti.this);
                listCuti.setLayoutManager(layoutManager);

                adapter = new RecyclerViewCutiAdapter(dataSet, History_Cuti.this);
                listCuti.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponBodyHistoryCuti> call, Throwable t) {
                Toast.makeText(History_Cuti.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}
