/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}
	
/** Plays game for number of rounds. */
	private void playGame() {
		//initializes categoryTracker array to all values of false;
		initializeCategoryTracker();
		//initializes scoreArray to all values of 0;
		initializeScoreArray();
		/*use nested for loop since we know exactly how many players we have and
		 * how many rounds will be played
		 */
		for(int rounds = 1; rounds <= N_SCORING_CATEGORIES; rounds++) {
			for(int player = 1; player <= nPlayers; player++) {
				takeTurn(playerNames[player - 1], player); //player - 1 since loop starts at 1
			}
		}
		displayWinner();
	}

/** Initializes the categoryTracker matrix to all false's. */
	private void initializeCategoryTracker() {
		categoryTracker = new boolean[nPlayers][N_CATEGORIES];
		for (int i = 0; i < nPlayers; i++) {
			for(int j = 0; j < N_CATEGORIES; j++) {
				categoryTracker[i][j] = false;
			}
		}
	}

/** Initializes the scoreArray to set each player's score to 0 for all categories. 
 *  The scoreArray is a 2d matrix with scoreArray[i] representing each player's number (i) - 1 and
 *  scoreArray[i][j] is the score for each category where j - category number.
 */
	private void initializeScoreArray() {
		scoreArray = new int[nPlayers][N_CATEGORIES + 1];
		for (int i = 0; i < nPlayers; i ++) {
			for (int j = 0; j <= N_CATEGORIES; j++) {
				scoreArray[i][j] = 0;
			}
		}
	}
	
/** Method for each players turn
 * @param name of player
 * @param player number
 */
	private void takeTurn(String playerName, int player) {
		display.printMessage(playerName + "'s turn!, Click the \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(player);
		initializeDiceArray(); //sets random values for all five dice
		display.displayDice(dice);
		//for re-roll 2 and 3
		for (int i = 0; i < 2; i++) {
			display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\"");
			display.waitForPlayerToSelectDice();
			updateDiceArray();
			display.displayDice(dice);
		}
		display.printMessage("Select a category for this roll");
		updateScore(player);
	}

/** Initializes dice array containing N_DICE dice. Conducts the first roll assigning 
 * a random number from 1 to 6 to each position in the dice array.
 * @return array of size N_DICE 
 */
	private void initializeDiceArray() {
		dice = new int[N_DICE];
		for (int i = 0; i < dice.length; i++) {
			int diceNum = rgen.nextInt(1,6);
			dice[i] = diceNum;
		}
	}

/** Re-rolls whichever dice were selected and updates the dice array with new
 * randomly generated values. 
 */
	private void updateDiceArray() {
		for (int i = 0; i < dice.length; i++) {
			if (display.isDieSelected(i)) {
				int newDiceNum = rgen.nextInt(1,6);
				dice[i] = newDiceNum;
			}
		}
	}	
	
/** Checks if category has already been selected using the categoryTracker matrix. If it has been
* 	player is asked to select a category. If not, score is updated and category tracker are 
* 	updated. Note player and categorySelected are indexed at 0 so need to subtract one for correct matching.
* 	@param player number 
*/
	private void updateScore(int player) {
		while (true) {
			int categorySelected = display.waitForPlayerToSelectCategory();
			if (!categoryTracker[player - 1][categorySelected - 1]) {
				int score = calculateScore(player, categorySelected);
				display.updateScorecard(categorySelected, player, score);
				categoryTracker[player - 1][categorySelected - 1] = true;
				updateTotals(player, categorySelected, score);
				break;
			} else {
				display.printMessage("You already picked that category. Please choose another category");
			}
		}
	}
	
/** Calculates score to be recorded based on what player selects. Uses the method checkCategory to see
 * if dice matches criteria for selected category. If not, player receives a 0.
 * @param player
 * @param categorySelected
 * @return score as an integer
 */
	private int calculateScore(int player, int categorySelected) {
		updateScoreArray(player, categorySelected);
		int score = 0;
		if (checkCategory(categorySelected)) {
			score = scoreArray[player - 1][categorySelected];
		}
		return score;
	}

/** Method keeps track of totals for each player using the scoreArray which is comprised of UPPER_SCORE,
 * LOWER_SCORE, and the UPPER_SCORE_BONUS if UPPER_SCORE is at least 63 points.
 * @param player
 * @param categorySelected
 * @param score to be added to totals
 */
	private void updateTotals(int player, int categorySelected, int score) {
		//update UPPER_SCORE is player selects ONES through SIXES
		if (categorySelected <= SIXES) {
			int upperScore = scoreArray[player - 1][UPPER_SCORE] += score;
			display.updateScorecard(UPPER_SCORE, player, upperScore);
		} else { //else update LOWER_SCORE
			int lowerScore = scoreArray[player - 1][LOWER_SCORE] += score;
			display.updateScorecard(LOWER_SCORE, player, lowerScore);
		}
		if (scoreArray[player - 1][UPPER_SCORE] >= 63) { //updates UPPER_BONUS if applicable
			int upperBonus = scoreArray[player - 1][UPPER_BONUS] = 35;
			display.updateScorecard(UPPER_BONUS, player, upperBonus);
		}
		//updates total score on each turn
		int total = scoreArray[player - 1][TOTAL] = (scoreArray[player - 1][UPPER_SCORE] + 
				scoreArray[player - 1][UPPER_BONUS] + scoreArray[player - 1][LOWER_SCORE]);
		display.updateScorecard(TOTAL, player, total);
	}

/** Creates a scoreArray matrix that tracks each players score for each category. player - 1 is used in place 
 * of each player's number since matrix is indexed at 0.  
 */
	private void updateScoreArray(int player, int categorySelected) {
		int diceSum = getDiceSum(dice);
		if (categorySelected <= SIXES) {
			//for ONES through SIXES add number up dice showing the categorySelected's number
			for (int numdie = 0; numdie < N_DICE; numdie++) {
				if (dice[numdie] == categorySelected) {
					scoreArray[player - 1][categorySelected] += categorySelected;
				}
			}
		} else if (categorySelected == THREE_OF_A_KIND || categorySelected == FOUR_OF_A_KIND || categorySelected == CHANCE) {
			//THREE_OF_A_KIND, FOUR_OF_A_KIND, CHANCE are equal to the total sum of the dice
			scoreArray[player - 1][categorySelected] = diceSum;
		} else {
			//FULL_HOUSE, SMALL_STRAIGHT, LARGE_STRAIGHT, YAHTZEE each have predetermined values
			int score = 0;
			switch (categorySelected) {
				case FULL_HOUSE: score = FULL_HOUSE_VALUE;
								break;
				case SMALL_STRAIGHT: score = SMALL_STRAIGHT_VALUE;
								break;
				case LARGE_STRAIGHT: score = LARGE_STRAIGHT_VALUE;
								break;
				case YAHTZEE: score = YAHTZEE_VALUE;
								break;
			}
			scoreArray[player - 1][categorySelected] = score;
		}
	}

/** Method to calculate the sum of an array of dice.	
 * @param dice array
 * @return sum of dice
 */
	private int getDiceSum(int[] dice) {
		int diceSum = 0;
		for (int i = 0; i < N_DICE; i++) {
			diceSum += dice[i];
		}
		return diceSum;
	}

/** This method replaces the YahteeMagicStub and checks to see if the dice meet the criteria to fill
 * the chosen category. 	
 * @param categorySelected
 * @return true is dice meet appropriate criteria, false otherwise
 */
	private boolean checkCategory(int categorySelected) {
		int nMatches = 0;
		int diceSum = getDiceSum(dice);
		for (int i = 0; i < N_DICE; i++) {
			for (int j = 0; j < N_DICE; j++) {
				if (dice[i] == dice[j]) nMatches += 1;
			}
		}
		if (categorySelected <= SIXES || categorySelected == CHANCE) {
			return true;
		} else if (categorySelected == THREE_OF_A_KIND) {
			if (nMatches >= 11) return true;
		} else if (categorySelected == FOUR_OF_A_KIND) {
			if (nMatches >= 17) return true;
		} else if (categorySelected == FULL_HOUSE) { 
			if (nMatches == 13) return true;
		} else if (categorySelected == SMALL_STRAIGHT) {
			if ((contains(dice, 1) && contains(dice,2) && contains(dice, 3) && contains(dice, 4)) ||
					(contains(dice, 2) && contains(dice, 3) && contains(dice, 4) && contains(dice, 5)) ||
					(contains(dice, 3) && contains(dice, 4) && contains(dice, 5) && contains(dice, 6))) {
				return true;
			}
		} else if (categorySelected == LARGE_STRAIGHT) {
			if (nMatches == 5 && (diceSum == 15 || diceSum == 20)) return true;
		} else if (categorySelected == YAHTZEE) {
			if (nMatches == 25) return true;
		}
		return false;
	}

/** Method to determine if an array of integers contains a certain integer.	
 * @param dice which is an integer array
 * @param value which is the integer we're interested in checking
 * @return true if array contains integer, otherwise false
 */
	private boolean contains(int[] dice, int value) {
		boolean contains = false;
		for (int i = 0; i < dice.length; i++) {			
			if (dice[i] == value) {
				contains = true;
				break;
			}
		}
		return contains;
	}
	
/** Displays the winner of the game along with his/her score */	
	private void displayWinner() {
		int max = 0;
		int winner = 0;
		for (int player = 1; player <= nPlayers; player++) {
			if (scoreArray[player - 1][TOTAL] > max) {
				max = scoreArray[player - 1][TOTAL];
				winner = player - 1;
			}
		}
		display.printMessage("Congratulations, " + playerNames[winner] + 
				", you're the winner with a total score of " + max + "!");
	}
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private int[] dice;
	private int[][] scoreArray;
	private boolean[][] categoryTracker;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
