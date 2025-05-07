package com.example.lost_and_found_app;
// Model class for lost or found items

public class LostFoundItem {

    private int recordId;
    private String advertType;
    private String itemName;
    private String contactPhone;
    private String details;
    private String recordDate;
    private String itemLocation;

    public LostFoundItem(int recordId, String advertType, String itemName, String contactPhone, String details, String recordDate, String itemLocation) {
        this.recordId = recordId;
        this.advertType = advertType;
        this.itemName = itemName;
        this.contactPhone = contactPhone;
        this.details = details;
        this.recordDate = recordDate;
        this.itemLocation = itemLocation;
    }

    // Getters
    public int getRecordId() { return recordId; }
    public String getAdvertType() { return advertType; }
    public String getItemName() { return itemName; }
    public String getContactPhone() { return contactPhone; }
    public String getDetails() { return details; }
    public String getRecordDate() { return recordDate; }
    public String getItemLocation() { return itemLocation; }
}
