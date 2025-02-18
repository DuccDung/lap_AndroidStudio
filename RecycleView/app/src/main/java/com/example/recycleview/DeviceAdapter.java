package com.example.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Device> devices;
    private OnItemClickListener listener;

    // Constructor
    public DeviceAdapter(List<Device> devices, OnItemClickListener listener) {
        this.devices = devices;
        this.listener = listener;
    }

    // Định nghĩa ViewType dựa trên loại thiết bị
    @Override
    public int getItemViewType(int position) {
        switch (devices.get(position).getType()) {
            case "AC":
                return 0;
            case "Speaker":
                return 1;
            case "Light":
                return 2;
            default:
                throw new IllegalArgumentException("Invalid type");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0: // AC
                View acView = inflater.inflate(R.layout.item_ac, parent, false);
                return new ACViewHolder(acView);
            case 1: // Speaker
                View speakerView = inflater.inflate(R.layout.item_speaker, parent, false);
                return new SpeakerViewHolder(speakerView);
            case 2: // Light
                View lightView = inflater.inflate(R.layout.item_light, parent, false);
                return new LightViewHolder(lightView);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Device device = devices.get(position);
        if (holder instanceof ACViewHolder) {
            ((ACViewHolder) holder).bind(device, listener);
        } else if (holder instanceof SpeakerViewHolder) {
            ((SpeakerViewHolder) holder).bind(device, listener);
        } else if (holder instanceof LightViewHolder) {
            ((LightViewHolder) holder).bind(device, listener);
        }
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    // Interface cho sự kiện click
    public interface OnItemClickListener {
        void onItemClick(Device device);
    }

    // ViewHolder cho Air Conditioner
    public static class ACViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, info;
        Switch statusSwitch;

        public ACViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.device_name);
            location = itemView.findViewById(R.id.device_location);
            info = itemView.findViewById(R.id.device_info);
            statusSwitch = itemView.findViewById(R.id.device_switch);
        }

        public void bind(final Device device, final OnItemClickListener listener) {
            name.setText(device.getName());
            location.setText(device.getLocation());
            info.setText(device.getAdditionalInfo() != null ? device.getAdditionalInfo() : "No Info");
            statusSwitch.setChecked(device.isStatus());
            itemView.setOnClickListener(v -> listener.onItemClick(device));
        }
    }

    // ViewHolder cho Speaker
    public static class SpeakerViewHolder extends RecyclerView.ViewHolder {
        TextView name, location;
        Switch statusSwitch;

        public SpeakerViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.device_name);
            location = itemView.findViewById(R.id.device_location);
            statusSwitch = itemView.findViewById(R.id.device_switch);
        }

        public void bind(final Device device, final OnItemClickListener listener) {
            name.setText(device.getName());
            location.setText(device.getLocation());
            statusSwitch.setChecked(device.isStatus());
            itemView.setOnClickListener(v -> listener.onItemClick(device));
        }
    }

    // ViewHolder cho Light
    public static class LightViewHolder extends RecyclerView.ViewHolder {
        TextView name, location;
        Switch statusSwitch;

        public LightViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.device_name);
            location = itemView.findViewById(R.id.device_location);
            statusSwitch = itemView.findViewById(R.id.device_switch);
        }

        public void bind(final Device device, final OnItemClickListener listener) {
            name.setText(device.getName());
            location.setText(device.getLocation());
            statusSwitch.setChecked(device.isStatus());
            itemView.setOnClickListener(v -> listener.onItemClick(device));
        }
    }
}

