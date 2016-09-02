package com.bakeryservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bakery.BakeryItem;
import com.bakery.DAO;

/**
 * Servlet implementation class addToDB
 */
@WebServlet("/addToDB")
public class addToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	// constructor
	public addToDB() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// method takes input from web form in addToDB.html and adds a new item to
	// the SQL database through DAO.writeToDB() method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BakeryItem itemToAdd = new BakeryItem();

		itemToAdd.setType(request.getParameter("type"));
		itemToAdd.setCalories(request.getParameter("calories"));
		itemToAdd.setPrice(request.getParameter("price"));
		itemToAdd.setTopping(request.getParameter("topping"));

		DAO.writeToDatabase(itemToAdd);

		request.getRequestDispatcher("readDB.jsp").forward(request, response);
	}// doPost()

}// class
