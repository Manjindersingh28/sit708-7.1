package com.example.lost_and_found_app;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
// Load all saved items and display in RecyclerView

public class ItemListActivity extends AppCompatActivity {

    RecyclerView recordsRecyclerView;
    DataHandler dbManager;
    ArrayList<LostFoundItem> recordsArray;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        recordsRecyclerView = findViewById(R.id.recyclerViewItems);
        dbManager = new DataHandler(this);
        recordsArray = new ArrayList<>();

        loadRecordsFromDB();

        itemAdapter = new ItemAdapter(this, recordsArray);
        recordsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recordsRecyclerView.setAdapter(itemAdapter);
    }

    private void loadRecordsFromDB() {
        Cursor result = dbManager.fetchAllRecords();
        if (result != null && result.moveToFirst()) {
            do {
                int recordId = result.getInt(0);
                String type = result.getString(1);
                String name = result.getString(2);
                String phone = result.getString(3);
                String desc = result.getString(4);
                String date = result.getString(5);
                String loc = result.getString(6);

                LostFoundItem newRecord = new LostFoundItem(recordId, type, name, phone, desc, date, loc);
                recordsArray.add(newRecord);

            } while (result.moveToNext());
        }
    }
}
