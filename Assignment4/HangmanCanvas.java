/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		lettertracker.setLabel("");
		add(new GLine(getWidth()/2 - BEAM_LENGTH, TOP_Y_OFFSET, getWidth()/2 - BEAM_LENGTH, 
				TOP_Y_OFFSET + SCAFFOLD_HEIGHT));
		add(new GLine(getWidth()/2 - BEAM_LENGTH, TOP_Y_OFFSET, getWidth()/2, TOP_Y_OFFSET));
		add(new GLine(getWidth()/2, TOP_Y_OFFSET, getWidth()/2, TOP_Y_OFFSET + ROPE_LENGTH));
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		//removes existing guessword and replaces with updated guessword
		if(getElementAt(getWidth()/2 - BEAM_LENGTH, TOP_Y_OFFSET + SCAFFOLD_HEIGHT + WORD_OFFSET_FROM_SCAFFOLD) != null) {
			remove(getElementAt(getWidth()/2 - BEAM_LENGTH, TOP_Y_OFFSET + SCAFFOLD_HEIGHT + WORD_OFFSET_FROM_SCAFFOLD));
		}
		GLabel guessword = new GLabel(word, getWidth()/2 - BEAM_LENGTH, TOP_Y_OFFSET + SCAFFOLD_HEIGHT 
				+ WORD_OFFSET_FROM_SCAFFOLD);
		guessword.setFont("Times-40");
		add(guessword);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int guess) {
		lettertracker.setLabel(lettertracker.getLabel() + Character.toString(letter));
		add(lettertracker, getWidth()/2 - BEAM_LENGTH ,TOP_Y_OFFSET + SCAFFOLD_HEIGHT + 
				LETTERS_OFFSET_FROM_SCAFFOLD);
		switch (guess) {
			case 1: addHead(); break;
			case 2: addBody(); break;
			case 3: addLeftArm(); break;
			case 4: addRightArm(); break;
			case 5: addLeftLeg(); break;
			case 6: addRightLeg(); break;
			case 7: addLeftFoot(); break;
			case 8: addRightFoot(); break;
		}
	}
	
	private void addHead() {
		GOval head = new GOval(getWidth()/2 - HEAD_RADIUS, TOP_Y_OFFSET + ROPE_LENGTH, HEAD_RADIUS*2, HEAD_RADIUS*2);
		add(head);
	}
	
	private void addBody() {
		GLine body = new GLine(getWidth()/2, TOP_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2, getWidth()/2,
				+ TOP_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2 + BODY_LENGTH);
		add(body);
	}
	
	private void addLeftArm() {
		GLine leftArmUpper = new GLine(getWidth()/2 - UPPER_ARM_LENGTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD, getWidth()/2, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD);
		GLine leftArmLower = new GLine(getWidth()/2 - UPPER_ARM_LENGTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 - UPPER_ARM_LENGTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(leftArmUpper);
		add(leftArmLower);
	}
	
	private void addRightArm() {
		GLine rightArmUpper = new GLine(getWidth()/2 + UPPER_ARM_LENGTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD, getWidth()/2, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD);
		GLine rightArmLower = new GLine(getWidth()/2 + UPPER_ARM_LENGTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD, getWidth()/2 + UPPER_ARM_LENGTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(rightArmUpper);
		add(rightArmLower);
	}
	
	private void addLeftLeg() {
		GLine leftLegUpper = new GLine(getWidth()/2 - HIP_WIDTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH, getWidth()/2, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH);
		GLine leftLegLower = new GLine(getWidth()/2 - HIP_WIDTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH, getWidth()/2 - HIP_WIDTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH);
		add(leftLegUpper);
		add(leftLegLower);
		}
	
	private void addRightLeg() {
		GLine rightLegUpper = new GLine(getWidth()/2 + HIP_WIDTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH, getWidth()/2, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH);
		GLine rightLegLower = new GLine(getWidth()/2 + HIP_WIDTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH, getWidth()/2 + HIP_WIDTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH);
		add(rightLegUpper);
		add(rightLegLower);
	}
	
	private void addLeftFoot() {
		GLine leftFoot = new GLine(getWidth()/2 - HIP_WIDTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH, getWidth()/2 - HIP_WIDTH - FOOT_LENGTH, 
				TOP_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH);
		add(leftFoot);
	}
	
	private void addRightFoot() {
		GLine rightFoot = new GLine(getWidth()/2 + HIP_WIDTH, TOP_Y_OFFSET + ROPE_LENGTH + 
				HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH, getWidth()/2 + HIP_WIDTH + FOOT_LENGTH, 
				TOP_Y_OFFSET + ROPE_LENGTH + HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH);
		add(rightFoot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 250;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 18;
	private static final int BODY_LENGTH = 72;
	private static final int ARM_OFFSET_FROM_HEAD = 14;
	private static final int UPPER_ARM_LENGTH = 36;
	private static final int LOWER_ARM_LENGTH = 22;
	private static final int HIP_WIDTH = 18;
	private static final int LEG_LENGTH = 54;
	private static final int FOOT_LENGTH = 14;
	private static final int TOP_Y_OFFSET = 50;
	private static final int WORD_OFFSET_FROM_SCAFFOLD = 50;
	private static final int LETTERS_OFFSET_FROM_SCAFFOLD = 80;
	
/* Private instance variables */
	private GLabel lettertracker = new GLabel("");
}
