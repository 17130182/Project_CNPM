package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "bill_detail")
public class Bill_Detail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "idbill")
	private Bill bill;
	@ManyToOne
	@JoinColumn(name = "idproduct")
	private Product product;
	
	
	private String price;
	@NotNull
	private int quantity;

	@NotNull
	private float totalcost;
	@Column(name="name")
	private String name;
	
	public Bill_Detail() {
		
	}
	public Bill_Detail(Bill bill, Product product, String price,String name, int quantity, float totalcost) {
		super();
		this.name = name;
		this.bill = bill;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
		this.totalcost = totalcost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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
