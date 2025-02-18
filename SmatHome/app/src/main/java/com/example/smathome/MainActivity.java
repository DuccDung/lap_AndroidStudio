package com.example.smathome;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kết nối MQTT
        MqttHandler mqttHandler = new MqttHandler();
        // Thông số kết nối
        String brokerUrl = "bbb04b2c528242358401e2add20b296a.s1.eu.hivemq.cloud";
        int port = 8883;
        String clientId = "device_13233434";
        String username = "hivemq.webclient.1735043576854";
        String password = "rAhT10mH$8Btos;6I,Z.";

        // Kết nối với MQTT broker
        mqttHandler.connect(brokerUrl, port, clientId, username, password);

        // Gửi tin nhắn
        mqttHandler.publish("esp8266/dht11", "Kết Nối Thành Công Từ App SmatHome!");

        // Đăng ký lắng nghe
        mqttHandler.subscribe("esp8266/dht11");

        // Ngắt kết nối sau khi hoàn thành
        //  mqttHandler.disconnect();


        // ENd MQTT



        // Device 1
        ImageView deviceLed1 = findViewById(R.id.deviceLed1);
        TextView deviceLed1Status = findViewById(R.id.deviceLed1Status);
        Switch deviceSwitchLed1 = findViewById(R.id.deviceSwitchLed1);

        deviceSwitchLed1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                deviceLed1.setImageResource(R.drawable.bong_den_bat);
                deviceLed1Status.setText("Status: On");
                mqttHandler.publish("esp8266/client", "{Led1:1}");
            } else {
                deviceLed1.setImageResource(R.drawable.bong_den_tat);
                deviceLed1Status.setText("Status: Off");
                mqttHandler.publish("esp8266/client", "{Led1:0}");
            }
        });

        // Device 2
        ImageView deviceLed2 = findViewById(R.id.deviceLed2);
        TextView deviceLed2Status = findViewById(R.id.deviceLed2Status);
        Switch deviceSwitchLed2 = findViewById(R.id.deviceSwitchLed2);

        deviceSwitchLed2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                deviceLed2.setImageResource(R.drawable.bong_den_bat);
                deviceLed2Status.setText("Status: On");
                mqttHandler.publish("esp8266/client", "{Led2:1}");

            } else {
                deviceLed2.setImageResource(R.drawable.bong_den_tat);
                deviceLed2Status.setText("Status: Off");
                mqttHandler.publish("esp8266/client", "{Led2:0}");
            }
        });

        // Device 3
        ImageView deviceLed3 = findViewById(R.id.deviceLed3);
        TextView deviceLed3Status = findViewById(R.id.deviceLed3Status);
        Switch deviceSwitchLed3 = findViewById(R.id.deviceSwitchLed3);

        deviceSwitchLed3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                deviceLed3.setImageResource(R.drawable.bong_den_bat);
                deviceLed3Status.setText("Status: On");
                mqttHandler.publish("esp8266/client", "{Led3:1}");
            } else {
                deviceLed3.setImageResource(R.drawable.bong_den_tat);
                deviceLed3Status.setText("Status: Off");
                mqttHandler.publish("esp8266/client", "{Led3:0}");

            }
        });

        // Device 4
        ImageView deviceLed4 = findViewById(R.id.deviceLed4);
        TextView deviceLed4Status = findViewById(R.id.deviceLed4Status);
        Switch deviceSwitchLed4 = findViewById(R.id.deviceSwitchLed4);

        deviceSwitchLed4.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                deviceLed4.setImageResource(R.drawable.bong_den_bat);
                deviceLed4Status.setText("Status: On");
                mqttHandler.publish("esp8266/client", "{Led4:1}");
            } else {
                deviceLed4.setImageResource(R.drawable.bong_den_tat);
                deviceLed4Status.setText("Status: Off");
                mqttHandler.publish("esp8266/client", "{Led4:0}");
            }
        });
    }
}
