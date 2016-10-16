package com.kennuware.sales.domain.Wearables;

public interface Wearable {

    void addProduct(int amount);
    void setProduct(int amount);
    Double calculatePrice();
    int getQuantity();
    int getuid();
}
