package com.myatm.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//import javax.servlet.http.HttpServletResponse;

public class AccountInitialize {

	private static Map<Long, Account> listOfAccounts = new HashMap<>();
	private static int atmHoldsAmount = 1500;

	private AccountInitialize() {
	}

	static {
		Account account1 = new Account(123456789, 1234, 800, 200);
		Account account2 = new Account(987654321, 4321, 1230, 150);
		listOfAccounts.put(account1.getAccountNumber(), account1);

		listOfAccounts.put(account2.getAccountNumber(), account2);
	}

	public static void reinitialiseAccounts() {
		listOfAccounts.clear();
		Account account1 = new Account(123456789, 1234, 800, 200);
		Account account2 = new Account(987654321, 4321, 1230, 150);
		listOfAccounts.put(account1.getAccountNumber(), account1);
		listOfAccounts.put(account2.getAccountNumber(), account2);
	}

	public static void reinitialiseATMholds() {
		atmHoldsAmount = 1500;
	}

	public static int getAtmHoldsAmount() {
		return atmHoldsAmount;
	}

	/**
	 * this will validate the pin enter by the customer
	 * 
	 * @param pin
	 * @param accountNumber
	 * @return boolean if valid then true else false.
	 */
	public static boolean checkValidPin(int pin, long accountNumber) {
		if (listOfAccounts.containsKey(accountNumber)) {
			if (pin == listOfAccounts.get(accountNumber).getPin()) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public static BalanceResponse getBalance(long accountNumber) {
		if (listOfAccounts.containsKey(accountNumber)) {
			BalanceResponse balanceResponse = new BalanceResponse(listOfAccounts.get(accountNumber).getOpeningBalance(),
					listOfAccounts.get(accountNumber).getOverDraft());
			return balanceResponse;
		} else {
			return null;
		}
	}

	private static WithdrawResponse dispense(int amount, long accountNumber) {

		WithdrawResponse withdrawResponse = new WithdrawResponse();
		Map<Integer, Integer> withdrawZeroNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		for (Map.Entry<Integer, Integer> entry : Notes.listOfNotes.entrySet()) {
			withdrawZeroNotes.put(entry.getKey(), 0);

		}
		// calculate the exact ammount requested. dispense minimum number of notes -
		// return success list
		Map<Integer, Integer> withdrawSuccessNotes = Notes.notesDispenserCals(amount);

		if ((withdrawSuccessNotes != null) && !withdrawSuccessNotes.isEmpty()) {
			// set the remaining bal in account and atm and create response object

			long remainingBalance = listOfAccounts.get(accountNumber).getOpeningBalance() - Long.valueOf(amount);
			listOfAccounts.get(accountNumber).setOpeningBalance(remainingBalance);
			long accountOverdraftWithdraw = remainingBalance + listOfAccounts.get(accountNumber).getOverDraft();
			withdrawResponse.setRemainingBalance(remainingBalance);
			withdrawResponse.setListOfResultNotes(withdrawSuccessNotes);
			withdrawResponse.setbalanceOverdraftWithdraw(accountOverdraftWithdraw);

			atmHoldsAmount = atmHoldsAmount - amount;
			return withdrawResponse;

		} else {
			System.out.println(" Withdrawal unsuccessful - Insufficient fund");

			String displayMessage = "Withdrawal unsuccessful - Insufficient fund";
			withdrawResponse.setRemainingBalance(listOfAccounts.get(accountNumber).getOpeningBalance());
			withdrawResponse.setListOfResultNotes(withdrawZeroNotes);
			withdrawResponse.setbalanceOverdraftWithdraw(listOfAccounts.get(accountNumber).getOverDraft());
			withdrawResponse.setDisplayMessage(displayMessage);
			return withdrawResponse;
		}
	}

	public static WithdrawResponse dispenseAmount(int amount, long accountNumber) {

		WithdrawResponse withdrawResponse = new WithdrawResponse();
		// If withdrawal in unsuccessful then no notes are dispensed .
		Map<Integer, Integer> withdrawZeroNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		for (Map.Entry<Integer, Integer> entry : Notes.listOfNotes.entrySet()) {
			withdrawZeroNotes.put(entry.getKey(), 0);
		}
    
//		try {
		// validate amount to check if the account or ATM holds it
		
		if ((amount <= ((listOfAccounts.get(accountNumber).getOpeningBalance())
				+ listOfAccounts.get(accountNumber).getOverDraft())) && amount < atmHoldsAmount) {
			withdrawResponse = dispense(amount, accountNumber);
			return withdrawResponse;
			//			return dispense(amount, accountNumber);
		} else {

			if (!(amount < (listOfAccounts.get(accountNumber).getOpeningBalance())) && amount < atmHoldsAmount) {

				String displayMessage = "Withdrawal unsuccessful - Insufficient fund in account  ";
				withdrawResponse.setDisplayMessage(displayMessage);
				withdrawResponse.setRemainingBalance(listOfAccounts.get(accountNumber).getOpeningBalance());
				withdrawResponse.setListOfResultNotes(withdrawZeroNotes);
				withdrawResponse.setbalanceOverdraftWithdraw(listOfAccounts.get(accountNumber).getOverDraft());
				return withdrawResponse;
			} else {
				if ((amount < (listOfAccounts.get(accountNumber).getOpeningBalance())) && !(amount < atmHoldsAmount)) {
					String displayMessage = "Withdrawal unsuccessful - Insufficient fund in ATM  ";
					withdrawResponse.setDisplayMessage(displayMessage);
					withdrawResponse.setRemainingBalance(listOfAccounts.get(accountNumber).getOpeningBalance());
					withdrawResponse.setListOfResultNotes(withdrawZeroNotes);
					withdrawResponse.setbalanceOverdraftWithdraw(listOfAccounts.get(accountNumber).getOverDraft());
					return withdrawResponse;
				}
			}

			String displayMessage = "Withdrawal unsuccessful - Insufficient fund in account and ATM ";
			withdrawResponse.setDisplayMessage(displayMessage);

			withdrawResponse.setRemainingBalance(listOfAccounts.get(accountNumber).getOpeningBalance());
			withdrawResponse.setListOfResultNotes(withdrawZeroNotes);
			withdrawResponse.setbalanceOverdraftWithdraw(listOfAccounts.get(accountNumber).getOverDraft());
			return withdrawResponse;
		}
//	}catch(NullPointerException ex ) {
//		System.out.println("Null pointer exception caught \n");
//	}
		
//		return withdrawResponse;
	}

}
