/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.util.*;
import java.io.*;

public class HangmanLexicon {

/* HangmanLexicon constructor reads in the HangmanLexicon text file line by line and
 * adds each line to an array containing the list of words to be used in the Hangman
 * game.
 */
	public HangmanLexicon() {
		try{
			//reads HangmanLexicon text file
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while(true){
				String line = rd.readLine();
				if (line == null) break;
				//adds each line of the HangmanLexicon as a new word in the Hangman word list array
				wordList.add(line);
			}
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	};
	
	/* Private instance variables */
	private ArrayList<String> wordList = new ArrayList<String>();
}
