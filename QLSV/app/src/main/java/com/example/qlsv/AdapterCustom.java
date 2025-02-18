package com.example.qlsv;

import static com.example.qlsv.api_service.ApiService.getBaseDomain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.qlsv.api_service.SSLUtils;

import java.util.List;

import okhttp3.OkHttpClient;

public class AdapterCustom extends ArrayAdapter<Leanners> {

    public AdapterCustom(@NonNull Context context , List<Leanners> listData){
        super(context , R.layout.leanner_item , listData);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Lấy đối tượng dữ liệu từ danh sách
        Leanners listData = getItem(position);

        // Nếu convertView là null, tạo mới View bằng LayoutInflater
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.leanner_item, parent, false);
        }

        // Tìm kiếm các view con bên trong convertView
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtLeannerId = convertView.findViewById(R.id.txtLeannerId);
        ImageView imgItem = convertView.findViewById(R.id.img_item);
        TextView txtMajor = convertView.findViewById(R.id.txtMajor);
        // Đặt dữ liệu vào các view
        if (listData != null) {
            txtName.setText(listData.getLastName() +" " +listData.getFirstName());
            txtLeannerId.setText(listData.getLeannerId() + "");
            txtMajor.setText(listData.getMajorId() + "");

            // Đường dẫn ảnh đầy đủ từ server
            String imageUrl = getBaseDomain() + listData.getInterfacePath();

            // Sử dụng Glide để tải ảnh vào ImageView

            OkHttpClient client = SSLUtils.getUnsafeOkHttpClient();

            Glide.with(getContext())
                    .load(imageUrl) // URL ảnh đầy đủ
                    .placeholder(R.drawable.error) // Ảnh chờ khi tải
                    .error(R.drawable.error) // Ảnh thay thế nếu tải lỗi
                    .into(imgItem); // Đặt vào ImageView
        }

        // Trả về convertView
        return convertView;
    }

}
