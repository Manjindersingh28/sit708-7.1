package com.example.lost_and_found_app;
// Collect user input and save to SQLite database

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    EditText inputName, inputPhone, inputDesc, inputDate, inputLocation;
    RadioGroup advertTypeGroup;
    RadioButton optionLost, optionFound;
    Button btnSubmit;

    DataHandler dbManager;  // Renamed from DatabaseHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // Connect views
        advertTypeGroup = findViewById(R.id.radioGroupType);
        optionLost = findViewById(R.id.radioLost);
        optionFound = findViewById(R.id.radioFound);
        inputName = findViewById(R.id.editName);
        inputPhone = findViewById(R.id.editPhone);
        inputDesc = findViewById(R.id.editDescription);
        inputDate = findViewById(R.id.editDate);
        inputLocation = findViewById(R.id.editLocation);
        btnSubmit = findViewById(R.id.btnSave);

        dbManager = new DataHandler(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String advertType = optionLost.isChecked() ? "Lost" : "Found";
                String name = inputName.getText().toString().trim();
                String phone = inputPhone.getText().toString().trim();
                String description = inputDesc.getText().toString().trim();
                String date = inputDate.getText().toString().trim();
                String location = inputLocation.getText().toString().trim();

                // Simple validation
                if (name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()) {
                    Toast.makeText(AddItemActivity.this, "All fields must be completed!", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean result = dbManager.addRecord(advertType, name, phone, description, date, location);

                if (result) {
                    Toast.makeText(AddItemActivity.this, "Advert created successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddItemActivity.this, "Failed to create advert.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
