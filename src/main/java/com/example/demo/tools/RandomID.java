package com.example.demo.tools;

import java.sql.SQLException;
import java.util.UUID;

public class RandomID {
	public static String getCartID() throws SQLException {
		String result = "CRT" + UUID.randomUUID().toString().substring(2, 8).toUpperCase();
		return result;
	}
}
