package com.example.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Bill_Detail;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Catalog;
import com.example.demo.entity.Product;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;

@Repository
public class RepoCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void add_editProduct(Product cm) {
		this.entityManager.merge(cm);
	}

	@Transactional
	public void add_editCart(Cart cm) {
		this.entityManager.merge(cm);
	}
	@Transactional
	public void add_editUser(User cm) {
		this.entityManager.merge(cm);
	}
	
	@Transactional
	public void add_editBill(Bill cm) {
		this.entityManager.merge(cm);
	}
	
	@Transactional
	public void add_editBill_Detail(Bill_Detail cm) {
		this.entityManager.merge(cm);
	}
	@Transactional
	public void addUserRole(UserRole cm) {
		this.entityManager.merge(cm);
	}
	@Transactional
	public void add_editUserRole(UserRole cm) {
		this.entityManager.merge(cm);
	}
	
	@Transactional
	public void add_editCatalog(Catalog cm) {
		this.entityManager.merge(cm);
	}
	@Transactional
	public void addReview(Review cm) {
		this.entityManager.merge(cm);
	}
	
	
}
