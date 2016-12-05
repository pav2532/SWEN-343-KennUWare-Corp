/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.sales.services;

import com.google.gson.Gson;
import com.kennuware.sales.Utilities.HttpUtils;
import com.kennuware.sales.domain.Item;
import com.kennuware.sales.domain.external.WearableItem;
import com.kennuware.sales.domain.external.WearableList;
import java.util.*;
import org.hibernate.Session;


public class ItemService {

    public static WearableList getItems(HttpUtils httpUtils, Session session) {
        String stringResult = httpUtils.get("http://localhost:8002/itemCatalog");
        Gson gson = new Gson();
        WearableList result = gson.fromJson(stringResult, WearableList.class);
        ArrayList<WearableItem> temp = result.getList();
        for (WearableItem i : temp) {

            if (session.getNamedQuery("ItemById").setString("id", String.valueOf(i.getId())).list().isEmpty()) {
                double price = 0;
                if (i.getType().equals("F"))
                    price = 10;
                if (i.getType().equals("C"))
                    price = 40;
                if (i.getType().equals("H"))
                    price = 70;
                if (i.getType().equals("A"))
                    price = 60;
                Random rn = new Random();
                price += rn.nextInt(35);
                Item a = new Item(i.getId(), i.getName(), price);
                session.save(a);

            } else {
                for (Item a : (ArrayList<Item>) session.getNamedQuery("ItemById").setString("id", String.valueOf(i.getId())).list()) {
                    a.setName(i.getName());
                    session.save(a);
                }

            }
        }

            return result;
        }
    }

