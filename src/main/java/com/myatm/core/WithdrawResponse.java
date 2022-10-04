package com.myatm.core;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class WithdrawResponse {
	private long remainingBalance;
	private long balanceOverdraftWithdraw;
	Map<Integer, Integer> listOfResultNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
	String displayMessage = "";

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

	public WithdrawResponse() {
		// TODO Auto-generated constructor stub
	}

	public Map<Integer, Integer> getListOfResultNotes() {
		return listOfResultNotes;
	}

	public void setListOfResultNotes(Map<Integer, Integer> listOfResultNotes) {
		this.listOfResultNotes = listOfResultNotes;
	}

	public long getRemainingBalance() {
		return remainingBalance;
	}

	public void setRemainingBalance(long remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

	public long getBalanceOverdraftWithdraw() {
		return balanceOverdraftWithdraw;
	}

	public void setbalanceOverdraftWithdraw(long balanceOverdraftWithdraw) {
		this.balanceOverdraftWithdraw = balanceOverdraftWithdraw;
	}
}
