package com.example.lap1;

public class SinhVien {
    private String Name;

    @Override
    public String toString() {
        return "SinhVien{" +
                "Name='" + Name + '\'' +
                '}';
    }

    public SinhVien(String name, String phone, String address){
        this.Name = name;

    }
    // Optional: Add getters for the fields
    public String getName() {
        return Name;
    }



}
