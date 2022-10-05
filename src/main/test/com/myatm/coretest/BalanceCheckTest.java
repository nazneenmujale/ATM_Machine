package com.myatm.coretest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.myatm.core.Account;
import com.myatm.core.AccountInitialize;
import com.myatm.core.AccountService;
import com.myatm.core.BalanceResponse;

public class BalanceCheckTest {


	@Test
	public void testBalance() {
		long accountNumber = 123456789;
		BalanceResponse balance = AccountInitialize.getBalance(accountNumber);

		assertEquals(800, balance.getOpeningBalance());
		assertEquals(200, balance.getOverdraft());

	}

	@Test
	public void testBalance2() {
		long accountNumber = 1234567891;
		BalanceResponse balance = AccountInitialize.getBalance(accountNumber);
		assertNull(balance);

	}

	@Test
	public void testPin1() {
		assertTrue(AccountInitialize.checkValidPin(1234, 123456789));
		assertFalse(AccountInitialize.checkValidPin(123, 123456789));
		assertFalse(AccountInitialize.checkValidPin(1234, 1234567891));
	}

	@Test
	public void testBalanceEnquiry() {

		BalanceResponse balance = AccountService.getBalanceEnquiry(123456789);
		assertEquals(800, balance.getOpeningBalance());
		assertEquals(200, balance.getOverdraft());

		BalanceResponse balance2 = AccountService.getBalanceEnquiry(1234567891);
			assertNull(balance2);
	}

	@Test
	public void testBalanceResponse() {
		BalanceResponse balance = new BalanceResponse(12345, 200);
		assertEquals(12345, balance.getOpeningBalance());
		assertEquals(200, balance.getOverdraft());
	}

	@Test
	public void testcheckValidatePin() {

		assertTrue(AccountService.validatePin(1234, 123456789));
		assertFalse(AccountService.validatePin(123, 123456789));
		assertFalse(AccountService.validatePin(1234, 1234567891));
	}

}

