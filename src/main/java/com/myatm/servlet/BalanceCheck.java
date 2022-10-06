package com.myatm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myatm.core.AccountService;
import com.myatm.core.BalanceResponse;
import com.myatm.core.WithdrawResponse;

/**
 * Servlet implementation class BalanceCheck This servlet has the get request
 * for the Balance check
 * @author Nazneen
 */

@WebServlet(urlPatterns = { "/BalanceCheck" }, initParams = { @WebInitParam(name = "accountNumber", value = ""),
		@WebInitParam(name = "pin", value = "") })

public class BalanceCheck extends HttpServlet {

	/**
	 * This servlet method handles the get request to the "/BalanceCheck URI, the
	 * balance enquiry for the customer account It gets the balance only if the
	 * customer validation is successfull.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) *
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameterMap().containsKey("accountNumber") && request.getParameterMap().containsKey("pin")) {
			long accountNumber = Long.valueOf(request.getParameter("accountNumber"));
			int pin = Integer.parseInt(request.getParameter("pin"));
			if (AccountService.validatePin(pin, accountNumber)) {
				BalanceResponse balance = AccountService.getBalanceEnquiry(accountNumber);
				response.getWriter()
						.append(" Total Balance is: " + balance.getOpeningBalance()
								+ "\n Amount that can be withdrawn is :"
								+ (balance.getOverdraft() + balance.getOpeningBalance()));
			} else {
				response.getWriter().append(" Invalid Account/Pin- Try Again ");
			}
		} else {

			response.getWriter().append(" Please provide account number & pin ");
		}
	}

}
