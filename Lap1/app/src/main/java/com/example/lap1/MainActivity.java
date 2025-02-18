package com.example.lap1;

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

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private Button btnAdd;
    private ListView listView;
    private ArrayList<String> sinhVienList;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edtName = findViewById(R.id.edtName);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.ListView);

        // Khởi tạo danh sách Spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.addresses, // Cần khai báo danh sách địa chỉ trong res/values/strings.xml
                android.R.layout.simple_spinner_item
        );

        // Khởi tạo danh sách và Adapter cho ListView
        sinhVienList = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sinhVienList);
        listView.setAdapter(listAdapter);

        // Xử lý sự kiện nhấn nút "Thêm"
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các trường nhập liệu
                String name = edtName.getText().toString().trim();

                // Kiểm tra dữ liệu nhập vào
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Thêm sinh viên mới vào danh sách
                String sinhVienInfo = name;
                sinhVienList.add(sinhVienInfo);

                // Cập nhật ListView
                listAdapter.notifyDataSetChanged();

                // Hiển thị thông báo
                Toast.makeText(MainActivity.this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();

                // Xóa dữ liệu sau khi thêm
                edtName.setText("");
            }
        });
    }
}
