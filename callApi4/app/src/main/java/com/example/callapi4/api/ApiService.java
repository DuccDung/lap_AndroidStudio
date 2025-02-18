package com.example.callapi4.api;

import com.example.callapi4.model.Hang;
import com.example.callapi4.model.Hang;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {
    //https://192.168.137.1:7012/api/Home/get-hangs
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://192.168.0.107:7012/")
            .client(SSLUtils.getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/Home/get-hangs")
    Call<List<Hang>> GetAllHang();

}
