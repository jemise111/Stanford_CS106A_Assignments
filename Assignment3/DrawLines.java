/*
 * File: DrawLines.java
 * -------------------
 * This file is a solution to the Drawing Lines question from 
 * section assignment 3.
 * 
 * Program will draw a line from when mouseClick is pressed to when mouseClick
 * is released with a ruberbanding effect.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class DrawLines extends GraphicsProgram {

/** Sets up program to record mouse events */
	public void run() {
			addMouseListeners();
	}

/** Records starting points for line at mousePressed event */	
	public void mousePressed(MouseEvent e) {
		double startX = e.getX();
		double startY = e.getY();
		line = new GLine(startX, startY, startX, startY); 
		add(line);
	}

/** Updates ending points for line as mouseDragged */
	public void mouseDragged(MouseEvent e) {
		double lastX = e.getX();
		double lastY = e.getY();
		line.setEndPoint(lastX, lastY);
	}
	
/* Instance variables */
	private GLine line;
}
