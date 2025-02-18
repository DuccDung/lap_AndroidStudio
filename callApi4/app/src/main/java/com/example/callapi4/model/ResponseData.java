package com.example.callapi4.model;

public class ResponseData {
    private Boolean Data;
    private String Message;

    public Boolean getData() {
        return Data;
    }

    public void setData(Boolean data) {
        Data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "Data=" + Data +
                ", Message='" + Message + '\'' +
                '}';
    }
}
