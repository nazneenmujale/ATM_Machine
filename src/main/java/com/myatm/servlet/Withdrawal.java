package com.myatm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myatm.core.AccountService;
import com.myatm.core.WithdrawResponse;

/**
 * Servlet implementation class Withdrawal This servlet has the get request for
 * the withdrawal
 * @author Nazneen
 */
@WebServlet(urlPatterns = { "/Withdrawal" }, initParams = { @WebInitParam(name = "pin", value = ""),
		@WebInitParam(name = "accountNumber", value = ""), @WebInitParam(name = "amount", value = "") })
public class Withdrawal extends HttpServlet {

	/**
	 * This method is to perform the post request for the withdrawal process. It
	 * return the balance amount in the account and the amount that can be
	 * withdrawn.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameterMap().containsKey("accountNumber") && request.getParameterMap().containsKey("pin")
				&& request.getParameterMap().containsKey("amount")) {
			long accountNumber = Long.valueOf(request.getParameter("accountNumber"));
			int pin = Integer.parseInt(request.getParameter("pin"));
			if (AccountService.validatePin(pin, accountNumber)) {
				int amount = Integer.parseInt(request.getParameter("amount"));
				if (amount % 5 != 0) {
					response.getWriter()
							.append(" Cannot be dispensed, please provide the amount in the multiples of 5");
				} else {
					WithdrawResponse withdrawResponse = AccountService.withdrawAmount(amount, accountNumber);
					if ((withdrawResponse != null) && withdrawResponse.getDisplayMessage().isEmpty()) {
						response.getWriter()
								.append(" Total Available Account Balance is:  "
										+ withdrawResponse.getRemainingBalance())
								.append("\n Total Withdrawn Amount is: " + withdrawResponse.getListOfResultNotes()
										+ "\n Amount that can be withdrawn : "
										+ withdrawResponse.getBalanceOverdraftWithdraw());
					} else {
						response.getWriter().append(withdrawResponse.getDisplayMessage());
					}
				}
			} else {

				response.getWriter().append(" Invalid Pin/Account - Try Again ");
			}
		} else {

			response.getWriter().append(" Please provide pin/ account number / amount ");

		}
	}
}
