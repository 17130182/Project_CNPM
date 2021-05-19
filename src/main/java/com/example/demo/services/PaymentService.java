package com.example.demo.services;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Bill_Detail;
import com.example.demo.models.CartItem;
import com.example.demo.repository.BillRepo;
import com.example.demo.repository.RepoCustom;

@Service
public class PaymentService {
	@Autowired
	CartServices cart;

	@Autowired
	UserServices user;
	@Autowired
	BillRepo billRepo;
	@Autowired
	RepoCustom repoCustom;

	public void createNewBill(String username, String address, String note) throws SQLException {
		Bill bill = new Bill(user.getUser(username), address, user.getUser(username).getPhone(), "CASH",cart.getPriceTotal(),new Timestamp(System.currentTimeMillis()),note,"Da nhan don hang");
		Collection<CartItem> listCartItem = cart.listCartItems() 	;
		for (CartItem i : listCartItem) {
			Bill_Detail bd = new Bill_Detail(bill, i.getS(), i.getS().getPrice(),i.getS().getName(), i.getQuantity(), i.getPriceItem());
			repoCustom.add_editBill_Detail(bd);
		}
		cart.clearAllCart();
		cart.deleteAllToDBWithUsername(username);
	}
}
