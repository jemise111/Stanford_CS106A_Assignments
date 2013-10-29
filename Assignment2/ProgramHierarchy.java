/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	//Width of each box in pixels
	private static final double BOX_WIDTH = 150;
	
	//Height of each box in pixels
	private static final double BOX_HEIGHT = 50;
	
	//Space between boxes in tree diagram
	private static final double BOX_SPACER = 30;
	
	/*Each box and label is created in its own method. Within each method
	 * the box is created immediately with correct positioning. The label is
	 * positioned in the center of the box and then positioned appropriately using
	 * .move on the label object. The final method creates the connecting lines.
	 */
	
	public void run() {
		program();
		graphicsProgram();
		consoleProgram();
		dialogProgram();
		connectLines();
	}
	
	private void program() {
		GRect programBox = new GRect(getWidth()/2 - (BOX_WIDTH/2), getHeight()/2 - (BOX_HEIGHT*3/2), BOX_WIDTH, BOX_HEIGHT);
		GLabel programLabel = new GLabel("Program", getWidth()/2,getHeight()/2 - BOX_HEIGHT);
		programLabel.move(-programLabel.getWidth()/2, programLabel.getAscent()/2);
		add(programBox);
		add(programLabel);
	}
	
	private void graphicsProgram() {
		GRect graphicsProgramBox = new GRect(getWidth()/2 - 1.5*BOX_WIDTH - BOX_SPACER, getHeight()/2 + BOX_HEIGHT/2, BOX_WIDTH, BOX_HEIGHT);
		GLabel graphicsProgramLabel = new GLabel("GraphicsProgram", getWidth()/2 - BOX_WIDTH - BOX_SPACER, getHeight()/2 + BOX_HEIGHT);
		graphicsProgramLabel.move(-graphicsProgramLabel.getWidth()/2, graphicsProgramLabel.getAscent()/2);
		add(graphicsProgramBox);
		add(graphicsProgramLabel);
	}
	
	private void consoleProgram() {
		GRect consoleProgramBox = new GRect(getWidth()/2 - 0.5*BOX_WIDTH, getHeight()/2 + BOX_HEIGHT/2, BOX_WIDTH, BOX_HEIGHT);
		GLabel consoleProgramLabel = new GLabel("ConsoleProgram", getWidth()/2, getHeight()/2 + BOX_HEIGHT);
		consoleProgramLabel.move(-consoleProgramLabel.getWidth()/2, consoleProgramLabel.getAscent()/2);
		add(consoleProgramBox);
		add(consoleProgramLabel);
	}
	
	private void dialogProgram() {
		GRect dialogProgramBox = new GRect(getWidth()/2 + 0.5*BOX_WIDTH + BOX_SPACER, getHeight()/2 + BOX_HEIGHT/2, BOX_WIDTH, BOX_HEIGHT);
		GLabel dialogProgramLabel = new GLabel("DialogProgram", getWidth()/2 + BOX_WIDTH + BOX_SPACER, getHeight()/2 + BOX_HEIGHT);
		dialogProgramLabel.move(-dialogProgramLabel.getWidth()/2, dialogProgramLabel.getAscent()/2);
		add(dialogProgramBox);
		add(dialogProgramLabel);
	}
	
	private void connectLines() {
		double startX = getWidth()/2;
		double startY = getHeight()/2 - BOX_HEIGHT/2;
		double endY = getHeight()/2 + BOX_HEIGHT/2;
		GLine connectLineGraphics = new GLine(startX, startY, getWidth()/2 - BOX_WIDTH - BOX_SPACER, endY);
		GLine connectLineConsole = new GLine(startX, startY, getWidth()/2, endY);
		GLine connectLineDialog = new GLine(startX, startY, getWidth()/2 + BOX_WIDTH + BOX_SPACER, endY);
		add(connectLineGraphics);
		add(connectLineConsole);
		add(connectLineDialog);
	}	
}
