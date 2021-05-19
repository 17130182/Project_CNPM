package com.example.demo.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.models.CartItem;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.RepoCustom;
import com.example.demo.repository.UserRepo;
import com.example.demo.tools.RandomID;

@Service
public class CartServices {

	@Autowired
	CartRepo cadao;
	@Autowired
	ProductService pro;

	@Autowired
	RepoCustom cadcus;

	@Autowired
	UserRepo userRepo;

	private static CartServices mngc = new CartServices();
	private List<Cart> listFromDB = new ArrayList<Cart>(); // Duoc dung den khi dang nhap
	private HashMap<Integer, CartItem> list = new HashMap<Integer, CartItem>(); // <id_san_pham, Cart_Item>
	private User customer;

	public static CartServices getInstances() {
		return mngc;
	}

	public void refeshCartWhenSignin(String username) {
		try {
			if (list.isEmpty()) {
				loadItemsFromDatabase(username);
			} else {
				System.out.println("list luc truoc khi signin ko rong!");
				deleteAllToDBWithUsername(username);
				for (CartItem ci : list.values()) {
					float price = Float.parseFloat(ci.getS().getPrice());
					Cart cartTemp = new Cart(RandomID.getCartID(), userRepo.findAllByUsername(username), ci.getS(),
							price, ci.getQuantity(), ci.getPriceItem());
					listFromDB.add(cartTemp);
					cadcus.add_editCart(cartTemp);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CartItem getItemByIDProduct(int id) {
		Collection<CartItem> listValue = list.values();
		for (CartItem ci : listValue)
			if (ci.getS().getId() == id)
				return ci;
		return null;
	}

	public void refeshCartWhenSignout() {
		list.clear();
		listFromDB.clear();
	}

	public int sizeCart() {
		return list.size();
	}

	// Use to load in cart.html
	public Collection<CartItem> listCartItems() {
		return list.values();
	}

	public int getQuantity(int id) {
		for (CartItem ci : listCartItems()) {
			if (ci.getS().getId() == id)
				return ci.getQuantity();
		}
		return 0;
	}

	public void loadItemsFromDatabase(String username) throws SQLException {
		listFromDB = cadao.findAllByUser(userRepo.findAllByUsername(username));
		for (Cart lc : listFromDB) {
			CartItem lci = new CartItem(lc.getProduct(), lc.getQuantity());
			list.put(lc.getProduct().getId(), lci);
		}
	}

	// Them 1 san pham id vao gio hang hoac tang so luong cua no len 1 don vi (neu
	// da ton tai)
	public void addItems(int id) throws SQLException {
		System.out.println("ID san pham can them vao cart: " + id);
		for (Product p : pro.getAll()) {
			if (p.getId() == id)
				if (list.containsKey(id)) {
					CartItem lci = list.get(id);
					lci.incrementQuantity();
					System.out.println("Tang so luong san pham: " + id);
					if (customer != null) {
						updateCartToDB(lci);
					}

				} else {
					CartItem lci = new CartItem(p, 1);
					list.put(id, lci);
					System.out.println("Them san pham: " + id);
					if (customer != null)
						addCartToDB(lci);
				}
		}

	}

	// Thay doi so luong san pham
	public void changeQuantity(String id, int num) throws SQLException {
		int idNum=Integer.parseInt(id);
		CartItem lci = list.get(idNum);
		lci.setQuantity(num);
		if (customer != null)
			updateCartToDB(lci);
	}

	// Giam 1 don vi so luong cua san pham id
	public void decreaseItems(String id) throws SQLException {
		CartItem lci = list.get(id);
		lci.decrementQuantity();
		if (customer != null)
			updateCartToDB(lci);
	}

	// Xoa 1 item co gia tri id trong gio hang
	public void deleteItems(String id) throws SQLException {
		list.remove(id);
		if (customer != null)
			deleteToDB(id);

	}

	// Xoa toan bo gio hang cua customer
	public void clearAllCart() throws SQLException {
		list.clear();
//		if (customer != null)
//			deleteAllToDB();
	}

	public float getPriceTotal() {
		float result = 0;
		for (CartItem l : listCartItems()) {
			result += l.getPriceItem();
		}
		return result;
	}

	public double translatePrice(String unit) {
		double result = 0;
		if (unit.equals("USD")) {
			result = (double) this.getPriceTotal() / 23175;
			result = Math.round(result * 100.0) / 100.0;
		}
		return result;
	}

	public ArrayList<Integer> getListIDShoesInCart() {
		ArrayList<Integer> result = new ArrayList<>();
		for (CartItem lci : list.values())
			if (!result.contains(lci.getS().getId()))
				result.add(lci.getS().getId());

		return result;

	}

	public void deleteAllToDB() {
		cadao.deleteInBatch(listFromDB);
		listFromDB.clear();

	}

	public void deleteToDB(String idProduct) {
		int parse = Integer.parseInt(idProduct);
		for (Cart crt : listFromDB)
			if (crt.getProduct().getId() == parse) {
				listFromDB.remove(crt);
				cadao.deleteById(crt.getId());
				break;
			}
	}

	public void updateCartToDB(CartItem ci) {
		for (Cart crt : listFromDB)
			if (crt.getProduct().getId() == (ci.getS().getId())) {
				crt.setQuantity(ci.getQuantity());
				crt.setTotalcost(ci.getPriceItem());
				cadcus.add_editCart(crt);
				break;
			}
	}

	public void addCartToDB(CartItem ci) {
		float price = Float.parseFloat(ci.getS().getPrice());
		try {
			Cart cartTemp = new Cart(RandomID.getCartID(), customer, ci.getS(), price, ci.getQuantity(),
					ci.getPriceItem());
			listFromDB.add(cartTemp);
			cadcus.add_editCart(cartTemp);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteAllToDBWithUsername(String username) {
		List<Cart> listToDel = cadao.findAllByUser(userRepo.findAllByUsername(username));
		cadao.deleteAll(listToDel);

	}

	public HashMap<Integer, CartItem> getList() {
		return list;
	}

	public void setList(HashMap<Integer, CartItem> list) {
		this.list = list;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

}
