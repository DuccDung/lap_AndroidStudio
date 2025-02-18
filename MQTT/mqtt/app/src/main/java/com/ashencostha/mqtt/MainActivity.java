package com.ashencostha.mqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String BROKER_URL = "bbb04b2c528242358401e2add20b296a.s1.eu.hivemq.cloud:8883"; // Địa chỉ MQTT broker
    private static final String CLIENT_ID = "your_client_id";             // MQTT Client ID
    private static final String USERNAME = "hivemq.webclient.1734466985272";               // MQTT Username
    private static final String PASSWORD = "3#2NHU;F.GRih5s6tqe&";               // MQTT Password

    private MqttHandler mqttHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo MqttHandler và kết nối
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL, CLIENT_ID, USERNAME, PASSWORD);

        // Ví dụ sử dụng publish và subscribe
        publishMessage("test/topic", "Hello MQTT!");
        subscribeToTopic("test/topic");
    }

    @Override
    protected void onDestroy() {
        // Ngắt kết nối MQTT khi Activity bị hủy
        mqttHandler.disconnect();
        super.onDestroy();
    }

    private void publishMessage(String topic, String message) {
        Toast.makeText(this, "Publishing message: " + message, Toast.LENGTH_SHORT).show();
        mqttHandler.publish(topic, message);
    }

    private void subscribeToTopic(String topic) {
        Toast.makeText(this, "Subscribing to topic " + topic, Toast.LENGTH_SHORT).show();
        mqttHandler.subscribe(topic);
    }
}
