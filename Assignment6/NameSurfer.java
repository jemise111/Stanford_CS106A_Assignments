/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		nameField = new TextField(30);
		add(new JLabel("Name "), SOUTH);
		add(nameField, SOUTH);
		add(new JButton("Graph"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		graph = new NameSurferGraph();
		add(graph);
		dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
		addActionListeners();
		nameField.addActionListener(this);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nameField || e.getActionCommand().equals("Graph")) {
			if (dataBase.findEntry(nameField.getText()) != null) {
				graph.addEntry(dataBase.findEntry(nameField.getText()));
				graph.update();
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
	}
	
	private TextField nameField;
	private NameSurferDataBase dataBase;
	private NameSurferGraph graph;
}
