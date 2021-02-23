package com.example.cx62.rpk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telecom.CallScreeningService;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Cuti extends AppCompatActivity {

    private DatePickerDialog datePickerDialog,datePickerDialogb;
    private SimpleDateFormat dateFormatter,dateFormatterb;
    private TextView tvDateResult,tvDateResultb;
    private Button btDatePicker,btDatePickerb;

    Button submitcuti;
    EditText keterangan;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuti);
        /**
         * Kita menggunakan format tanggal dd-MM-yyyy
         * jadi nanti tanggal nya akan diformat menjadi
         * misalnya 01-12-2017
         */
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        tvDateResult = (TextView) findViewById(R.id.tv_dateresult);
        btDatePicker = (Button) findViewById(R.id.bt_datepicker);
        tvDateResultb = (TextView) findViewById(R.id.tv_dateresultb);
        btDatePickerb = (Button) findViewById(R.id.bt_datepickerb);
        submitcuti = findViewById(R.id.submitcuti);
        keterangan = findViewById(R.id.keterangan);

        btDatePickerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(2);
            }
        });

        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(1);
            }
    });

        submitcuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Cuti.this, keterangan.getText().toString(),Toast.LENGTH_SHORT).show();
                Call<ResponBodyRegister> callBuatCuti = RetrofitClient.getInstance().getAPI().buatCuti
                        (HasilLogin.ID_Karyawan, tvDateResult.getText().toString(), tvDateResultb.getText().toString(), keterangan.getText().toString());
                callBuatCuti.enqueue(new Callback<ResponBodyRegister>() {
                    @Override
                    public void onResponse(Call<ResponBodyRegister> call, Response<ResponBodyRegister> response) {
                        Toast.makeText(Cuti.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponBodyRegister> call, Throwable t) {
                        Toast.makeText(Cuti.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

}
    private void showDateDialog(int id){

        final int ids = id;

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                if (ids == 1){
                    tvDateResult.setText(dateFormatter.format(newDate.getTime()));
                }else{
                    tvDateResultb.setText(dateFormatter.format(newDate.getTime()));

                }
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

}

