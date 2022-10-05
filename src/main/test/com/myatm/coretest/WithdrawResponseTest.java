package com.myatm.coretest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import com.myatm.core.WithdrawResponse;

public class WithdrawResponseTest {

	@Test
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
}
