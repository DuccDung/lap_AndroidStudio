package com.example.recycleview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Gắn layout chính

        // Khởi tạo RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rcv_user);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        // Danh sách dữ liệu mẫu
        List<Device> devices = Arrays.asList(
                new Device("1", "Air Conditioner", "Entrance", "AC", true, "23°C Mode: Auto"),
                new Device("2", "Speaker", "Bedroom", "Speaker", false, null),
                new Device("3", "Smart TV", "Living Room", "Light", true, null),
                new Device("4", "Ceiling Light", "Kids Room", "Light", false, null)
        );

        // Adapter
        DeviceAdapter adapter = new DeviceAdapter(devices, device -> {
            // Xử lý khi click vào item
            Toast.makeText(this, "Clicked: " + device.getName(), Toast.LENGTH_SHORT).show();
        });

        // Gắn adapter cho RecyclerView
        recyclerView.setAdapter(adapter);
    }
}
