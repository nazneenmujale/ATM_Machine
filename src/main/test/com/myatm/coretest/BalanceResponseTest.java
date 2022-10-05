package com.myatm.coretest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import com.myatm.core.BalanceResponse;

public class BalanceResponseTest {

	
	@Test
	public void testBalanceResponse() {
		
		BalanceResponse  response = new BalanceResponse(1000,100);
		assertEquals(100, response.getOverdraft());
		assertEquals(1000, response.getOpeningBalance());
	
		}
}
