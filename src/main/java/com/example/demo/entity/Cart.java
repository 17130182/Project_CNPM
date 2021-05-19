package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	private String id;
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;

	@ManyToOne
	@JoinColumn(name = "idproduct")
	private Product product;

	@NotNull
	private float price;

	@NotNull
	private int quantity;

	@NotNull
	private float totalcost;

	public Cart() {

	}

	public Cart(String id, User user, Product product, float price, int quantity, float totalcost) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
		this.totalcost = totalcost;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(float totalcost) {
		this.totalcost = totalcost;
	}

	

}
