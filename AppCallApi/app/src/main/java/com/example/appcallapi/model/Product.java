package com.example.appcallapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    @SerializedName("mahang")
    private String maHang;

    @SerializedName("tenHang")
    private String tenHang;
    private String chatLieu;
    private int soLuong;
    private String donGiaNhap;
    private String donGiaBan;
    private String anh;
    private String chatLieuNavigation;
    private List<Object> tblChiTietHdbans;

    // Getters and setters
    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(String donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

    public String getDonGiaBan() {
        return donGiaBan;
    }

    public void setDonGiaBan(String donGiaBan) {
        this.donGiaBan = donGiaBan;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getChatLieuNavigation() {
        return chatLieuNavigation;
    }

    public void setChatLieuNavigation(String chatLieuNavigation) {
        this.chatLieuNavigation = chatLieuNavigation;
    }

    public List<Object> getTblChiTietHdbans() {
        return tblChiTietHdbans;
    }

    public void setTblChiTietHdbans(List<Object> tblChiTietHdbans) {
        this.tblChiTietHdbans = tblChiTietHdbans;
    }
}
