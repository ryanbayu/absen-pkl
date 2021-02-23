package com.example.cx62.rpk;

public class CutiModel {

    String tanggalPengajuan, tanggalMulai, tanggalSelesai, keterangan, status;

    public CutiModel(String tanggalPengajuan, String tanggalMulai, String tanggalSelesai, String keterangan, String status) {
        this.tanggalPengajuan = tanggalPengajuan;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.keterangan = keterangan;
        this.status = status;
    }

    public String getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(String tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public String getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(String tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public String getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(String tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
