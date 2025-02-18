package com.example.smartiot.api_service;

import com.example.smartiot.DeviceFunction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //https://192.168.0.107:7012/api/Leanners/get-leanners

    // Base URL
    String BASE_URL = "https://192.168.50.222:7012/";
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(SSLUtils.getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    // Static method to get the base domain
    static String getBaseDomain() {
        return BASE_URL;
    }
    @GET("api/Device/GetDeviceFunction")
    Call<List<DeviceFunction>> GetDeviceFunction();
}
