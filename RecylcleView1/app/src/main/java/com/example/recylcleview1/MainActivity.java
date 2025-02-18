package com.example.recylcleview1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvMain ;
    private DeviceAdapter2 deviceAdapter;
    private List<Device2> deviceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rcvMain =  findViewById(R.id.rcvMain);

        Device2 d1 = new Device2("lamp1" , Device2.TYPE_LAMP);
        Device2 d2 = new Device2("lamp2" , Device2.TYPE_AIR);
        Device2 d3 = new Device2("lamp3" , Device2.TYPE_WATER);
        Device2 d4 = new Device2("lamp3" , Device2.TYPE_WATER);

        deviceList.add(d2);
        deviceList.add(d3);
        deviceList.add(d4);
        deviceList.add(d1);

       // rcvMain.setLayoutManager(new LinearLayoutManager(this));
        rcvMain.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //rcvMain.setLayoutManager(new GridLayoutManager(this, 2));

        deviceAdapter = new DeviceAdapter2(deviceList); // đưa list vào adapter
        rcvMain.setAdapter(deviceAdapter);

    }
}