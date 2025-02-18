package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.qlsv.api_service.ApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeannerDetailActivity extends AppCompatActivity {

    private ImageView _Interface;
    private EditText _LeannerID;
    private EditText _LeannerName;
    private EditText _LeannerDate;
    private EditText _MajorId;

    private Button btnDeleteDetail;
    private Button btnUpdateDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leanner_detail);

        // Gắn các View sau khi setContentView
        _LeannerID = findViewById(R.id.edtLeannerIDDetail);
        _LeannerName = findViewById(R.id.edtNameDetail);
        _LeannerDate = findViewById(R.id.edtErrollmentDeatail);
        _MajorId = findViewById(R.id.edtMajorDeail);
        _Interface = findViewById(R.id.imgLeannerDetail);

        btnUpdateDetail = findViewById(R.id.btnUpdateDetail);
        btnDeleteDetail = findViewById(R.id.btnDeleteDetail);


        // Lấy dữ liệu từ Intent
        String Interface = getIntent().getStringExtra("Interface");
        String leannerID = getIntent().getStringExtra("LeannerID");
        String leannerName = getIntent().getStringExtra("LeannerName");
        String leannerDate = getIntent().getStringExtra("LeannerDate");
        String majorId = getIntent().getStringExtra("MajorId");

        // Gán dữ liệu vào các EditText
        _LeannerID.setText(leannerID);
        _LeannerName.setText(leannerName);
        _LeannerDate.setText(leannerDate);
        _MajorId.setText(majorId);

        // Hiển thị ảnh bằng Glide
        Glide.with(this)
                .load(ApiService.BASE_URL + Interface) // Đường dẫn ảnh
                .placeholder(R.drawable.placeholder) // Ảnh chờ
                .error(R.drawable.error) // Ảnh khi lỗi
                .into(_Interface); // Đặt vào ImageView


        btnUpdateDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(_LeannerID.getText().toString());
                String fullName = _LeannerName.getText().toString();
                int Major = Integer.parseInt(_MajorId.getText().toString());

                // Call Api

            }
        });
        btnDeleteDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int LeannerID = Integer.parseInt(_LeannerID.getText().toString());
                ApiService.apiService.deleteLeanner(LeannerID).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            // Response code is 200-299
                            Toast.makeText(LeannerDetailActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LeannerDetailActivity.this , MainActivity.class);
                            startActivity(intent);
                        } else {
                            // Response is not successful, handle error
                            Toast.makeText(LeannerDetailActivity.this, "Xóa thất bại: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }
}
