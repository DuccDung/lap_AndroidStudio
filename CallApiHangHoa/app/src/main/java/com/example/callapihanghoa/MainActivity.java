package com.example.callapihanghoa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.callapihanghoa.model.HangHoa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = findViewById(R.id.txtView);
        // Hàm Call Api

        ApiService.apiService.getHangHoa().enqueue(new retrofit2.Callback<List<HangHoa>>() {
            @Override
            public void onResponse(Call<List<HangHoa>> call, retrofit2.Response<List<HangHoa>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<HangHoa> hangHoaList = response.body();
                    // Handle the list of HangHoa (e.g., display in a RecyclerView)
                    for (HangHoa hangHoa : hangHoaList) {
                        System.out.println(hangHoa.getTenHang()); // Example: Print names
                        txtView.setText(hangHoa.getTenHang());
                    }
                } else {
                    System.out.println("Response failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<HangHoa>> call, Throwable t) {
                t.printStackTrace();
                txtView.setText(t.getMessage());

            }
        });

    }

}