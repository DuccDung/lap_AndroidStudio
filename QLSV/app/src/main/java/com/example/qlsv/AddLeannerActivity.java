package com.example.qlsv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsv.api_service.ApiService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLeannerActivity extends AppCompatActivity {

    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imageView;
    private TextView selectImageButton;
    private EditText EdtFirstName;
    private EditText EdtLastName;

    private Button btnAddLearner;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leanner);

        imageView = findViewById(R.id.imageView);
        selectImageButton = findViewById(R.id.selectImageButton);
        EdtFirstName = findViewById(R.id.editFirstName);
        EdtLastName = findViewById(R.id.editLastName);
        btnAddLearner = findViewById(R.id.btnAddLeanner);

        selectImageButton.setOnClickListener(x -> openImagePicker());
        btnAddLearner.setOnClickListener(x -> UploadLearner());
    }


    private void openImagePicker() {
        // Tạo intent để mở thư viện ảnh
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                // Chuyển URI thành bitmap để hiển thị trong ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                Toast.makeText(this, "Lỗi khi tải ảnh", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    private File getFileFromUri(Uri uri) {
        File file = new File(getCacheDir(), "temp_image.jpg");
        try (InputStream inputStream = getContentResolver().openInputStream(uri);
             OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Upload Leanner lên server
     */
    private void UploadLearner(){
     if(filePath == null){
         Toast.makeText(this, "Vui Lòng chọn ảnh!", Toast.LENGTH_SHORT).show();
         return;
     }
     String firstName = EdtFirstName.getText().toString();
     String lastName = EdtLastName.getText().toString();
     //
     // chuyển Uri thành file ảnh
     File file = getFileFromUri(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*") , file);
        MultipartBody.Part Body = MultipartBody.Part.createFormData("ImageInterface" , file.getName() , requestFile);

        ApiService.apiService.uploadImage(lastName , firstName , 1 , Body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AddLeannerActivity.this, "Upload Success!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddLeannerActivity.this, "Upload fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AddLeannerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}