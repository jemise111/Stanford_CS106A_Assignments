/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while(frontIsClear()){
			doColumn();
			moveToNext();
		}
		doColumn();	
	}
	
	/*Karel starts at south-most corner of a column facing east. Karel will
	 * move north one corner at a time putting a beeper if none exists.
	 * Karel ends at its original position facing east. 
	 */
	
	private void doColumn() {
		turnLeft();
		while(frontIsClear()){
			fillEmpty();
			move();
		}
		fillEmpty();
		turnAround();
		moveToEnd();
		turnLeft();
	}
	
	//Karel will move 4 corners in the direction it faces.
	
	private void moveToNext() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}
	
	//Karel will put a Beeper in current position if none exists.
	
	private void fillEmpty() {
		if (noBeepersPresent()){
			putBeeper();
		}
	}
	
	//Karel will move in direction it faces until a wall is in front.
	
	private void moveToEnd() {
		while (frontIsClear()){
			move();
		}
	}
}
