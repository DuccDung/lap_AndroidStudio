package com.example.callapi4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.callapi4.api.ApiService;
import com.example.callapi4.api.SSLUtils;
import com.example.callapi4.model.Hang;

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
                            List<Hang> hangList = response.body();
                            // Dữ liệu đã được ánh xạ thành công về List<Hang>
                            for (Hang hang : hangList) {
                                Log.d("Hang", "Tên Hàng: " + hang.getTenHang());
                            }
                        } else {
                            Log.e("API_ERROR", "Không nhận được dữ liệu hoặc lỗi từ API");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Hang>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Call Fail", Toast.LENGTH_SHORT).show();
                        txtCallApi.setText(t.getMessage());
                    }
                });
            }
        });
    }

}