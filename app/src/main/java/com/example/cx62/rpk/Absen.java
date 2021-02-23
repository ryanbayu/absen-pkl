package com.example.cx62.rpk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Absen extends AppCompatActivity {

    Button Buttone, btnMasuk, btnKeluar;
    TextView tvNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);

        Buttone = (Button) findViewById(R.id.submithistory);
        btnMasuk = findViewById(R.id.btnAbsenMasuk);
        btnKeluar = findViewById(R.id.btnAbsenKeluar);
        tvNow = findViewById(R.id.tvTanggalNow);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateandTime = sdf.format(new Date());

        tvNow.setText(currentDateandTime);


        Buttone.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent i = new Intent(Absen.this, History.class);
                                           startActivity(i);
                                       }
                                   }
        );

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponBodyRegister> callAbsenMasuk = RetrofitClient.getInstance().getAPI().absenmasuk(HasilLogin.ID_Karyawan,HasilLogin.lat,HasilLogin.lng);
                callAbsenMasuk.enqueue(new Callback<ResponBodyRegister>() {
                    @Override
                    public void onResponse(Call<ResponBodyRegister> call, Response<ResponBodyRegister> response) {

                        Toast.makeText(Absen.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponBodyRegister> call, Throwable t) {

                        Toast.makeText(Absen.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponBodyRegister> callAbsenKeluar = RetrofitClient.getInstance().getAPI().absenkeluar(HasilLogin.ID_Karyawan, HasilLogin.lat,HasilLogin.lng);
                callAbsenKeluar.enqueue(new Callback<ResponBodyRegister>() {
                    @Override
                    public void onResponse(Call<ResponBodyRegister> call, Response<ResponBodyRegister> response) {
                        Toast.makeText(Absen.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponBodyRegister> call, Throwable t) {
                        Toast.makeText(Absen.this,  t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });
    }
}
