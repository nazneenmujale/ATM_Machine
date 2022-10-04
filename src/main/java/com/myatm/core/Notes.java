package com.myatm.core;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Notes {

	final static Map<Integer, Integer> listOfNotes = new TreeMap<Integer, Integer>(Collections.reverseOrder());
	static boolean withDrawalResult = false;

	static {
		cashUpload();
	}

	public static void cashUpload() {
		listOfNotes.clear();
		listOfNotes.put(50, 10);
		listOfNotes.put(20, 30);
		listOfNotes.put(10, 30);
		listOfNotes.put(5, 20);

	}

	public static Map<Integer, Integer> notesDispenserCals(int amountTobeDispensed) {
		// Logic for notes to be dispensed

		final Map<Integer, Integer> listOfNotesBackup = new TreeMap<Integer, Integer>(Collections.reverseOrder());

		for (Map.Entry<Integer, Integer> entry : listOfNotes.entrySet()) {
			listOfNotesBackup.put(entry.getKey(), entry.getValue());
		}

		for (Entry<Integer, Integer> entry : listOfNotes.entrySet()) {

			int key = entry.getKey();
			if (key > amountTobeDispensed) {
				// System.out.println("The key:" + key + " is bigger than the amount: " +
				// amountTobeDispensed);
			} else {

				int notes;
				notes = amountTobeDispensed / key;
				// Check to see there are sufficient notes for dispense
				if (entry.getValue() != 0 && entry.getValue() >= notes) {
					// If sufficient, reduce note from ATM
					listOfNotes.put(key, entry.getValue() - notes);

					int remainingAmount = amountTobeDispensed % key;
					amountTobeDispensed = remainingAmount;

				} else if (entry.getValue() != 0) {
					// eg : amountTobeDispensed =555, 50_notes available 10 , all 10 taken
					int valueOfNotes = entry.getValue() * key;
					amountTobeDispensed = amountTobeDispensed - valueOfNotes;

					// Reduce the notes from ATM - all notes are taken
					listOfNotes.put(key, 0);
				}
			}
		}

		if (amountTobeDispensed == 0)
			withDrawalResult = true;
		else {
			// Rollback the listOfNotes if withdrawal fails

			for (Map.Entry<Integer, Integer> entry : listOfNotesBackup.entrySet()) {
				listOfNotes.put(entry.getKey(), entry.getValue());
			}
		}

//		if (withDrawalResult) {
			// Resultant Map after cash withdrawal is as follows
			for (Map.Entry<Integer, Integer> entry : listOfNotesBackup.entrySet()) {
				listOfNotesBackup.put(entry.getKey(), entry.getValue() - listOfNotes.get(entry.getKey()));
			}
			return listOfNotesBackup;
//		} else {
//			return null;
//		}
			
	}
}
