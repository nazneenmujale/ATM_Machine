package com.myatm.core;

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
