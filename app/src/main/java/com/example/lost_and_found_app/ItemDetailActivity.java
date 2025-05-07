package com.example.lost_and_found_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    TextView typeText, nameText, phoneText, descText, dateText, locationText;
    Button btnRemove;

    DataHandler dbManager;
    int recordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        dbManager = new DataHandler(this);

        typeText = findViewById(R.id.detailType);
        nameText = findViewById(R.id.detailName);
        phoneText = findViewById(R.id.detailPhone);
        descText = findViewById(R.id.detailDescription);
        dateText = findViewById(R.id.detailDate);
        locationText = findViewById(R.id.detailLocation);
        btnRemove = findViewById(R.id.btnDelete);

        // Receive item data from intent
        recordId = getIntent().getIntExtra("id", -1);
        typeText.setText("Type: " + getIntent().getStringExtra("type"));
        nameText.setText("Name: " + getIntent().getStringExtra("name"));
        phoneText.setText("Phone: " + getIntent().getStringExtra("phone"));
        descText.setText("Description: " + getIntent().getStringExtra("description"));
        dateText.setText("Date: " + getIntent().getStringExtra("date"));
        locationText.setText("Location: " + getIntent().getStringExtra("location"));

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmRemoval();
            }
        });
    }

    private void confirmRemoval() {
        new AlertDialog.Builder(this)
                .setTitle("Remove Advert")
                .setMessage("Are you sure you want to delete this advert?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        boolean deleted = dbManager.removeRecord(recordId);
                        if (deleted) {
                            Toast.makeText(ItemDetailActivity.this, "Advert deleted.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ItemDetailActivity.this, "Failed to delete advert.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
