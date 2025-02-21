package com.example.lap1_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lap1_1.model.SinhVien;
import com.example.lap1_1.model.SpinnerItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtSDT;
    private RadioGroup radGroup;
    private RadioButton radNam, radNu;
    private Button btnAdd, btnChooseImg;
    private Spinner spinAdd;
    private ListView listView;
    private ImageView imgView;

    private List<SinhVien> sinhVienList;
    private ListViewAdapter listAdapter;
    private Uri uri;
    private Spinner customSpinner;
    private List<SpinnerItem> spinnerItems;
    private SpinnerAdapter spinnerHobbyAdapter;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // customSpinner.setSelection(0);
        initializeView();
        initializeSpinner();
        initializeListView();

        btnAdd.setOnClickListener(view -> addSinhVien());
        btnChooseImg.setOnClickListener(view -> openImagePicker());
    }

    private void addSinhVien() {
        String name = edtName.getText().toString().trim();
        String sdt = edtSDT.getText().toString().trim();
        String address = spinAdd.getSelectedItem().toString();
        String gender = radNam.isChecked() ? "Nam" : "Nữ";
        String Hobby = getSelectedSpinnerItems();

        if (name.isEmpty() || sdt.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ các trường!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (uri == null) {
            Toast.makeText(this, "Vui lòng chọn ảnh!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Thêm sinh viên mới vào danh sách
        SinhVien sv = new SinhVien(sinhVienList.size() + 1, name, sdt, gender,Hobby, address, uri);
        sinhVienList.add(sv);
        listAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Đã Thêm Thành Công!", Toast.LENGTH_SHORT).show();
        resetForm();
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();
            Glide.with(this)
                    .load(uri)
                    .placeholder(R.drawable.img_load)
                    .error(R.drawable.img_error)
                    .into(imgView);
        }
    }

    private void initializeView() {
        edtName = findViewById(R.id.edtName);
        edtSDT = findViewById(R.id.edtSDT);
        radGroup = findViewById(R.id.radioGroup);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        btnAdd = findViewById(R.id.btnAdd);
        btnChooseImg = findViewById(R.id.btnChooseImg);
        listView = findViewById(R.id.ListView);
        spinAdd = findViewById(R.id.spinAdd);
        imgView = findViewById(R.id.imgInterface);
    }

    private void initializeSpinner() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.addresses, android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAdd.setAdapter(spinnerAdapter);


        // ===========================

        customSpinner = findViewById(R.id.spinAddHobby);

        // Danh sách lựa chọn trong Spinner
        spinnerItems = new ArrayList<>();
        spinnerItems.add(new SpinnerItem("đọc sách", false));
        spinnerItems.add(new SpinnerItem("gym", false));
        spinnerItems.add(new SpinnerItem("hát", false));
        spinnerItems.add(new SpinnerItem("chơi game", false));

        spinnerHobbyAdapter= new SpinnerAdapter(MainActivity.this, spinnerItems);
        customSpinner.setAdapter(spinnerHobbyAdapter);
       // customSpinner.setSelection(0 , false);
    }

    // Lấy danh sách các mục đã chọn
    private String getSelectedSpinnerItems() {
        List<String> selectedItems = new ArrayList<>();
        for (SpinnerItem item : spinnerHobbyAdapter.getSelectedItems()) {
            if (item.isChecked()) {
                selectedItems.add(item.getName());
            }
        }
        return selectedItems.toString();
    }

    private void initializeListView() {
        sinhVienList = new ArrayList<>();
        listAdapter = new ListViewAdapter(MainActivity.this, sinhVienList);
        listView.setAdapter(listAdapter);
    }

    private void resetForm() {
        edtName.setText("");
        edtSDT.setText("");
        radGroup.clearCheck();
        spinAdd.setSelection(0);
        imgView.setImageResource(R.drawable.img_load);
        uri = null;
    }
}
