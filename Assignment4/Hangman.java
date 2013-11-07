/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;

public class Hangman extends ConsoleProgram {
	
/** Maxiumum number of guesses allowed */
	private static final int GUESSES_ALLOWED = 8;

    public void init() {
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
	
	public void run() {
		while (true) {
			println("Welcome to Hangman!");
			chooseWord();
			playGame();
			if (!isNewGame()) break;
		}
    }
    
/** Grabs a random word from the Hangman Lexicon */
    private void chooseWord() {
    	solution = hanglex.getWord(rgen.nextInt(0, hanglex.getWordCount()-1)); 
    }
    
    private void playGame() {
    	canvas.reset();
    	initializeGuessWord();
    	numguesses = 0; //resets numguesses
    	while(guessword.equals(solution) == false && numguesses < GUESSES_ALLOWED) {
    		showWord();
    		getGuess();
    		update();
    	}
    	if (numguesses >= GUESSES_ALLOWED) {
    		//losing scenario
    		println("You're completely hung.");
    		println("The word was: " + solution);
    		println("You lose");
    	} else {
    		//winning scenario
    		canvas.displayWord(solution);
    		println("You guessed the word " + solution + ".");
    		println("You win!");
    	}
    	guessword = ""; //resets guessword
    }
    
/** Creates initial displayed word which is series of dashes with length equal to the solution. */
    private void initializeGuessWord() {
    	for (int i = 0; i < solution.length(); i++) {
    		guessword += "-";
    	}
    }
    
/** Tells user what current word looks like and how many guesses remain. */
    private void showWord() {
    	println("The word now looks like this: " + guessword);
    	canvas.displayWord(guessword);
    	if (GUESSES_ALLOWED - numguesses == 1) {println("You have only one guess left.");}
    	else {println("You have " + (GUESSES_ALLOWED - numguesses) + " guesses left.");}
    }
    
/** Gets character guess from user. Checks for errors in length and type of character. */    
    private void getGuess() {
    	while (true) {
    		String str = readLine("Your guess: ").toUpperCase();
    		if (str.length() != 1) {
    			println("Please enter 1 character!");
    		} else {
    			guesschar = str.charAt(0);
    			if (guesschar < 'A' || guesschar > 'Z')  {
    				println("Please enter a valid letter!");
    			} else { break;}
    		}
    	}
    }
    
    
/** First case is an incorrect guess where player loses one turn. Second case replaces the 
 * 	dashes in the guessword with the correctly guessed characters.    
 */
    private void update() {
    	if (solution.indexOf(guesschar) == -1) {
    		//case for incorrect guess
    		println("There are no " + guesschar + "'s in the word.");
    		numguesses ++;
    		canvas.noteIncorrectGuess(guesschar, numguesses);
    	} else {
    		println("That guess is correct.");
    		replaceCharacters();
    	}
    }
    
    
/** Loops through each letter of solution. If guessed character is found, the dash in guessword
 * 	is replaced with the correct character.
 */
    private void replaceCharacters() { 
    	for (int i = 0; i < solution.length(); i++) {
    		if (solution.charAt(i) == guesschar) {
    			guessword = guessword.substring(0,i) + guesschar + guessword.substring(i + 1);
    		}
    	}
    }

/** Checks if user wants to play another game. Returns true if yes. False if no. Takes care of
 * case when user doesn't enter y or n.
 */
    private boolean isNewGame() {
    	while (true){
    		String response = readLine("Play again (y) ? ").toUpperCase();
    		if (response.length() != 1) {
    			println("One character please!");
    		} else if (response.charAt(0) == 'Y') {
    			return true;
    		} else {
    			return false;
    		}
    	} 	
    }
    
/* Private instance variables */
    
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private HangmanLexicon hanglex = new HangmanLexicon();
    private String solution;
    private int numguesses = 0;
    private String guessword = "";
    private char guesschar;
    private HangmanCanvas canvas;

}
