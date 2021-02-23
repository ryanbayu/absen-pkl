package com.example.cx62.rpk;

import android.graphics.Color;
import android.media.browse.MediaBrowser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener {

    public static final int RequestPermissionCode = 1;
    private GoogleApiClient googleApiClient;
    private FusedLocationProviderClient fusedLocationProviderClient;


    Button Buttona, Buttonb;
    TextView Device_ID, Latitude, Longitude;
    EditText inputNIK, inputPassword;

    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Device_ID = findViewById(R.id.Device_ID);
        inputNIK = findViewById(R.id.inputNIK);
        inputPassword = findViewById(R.id.inputPassword);

        Latitude = (TextView)findViewById(R.id.Latitude);
        Longitude = (TextView)findViewById(R.id.Longitude);



        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                1);






        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        Buttona = (Button) findViewById(R.id.buttonsignin);
        Buttona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Buttona.setBackgroundColor(Color.rgb(54, 53, 59));
                Buttona.setEnabled(false);



                    String NIK = inputNIK.getText().toString();
                    String Password = inputPassword.getText().toString();
                    final String devid = Device_ID.getText().toString();

                    Call<ResponBodyKaryawan> callLogin = RetrofitClient.getInstance().getAPI().login(NIK,Password,devid);
                    callLogin.enqueue(new Callback<ResponBodyKaryawan>() {
                        @Override
                        public void onResponse(Call<ResponBodyKaryawan> call, Response<ResponBodyKaryawan> response) {
                            if (response.code() == 200){
                                HasilLogin.Device_ID = devid;
                                HasilLogin.ID_Karyawan = response.body().data.ID_Karyawan;
                                HasilLogin.NIK = response.body().data.getNIK();
                                HasilLogin.lat = ""+lat;
                                HasilLogin.lng = ""+lng;
                                Intent i = new Intent(MainActivity.this, dashboard.class);
                                startActivity(i);
                            }else if (response.code() == 201){
                                Toast.makeText(MainActivity.this, "Anda Masih Berstatus Pending", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                            Buttona.setEnabled(true);
                            Buttona.setBackgroundColor(Color.rgb(214, 118, 1));

                        }

                        @Override
                        public void onFailure(Call<ResponBodyKaryawan> call, Throwable t) {
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Buttona.setEnabled(true);
                            Buttona.setBackgroundColor(Color.rgb(214, 118, 1));
                        }
                    });



            }
        });

        Buttonb = (Button) findViewById(R.id.buttonregister);
        Buttonb.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent i = new Intent(MainActivity.this, registrasi.class);
                                           i.putExtra("Device_ID", Device_ID.getText().toString());
                                           i.putExtra("Latitude", ""+lat);
                                           i.putExtra("Longitude", ""+lng);
                                           startActivity(i);
                                       }
                                   }

        );
    }
    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
        super.onStop();

    }
    @Override
    public void onConnectionSuspended(int i) {
        Log.e("MainActivity", "Connection Suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("MainActivity", "Connection Failed" + connectionResult.getErrorCode());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }else{
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                          lng = location.getLongitude();
                          lat=location.getLatitude();
                            Latitude.setText(""+lng);
                            Longitude.setText(""+lat);


                        }
                    });
        }


    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, RequestPermissionCode);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
                    Device_ID.setText(tm.getDeviceId());

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}


