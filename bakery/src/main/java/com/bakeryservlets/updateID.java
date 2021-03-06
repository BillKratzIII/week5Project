package com.bakeryservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bakery.DAO;

/**
 * Servlet implementation class updateDB
 */
@WebServlet("/updateID")
public class updateID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	// constructor
	public updateID() {
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
	// method to take input from modifyItem.jsp and set the product ID of the
	// global variable itemToModify, then open the page updateDB.jsp
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DAO.itemToModify.setProductID(request.getParameter("productID"));

		request.getRequestDispatcher("updateDB.jsp").forward(request, response);
	}// doPost()

}// class
