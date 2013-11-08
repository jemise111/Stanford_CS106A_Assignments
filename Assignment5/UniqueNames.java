/*
 * File: WordCount.java
 * ------------------
 * This program is a solution to section assignment 5 problem Unique Names.
 */

import acm.program.*;
import java.util.*;

public class UniqueNames extends ConsoleProgram{
	
	public void run() {
		println("Add names to your unique list with return key. Show list by returning a blank line");
		ArrayList<String> nameArray = new ArrayList<String>();
		while (true) {
			//adds unique name user inputs to name list. Breaks when blank line is entered
			String name = readLine("Enter name: ");
			if (name.length() == 0) break;
			if (!nameArray.contains(name)) nameArray.add(name);
		}
		println("Unique name list contains:");
		printArrayElements(nameArray);
	}
/** Prints elements of an array. Each element printed on new line. */
	private void printArrayElements(ArrayList list) {
		for (int i = 0; i < list.size(); i++) {
			println(list.get(i));
		}
	}
}
