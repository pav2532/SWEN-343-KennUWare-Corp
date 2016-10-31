package com.kennuware.customersupport.services;

import com.google.gson.Gson;
import com.kennuware.customersupport.Utilities.HttpUtils;
import com.kennuware.customersupport.domain.external.WearableList;

public class ItemService {
    public static WearableList getItems(HttpUtils httpUtils) {
        String stringResult = httpUtils.get("http://localhost:8002/itemCatalog");
        Gson gson = new Gson();
        WearableList result = gson.fromJson(stringResult, WearableList.class);

        return result;
    }
}
