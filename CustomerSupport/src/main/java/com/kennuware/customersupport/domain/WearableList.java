package com.kennuware.customersupport.domain;

import java.util.ArrayList;

/**
 * Created by Ryan on 10/31/2016.
 */
public class WearableList {
    private ArrayList<WearableItem> wearables;
    public WearableList() {
    }

    public ArrayList getList(){
        return wearables;
    }

    public String toString() {
        String result = "";
        for(WearableItem w : wearables) {
            result += w.toString() + "\n";
        }
        return result;
    }
}
