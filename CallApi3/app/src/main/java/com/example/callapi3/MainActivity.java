package com.example.callapi3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.callapi3.api.ApiService;
import com.example.callapi3.model.Hang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button btnCallApi;
    private TextView txtCallApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCallApi = findViewById(R.id.btncallapi);
        txtCallApi = findViewById(R.id.txtcallapi);


        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallApiWeb();
            }

            private void CallApiWeb() {
                ApiService.apiService.GetAllHang().enqueue(new Callback<List<Hang>>() {
                    @Override
                    public void onResponse(Call<List<Hang>> call, Response<List<Hang>> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(MainActivity.this, "Call Success", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Hang>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Call Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}