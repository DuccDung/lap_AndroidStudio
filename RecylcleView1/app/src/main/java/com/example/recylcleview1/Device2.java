package com.example.recylcleview1;

public class Device2 {
    private String Name;
    private int type;

    public static final int   TYPE_LAMP = 1;
    public static final int   TYPE_AIR = 2;
    public static final int   TYPE_WATER = 3;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Device2(String text, int type) {
        this.Name = text;
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
