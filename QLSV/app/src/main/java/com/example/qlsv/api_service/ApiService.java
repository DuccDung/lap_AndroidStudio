package com.example.qlsv.api_service;

import com.example.qlsv.Leanners;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //https://192.168.0.107:7012/api/Leanners/get-leanners

    // Base URL
    String BASE_URL = "https://192.168.0.101:7012/";
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
    @GET("api/Leanners/get-leanners")
    Call<List<Leanners>>GetLeanners();


    // DELETE method for deleting a learner by ID
    @DELETE("api/Leanners/{id}")
    Call<Void> deleteLeanner(@Path("id") int id);

    @Multipart
    @POST("api/Leanners/Post_Leanner") // Thay đổi endpoint nếu cần
    Call<ResponseBody> uploadImage(
            @Query("FistName") String firstName,
            @Query("LastName") String lastName,
            @Query("MajorId") int majorId,
            @Part MultipartBody.Part ImageInterface
    );
}
