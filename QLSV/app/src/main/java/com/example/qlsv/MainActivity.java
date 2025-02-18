package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.qlsv.api_service.ApiService;
import com.example.qlsv.Leanners.*;
import com.example.qlsv.api_service.DataCallback;

import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {

    private ListView listViewLeanner;
    private AdapterCustom listAdapter;
    private List<Leanners> LeannersList = new ArrayList<>(); // Khởi tạo danh sách để tránh lỗi NullPointerException
    private Button btnAdd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewLeanner = findViewById(R.id.listViewMain);
        btnAdd = findViewById(R.id.btnAdd);

        // connect and Get data form server
        Connect(new DataCallback() {
            @Override
            public void onSuccess(List<Leanners> leannersList) {
                // Xử lý dữ liệu khi lấy thành công
                listAdapter = new AdapterCustom(MainActivity.this , leannersList);
                listViewLeanner.setAdapter(listAdapter);
                LeannersList.addAll(leannersList);
            }

            @Override
            public void onFailure(Throwable t) {
                // Xử lý lỗi
                Log.e("DATA_FAILURE", "Error: " + t.getMessage());
            }
        });

        // Xử Lý click item ListView
        listViewLeanner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy item được click
                Leanners selectedLeanner = LeannersList.get(position);
                // Hiển thị hoặc xử lý logic
                Toast.makeText(MainActivity.this, "Bạn đã chọn " + selectedLeanner.getFirstName() + "", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, LeannerDetailActivity.class);
                intent.putExtra("Interface" , selectedLeanner.getInterfacePath());
                intent.putExtra("LeannerID" , selectedLeanner.getLeannerId() + "");
                intent.putExtra("LeannerName" , selectedLeanner.getFirstName() + selectedLeanner.getLastName());
                intent.putExtra("LeannerDate" , selectedLeanner.getEnrollmentDate() + "");
                intent.putExtra("MajorId" , selectedLeanner.getMajorId() + "");

                startActivity(intent); // Chuyển sang SecondActivity
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AddLeannerActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Connect(DataCallback callback) {
        ApiService.apiService.GetLeanners().enqueue(new Callback<List<Leanners>>() {
            @Override
            public void onResponse(Call<List<Leanners>> call, Response<List<Leanners>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    List<Leanners> ListData = response.body();
                    callback.onSuccess(ListData); // Trả dữ liệu ra ngoài qua callback
                    Toast.makeText(MainActivity.this, "Có Dữ Liệu", Toast.LENGTH_SHORT).show();
                } else {
                    callback.onFailure(new Exception("Response is not successful or body is null"));
                }
            }

            @Override
            public void onFailure(Call<List<Leanners>> call, Throwable t) {
                callback.onFailure(t); // Trả lỗi ra ngoài qua callback
                Toast.makeText(MainActivity.this, "Connect Fail", Toast.LENGTH_SHORT).show();
                Log.e("API_FAILURE", "Error: " + t.getMessage());
            }
        });
    }
}