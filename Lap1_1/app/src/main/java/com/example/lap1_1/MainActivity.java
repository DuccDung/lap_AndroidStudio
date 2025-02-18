package com.example.lap1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText EdtName , EdtSDT;
    private RadioGroup radGroup;
    private RadioButton radNam, radNu;
    private Button btnAdd;
    private Spinner spinAdd;
    private ListView listView;
    private ArrayList<String> sinhVienList;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdtName = findViewById(R.id.edtName);
        EdtSDT = findViewById(R.id.edtSDT);
        radGroup = findViewById(R.id.radioGroup);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.ListView);
        spinAdd = findViewById(R.id.spinAdd);


        // Khởi tạo danh sách spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
            this,   R.array.addresses , android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAdd.setAdapter(spinnerAdapter);

        // Intilized listView Students
        sinhVienList = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(
            this , android.R.layout.simple_expandable_list_item_1 , sinhVienList
        );
        listView.setAdapter(listAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = EdtName.getText().toString().trim();
                String SDT = EdtSDT.getText().toString().trim();
                String Add = spinAdd.getSelectedItem().toString();
                String Gender = radNam.isChecked() ? "Nam" : "Nữ";

                if(Name.isEmpty() || SDT.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ các trường!", Toast.LENGTH_SHORT).show();
                    return;
                }
                sinhVienList.add(Name + " " + SDT + " " + Add + " " + Gender);
                listAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã Thêm Thành Công!", Toast.LENGTH_SHORT).show();

                EdtName.setText("");
                EdtSDT.setText("");
                radGroup.clearCheck();
                spinAdd.setSelection(0);
            }
        });
    }
}