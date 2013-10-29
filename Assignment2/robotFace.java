/*
 * File: RobotFace.java
 * -----------------
 * This file is a solution to the robot face 
 * problem in section assignment 2.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class robotFace extends GraphicsProgram {
	
	private static final double HEAD_WIDTH = 200;

	private static final double HEAD_HEIGHT = 300;

	private static final double EYE_RADIUS = 15;

	private static final double MOUTH_WIDTH = 150;
	
	private static final double MOUTH_HEIGHT = 50;
	
	public void run() {
		drawHead();
		drawLefteye();
		drawRighteye();
		drawMouth();
	}
	
	private void drawHead() {
		GRect head = new GRect(getWidth()/2 - HEAD_WIDTH/2, getHeight()/2 - HEAD_HEIGHT/2, HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.gray);
		add(head);
	}
	
	private void drawLefteye() {
		GOval lefteye = new GOval(getWidth()/2 - HEAD_WIDTH/4 - EYE_RADIUS, getHeight()/2 - HEAD_HEIGHT/4 - EYE_RADIUS, EYE_RADIUS*2, EYE_RADIUS*2);
		lefteye.setColor(Color.yellow);
		lefteye.setFilled(true);
		lefteye.setFillColor(Color.yellow);
		add(lefteye);
	}
	
	private void drawRighteye() {
		GOval righteye = new GOval(getWidth()/2 + HEAD_WIDTH/4 - EYE_RADIUS, getHeight()/2 - HEAD_HEIGHT/4 - EYE_RADIUS, EYE_RADIUS*2, EYE_RADIUS*2);
		righteye.setColor(Color.yellow);
		righteye.setFilled(true);
		righteye.setFillColor(Color.yellow);
		add(righteye);
	}
	
	private void drawMouth() {
		GRect mouth = new GRect(getWidth()/2 - MOUTH_WIDTH/2, getHeight()/2 + HEAD_HEIGHT/4 - MOUTH_HEIGHT/2, MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setColor(Color.white);
		mouth.setFilled(true);
		mouth.setFillColor(Color.white);
		add(mouth);
	}
}