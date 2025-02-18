package com.example.smartiot;

import com.google.gson.annotations.SerializedName;

public class DeviceFunction {
    @SerializedName("deviceID")
    private String deviceID;

    @SerializedName("nameDevice")
    private String nameDevice;

    @SerializedName("urlMQTT")
    private String urlMQTT;

    @SerializedName("userMQTT")
    private String userMQTT;

    @SerializedName("passwordMQTT")
    private String passwordMQTT;

    @SerializedName("functionID")
    private String functionID;

    @SerializedName("description")
    private String description;

    // Constructor
    public DeviceFunction(String deviceID, String nameDevice, String urlMQTT, String userMQTT,
                          String passwordMQTT, String functionID, String description) {
        this.deviceID = deviceID;
        this.nameDevice = nameDevice;
        this.urlMQTT = urlMQTT;
        this.userMQTT = userMQTT;
        this.passwordMQTT = passwordMQTT;
        this.functionID = functionID;
        this.description = description;
    }

    // Getters
    public String getDeviceID() {
        return deviceID;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public String getUrlMQTT() {
        return urlMQTT;
    }

    public String getUserMQTT() {
        return userMQTT;
    }

    public String getPasswordMQTT() {
        return passwordMQTT;
    }

    public String getFunctionID() {
        return functionID;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public void setUrlMQTT(String urlMQTT) {
        this.urlMQTT = urlMQTT;
    }

    public void setUserMQTT(String userMQTT) {
        this.userMQTT = userMQTT;
    }

    public void setPasswordMQTT(String passwordMQTT) {
        this.passwordMQTT = passwordMQTT;
    }

    public void setFunctionID(String functionID) {
        this.functionID = functionID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString() - Để hiển thị dễ dàng
    @Override
    public String toString() {
        return "DeviceFunction{" +
                "deviceID='" + deviceID + '\'' +
                ", nameDevice='" + nameDevice + '\'' +
                ", urlMQTT='" + urlMQTT + '\'' +
                ", userMQTT='" + userMQTT + '\'' +
                ", passwordMQTT='" + passwordMQTT + '\'' +
                ", functionID='" + functionID + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
