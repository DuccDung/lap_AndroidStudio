package com.example.smartiot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.smartiot.api_service.ApiService;
import com.example.smartiot.api_service.DataCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MqttHandler.MqttListener { // ✅ Thêm Interface để nhận dữ liệu từ MQTT
    private MqttHandler mqttHandler;
    private DeviceFunctionAdapter adapter;
    private RecyclerView rcvMain;
    private List<DeviceFunction> deviceList = new ArrayList<>(); // ✅ Di chuyển danh sách ra ngoài để cập nhật dễ dàng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvMain = findViewById(R.id.rcv_device);
        rcvMain.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));



        // ✅ Gọi API để lấy danh sách thiết bị từ server
        CallApiGetDeviceFunction(new DataCallback<List<DeviceFunction>>() {
            @Override
            public void onSuccess(List<DeviceFunction> data) {
                deviceList.clear();
                deviceList.addAll(data);

                // ✅ Gọi kết nối MQTT
                ConnectMQTT();

                // ✅ Khởi tạo Adapter trước, nhưng chưa có dữ liệu
                adapter = new DeviceFunctionAdapter(deviceList , mqttHandler);
                rcvMain.setAdapter(adapter);
                adapter.notifyDataSetChanged(); // ✅ Cập nhật RecyclerView khi có dữ liệu mới

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Lỗi MainActivity call Api", "onFailure: " + t.getMessage());
            }
        });


    }

    private void ConnectMQTT() {
        // ✅ Đảm bảo `MainActivity` triển khai `MqttHandler.MqttListener`
        mqttHandler = new MqttHandler(this);

        // Thông số kết nối
        String brokerUrl = "bbb04b2c528242358401e2add20b296a.s1.eu.hivemq.cloud";
        int port = 8883;
        String clientId = "device_13233434";
        String username = "hivemq.webclient.1735043576854";
        String password = "rAhT10mH$8Btos;6I,Z.";

        // ✅ Kết nối với MQTT broker
        mqttHandler.connect(brokerUrl, port, clientId, username, password);

        // ✅ Đăng ký topic để lắng nghe dữ liệu b
       // mqttHandler.subscribe("airConditioner_1");

        // ✅ Kiểm tra danh sách trước khi subscribe
        if (deviceList == null || deviceList.isEmpty()) {
            Log.e("MQTT", "Device list is empty, skipping subscription.");
            return;
        }
        // ✅ Subscribe tất cả các device ID trong danh sách
        for (DeviceFunction device : deviceList) {
            String topic = device.getDeviceID();
            Log.d("MQTT", "Subscribing to topic: " + topic);  // Kiểm tra xem có đúng ID không
            mqttHandler.subscribe(topic);
        }

    }

    // ✅ Nhận dữ liệu từ MQTT và cập nhật RecyclerView Adapter
    @Override
    public void onMessageReceived(String topic, String payload) {
        runOnUiThread(() -> {
            Log.d("MQTT", "Dữ liệu MQTT nhận được là: " + payload);
                adapter.updateData(topic, payload);
        });
    }

    // ✅ Hàm gọi API để lấy danh sách thiết bị từ server
    void CallApiGetDeviceFunction(DataCallback<List<DeviceFunction>> deviceFunctionDataCallback) {
        ApiService.apiService.GetDeviceFunction().enqueue(new Callback<List<DeviceFunction>>() {
            @Override
            public void onResponse(Call<List<DeviceFunction>> call, Response<List<DeviceFunction>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    deviceFunctionDataCallback.onSuccess(response.body());
                } else {
                    deviceFunctionDataCallback.onFailure(new Exception("Failed to fetch data: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<List<DeviceFunction>> call, Throwable t) {
                deviceFunctionDataCallback.onFailure(t);
            }
        });
    }
}
