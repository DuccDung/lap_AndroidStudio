package com.example.callapi3.model;

import java.util.List;

public class Hang {
    private String maHang;
    private String tenHang;
    private String chatLieu;
    private int soLuong;
    private String donGiaNhap;
    private String donGiaBan;
    private String anh;
    private Object chatLieuNavigation;
    private List<Object> tblChiTietHdbans;

    // Getters và Setters
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

    public Object getChatLieuNavigation() {
        return chatLieuNavigation;
    }

    public void setChatLieuNavigation(Object chatLieuNavigation) {
        this.chatLieuNavigation = chatLieuNavigation;
    }

    public List<Object> getTblChiTietHdbans() {
        return tblChiTietHdbans;
    }

    public void setTblChiTietHdbans(List<Object> tblChiTietHdbans) {
        this.tblChiTietHdbans = tblChiTietHdbans;
    }
}
