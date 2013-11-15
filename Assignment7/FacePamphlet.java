/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		//initialize the database
		dataBase = new FacePamphletDatabase();
		
		//top row of interactors
		add (new JLabel("Name "), NORTH);
		nameField = new TextField(TEXT_FIELD_SIZE);
		add (nameField, NORTH);
		nameField.addActionListener(this);
		add (new JButton("Add"), NORTH);
		add (new JButton("Delete"), NORTH);
		add (new JButton("Lookup"), NORTH);
		
		//left row of interactors
		statusField = new TextField(TEXT_FIELD_SIZE);
		add(statusField, WEST);
		statusField.addActionListener(this);
		add (new JButton("Change Status"), WEST);
		add (new JLabel(EMPTY_LABEL_TEXT), WEST);
		pictureField = new TextField(TEXT_FIELD_SIZE);
		add(pictureField, WEST);
		pictureField.addActionListener(this);
		add (new JButton("Change Picture"), WEST);
		add (new JLabel(EMPTY_LABEL_TEXT), WEST);
		friendField = new TextField(TEXT_FIELD_SIZE);
		add(friendField, WEST);
		friendField.addActionListener(this);
		add (new JButton("Add Friend"), WEST);
		add (new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		//add the canvas to display profiles
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		//add event listeners
		addActionListeners();
    }
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	String name = nameField.getText();
    	
    	/* Method when "Add" button is clicked */
    	if (e.getActionCommand().equals("Add")) {
    		canvas.removeAll();
    		addProfile(name);
    		currentProfile = dataBase.getProfile(name); //update current profile and display it
    		canvas.displayProfile(currentProfile);
    	}
    	
    	/* Method when "Delete" button is clicked */
    	if (e.getActionCommand().equals("Delete")) {
    		canvas.removeAll();
    		deleteProfile(name);
    		currentProfile = null; //no current profile is displayed
    	}
    	
    	/* Method when "Lookup" button is clicked */
    	if (e.getActionCommand().equals("Lookup")) {
    		canvas.removeAll();
    		lookupProfile(name);
    	}
    	
    	/* Method when "Change Status" is clicked or status is entered in statusField */
    	if (e.getSource() == statusField || e.getActionCommand().equals("Change Status")) {
    		canvas.removeAll();
    		changeStatus(statusField.getText());
    		canvas.displayProfile(currentProfile);
    	}
    	
    	/* Method when "Change Picture" is clicked or picture file name is entered in pictureField */
    	if (e.getSource() == pictureField || e.getActionCommand().equals("Change Picture")) {
    		canvas.removeAll();
    		changePicture(pictureField.getText());
    		canvas.displayProfile(currentProfile);
    	}
    	
    	/* Method when "Add Friend" is clicked or friend name is entered in friendField */
    	if (e.getSource() == friendField || e.getActionCommand().equals("Add Friend")) {
    		canvas.removeAll();
    		addFriend(friendField.getText());
    		canvas.displayProfile(currentProfile);
    	}
	}
    
    /* Adds profile if it does not already exists */
    private void addProfile(String name) {
    	FacePamphletProfile profile = new FacePamphletProfile(name); 
		if (dataBase.containsProfile(name)) {
			canvas.showMessage("A profile with the name " + name + " already exists");
		} else {
			dataBase.addProfile(profile);
			canvas.showMessage("New profile created");
		}
    }
    
    /* Deletes profile if it exists */
    private void deleteProfile(String name) {
    	if (dataBase.containsProfile(name)) {
			dataBase.deleteProfile(name);
			canvas.showMessage("Profile of " + name + " deleted");
		} else {
			canvas.showMessage("A profile with the name " + name + " does not exist");
		}
    }
    
    /* Looks up and displays profile if it exists. Otherwise nothing is displayed */
    private void lookupProfile(String name) {
    	if (dataBase.containsProfile(name)) {
			currentProfile = dataBase.getProfile(name);
			canvas.showMessage("Displaying " + name);
			canvas.displayProfile(currentProfile);
		} else {
			currentProfile = null;
			canvas.showMessage("A profile with the name " + name + " does not exist");
		}
    }
    
    /* Updates status if a current profile is displayed */
    private void changeStatus(String status) {
    	if (currentProfile != null) {
			currentProfile.setStatus(status);
			canvas.showMessage("Status updated to " + status);
		} else {
			canvas.showMessage("Please select a profile to change status");
		}
    }
    
    /* Updates picture if current profile is displayed and picture file name is valid */
    private void changePicture(String fileName) {
    	if (currentProfile != null) {
			String pictureFile = "images/" + fileName; //images located in the images directory
			GImage image = getProfPic(pictureFile); //use getProfPic to ensure image exists, else returns null
			if (image != null) {
				currentProfile.setImage(image); 
				canvas.showMessage("Picture updated");
			} else {
				canvas.showMessage("That image cannot be found");
			}
		} else {
			canvas.showMessage("Please select a profile to change picture");
		}
    }
    
    /* Check if a given image filename exists in the current directory. If so returns that image.
     * Else returns null. 
     */
    private GImage getProfPic(String pictureFile) {
    	GImage image = null;
    	if (new File(pictureFile).isFile()) {
    		image = new GImage(pictureFile);
    	} 
    	return image;
    }
    
    /* If friendName's profile exists, adds friendName as a friend to the current profile's friend list. And
     * adds the current profile's name to friendName's friend list. If they are already friends nothing is
     * updated.
     */
    private void addFriend(String friendName) {
    	if (currentProfile != null) {
			if (dataBase.containsProfile(friendName)) {
				if (currentProfile.addFriend(friendName)) {
					dataBase.getProfile(friendName).addFriend(currentProfile.getName());
					canvas.showMessage(friendName + " added as a friend");
				} else {
					canvas.showMessage(currentProfile.getName() + " already has " + friendName + " as a friend");
				}	
			} else {
				canvas.showMessage(friendName + " does not exist");
			}
		} else {
			canvas.showMessage("Please select a profile to add friend");
		}
    }
    
    /* Private instance variables */
    
    private TextField nameField;
    private TextField statusField;
    private TextField pictureField;
    private TextField friendField;
    private FacePamphletDatabase dataBase;
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas canvas;

}
