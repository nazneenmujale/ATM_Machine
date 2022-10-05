package com.myatm.coretest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import com.myatm.core.AccountService;
import com.myatm.core.WithdrawResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTest {

	@Test
	@Order(1)
	public void testwithdrawal() {
		
		WithdrawResponse balance6 = AccountService.withdrawAmount(1200, 123456789);
		assertEquals("Withdrawal unsuccessful - Insufficient fund in account  ", balance6.getDisplayMessage());

	}
	
	@Test
	@Order(7)
	public void testwithdrawal9() {
		
		WithdrawResponse balance7 = AccountService.withdrawAmount(5000, 123456789);
		assertEquals("Withdrawal unsuccessful - Insufficient fund in account and ATM ", balance7.getDisplayMessage());
	}
	
	
	
}
