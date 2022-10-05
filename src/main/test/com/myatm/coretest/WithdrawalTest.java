package com.myatm.coretest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.myatm.core.AccountService;
import com.myatm.core.WithdrawResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WithdrawalTest {

	@Test
	@Order(1)
	public void testwithdrawResponse() {

		WithdrawResponse response = new WithdrawResponse();

		response.setRemainingBalance(100);
		assertEquals(100, response.getRemainingBalance());

		Map<Integer, Integer> listOfResultNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		listOfResultNotes.put(50, 10);
		listOfResultNotes.put(30, 10);

		response.setListOfResultNotes(listOfResultNotes);
		assertEquals(listOfResultNotes, response.getListOfResultNotes());

	}

	@Test
	@Order(2)
	public void testwithdrawal() {

		System.out.println("============= Start: Test Case 4.1 =================");

		WithdrawResponse balance = AccountService.withdrawAmount(185, 123456789);
		assertEquals(615, balance.getRemainingBalance());
		assertEquals(815, balance.getBalanceOverdraftWithdraw());

		System.out.println("============= End: Test Case 4.1 =============");

	}

	@Test
	@Order(3)
	public void testwithdrawal6() {
		System.out.println("============= Start: Test Case 4.4 =================");
		WithdrawResponse balance6 = AccountService.withdrawAmount(1200, 123456789);
		assertEquals("Withdrawal unsuccessful - Insufficient fund in account  ", balance6.getDisplayMessage());

		System.out.println("============= End: Test Case 4.4 =================");

	}

	@Test
	@Order(4)
	public void testwithdrawal2() {

		System.out.println("============= Start: Test Case 4.5 =================");
		WithdrawResponse balance1 = AccountService.withdrawAmount(555, 123456789);
		assertEquals(60, balance1.getRemainingBalance());
		assertEquals(260, balance1.getBalanceOverdraftWithdraw());
		System.out.println("============= End: Test Case 4.5 =================");

	}

	@Test
	@Order(5)
	public void testwithdrawal3() {
		System.out.println("============= Start: Test Case 4.6 =================");
		WithdrawResponse balance3 = AccountService.withdrawAmount(60, 123456789);
		assertEquals(0, balance3.getRemainingBalance());
		assertEquals(200, balance3.getBalanceOverdraftWithdraw());
		System.out.println("============= End: Test Case 4.6 =================");

	}

	@Test
	@Order(6)
	public void testwithdrawal4() {
		System.out.println("============= Start: Test Case 4.7 =================");
		WithdrawResponse balance4 = AccountService.withdrawAmount(50, 123456789);
		assertEquals(-50, balance4.getRemainingBalance());
		assertEquals(150, balance4.getBalanceOverdraftWithdraw());
		System.out.println("============= End: Test Case 4.7 =================");

	}

	@Test
	@Order(7)
	public void testwithdrawal7() {
		System.out.println("============= Start: Test Case 4.8 =================");
		WithdrawResponse balance6 = AccountService.withdrawAmount(250, 123456789);
		assertEquals("Withdrawal unsuccessful - Insufficient fund in account  ", balance6.getDisplayMessage());

		System.out.println("============= End: Test Case 4.8 =================");

	}

	@Test
	@Order(8)
	public void testwithdrawal5() {
		System.out.println("============= Start: Test Case 4.9 =================");
		WithdrawResponse balance5 = AccountService.withdrawAmount(200, 987654321);
		assertEquals(1030, balance5.getRemainingBalance());
		assertEquals(1180, balance5.getBalanceOverdraftWithdraw());
		System.out.println("============= End: Test Case 4.9 =================");

	}

	@Test
	@Order(9)
	public void testwithdrawal8() {
		System.out.println("============= Start: Test Case 4.10 =================");
		WithdrawResponse balance7 = AccountService.withdrawAmount(600, 987654321);
		assertEquals("Withdrawal unsuccessful - Insufficient fund in ATM  ", balance7.getDisplayMessage());
		System.out.println("============= End: Test Case 4.10 =================");

	}

	@Test
	@Order(10)
	public void testwithdrawal9() {
		System.out.println("============= Start: Test Case 4.11 =================");
		WithdrawResponse balance7 = AccountService.withdrawAmount(600, 123456789);
		assertEquals("Withdrawal unsuccessful - Insufficient fund in account and ATM ", balance7.getDisplayMessage());
		System.out.println("============= End: Test Case 4.11 =================");

	}
}
