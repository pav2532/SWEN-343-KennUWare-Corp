/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.sales.services;

import com.google.gson.Gson;
import com.kennuware.sales.Utilities.HttpUtils;
import com.kennuware.sales.domain.WearableList;

public class ItemService {

    public static WearableList getItems(HttpUtils httpUtils) {
        String stringResult = httpUtils.get("http://localhost:8002/itemCatalog");
        Gson gson = new Gson();
        WearableList result = gson.fromJson(stringResult, WearableList.class);

        return result;
    }

}
