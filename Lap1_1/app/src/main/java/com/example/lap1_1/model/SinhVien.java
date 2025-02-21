package com.example.lap1_1.model;

import android.net.Uri;

public class SinhVien {
    private int Id;
    private String Name;
    private String Phone;
    private String Gen;
    private String Hobby;

    public String getHobby() {
        return Hobby;
    }

    public void setHobby(String hobby) {
        Hobby = hobby;
    }

//    public SinhVien(int id, String name, String phone, String gen, String address, Uri selectedImageUri) {
//        Id = id;
//        Name = name;
//        Phone = phone;
//        Gen = gen;
//        Address = address;
//        this.selectedImageUri = selectedImageUri;
//    }

    public SinhVien(int id, String name, String phone, String gen, String hobby, String address, Uri selectedImageUri) {
        Id = id;
        Name = name;
        Phone = phone;
        Gen = gen;
        Hobby = hobby;
        Address = address;
        this.selectedImageUri = selectedImageUri;
    }

    public String getGen() {
        return Gen;
    }

    public void setGen(String gen) {
        Gen = gen;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    private String Address;
    private Uri selectedImageUri;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Uri getSelectedImageUri() {
        return selectedImageUri;
    }

    public void setSelectedImageUri(Uri selectedImageUri) {
        this.selectedImageUri = selectedImageUri;
    }
}
