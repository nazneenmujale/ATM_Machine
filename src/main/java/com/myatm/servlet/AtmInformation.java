package com.myatm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myatm.core.AccountInitialize;

/**
 * Servlet implementation class AtmInformation This servlet has the get request
 * for the ATM information
 */
@WebServlet(urlPatterns = { "/AtmInformation" }, initParams = { @WebInitParam(name = "AmountInfo", value = "") })
public class AtmInformation extends HttpServlet {

	/**
	 * The Get request just gets the current ATM balance .
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			if (request.getParameterMap().containsKey("AmountInfo"))

			response.getWriter().append("Served at: " + AccountInitialize.getAtmHoldsAmount());
	}

	
}
