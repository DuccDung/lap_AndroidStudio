package com.example.baitap;

public class SinhVien {
    private String Name;
    private String Phone;
    private String Address;

    public SinhVien(String name, String phone, String address){
        this.Name = name;
        this.Phone = phone;
        this.Address = address;
    }
    // Optional: Add getters for the fields
    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }
    @Override
    public String toString() {
        // Hiển thị thông tin của SinhVien dưới dạng chuỗi
        return Name + " - " + Phone + " - " + Address;
    }
}
