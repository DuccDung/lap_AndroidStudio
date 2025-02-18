package com.example.dashboard.api_service;

import com.example.dashboard.model.Hang;
import com.example.dashboard.model.ResponseData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://192.168.0.107:7012/")
            .client(SSLUtils.getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    //https://192.168.137.1:7012/api/Home/get-hangs
    @GET("api/Home/get-hangs")
    Call<List<Hang>>GetAllProduct();


    // https://192.168.50.76:7012/api/Home/post-login?Name=%C4%90%C3%B4ng&Password=001
    @GET("api/Home/post-login")
    Call<ResponseData> Login(
            @Query("Name") String name,     // Trường 'Name' từ API
            @Query("Password") String password // Trường 'Password' từ API
    );
}
