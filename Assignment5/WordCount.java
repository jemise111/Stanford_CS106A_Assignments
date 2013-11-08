/*
 * File: WordCount.java
 * ------------------
 * This program is a solution to section assignment 5 problem Word Count.
 */

import acm.program.*;
import java.io.*;

public class WordCount extends ConsoleProgram {
	
	public void run() {
		int numlines = 0;
		int numwords = 0;
		int numchars = 0;
		BufferedReader rd = openFileReader("Enter file name: ");
		try {
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				/*For each line number of lines is updated and number of characters is 
				updated for the length of the line.*/
				numlines ++;
				numwords += addWords(line);
				numchars += line.length();
			}
			rd.close();
		} catch (IOException ex) {
			println("Error");
		}
		println("Lines = " + numlines);
		println("Words = " + numwords);
		println("Characters = " + numchars);
	}

/** Checks each character in a given line. If the character is not a letter or 
 * digit the preceding word is marked as a word. Returns number of words in that
 * line.
 * @param line
 * @return numWordsInLine
 */
	private int addWords (String line) {
		int numWordsInLine = 0;
		for (int i = 1; i < line.length() ; i++) {
			if (!Character.isLetterOrDigit(line.charAt(i))) {
				numWordsInLine ++;
			}
		}
		return numWordsInLine;
	}
	
/** Returns BufferReader attached to contents of file that user inputs. If no file is found
 * asks user for another file.
 * @param prompt
 * @return BufferedReader
 */	
	private BufferedReader openFileReader(String prompt) {
		BufferedReader rd = null;
		while (rd == null) {
			String name = readLine(prompt);
			try {
				rd = new BufferedReader(new FileReader(name));
			} catch (IOException ex) {
				println("Can't open that file.");
			}
		}
		return rd;
	}
	
}
