package com.myatm.coretest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.myatm.core.Account;

public class AccountTest {
	@Test
	public void testAccount() {
		Account account1 = new Account(123456789, 1234, 800, 200);
		assertEquals(123456789, account1.getAccountNumber());
		assertEquals(1234, account1.getPin());
		assertEquals(800, account1.getOpeningBalance());
		assertEquals(200, account1.getOverDraft());
		account1.setOpeningBalance(500);
		assertEquals(500, account1.getOpeningBalance());
		account1.setPin(2222);
		assertEquals(2222, account1.getPin());
		account1.setOverDraft(300);
		assertEquals(300,account1.getOverDraft());
	}

}
