package com.example.cx62.rpk;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.FormUrlEncoded;

public interface API {


    @GET("getAllAbsen/{id}")
    Call<ResponBody> getAllAbsen(@Path("id") String id);

    @FormUrlEncoded
    @POST("login")
    Call<ResponBodyKaryawan> login (
            @Field("NIK") String NIK,
            @Field("Password") String Password,
            @Field("Device_ID") String Device_ID
    );

    @FormUrlEncoded
    @POST("register")
    Call<ResponBodyRegister> register(
      @Field("name") String name,
      @Field("nik") String nik,
      @Field("dev_id") String dev_id,
      @Field("password") String password,
      @Field("lat") String lat,
      @Field("long") String lng
    );

    @FormUrlEncoded
    @POST("absenmasuk")
    Call<ResponBodyRegister> absenmasuk(
            @Field("ID_Karyawan") String ID_Karyawan,
            @Field("lat") String lat,
            @Field("long") String lng
    );

    @FormUrlEncoded
    @POST("absenkeluar")
    Call<ResponBodyRegister> absenkeluar(
            @Field("ID_Karyawan") String ID_Karyawan,
            @Field("lat") String lat,
            @Field("long") String lng

    );

    @FormUrlEncoded
    @POST("buatcuti")
    Call<ResponBodyRegister> buatCuti(
            @Field("id_Karyawan") String id_Karyawan,
            @Field("tanggalMulai") String tglmulai,
            @Field("tanggalSelesai") String tglselesai,
            @Field("keterangan") String keterangan

    );

    @GET("getAllCuti/{id}")
    Call<ResponBodyHistoryCuti> getAllCuti(@Path("id") String id);



}
