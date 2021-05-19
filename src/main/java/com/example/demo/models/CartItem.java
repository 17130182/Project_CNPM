package com.example.demo.models;

import com.example.demo.entity.Product;

public class CartItem {
	private Product s;
	private int quantity;

	public CartItem(Product s, int quantity) {
		super();
		this.s = s;
		this.quantity = quantity;
	}


	public Product getS() {
		return s;
	}

	public void setS(Product s) {
		this.s = s;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void incrementQuantity() {
		quantity++;
	}

	public void decrementQuantity() {
		quantity--;
	}

	public int getPriceItem() {
		int price = Integer.parseInt(s.getPrice());
		return (int) (price * quantity);
		
	}

}
