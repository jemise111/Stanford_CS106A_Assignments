/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
	public void run() {
		int num = readInt("Enter a number: ");
		if (num < 0) {
			println("You must enter a positive number.");
		} else {
			int counter = 0;
			while (num != 1) {
				int prev = num;
				counter ++;
				if (num % 2 == 0) {
					num /= 2;
					println(prev + " is even, so I take half: " + num);
				} else { 
					num = 3*num + 1;
					println(prev + " is odd, so I make 3n + 1: " + num);
				}
			}
			println("The process took " + counter + " to reach 1.");
		}
	}
}

