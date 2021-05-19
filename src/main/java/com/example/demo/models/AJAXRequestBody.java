package com.example.demo.models;

public class AJAXRequestBody {
	String id;
	String quantity;
	String priceItem;
	String totalCost;
	

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPriceItem() {
		return priceItem;
	}

	public void setPriceItem(String priceItem) {
		this.priceItem = priceItem;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
