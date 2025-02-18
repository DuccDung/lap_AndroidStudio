package com.example.smartiot;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceFunctionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<DeviceFunction> deviceFunctionList;
    private Map<String, String> latestData = new HashMap<>();
    private MqttHandler _mqttHandler; // MQTT Handler

    public DeviceFunctionAdapter(List<DeviceFunction> deviceFunctionList , MqttHandler mqttHandler) {
        this.deviceFunctionList = deviceFunctionList;
        this._mqttHandler = mqttHandler;
    }
    public void updateData(String topic, String data) {
        latestData.put(topic, data);
        notifyDataSetChanged(); // Cập nhật UI
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater Inflater = LayoutInflater.from(parent.getContext());
        if(viewType == 1){
            View view = Inflater.inflate(R.layout.item_air , parent ,false);
            return new DeviceAirHolder(view);
        } else if (viewType == 2) {
            View view = Inflater.inflate(R.layout.item_water , parent , false);
            return new DeviceWaterHolder(view);
        }
        else {
            View view = Inflater.inflate(R.layout.item_lamp , parent ,false);
            return new DeviceLampHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(holder instanceof DeviceAirHolder){
            DeviceAirHolder airHolder = (DeviceAirHolder) holder;
            airHolder.txtAirName.setText(deviceFunctionList.get(position).getNameDevice());


            airHolder.swAirConditioner.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                handleSwitchChange(deviceFunctionList.get(position), isChecked);
            });

            // Cập nhật nhiệt độ từ dữ liệu MQTT
            String topic = deviceFunctionList.get(position).getDeviceID();
            String payload = latestData.get(topic);
            double temperature;
            if (payload != null) {
                // Chuyển payload từ JSON sang đối tượng JSONObject
                try {
                    JSONObject jsonObject = new JSONObject(payload);
                    // Lấy giá trị nhiệt độ từ JSON
                     temperature = jsonObject.getDouble("temperature");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                airHolder.txtAirTemperature.setText(temperature + "°C");
            } else {
                airHolder.txtAirTemperature.setText("Đang chờ dữ liệu...");
            }
        } else if (holder instanceof DeviceLampHolder) {
            DeviceLampHolder lampHolder = (DeviceLampHolder) holder;
            lampHolder.txtNameLamp.setText(deviceFunctionList.get(position).getNameDevice());

            lampHolder.swLamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean  isChecked) {
                    handleSwitchChange(deviceFunctionList.get(position), isChecked);
                }
            });


            // Đặt full span cho Lamp (chiếm toàn bộ hàng)
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }

            // condition
        } else if (holder instanceof DeviceWaterHolder) {
            DeviceWaterHolder waterHolder = (DeviceWaterHolder) holder;
            waterHolder.txtWaterHeaterName.setText(deviceFunctionList.get(position).getNameDevice());

            waterHolder.swWaterHeater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean  isChecked) {
                    handleSwitchChange(deviceFunctionList.get(position) ,  isChecked);
                }
            });


            // condition
        }
    }

    @Override
    public int getItemCount() {
        return deviceFunctionList.size();
    }
    @Override
    public int getItemViewType(int position) {
        String functionID = deviceFunctionList.get(position).getFunctionID();

        switch (functionID) {
            case "F_Air":
                return 1;
            case "F_WaterHeater":
                return 2;
            case "F_lamp":
                return 3;
            default:
                return 0; // Default view type
        }
    }


    public class DeviceLampHolder extends RecyclerView.ViewHolder{
        private TextView txtNameLamp;
        private TextView txtLampCondition;
        private Switch swLamp;
        public DeviceLampHolder(@NonNull View itemView) {
            super(itemView);
            txtNameLamp = itemView.findViewById(R.id.txtDeviceLampName);
            txtLampCondition = itemView.findViewById(R.id.txtDeviceLampCondition);
            swLamp = itemView.findViewById(R.id.switch_lamp);
        }
    }

    public class DeviceAirHolder extends RecyclerView.ViewHolder{
        private TextView txtAirName;
        private  TextView txtAirTemperature;
        private Switch swAirConditioner;

        public DeviceAirHolder(@NonNull View itemView) {
            super(itemView);
            txtAirName = itemView.findViewById(R.id.txtDeviceAirName);
            txtAirTemperature = itemView.findViewById(R.id.txtDeviceAirTemperature);
            swAirConditioner = itemView.findViewById(R.id.switch_Air);
        }
    }

    public class DeviceWaterHolder extends RecyclerView.ViewHolder{

        private TextView txtWaterHeaterName;
        private TextView txtWaterHeaterCondition;
        private Switch swWaterHeater;

        public DeviceWaterHolder(@NonNull View itemView) {
            super(itemView);
            txtWaterHeaterName = itemView.findViewById(R.id.txtDeviceWaterName);
            txtWaterHeaterCondition = itemView.findViewById(R.id.txtDeviceLampCondition);
            swWaterHeater = itemView.findViewById(R.id.switch_water);
        }
    }

    private void handleSwitchChange(DeviceFunction device, boolean isChecked) {
        String deviceID = device.getDeviceID();
        String status = isChecked ? "ON" : "OFF";
        // Gửi dữ liệu MQTT hoặc cập nhật trạng thái
        Log.d(deviceID, "click " + status );  // Kiểm tra xem có đúng ID không
        _mqttHandler.publish(deviceID , status);
    }

}
