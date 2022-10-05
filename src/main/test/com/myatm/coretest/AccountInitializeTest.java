package com.myatm.coretest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import com.myatm.core.AccountInitialize;
import com.myatm.core.AccountService;
import com.myatm.core.BalanceResponse;

import com.myatm.core.WithdrawResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountInitializeTest {

	@Test
	public void testcheckValidatePin() {

		assertTrue(AccountService.validatePin(1234, 123456789));
		assertFalse(AccountService.validatePin(123, 123456789));
		assertFalse(AccountService.validatePin(1234, 1234567891));
	}
	
	@Test
	@Order(2)
	public void testwithdrawal6() {
		
		WithdrawResponse balance6 = AccountInitialize.dispenseAmount(1200, 123456789);
		assertEquals("Withdrawal unsuccessful - Insufficient fund in account  ", balance6.getDisplayMessage());

	}
	
	
	@Test
	public void testBalance2() {
		long accountNumber = 1234567891;
		BalanceResponse balance = AccountInitialize.getBalance(accountNumber);
		assertNull(balance);

	}

}
