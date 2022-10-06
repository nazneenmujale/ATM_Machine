package com.myatm.core;

/**
 * + The object of this class is sent in response to the balance request . It
 * holds two values the opening balance and with the overdraft value.
 * 
 * @author Nazneen
 *
 */
public class BalanceResponse {
	private long openingBalance;
	private int overdraft;

	public BalanceResponse(long openingBalance, int overdraft) {

		this.openingBalance = openingBalance;
		this.overdraft = overdraft;
	}

	public long getOpeningBalance() {
		return openingBalance;
	}

	public int getOverdraft() {
		return overdraft;
	}
}
