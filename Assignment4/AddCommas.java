/*
 * File: AddCommas.java
 * ------------------
 * This program will add commas to a numeric string.
 */

import acm.program.*;
import acm.util.*;
import java.awt.*;

public class AddCommas extends ConsoleProgram {

	public void run() {
		while (true) {
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0) break;
			println(addCommasToNumericString(digits));	
		}
	}
	
	private String addCommasToNumericString (String digits) {
		String result = Character.toString(digits.charAt(0));
		for (int i = 1; i < digits.length(); i++) {
			if ((digits.length() - i) % 3 == 0) {
				result += "," + digits.charAt(i);
			} else {
				result += digits.charAt(i);
			}
		}
		return result;
	}
}

