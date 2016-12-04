/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.sales.services;

import com.google.gson.Gson;
import com.kennuware.sales.Utilities.HttpUtils;
import com.kennuware.sales.domain.ItemOrders;
import org.hibernate.Session;
import com.kennuware.sales.domain.SalesOrder;
import com.kennuware.sales.domain.Item;
import java.util.ArrayList;


public class OrderServices {
    public static int completeSaleOrder(String customerName, int employeeID, String creditCardNumber,
                                        String expirationDate, Double bulkDiscount, String address,
                                        String date, Session session) {
        if (creditCardNumber.length() != 16) {
            return -1;
        }

        int sum = 0;
        boolean alternate = false;

        for (int i = creditCardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(creditCardNumber.substring(i, i + 1));
            if (alternate) {
                n = n * 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        if (sum % 10 == 0) {

            SalesOrder newSO = new SalesOrder();

            newSO.setBulkDiscount(bulkDiscount);
            newSO.setCreditCardNumber(creditCardNumber);
            newSO.setExpirationDate(expirationDate);
            newSO.setEmployeeID(employeeID);
            newSO.setCustomerName(customerName);
            newSO.setDate(date);

            session.save(newSO);
            sendOrder(newSO, session);

            HttpUtils utils = new HttpUtils();
            //orderItemsFromInventory(address, newSO, customerName, utils);

            return newSO.getOrderid();
        } else {
            return -1;
        }
    }

    public void orderItemsFromInventory(String address, ItemOrders order, String custName, HttpUtils utils) {

        InventoryCustomer customer = new InventoryCustomer();
        customer.setCustomerName(custName);
        customer.setAddress(address);

        InventoryOrder iOrder = new InventoryOrder();
        iOrder.setOrderDetails(customer);
        iOrder.setType("new");
        iOrder.setWearableID(order.getItemId());
        iOrder.setQuantity(order.getQuantity());
        String responseBody = utils.post(iOrder, "http://localhost:8002/productOrderInv");
        System.out.println("----------------------------------------");
        System.out.println(responseBody);


    }

    public static void addItemOrders(int orderId, int itemId, int quantity, Session session) {

        ItemOrders newIO = new ItemOrders();

        newIO.setItemId(itemId);
        newIO.setOrderId(orderId);
        newIO.setQuantity(quantity);

        session.save(newIO);
    }

    public static String sendOrder(SalesOrder order, Session session) {
        Gson gson = new Gson();

        /*try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(
                    "http://localhost:8080/RESTfulExample/json/product/post");  //Edit this to be the correct url

            StringEntity input = null;
                input = new StringEntity(gson.toJson(order));

            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest); //Do something with response when we know what they respond with

            httpClient.getConnectionManager().shutdown();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return gson.toJson(order);
    }

    public static String getHighestOrder(Session session) {
        double result = 0;
        double tempResult = 0;
        String name = "";
        int id = 0;
        int tempId = 0;
        ArrayList<Item> catalog;
        ;
        for (Item c : (ArrayList<Item>) session.getNamedQuery("findAllItems").list()) {
            tempId = c.getId();
            for (ItemOrders iId : getItems(id, session)) {
                tempResult += iId.getQuantity();
            }
            if (result <= tempResult) {
                name = c.getName();
                result = tempResult;
                id = tempId;
            }
            tempResult = 0;
        }
        return "Product: " + name + " Quantity: " + result;
    }

    public static String getLowestOrder(Session session) {
        double result = 0;
        double tempResult = 0;
        String name = "";
        int id = 0;
        int tempId = 0;
        ArrayList<Item> catalog;
        ;
        for (Item c : (ArrayList<Item>) session.getNamedQuery("findAllItems").list()) {
            tempId = c.getId();
            for (ItemOrders iId : getItems(id, session)) {
                tempResult += iId.getQuantity();
            }
            if(result == 0){
                name = c.getName();
                result = tempResult;
                id = tempId;
            } else {
                if (result >= tempResult) {
                    name = c.getName();
                    result = tempResult;
                    id = tempId;
                }
            }
            tempResult = 0;
        }
        return "Product: " + name + " Quantity: " + result;
    }

    public static ArrayList<String> getRevenueByModel(Session session) {
        double result = 0;
        String string = "";
        int id = 0;
        double price = 0;
        double total = 0;
        int quantity = 0;
        ArrayList<String> revenue = new ArrayList<String>();
        ArrayList<Item> catalog;
        ;
        for (Item c : (ArrayList<Item>) session.getNamedQuery("findAllItems").list()) {
            id = c.getId();
            price = c.getUnitPrice();

            for (ItemOrders iId : getItems(id, session)) {
                quantity += iId.getQuantity();
            }
            total = quantity * price;
            revenue.add(c.getName() + " Revenue: " + total);
        }
        quantity = 0;
        return revenue;
    }
    private static ArrayList<ItemOrders> getItems(int id, Session session){
        ArrayList<ItemOrders> list = (ArrayList<ItemOrders>) session.getNamedQuery("findItemById")
                .setString("itemId", String.valueOf(id)).list();
        return list;

    }

    private class InventoryOrder {
        private int wearableID;
        private String type;
        private int quantity;
        private InventoryCustomer orderDetails;

        public InventoryOrder() {}

        public void setWearableID(int id) {
            wearableID = id;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setQuantity(int quantity){this.quantity = quantity;}

        public void setOrderDetails(InventoryCustomer customer) {
            orderDetails = customer;
        }

    }

    private class InventoryCustomer {
        private String customerName;
        private String address;

        public InventoryCustomer() {
            customerName = "";
            address = "";
        }

        public void setCustomerName(String name) {
            customerName = name;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}


