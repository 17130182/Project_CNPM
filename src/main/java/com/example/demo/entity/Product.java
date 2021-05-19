package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="catalog_id")
	private Catalog catalog;
	private String name;
	private String price;
	private int qty;
	private int status;
	private String description;
	private String content;
	private String image_link;
	private Date created;
	private int discount;
	public Product() {}
	


	public Product(int id, Catalog catalog, String name, String price, int qty, int status, String description,
			String content, String image_link, Date created, int discount) {
		super();
		this.id = id;
		this.catalog = catalog;
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.status = status;
		this.description = description;
		this.content = content;
		this.image_link = image_link;
		this.created = created;
		this.discount = discount;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Catalog getCatalog() {
		return catalog;
	}



	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public int getQty() {
		return qty;
	}



	public void setQty(int qty) {
		this.qty = qty;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getImage_link() {
		return image_link;
	}



	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}



	public Date getCreated() {
		return created;
	}



	public void setCreated(Date created) {
		this.created = created;
	}



	public int getDiscount() {
		return discount;
	}



	public void setDiscount(int discount) {
		this.discount = discount;
	}



	@Override
	public String toString() {
		return "Product [id=" + id + ", catalog_id=" + catalog.getName() + ", name=" + name + ", price=" + price + ", status="
				+ status + ", description=" + description + ", content=" + content + ", discount=" + discount
				+ ", image_link=" + image_link + ", created="
				+ created + "]";
	}

	
}
