/*
 * File: FlipHorizontal
 * --------------------
 * This program is a solution to section assignment 6 problem
 * Flip Horizontal.
 */

import acm.program.*;
import acm.graphics.*;

public class FlipHorizontal extends GraphicsProgram{
	
/**	X and Y positions for final image to be displayed in pixels */	
	private static final int X_POS = 100;
	private static final int Y_POS = 100;
	
	public void run() {
		GImage dalya = new GImage("dalya.gif");
		add(flipHorizontal(dalya), X_POS, Y_POS);
	}

/** Flips an image horizontally 
 * 
 * @param Image to be flipped
 * @return Flipped image
 */
	private GImage flipHorizontal(GImage image) {
		int[][] array = image.getPixelArray();
		//width in pixels of each row of image
		int width = array[0].length;
		//loops through each row of the image
		for (int i = 0; i < array.length; i++) {
			//in each row flip each pixel horizontally
			for (int p1 = 0; p1 < width / 2; p1++) {
				int p2 = width - p1 - 1;
				int temp = array[i][p1];
				array[i][p1] = array[i][p2];
				array[i][p2] = temp;
			}
		}
		return new GImage(array);
	}
}
