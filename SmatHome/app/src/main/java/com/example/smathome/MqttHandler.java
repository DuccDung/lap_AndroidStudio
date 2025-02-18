package com.example.smathome;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttHandler {

    private MqttClient client;

    public void connect(String brokerUrl, int port, String clientId, String username, String password) {
        try {
            // Địa chỉ broker với cổng
            String completeBrokerUrl = "ssl://" + brokerUrl + ":" + port;

            // Thiết lập MemoryPersistence
            MemoryPersistence persistence = new MemoryPersistence();

            // Khởi tạo MQTT client
            client = new MqttClient(completeBrokerUrl, clientId, persistence);

            // Cài đặt các tùy chọn kết nối
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(true);

            // Thêm username và password để xác thực
            connectOptions.setUserName(username);
            connectOptions.setPassword(password.toCharArray());

            // Kết nối đến broker
            client.connect(connectOptions);

            System.out.println("Kết nối thành công tới broker: " + completeBrokerUrl);
        } catch (MqttException e) {
            System.err.println("Không thể kết nối tới broker: " + brokerUrl);
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            client.disconnect();
            System.out.println("Ngắt kết nối thành công.");
        } catch (MqttException e) {
            System.err.println("Lỗi khi ngắt kết nối.");
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            client.publish(topic, mqttMessage);
            System.out.println("Tin nhắn đã được gửi đến topic: " + topic);
        } catch (MqttException e) {
            System.err.println("Không thể gửi tin nhắn.");
            e.printStackTrace();
        }
    }

    public void subscribe(String topic) {
        try {
            client.subscribe(topic);
            System.out.println("Đã đăng ký lắng nghe topic: " + topic);
        } catch (MqttException e) {
            System.err.println("Không thể đăng ký topic.");
            e.printStackTrace();
        }
    }
}
