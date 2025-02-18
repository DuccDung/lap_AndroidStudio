package com.ashencostha.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttHandler {
    private MqttClient mqttClient;

    public void connect(String brokerUrl, String clientId, String username, String password) {
        try {
            mqttClient = new MqttClient(brokerUrl, clientId, null);

            // Tùy chọn kết nối
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(username);
            options.setPassword(password.toCharArray()); // Chuyển password thành mảng ký tự
            options.setCleanSession(true); // Tạo session mới mỗi lần kết nối

            mqttClient.connect(options); // Thực hiện kết nối với tùy chọn xác thực

            System.out.println("Kết nối thành công với MQTT Broker: " + brokerUrl);
        } catch (MqttException e) {
            e.printStackTrace();
            System.err.println("Kết nối thất bại: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (mqttClient != null && mqttClient.isConnected()) {
                mqttClient.disconnect();
                System.out.println("Ngắt kết nối MQTT thành công.");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message) {
        try {
            if (mqttClient != null && mqttClient.isConnected()) {
                mqttClient.publish(topic, message.getBytes(), 0, false);
                System.out.println("Đã gửi tin nhắn tới topic: " + topic);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic) {
        try {
            if (mqttClient != null && mqttClient.isConnected()) {
                mqttClient.subscribe(topic, (topic1, message) -> {
                    System.out.println("Nhận tin nhắn từ topic " + topic1 + ": " + new String(message.getPayload()));
                });
                System.out.println("Đã đăng ký topic: " + topic);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}