package com.example.recycleview;

public class Device {
    private String id;
    private String name;
    private String location;
    private String type; // "AC", "Speaker", "Light"
    private boolean status; // true = On, false = Off
    private String additionalInfo;

    // Constructor
    public Device(String id, String name, String location, String type, boolean status, String additionalInfo) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.status = status;
        this.additionalInfo = additionalInfo;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getType() { return type; }
    public boolean isStatus() { return status; }
    public String getAdditionalInfo() { return additionalInfo; }

    public void setStatus(boolean status) { this.status = status; }
}

