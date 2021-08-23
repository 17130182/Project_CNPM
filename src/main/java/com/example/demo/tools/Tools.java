package com.example.demo.tools;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Product;

public class Tools {
	public static List<Product> copyArrayList(List<Product> productlist, int start, int limit) {
		List<Product> result = new ArrayList<Product>();
		int size = 0;
		for (int i = 0; i < productlist.size(); i++) {
			if(i>=start&&size<limit) {
				result.add(productlist.get(i));
				size++;
			}
		}
		return result;
}
}
