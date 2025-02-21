package com.example.lap1_1.model;

public class SpinnerItem {
    private String name;
    private boolean isChecked;

    public SpinnerItem(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
