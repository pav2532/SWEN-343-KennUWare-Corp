package com.kennuware.sales.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 10/31/2016.
 */
public class WearableList {
    private List<WearableItem> wearables;
    public WearableList() {
        wearables = new ArrayList<WearableItem>();
    }

    public List<WearableItem> getList(){
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
