package com.kennuware.sales.domain;

/**
 * Created by Pedro Vega on 12/4/2016.
 */
public class ItemMetrics {
    private String name;
    private int quantity = 0;
    private double revenue = 0;
    public ItemMetrics(String name, int quantity, double revenue){
        this.name = name;
        this.quantity = quantity;
        this.revenue = revenue;
    }
    public ItemMetrics(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemMetrics{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", revenue=" + revenue +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemMetrics)) return false;

        ItemMetrics that = (ItemMetrics) o;

        if (quantity != that.quantity) return false;
        if (Double.compare(that.revenue, revenue) != 0) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }
}
