package com.example.adapter_3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdapterCustom extends ArrayAdapter<ListData> {

    public AdapterCustom(@NonNull Context context , ArrayList<ListData> listData){
        super(context , R.layout.list_item , listData);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Lấy đối tượng dữ liệu từ danh sách
        ListData listData = getItem(position);

        // Nếu convertView là null, tạo mới View bằng LayoutInflater
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Tìm kiếm các view con bên trong convertView
        TextView txtName = convertView.findViewById(R.id.txtName);
        ImageView imgItem = convertView.findViewById(R.id.img_item);

        // Đặt dữ liệu vào các view
        if (listData != null) {
            txtName.setText(listData.getName());
            imgItem.setImageResource(listData.getImage());
        }

        // Trả về convertView
        return convertView;
    }

}
