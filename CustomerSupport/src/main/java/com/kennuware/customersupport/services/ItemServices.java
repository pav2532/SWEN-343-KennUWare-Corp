package com.kennuware.customersupport.services;

import com.google.gson.Gson;
import com.kennuware.customersupport.Utilities.HttpUtils;
import com.kennuware.customersupport.domain.*;
import com.kennuware.customersupport.domain.Employees.Employee;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ItemServices{
    public static WearableList getItems(HttpUtils httpUtils) {
        String stringResult = httpUtils.get("http://localhost:8002/itemCatalog");
        Gson gson = new Gson();
        WearableList result = gson.fromJson(stringResult, WearableList.class);

        return result;
    }
}
