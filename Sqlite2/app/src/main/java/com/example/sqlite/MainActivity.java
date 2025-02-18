package com.example.sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextTitle, editTextAuthor, editTextPages;
    Button buttonAdd, buttonView, buttonDeleteAll;
    TextView textViewDisplay;
    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextPages = findViewById(R.id.editTextPages);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonView = findViewById(R.id.buttonView);
        buttonDeleteAll = findViewById(R.id.buttonDeleteAll);
        textViewDisplay = findViewById(R.id.textViewDisplay);

        // Initialize database
        myDB = new MyDatabaseHelper(MainActivity.this);

        // Add book
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString().trim();
                String author = editTextAuthor.getText().toString().trim();
                String pages = editTextPages.getText().toString().trim();

                if (title.isEmpty() || author.isEmpty() || pages.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    myDB.addBook(title, author, Integer.parseInt(pages));
                }
            }
        });

        // View all books
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDB.readAllData();
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder stringBuilder = new StringBuilder();
                while (cursor.moveToNext()) {
                    stringBuilder.append("ID: ").append(cursor.getString(0)).append("\n");
                    stringBuilder.append("Title: ").append(cursor.getString(1)).append("\n");
                    stringBuilder.append("Author: ").append(cursor.getString(2)).append("\n");
                    stringBuilder.append("Pages: ").append(cursor.getString(3)).append("\n\n");
                }
                textViewDisplay.setText(stringBuilder.toString());
            }
        });

        // Delete all books
        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteAllData();
                Toast.makeText(MainActivity.this, "All data deleted", Toast.LENGTH_SHORT).show();
                textViewDisplay.setText("");
            }
        });
    }
}
