/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		//finds width of base
		int baseWidth = BRICKS_IN_BASE * BRICK_WIDTH;
		//x-coordinate start position
		int startPosX = getWidth()/2 - baseWidth/2;
		//y-coordinate start position
		int startPosY = getHeight() - BRICK_HEIGHT;
		
		/*Initial for loop builds pyramid moving vertically. Each
		 * iteration increases startPosX by half the width of one brick
		 * and startPosY by height of one brick. Nested for loop builds each row.
		 * Each iteration increases startPosX by width of one brick.
		 */
		for(int i = BRICKS_IN_BASE; i > 0; i--) {	
			for (int j = i; j > 0; j--){
				GRect brick = new GRect(startPosX, startPosY, BRICK_WIDTH, BRICK_HEIGHT);
				add (brick);
				startPosX += BRICK_WIDTH;
			}
			startPosY -= BRICK_HEIGHT;
			/*At this stage the new row would have a startPosX at the end
			 * of the previous row. We subtract the number of pixels equal to the full
			 * length of the row less one half the width of one brick.
			 */
			startPosX -= (BRICK_WIDTH * i) - ((double)BRICK_WIDTH / 2);
		}
	}
}

