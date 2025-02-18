package com.example.callapihanghoa;

import com.example.callapihanghoa.model.HangHoa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {

    // Configure Gson for parsing JSON
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss") // Adjust date format as needed
            .create();

    // Retrofit instance
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.36:5000/") // Replace with the actual server address
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    // API endpoint to fetch the list of HangHoa
    @GET("api/HangHoa")
    Call<List<HangHoa>> getHangHoa(); // Renamed method to follow Java naming conventions
}
