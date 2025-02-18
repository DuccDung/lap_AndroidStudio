package com.example.adapter_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ListData> listData;
    private AdapterCustom listAdapter;
    private ListData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listData = new ArrayList<ListData>();

        ListView listView = findViewById(R.id.listview_main);
        String[] nameList = {"Pasta"};
        int[] imageList = {R.drawable.bun_img1};
        if (nameList.length == imageList.length) {
            for (int i = 0; i < imageList.length; i++) {
                listData.add(new ListData(imageList[i], nameList[i]));
            }
        } else {
            throw new IllegalStateException("nameList and imageList must have the same length");
        }

        listAdapter = new AdapterCustom(MainActivity.this , listData);
        listView.setAdapter(listAdapter);
    }
}