package com.example.listphone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private  int Image[] = {R.drawable.phone_1 , R.drawable.phone_2 , R.drawable.phone_3 , R.drawable.phone_5};
    private  String Name[] = {"Điện Thoại 1" , "Điện Thoại 2", "Điện Thoại 3" , "Điện Thoại 4"};
    ArrayList<Phone> myList;
    ArrayAdapter myAdapter;
    ListView list_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view = findViewById(R.id.lv);
        myList = new ArrayList<>();

        for(int i=0 ;i< Name.length ;i++){
            myList.add(new Phone(Image[i] , Name[i]));
        }
        myAdapter = new  ArrayAdapter(MainActivity.this , R.layout.layout_item, myList);

        list_view.setAdapter(myAdapter);
    }
}