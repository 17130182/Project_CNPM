package com.example.demo.tools;

import java.sql.SQLException;
import java.util.UUID;

public class Validate { // tao ma ngau nhien
	public static String getCodeNumber() throws SQLException {
		double num = Math.random();
		String code = num + "";
		String result = code.substring(2, 8);
		return result;
	}
}
