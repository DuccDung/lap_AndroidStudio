package com.example.recycleview;

public class User {
    private int img;
    private String name;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public User(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
