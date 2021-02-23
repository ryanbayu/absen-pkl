package com.example.cx62.rpk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {

    Button Buttonc,Buttond, ButtonHistory;
    TextView tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Buttonc = (Button) findViewById(R.id.submitcuti);
        tvId = findViewById(R.id.tv_IDKaryawan);
        tvId.setText(HasilLogin.NIK);
        Buttonc.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent i = new Intent(dashboard.this, Cuti.class);
                                           startActivity(i);
                                       }
                                   }

        );
        Buttond = (Button) findViewById(R.id.submitabsen);
        Buttond.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(dashboard.this, Absen.class);
                        startActivity(i);
                    }
                }
        );

        ButtonHistory = findViewById(R.id.HistoryCuti);
        ButtonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this, History_Cuti.class);
                startActivity(i);
            }
        });

    }

}
