package com.example.dashboard.model;

public class ResponseData {
    private Boolean data;
    private String message;

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
