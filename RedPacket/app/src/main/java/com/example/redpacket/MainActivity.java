package com.example.redpacket;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Danh sách ảnh tiền
    private int[] moneyImages = {
            R.drawable.img_1k,
            R.drawable.img_2k,
            R.drawable.img_5k,
            R.drawable.img_10k,
            R.drawable.img_20k,
//            R.drawable.img_50k,
//            R.drawable.img_100k,
//            R.drawable.img_200k,
//            R.drawable.img_500k,
    };

    private Handler handler = new Handler();
    private Runnable imageChanger;
    private boolean isChanging = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kết nối các thành phần trong layout
        ImageView imageView = findViewById(R.id.imageView);
        Button randomButton = findViewById(R.id.randomButton);

        // MediaPlayer để phát âm thanh
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.phao_no);

        // Kết nối LottieAnimationView
        LottieAnimationView fireworksAnimation = findViewById(R.id.fireworksAnimation);

        // Xử lý sự kiện khi nhấn nút
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị animation pháo hoa và lặp lại trong 4 giây
                fireworksAnimation.setVisibility(View.VISIBLE);
                fireworksAnimation.setRepeatCount(LottieDrawable.INFINITE); // Lặp vô hạn
                fireworksAnimation.playAnimation();

                // Phát âm thanh pháo nổ
                mediaPlayer.start();

                // Đặt trạng thái thay đổi ảnh liên tục
                isChanging = true;

                // Runnable để thay đổi ảnh liên tục
                imageChanger = new Runnable() {
                    @Override
                    public void run() {
                        if (isChanging) {
                            // Random một ảnh
                            Random random = new Random();
                            int randomIndex = random.nextInt(moneyImages.length);
                            imageView.setImageResource(moneyImages[randomIndex]);

                            // Tiếp tục chạy lại Runnable sau mỗi 100ms
                            handler.postDelayed(this, 100);
                        }
                    }
                };

                // Bắt đầu thay đổi ảnh
                handler.post(imageChanger);

                // Dừng âm thanh, animation và ảnh nhảy loạn sau 4 giây
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Dừng thay đổi ảnh
                        isChanging = false;

                        // Random một ảnh cuối cùng
                        Random random = new Random();
                        int finalIndex = random.nextInt(moneyImages.length);
                        imageView.setImageResource(moneyImages[finalIndex]);

                        // Ẩn animation và dừng âm thanh
                        fireworksAnimation.setVisibility(View.GONE);
                        fireworksAnimation.cancelAnimation();
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }
                }, 4000); // Thời gian 4 giây
            }
        });
    }
}
