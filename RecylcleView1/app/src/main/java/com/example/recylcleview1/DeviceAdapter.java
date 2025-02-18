package com.example.recylcleview1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceHolder>{
    private List<Device> deviceList;

    public DeviceAdapter(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lamp , parent , false); // Tạo 1 view holder sẵn
        return new DeviceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder holder, int position) {
        Device device = deviceList.get(position);
        holder.txtDeviceName.setText(device.getName());
    }

    @Override
    public int getItemCount() {
       return deviceList.size();
    }
    public class DeviceHolder extends RecyclerView.ViewHolder{
    private TextView txtDeviceName;
    public DeviceHolder(@NonNull View itemView) {
        super(itemView);
        txtDeviceName = itemView.findViewById(R.id.txtDeviceLampName);
    }
}
}
