package com.bakery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
	// declare variables
	public static ArrayList<BakeryItem> bakeryItems = new ArrayList<>();
	public static BakeryItem itemToModify = new BakeryItem();
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "sesame";
	static Connection CONN = null;
	static Statement STMT = null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET = null;

	// method to connect to SQL database
	public static void connToDB() {
		System.out.println("Connecting to DB...");

		try {
			Class.forName(JDBC_DRIVER);
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to the database.");
		} catch (Exception e) {
			System.out.println("Failed to connect to the database.");
			e.printStackTrace();
		}
	}// connToDB()

	// method to read data from database
	public static void readFromDB() {

		connToDB();

		try {

			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM bakery.products;");

			while (RES_SET.next()) {

				BakeryItem item = new BakeryItem();

				item.setProductID(RES_SET.getString("product_id"));
				item.setType(RES_SET.getString("type"));
				item.setCalories(RES_SET.getString("calories"));
				item.setPrice(RES_SET.getString("price"));
				item.setTopping(RES_SET.getString("topping"));

				bakeryItems.add(item);

			}

			for (int i = 0; i < bakeryItems.size(); i++) {
				System.out.println(bakeryItems.get(i));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// readFromDB()

	// string to be used as prepared statement to insert new bakery item into
	// the SQL database
	private static String insertIntoTable = "INSERT INTO `bakery`.`products` " + "(type, calories, price, topping) "
			+ "VALUES " + "(?, ?, ?, ?)";

	// method to take in a new bakery item and add it to the SQL database
	public static void writeToDatabase(BakeryItem itemToAdd) {

		try {

			connToDB();

			PREP_STMT = CONN.prepareStatement(insertIntoTable);

			PREP_STMT.setString(1, itemToAdd.getType());
			PREP_STMT.setString(2, itemToAdd.getCalories());
			PREP_STMT.setString(3, itemToAdd.getPrice());
			PREP_STMT.setString(4, itemToAdd.getTopping());

			System.out.println(PREP_STMT);

			PREP_STMT.executeUpdate();
			System.out.println("Item added");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// writeToDB();

	// method that takes in a product ID to return a string to be used as a
	// prepared statement to delete an item from our SQL database
	private static String deleteFromTable(int id) {
		return "DELETE FROM `bakery`.`products`" + " WHERE product_id = " + id + " ;";
	}// deleteFromTable()

	// method to take in a bakery item object and delete it from the SQL
	// database
	public static void deleteFromDB(BakeryItem itemToDelete) {
		System.out.println(itemToDelete.getProductID());
		connToDB();

		try {
			PREP_STMT = CONN.prepareStatement(deleteFromTable(Integer.parseInt(itemToDelete.getProductID())));
			// PREP_STMT.setString(1, playerIDToDelete);
			PREP_STMT.executeUpdate();
			System.out.println("Item deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// deleteFromDB()

	// string to be used as a prepared statement to modify an item in the SQL
	// database
	private static String modifyInDB = "UPDATE `bakery`.`products` " + "SET " + "type = (?), " + "calories = (?), "
			+ "price = (?), " + "topping = (?) " + "WHERE " + "product_id = (?)";

	// method to modify an item in the SQL database based of global variable
	// itemToModify given attributes from the JSPs
	public static void modifyInDB() {

		connToDB();

		try {
			PREP_STMT = CONN.prepareStatement(modifyInDB);

			PREP_STMT.setString(1, itemToModify.getType());
			PREP_STMT.setString(2, itemToModify.getCalories());
			PREP_STMT.setString(3, itemToModify.getPrice());
			PREP_STMT.setString(4, itemToModify.getTopping());
			PREP_STMT.setString(5, itemToModify.getProductID());

			PREP_STMT.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// modifyInDB()

}// class
