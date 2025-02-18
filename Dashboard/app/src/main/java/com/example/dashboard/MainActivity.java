package com.example.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dashboard.api_service.ApiService;
import com.example.dashboard.model.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        if (btnLogin == null) {
            Log.e("DEBUG", "btnLogin is null! Check your XML layout and ID.");
        } else {
            Log.d("DEBUG", "btnLogin initialized successfully.");
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = edtEmail.getText().toString();
                String Password = edtPassword.getText().toString();
                if(Email.isEmpty() || Password.isEmpty()){
                    return;
                }
               Login(Email , Password);
            }

            private void Login(String name, String password) {
                ApiService.apiService.Login(name, password).enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ResponseData data = response.body();
                            if (Boolean.TRUE.equals(data.getData())) {
                                Log.e("Login", "Success: " + data.getMessage());
                                Toast.makeText(MainActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                // Chuyển đến màn hình khác nếu cần
                            } else {
                                Log.e("Login", "Failed: " + data.getMessage());
                                Toast.makeText(MainActivity.this, "Đăng nhập thất bại: " + data.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Đăng nhập thất bại! Phản hồi không hợp lệ.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseData> call, Throwable t) {
                        Log.e("LoginError", t.getMessage(), t);
                        Toast.makeText(MainActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}