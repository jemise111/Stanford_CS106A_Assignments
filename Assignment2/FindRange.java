/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	//sets sentinel value to break program
	private static final int SENTINEL = 0;
	
	public void run() {
		/*Sets initial conditions for max and min and
		 * takes care of case when 0 or 1 value is entered.
		 */
		int initVal = readInt("Enter value: ");
		int max = initVal;
		int min = initVal;
		if (initVal == SENTINEL) {
			println("Please enter at least one value before entering the sentinel.");
		} else {
			/*Takes integer values until user inputs sentinel value.
			 * Keeps track of max and min and displays these values 
			 * when sentinel user inputs sentinel.
			 */
			while (true) {
				int val = readInt("Enter value: ");
				if (val == SENTINEL) break;
				if (val > max) {
					max = val;
				}
				if (val < min) {
					min = val;
				}
			}
			println("smallest: " + min);
			println("largest: " + max);
		}
	}
}

