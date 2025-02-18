package com.example.baitap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtSDT;
    private RadioGroup radioGroup;
    private RadioButton radNam, radNu;
    private Spinner spinAdd;
    private Button btnAdd;
    private ListView listView;
    private EditText edtTime;
    private ArrayList<String> sinhVienList;
    private ArrayAdapter<String> listAdapter;

    // Arrays for Thiên Can và Địa Chi
    private static final String[] CAN = {
            "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ", "Canh", "Tân", "Nhâm", "Quý"
    };

    private static final String[] CHI = {
            "Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi", "Thân", "Dậu", "Tuất", "Hợi"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edtName = findViewById(R.id.edtName);
        spinAdd = findViewById(R.id.spinAdd);
        radioGroup = findViewById(R.id.radioGroup);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.ListView);
        edtTime = findViewById(R.id.edtTime);

        // Khởi tạo dateTime
        edtTime.setOnClickListener(v -> showTimePickerDialog());




        // Khởi tạo danh sách Spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.addresses, // Cần khai báo danh sách địa chỉ trong res/values/strings.xml
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinAdd.setAdapter(spinnerAdapter);

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
                String time = edtTime.getText().toString().trim();
                String address = spinAdd.getSelectedItem().toString();
                String gender = radNam.isChecked() ? "Nam" : "Nữ";

                // Kiểm tra dữ liệu nhập vào
                if (name.isEmpty() || time.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Thêm sinh viên mới vào danh sách
                String sinhVienInfo = name + " - " + calculateCanChi(time) +" - "+calculateAge(time)+ " Tuổi - " + gender + " - " + address;
                sinhVienList.add(sinhVienInfo);

                // Cập nhật ListView
                listAdapter.notifyDataSetChanged();

                // Hiển thị thông báo
                Toast.makeText(MainActivity.this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();

                // Xóa dữ liệu sau khi thêm
                edtName.setText("");
                edtTime.setText("");
                radioGroup.clearCheck();
                spinAdd.setSelection(0);
            }
        });
    }

    private void showTimePickerDialog() {
        // Bước 1: Lấy ngày hiện tại (năm, tháng, ngày)
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR); // Lấy năm hiện tại
        int currentMonth = calendar.get(Calendar.MONTH); // Lấy tháng hiện tại (bắt đầu từ 0)
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH); // Lấy ngày hiện tại


        // Bước 2: Tạo và hiển thị DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, // Context: màn hình hiện tại
                new DatePickerDialog.OnDateSetListener() { // Xử lý sự kiện khi người dùng chọn ngày
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Bước 3: Định dạng ngày đã chọn thành chuỗi dd/MM/yyyy
                        String formattedDate = String.format(Locale.getDefault(), "%02d-%02d-%04d", dayOfMonth, month + 1, year);

                        // Bước 4: Gán chuỗi ngày đã chọn vào EditText
                        edtTime.setText(formattedDate);
                    }
                },
                currentYear, // Năm mặc định
                currentMonth, // Tháng mặc định
                currentDay // Ngày mặc định
        );

        // Bước 5: Hiển thị DatePickerDialog
        datePickerDialog.show();
    }


    public static String calculateCanChi(String dateString) {
        try {
            // Parse the input date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateString, formatter);

            int year = date.getYear();

            // Calculate Can and Chi for the year
            String can = CAN[(year - 4) % 10]; // Thiên Can
            String chi = CHI[(year - 4) % 12]; // Địa Chi

            return  can + " " + chi;
        } catch (DateTimeParseException e) {
            return "Invalid date format. Please use 'dd-MM-yyyy'.";
        }
    }
    public static int calculateAge(String dateString) {
        try {
            // Format date string to LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birthDate = LocalDate.parse(dateString, formatter);
            LocalDate currentDate = LocalDate.now();

            // Calculate age
            return Period.between(birthDate, currentDate).getYears();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'dd-MM-yyyy'.");
        }
    }
}
