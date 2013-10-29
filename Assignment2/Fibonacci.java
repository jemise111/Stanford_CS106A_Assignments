/*
 * File: Fibonacci.java 
 * --------------------
 * This file is a solution to the Fibonacci question
 * in section assignment 2.
 */

import acm.program.*;

public class Fibonacci extends ConsoleProgram {
	//Sets first integer in Fibonacci sequence
	private static final int FIB_FIRST = 0;
	//Sets second integer in Fibonacci sequence
	private static final int FIB_SECOND = 1;
	//Sets max value Fibonacci sequence program will run to
	private static final int MAX_TERM_VALUE = 10000;
	
	/*Will list all values in Fibonacci sequence starting with 
	 * FIB_FIRST and FIB_SECOND constants set previously.
	 * While loop will break when next number in sequence is 
	 * greater than MAX_TERM VALUE constant set previously.
	 */
	
	public void run() {
		println("This program lists the Fibonacci sequence.");
		int fib1 = FIB_FIRST;
		int fib2 = FIB_SECOND;
		println(fib1);
		println(fib2);
		while (true) {
			int term = fib1 + fib2;
			if (term > MAX_TERM_VALUE) break;
			println(term);
			fib1 = fib2;
			fib2 = term;
		}
	}
}
