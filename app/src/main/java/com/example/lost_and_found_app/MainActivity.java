package com.example.lost_and_found_app;

// Main screen where users can navigate to post or view items


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
// Open Add Item screen
// Open Item List screen

    Button btnNewAdvert, btnViewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Link buttons
        btnNewAdvert = findViewById(R.id.btnCreateAdvert);
        btnViewItems = findViewById(R.id.btnShowItems);

        // Set button actions
        btnNewAdvert.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddItemActivity.class));
        });

        btnViewItems.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ItemListActivity.class));
        });
    }
}
