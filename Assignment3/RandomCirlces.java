/*
 * File: RandomCircles.java
 * -------------------
 * This file is a solution to the Random Cirlces question from 
 * section assignment 3.
 * 
 * Creates NUM_CIRCLES number of circles with random position
 * and random diameter between 5 and 50 pixels.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class RandomCirlces extends GraphicsProgram {

/** Number of cirlces to generate */
	private static final int NUM_CIRCLES = 10;

	public void run() {
		for (int i = 0; i < 10; i++) {
			int diameter = rgen.nextInt(5, 50);
			int xpos = rgen.nextInt(0, getWidth() - diameter);
			int ypos = rgen.nextInt(0, getHeight() - diameter);
			GOval circle = new GOval(xpos, ypos, diameter, diameter);
			circle.setFilled(true);
			circle.setColor(rgen.nextColor());
			add(circle);
		}
	}

/*Creates random number instance variable*/
	
private RandomGenerator rgen = RandomGenerator.getInstance();

}