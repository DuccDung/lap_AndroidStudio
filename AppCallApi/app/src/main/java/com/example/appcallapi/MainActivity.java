package com.example.appcallapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcallapi.api.ApiService;
import com.example.appcallapi.model.Product;

import com.example.appcallapi.api.ApiService;
import com.example.appcallapi.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);

        // Gọi API
        ApiService apiService = ApiService.apiService;

        Call<List<Product>> call = apiService.convertProduct();

        // Thực hiện bất đồng bộ
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(MainActivity.this, "call success", Toast.LENGTH_SHORT).show();
                    }
                }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                System.err.println("Lỗi khi gọi API: " + t.getMessage());
                Toast.makeText(MainActivity.this, "call fail", Toast.LENGTH_SHORT).show();

            }
        });

    }



}
