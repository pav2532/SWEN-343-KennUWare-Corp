package com.kennuware.customersupport.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ItemOrders {

	@Id
	@GeneratedValue
	private int id;
	
	private int orderId;
	
	private int itemId;
	
	private int quantity;
	
	public ItemOrders(){

		
	}
	
	public ItemOrders(int orderId, int itemId, int quantity){
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;

	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
