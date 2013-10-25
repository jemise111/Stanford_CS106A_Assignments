/*
 * File: ballotCleaningKarel.java
 * ------------------------------
 * The ballotCleaningKarel subclass cleans out the chad from
 * a ballot as described in the section handout.
 */

package section;

import stanford.karel.SuperKarel;

public class ballotCleaningKarel extends SuperKarel {
	
	public void run() {
		while(frontIsClear()) {			
			move();
			if (noBeepersPresent()) {
				cleanBallot();
			}
			move();
		}	
	}
	
	/*Karel starts at center of ballot facing east. Karel positions itself
	 * at top of ballot facing south, makes three passes picking up an beepers
	 * in the ballot. Karel ends in middle of ballot facing east.*/
	
	private void cleanBallot() {
		turnLeft();
		move();
		turnAround();
		for (int i = 0; i < 3; i++) {
			cleanSweep();
			turnAround();
			move();
			move();
			turnAround();
		}
		move();
		turnLeft();
	}
	
	/*Karel beings at top of ballot facing south. Karel moves down the ballot 
	 * once removing beepers if they are present. Karel ends at bottom of ballot
	 * facing south.
	 */
	
	private void cleanSweep() {
		for (int i = 0; i < 3; i++) {
			if (beepersPresent()) {
				pickBeeper();
			}			
			if (frontIsClear()) {
				move();
			}
		}
	}
		
}

