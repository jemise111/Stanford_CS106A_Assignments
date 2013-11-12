/* 
 * File: UsingInteractors.java
 * ---------------------------
 * This program is a solution to section assignment 7.
 */

import acm.program.*;
import acm.graphics.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class UsingInteractors extends GraphicsProgram {

/** Width of each box */
	private static final double BOX_WIDTH = 120.0;
	
/** Height of each box */
	private static final double BOX_HEIGHT = 50.0;

/** Initializes the console */	
	public void init() {
		nameField = new JTextField(30);
		add(nameField, SOUTH);
		nameField.addActionListener(this);
		add (new JLabel("Name "), SOUTH);
		JButton addButton = new JButton("Add");
		add(addButton, SOUTH);
		JButton removeButton = new JButton("Remove");
		add(removeButton, SOUTH);
		JButton clearButton = new JButton("Clear");
		add(clearButton, SOUTH);
		addActionListeners();
		addMouseListeners();
	}

/** Recognizes action taken and conducts appropriate method */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Add") || e.getSource() == nameField) {
			//if "Add is pressed or enter is pressed creates a new box with text
			addNameBox(nameField.getText());
		} 
		if (e.getActionCommand().equals("Remove")) {
			//if "Remove" is pressed, removes box containing text in the TextField
			GCompound lastCompound = boxMap.get(nameField.getText());
			if (lastCompound != null) {
				remove(lastCompound);
				boxMap.remove(nameField.getText());
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			//if "Clear" is pressed, removes all boxes
			clearAllBoxes();
		}
	}
	
/** Creates a new GCompound with a GRect and GLabel centered inside it. GCompound centered in console
 *  GLabel contents are what is entered in the TextField
 * @param text is String of whatever is in TextField
 */
	private void addNameBox(String text) {
		GCompound nameBox = new GCompound();
		GRect box = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel name = new GLabel(text);
		nameBox.add(box, getWidth()/2 - BOX_WIDTH/2, getHeight()/2 - BOX_HEIGHT/2);
		nameBox.add(name, getWidth()/2 - name.getWidth()/2, getHeight()/2 + name.getAscent()/2);
		add(nameBox);
		boxMap.put(text, nameBox);
	}

/** When mouse is pressed method records GPoint and element where mouse pointer currently is */
	public void mousePressed(MouseEvent e) {
		lastPt = new GPoint(e.getPoint());
		lastObj = getElementAt(lastPt);
	}

/** When mouse is dragged method records GPoint of mouse location and moves the object to where
 * mouse is moving.	
 */
	public void mouseDragged(MouseEvent e) {
		if (lastObj != null) {
			lastObj.move(e.getX() - lastPt.getX(), e.getY() - lastPt.getY());
			lastPt = new GPoint(e.getPoint());
		}
	}
	
/** Removes all boxes in the console and clears the HashMap keeping track of boxes */	
	private void clearAllBoxes() {
		Iterator<String> it = boxMap.keySet().iterator();
		while(it.hasNext()) {
			String name = it.next();
			remove(boxMap.get(name));
		}
		boxMap.clear();
	}
	
	private HashMap<String, GCompound> boxMap = new HashMap<String, GCompound>();
	private JTextField nameField;
	private GPoint lastPt;
	private GObject lastObj;
}
