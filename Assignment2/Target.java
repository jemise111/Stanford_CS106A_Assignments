/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	//Constant for number of pixels in one inch
	private static final double PIXEL_INCH = 72; 
	
	public void run() {
		outerCircle();
		middleCircle();
		innerCircle();
	}
	
	private void outerCircle() {
		double radius = PIXEL_INCH;
		double startPosX = getWidth()/2 - radius;
		double startPosY = getHeight()/2 - radius;
		GOval outerCircle = new GOval(startPosX, startPosY, radius*2, radius*2);
		outerCircle.setColor(Color.red);
		outerCircle.setFilled(true);
		outerCircle.setFillColor(Color.red);
		add(outerCircle);
	}
	
	private void middleCircle(){
		double radius = PIXEL_INCH * 0.65;
		double startPosX = getWidth()/2 - radius;
		double startPosY = getHeight()/2 - radius;
		GOval middleCircle = new GOval(startPosX, startPosY, radius*2, radius*2);
		middleCircle.setColor(Color.white);
		middleCircle.setFilled(true);
		middleCircle.setFillColor(Color.white);
		add(middleCircle);
	}
	
	private void innerCircle(){
		double radius = PIXEL_INCH * 0.3;
		double startPosX = getWidth()/2 - radius;
		double startPosY = getHeight()/2 - radius;
		GOval innerCircle = new GOval(startPosX, startPosY, radius*2, radius*2);
		innerCircle.setColor(Color.red);
		innerCircle.setFilled(true);
		innerCircle.setFillColor(Color.red);
		add(innerCircle);
	}
}

