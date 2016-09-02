package com.bakeryservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bakery.DAO;

/**
 * Servlet implementation class updateItem
 */
@WebServlet("/updateItem")
public class updateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	// constructor
	public updateItem() {
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
	// method to take in form date from updateDB.jsp and update the date in the
	// SQL dateabase by setting the attributes to the global variable
	// itemToModify and then calling the modifyInDB() method
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DAO.itemToModify.setType(request.getParameter("type"));
		DAO.itemToModify.setCalories(request.getParameter("calories"));
		DAO.itemToModify.setPrice(request.getParameter("price"));
		DAO.itemToModify.setTopping(request.getParameter("topping"));

		DAO.modifyInDB();

		request.getRequestDispatcher("readDB.jsp").forward(request, response);
	}// doPost()

}// class
