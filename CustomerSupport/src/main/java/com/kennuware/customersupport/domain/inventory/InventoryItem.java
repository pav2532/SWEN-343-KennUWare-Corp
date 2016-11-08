package com.kennuware.customersupport.domain.inventory;

/**
 * Created by Ryan on 10/30/2016.
 */
public class InventoryItem {
    private int wearableID;
    private String type;

    public InventoryItem() {
        wearableID = 0;
        type = "refurbished";
    }

    public void setWearableID(int id) {
        wearableID = id;
    }

    public int getWearableID() {
        return wearableID;
    }

    public void setType(String type) {
            this.type = type;
        }

    public String getType() {
        return type;
    }

}
