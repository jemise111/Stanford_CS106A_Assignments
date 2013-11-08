/*
 * File: WordCount.java
 * ------------------
 * This program is a solution to section assignment 5 problem Histograms.
 */

import acm.program.*;
import java.io.*;

public class ReadScores extends ConsoleProgram{
	
	public void run() {
		BufferedReader rd = openFileReader("Enter File Name: ");
		try {
			while (true) {
			String line = rd.readLine();
			if (line == null) break;
			//converts line read from BufferedReader from string to integer
			int score = Integer.parseInt(line);
			addScore(score);
			}
			rd.close();
		} catch (IOException ex) {
			println("Error");
		}
		displayHistogram();
	}
	
/** Takes the integer score found in the BufferedReader and adds a "*" to the appropriate
 * 	String variable that represents a range of scores.	
 * @param score
 */
	private void addScore(int score) {
		if (score == 100) {onehundreds += "*";}
		else if (score >= 90) {nineties += "*";}
		else if (score >= 80) {eighties += "*";}
		else if (score >= 70) {seventies += "*";}
		else if (score >= 60) {sixties += "*";}
		else if (score >= 50) {fifties += "*";}
		else if (score >= 40) {fourties += "*";}
		else if (score >= 30) {thirties += "*";}
		else if (score >= 20) {twenties += "*";}
		else if (score >= 10) {tens += "*";}
		else { singles += "*";}
	}
	
/** Displays a Histogram with one "*" representing one occurrence of a score found within
 * 	given range.	
 */
	private void displayHistogram() {
		println(singles);
		println(tens);
		println(twenties);
		println(thirties);
		println(fourties);
		println(fifties);
		println(sixties);
		println(seventies);
		println(eighties);
		println(nineties);
		println(onehundreds);
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
	
/* Private instance variables */
	
	private String singles = "00-09: ";
	private String tens = "10-19: ";
	private String twenties = "20-29: ";
	private String thirties = "30-39: ";
	private String fourties = "40-49: ";
	private String fifties = "50-59: ";
	private String sixties = "60-69: ";
	private String seventies = "70-79: ";
	private String eighties = "80-89: ";
	private String nineties = "90-99: ";
	private String onehundreds = "  100: ";	
}
