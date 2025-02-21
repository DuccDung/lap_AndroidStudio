package com.example.lap1_1;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lap1_1.model.SinhVien;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<SinhVien> data;
    private Context context;
    private LayoutInflater inflater;

    // Constructor
    public ListViewAdapter(Context context, List<SinhVien> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (data != null) ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (data != null) ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return (data != null) ? data.get(position).getId() : 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_view, parent, false);
            holder = new ViewHolder();
            holder.info = convertView.findViewById(R.id.txtInfo);
            holder.imgInterface = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SinhVien itemData = data.get(position);

        if (itemData == null) {
            return convertView;
        }

        holder.info.setText(
                itemData.getName() + " - " + itemData.getGen()+" - "+ itemData.getHobby()  +" - " + itemData.getPhone() + " - " + itemData.getAddress()
        );

        Uri uri = itemData.getSelectedImageUri();
        if (uri != null) {
            Log.d("DEBUG", "Tải ảnh từ URI: " + uri.toString());
            Glide.with(context)
                    .load(uri)
                    .placeholder(R.drawable.img_load)
                    .error(R.drawable.img_error)
                    .into(holder.imgInterface);
        } else {
            Log.e("DEBUG", "URI ảnh bị null tại vị trí: " + position);
            holder.imgInterface.setImageResource(R.drawable.img_load);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView info;
        ImageView imgInterface;
    }
}
