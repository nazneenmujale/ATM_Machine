package com.myatm.coretest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.myatm.core.Notes;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotesTest {

	@Test
	@Order(1)
	public void testwithdrawa20() {
		
		Map<Integer, Integer> withdrawZeroNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		
			withdrawZeroNotes.put(50,0);
			withdrawZeroNotes.put(20,0);
			withdrawZeroNotes.put(10,0);
			withdrawZeroNotes.put(5,0);
		
	    
		assertEquals(withdrawZeroNotes, Notes.notesDispenserCals(0));
		

	}
	
	
	
}