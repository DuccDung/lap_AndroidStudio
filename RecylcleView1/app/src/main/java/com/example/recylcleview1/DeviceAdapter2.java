package com.example.recylcleview1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter2 extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Device2> device2List = new ArrayList<>();
    public DeviceAdapter2(List<Device2> _device2List){
        device2List = _device2List;
    }

    @Override
    public int getItemViewType(int position) {
        return device2List.get(position).getType(); // Xác định loại layout
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        if(viewType == Device2.TYPE_LAMP){
            View view = Inflater.inflate(R.layout.item_lamp , parent , false);
            return new DeviceLampHolder(view);
        } else if (viewType == Device2.TYPE_AIR) {
            View view = Inflater.inflate(R.layout.item_air , parent , false);
            return new DeviceAirHolder(view);
        } else {
            View view = Inflater.inflate(R.layout.item_water , parent, false);
            return new DeviceWaterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Device2 device = device2List.get(position);

        if (holder instanceof DeviceLampHolder) {
            ((DeviceLampHolder) holder).txtDeviceLamp.setText(device.getName());

            // Đặt full span cho Lamp (chiếm toàn bộ hàng)
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }
        else if (holder instanceof DeviceAirHolder) {
            ((DeviceAirHolder) holder).txtDeviceAir.setText(device.getName());
        }
        else if (holder instanceof DeviceWaterHolder) {
            ((DeviceWaterHolder) holder).txtDeviceWater.setText(device.getName());
        }
    }


    @Override
    public int getItemCount() {
        return device2List.size();
    }

    private class DeviceLampHolder extends RecyclerView.ViewHolder{
    private TextView txtDeviceLamp;
    public DeviceLampHolder(@NonNull View itemView) {
        super(itemView);
        txtDeviceLamp = itemView.findViewById(R.id.txtDeviceLampName);
    }
}
private class DeviceAirHolder extends RecyclerView.ViewHolder{
    private TextView txtDeviceAir;
    public DeviceAirHolder(@NonNull View itemView) {
        super(itemView);
        txtDeviceAir = itemView.findViewById(R.id.txtDeviceAirName);
    }
}
private class DeviceWaterHolder extends RecyclerView.ViewHolder{
    private TextView txtDeviceWater;
    public DeviceWaterHolder(@NonNull View itemView) {
        super(itemView);
        txtDeviceWater = itemView.findViewById(R.id.txtDeviceWaterName);
    }
}
}
