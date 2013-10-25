/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	/*first two conditionals test the special cases for 1 or 2 column worlds.
	 */
	
	public void run() {
		if (frontIsBlocked()) {
			putBeeper();
		} else {
			move();
			if (frontIsBlocked()) {
				turnAround();
				move();
				putBeeper();
			} else {
				turnAround();
				move();
				turnAround();
				countCornersInRowUsingBeeperPile();
				halveBeeperPile();
				distributeBeeperPile();
				cleanUp();
			}
		}
	}
	
	/*Karel starts at (1,1). Places a beeper at every corner of current row.
	 *Then removes all beepers and leaves a beeper pile at (1,1) where number of
	 *beepers in pile = number of corners in row. Karel ends at (1,1) facing east. 
	 */
	
	private void countCornersInRowUsingBeeperPile() {
		putBeeper();
		while (frontIsClear()) {
			move();
			if (noBeepersPresent()){
			putBeeper();
			returnToFirst();
			putBeeper();
			}
		}
		turnAround();
		while (frontIsClear()) {
			pickBeeper();
			move();
		}
		turnAround();
	}
	
	/*Karel starts facing east at any corner of a row. Karel will return
	 *to the first column of that row. Karel ends facing east. 
	 */
	private void returnToFirst() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnAround();
	}
	
	/*Karel starts on a beeper pile facing east. Karel divides the beeper pile
	 *by 2. The result is an integer number of beepers. Karel ends on the beeper 
	 *pile facing east.
	 */
	private void halveBeeperPile() {
		createHalfPile();
		movePile();
	}
	/*Karel starts on a beeper pile facing east. Karel moves half of the beepers
	 * to a new pile one corner to the east. The result is an integer number of beepers.
	 * Karel ends on the new pile facing west.
	 */
	private void createHalfPile() {
		while (beepersPresent()) {
			pickBeeper();
			move();
			putBeeper();
			turnAround();
			move();
			turnAround();
			if (beepersPresent()) {
				pickBeeper();
			}
		}
		move();
		turnAround();
	}
	/*Karel starts on a beeper pile facing west. Karel moves the entire pile
	 *one corner to the west. Karel ends on the pile facing east. 
	 */
	private void movePile() {
		while(beepersPresent()) {
			pickBeeper();
			move();
			putBeeper();
			turnAround();
			move();
			turnAround();
		}
		move();
		turnAround();
	}
	
	/*Karel starts on a beeper pile facing east. Karel distributes the beeper
	 * pile accross a row placing one beeper per corner less the original corner
	 * the pile was on. Karel ends on the east-most beeper facing west. This is the
	 * midpoint beeper.
	 */
	private void distributeBeeperPile() {
		pickBeeper();
		while (beepersPresent()) {
			pickBeeper();
			move();
			while (beepersPresent()) {
				move();
			}
			putBeeper();
			returnToFirst();
		}
		move();
		while(beepersPresent()) {
			move();
		}
		turnAround();
		move();
		move();
	}
	
	/*Karel starts on the beeper one corner west of the midpoint beeper. Karel
	 * picks up all remaining beepers west of the midpoint beeper. Karel ends
	 * at (1,1) facing west.
	 */
	private void cleanUp() {
		while(frontIsClear()) {
			while(beepersPresent()){
				pickBeeper();
				move();
			}
		}
	}
}
	
