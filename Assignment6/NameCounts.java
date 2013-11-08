/* 
 * File: NameCounts.java
 * ----------------------
 * This is a solution to section assignment 6 problem Name
 * Counts.
 */

import acm.program.*;
import java.util.*;

public class NameCounts extends ConsoleProgram {
	
	public void run() {
		//Initialize HashMap of String names and Integer count 
		HashMap<String, Integer> nameMap = new HashMap<String, Integer>();
		println("Enter names to keep track of. Enter a blank line");
		println("to retrieve your name count.");
		
		while(true) {
			String name = readLine("Enter name: ");
			if (name.length() == 0) break;
			addNameToMap(name, nameMap);
		}
		displayNameMap(nameMap);
	}
/** Adds String parameter to HashMap parameter as a key. If key already exists
 * increments the associated value, the name count, by one. If not, assigns String 
 * to a new key and gives it the value 1.
 * @param name is the name key to add or increment count by 1
 * @param map is the HashMap to add name to
 */
	private void addNameToMap(String name, HashMap<String, Integer> map) {
		Integer count = map.get(name);
		if (count == null) { //if map doesn't contain name
			map.put(name, 1); //add name and set initial value to 1
		} else {
			count++; 
			map.put(name, count); //else increment count by one and set as new count
		}
	}
/** Displays names and counts from the name map.
 * @param HashMap to display
 */
	private void displayNameMap(HashMap<String, Integer> map) {
		for(String name : map.keySet()) {
			Integer count = map.get(name);
			println("Entry [" + name + "] has count " + count);
		}
	}
}
