package com.myatm.core;

public class Account {

	private long accountNumber;
	private int pin;
	private long openingBalance;
	private int overDraft;
	/**+ 
	 * Customer account holds the details as account number , pin number,
	 * opening balance and overdraft.
	 * The details are initialized in this constructor.
	 * 
	 * @param accountNumber
	 * @param pin
	 * @param openingBalance
	 * @param overDraft
	 */
	public Account(long accountNumber, int pin, long openingBalance, int overDraft) {
		super();
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.openingBalance = openingBalance;
		this.overDraft = overDraft;
	}
	/**+
	 * This return the opening balance of the customer account for the balance
	 * enquiry and also check if amount exists while performing withdrawal.
	 * 
	 * @return opening balance 
	 */
	public long getOpeningBalance() {
		return openingBalance;
	}
	
	/**+
	 * Set the opening balance of the customer after a withdrawal is made.
	 * @param openingBalance
	 */
	public void setOpeningBalance(long openingBalance) {
		this.openingBalance = openingBalance;
	}
	/**+
	 * Get the account number of the customer which checking the validation of account number 
	 * and pin and for operations like balance check , withdrawal. 
	 * @return
	 */
	public long getAccountNumber() {
		return accountNumber;
	}
	
	/**+
	 * Get the pin of the customer account for validation to further allow for 
	 * the operations like balance check , withdrawal 
	 * @return
	 */
	public int getPin() {
		return pin;
	}

	/**+
	 * Set the pin if pin needs to be changed
	 * @param pin
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}
	/**+
	 * Set the overdraft when it is used at the withdrawal when the opening balance 
	 * becomes zero.The remaining overdraft value is set here. 
	 * @param overDraft
	 */
	public void setOverDraft(int overDraft) {
		this.overDraft = overDraft;
	}
	
	/**+
	 * This returns the overdraft wherever it is required to check this amount . 
	 * @return
	 */
	public int getOverDraft() {
		return overDraft;
	}

}
