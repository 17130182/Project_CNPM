package com.example.demo.models;

public class AJAXRespondBody {
	String name;
	String link_image;
	String price;
	int quantity;
	int totalPrice;
	int sizeCart;
	

	public int getSizeCart() {
		return sizeCart;
	}

	public void setSizeCart(int sizeCart) {
		this.sizeCart = sizeCart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink_image() {
		return link_image;
	}

	public void setLink_image(String link_image) {
		this.link_image = link_image;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
