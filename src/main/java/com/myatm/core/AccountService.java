package com.myatm.core;



public class AccountService {

	public static boolean validatePin(int pin, long accountNumber) {
		return AccountInitialize.checkValidPin(pin, accountNumber);

	}

	public static BalanceResponse getBalanceEnquiry(long accountNumber) {
		return AccountInitialize.getBalance(accountNumber);

	}

	public static WithdrawResponse withdrawAmount(int amount, long accountNumber) {
		return AccountInitialize.dispenseAmount(amount, accountNumber);
	}

}
