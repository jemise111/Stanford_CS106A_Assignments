/*
 * File: DeleteCharacters.java
 * ------------------
 * This program will delete characters from a string.
 */

import acm.program.*;
import acm.util.*;
import java.awt.*;

public class DeleteCharacters extends ConsoleProgram {
	public void run() {
		while (true) {
			String str = readLine("Enter a string: ");
			if (str.length() == 0) break;
			char ch = readLine("Enter a character: ").charAt(0);
			println(removeAllOccurrences(str, ch));
		}
	}
	
	public String removeAllOccurrences(String str, char ch) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if (Character.toString(str.charAt(i)).equals(Character.toString(ch))) {
				result += "";
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}
}
