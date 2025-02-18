package com.example.mqtt3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnConnect = findViewById(R.id.btnConnect);
        Button btnLed1 = findViewById(R.id.btnLed1);
        Button offBtnLed1 = findViewById(R.id.OffbtnLed1);
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
            mqttHandler.publish("esp8266/dht11", "Xin chào từ MQTT Client!");

            // Đăng ký lắng nghe
            mqttHandler.subscribe("esp8266/dht11");

            // Ngắt kết nối sau khi hoàn thành
         //  mqttHandler.disconnect();

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gửi tin nhắn qua MQTT
                mqttHandler.publish("esp8266/client", "Xin chào từ MQTT Client!");
                System.out.println("Đã gửi tin nhắn MQTT: Xin chào từ MQTT Client!");
            }
        });
        btnLed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gửi tin nhắn qua MQTT
                mqttHandler.publish("esp8266/client", "{\"out1\": 1}");
                System.out.println("Đã gửi tin nhắn MQTT");
            }
        });
        offBtnLed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gửi tin nhắn qua MQTT
                mqttHandler.publish("esp8266/client", "{\"out1\": 0}");
                System.out.println("Đã gửi tin nhắn MQTT");
            }
        });
    }
}