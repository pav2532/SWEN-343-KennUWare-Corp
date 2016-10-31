/**
 * Created by John King on 07-Oct-16.
 */

package com.kennuware.sales.services;

import com.google.gson.Gson;
import com.kennuware.sales.Utilities.HttpUtils;
import com.kennuware.sales.domain.ItemOrders;
import org.hibernate.Session;
import com.kennuware.sales.domain.SalesOrder;


public class OrderServices {
    public static int completeSaleOrder(String customerName, int employeeID, String creditCardNumber,
                                            String expirationDate, Double bulkDiscount, Session session){
        if(creditCardNumber.length() != 16 ){
            return -1;
        }



        int sum = 0;
        boolean alternate = false;

        for (int i = creditCardNumber.length() - 1; i >= 0; i--){
            int n = Integer.parseInt(creditCardNumber.substring(i, i + 1));
            if (alternate){
                n = n*2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        if(sum % 10 == 0) {

            SalesOrder newSO = new SalesOrder();

            newSO.setBulkDiscount(bulkDiscount);
            newSO.setCreditCardNumber(creditCardNumber);
            newSO.setExpirationDate(expirationDate);
            newSO.setEmployeeID(employeeID);
            newSO.setCustomerName(customerName);

            session.save(newSO);
            sendOrder(newSO, session);
            return newSO.getOrderid();
        }else{
            return -1;
        }
    }



    public void orderItemsFromInventory(String address, ItemOrders order, String custName, HttpUtils utils){


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

    public static void addItemOrders(int orderId, int itemId, int quantity, Session session){

        ItemOrders newIO = new ItemOrders();

        newIO.setItemId(itemId);
        newIO.setOrderId(orderId);
        newIO.setQuantity(quantity);

        session.save(newIO);
    }

    public static String sendOrder(SalesOrder order, Session session){
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


