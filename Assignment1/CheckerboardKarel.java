/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	//assuming Karel always starts at 1st Ave, 1st Street
	
	public void run(){
		fillRowFirstType();
		while (leftIsClear()) {
			moveUp();
			fillRowSecondType();
			if (leftIsClear()) {
				moveUp();
				fillRowFirstType();
			}
		}
	}
	
	/*Karel starts facing east starting as west-most corner of row.
	 * Karel will fill row with alternating beepers.
	 * FirstType is a row in which Karel will place a beeper in the west-most row.
	 * Karel ends facing east at west-most corner of same row.
	 */
	private void fillRowFirstType() {
		putBeeper();
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
		moveToRowStart();
	}
	
	/*Karel starts facing east starting as west-most corner of row.
	 * Karel will fill row with alternating beepers.
	 * SecondType is a row in which Karel will skip the west-most row.
	 * Karel ends facing east at west-most corner of same row.
	 */
	private void fillRowSecondType() {
		while (frontIsClear()) {
			move();
			putBeeper();
			if (frontIsClear()) {
				move();
			}
		}
		moveToRowStart();
	}
	
	/*Karel starts in any position of a row facing east.
	 * Karel will return to west-most corner of same row.
	 * Karel ends facing east.
	 */
	private void moveToRowStart() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnAround();
	}
	
	/*Karel starts in west-most corner of row.
	 * Karel will move up one corner so long as there is no wall above her.
	 * Karel ends in west-most corner of new row, facing east.
	 */
	private void moveUp() {
		if (leftIsClear()) {
			turnLeft();
			move();
			turnRight();
		}
	}	
}
