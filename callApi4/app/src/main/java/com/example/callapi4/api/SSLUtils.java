package com.example.callapi4.api;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

import okhttp3.OkHttpClient;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class SSLUtils {
    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Tạo TrustManager bỏ qua tất cả các kiểm tra chứng chỉ
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Cài đặt SSLContext sử dụng TrustManager trên
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Tạo SSLSocketFactory từ SSLContext
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            // Xây dựng OkHttpClient bỏ qua SSL và kiểm tra hostname
            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .connectTimeout(30, TimeUnit.SECONDS) // Thêm timeout nếu cần
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}




