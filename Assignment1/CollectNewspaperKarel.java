/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {
	public void run() {
		gotoNewspaper();
		pickBeeper();
		goHome();
	}
	
	private void gotoNewspaper(){
		goTillStop();
		turnRight();
		move();
		turnLeft();
		move();
	}
	
	private void goHome(){
		turnAround();
		goTillStop();
		turnRight();
		move();
		turnRight();
	}
	
	private void goTillStop() {
		while(frontIsClear()) {
			move();
		}
	}
}
