/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		applicationMessage = new GLabel("");
		applicationMessage.setFont(MESSAGE_FONT);
		profileName = new GLabel("");
		profileName.setColor(Color.blue);
		profileName.setFont(PROFILE_NAME_FONT);
		imageBox = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
		imageLabel = new GLabel("No Image");
		imageLabel.setFont(PROFILE_IMAGE_FONT);
		statusLabel = new GLabel("");
		statusLabel.setFont(PROFILE_STATUS_FONT);
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		applicationMessage.setLabel(msg);
		add(applicationMessage, getWidth()/2 - applicationMessage.getWidth()/2, 
				getHeight() - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		profileName.setLabel(profile.getName());
		add(profileName, LEFT_MARGIN, NORTH_HEIGHT + TOP_MARGIN);
		if (profile.getImage() != null) {
			profileImage = profile.getImage();
			profileImage.setSize(IMAGE_WIDTH + 1, IMAGE_HEIGHT + 1);
			add(profileImage, LEFT_MARGIN, NORTH_HEIGHT + TOP_MARGIN + IMAGE_MARGIN);
		} else {
			add(imageBox, LEFT_MARGIN, NORTH_HEIGHT + TOP_MARGIN + IMAGE_MARGIN);
			add(imageLabel, LEFT_MARGIN + imageBox.getWidth()/2 - imageLabel.getWidth()/2, 
					NORTH_HEIGHT + TOP_MARGIN + IMAGE_MARGIN + imageBox.getHeight()/2);
		}
		if (profile.getStatus() == "") {
			statusLabel.setLabel("No current status");
		} else {
			statusLabel.setLabel(profile.getName() + " is " + profile.getStatus());
		}
		add(statusLabel, LEFT_MARGIN, NORTH_HEIGHT + TOP_MARGIN + IMAGE_MARGIN + 
				imageBox.getHeight() + STATUS_MARGIN + statusLabel.getAscent());
		displayFriendList(profile);
	}
	
	/**  Displays the friend list for the given profile. */
	private void displayFriendList(FacePamphletProfile profile) {
		GLabel friendLabel = new GLabel("Friends: ");
		friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendLabel, getWidth()/2, NORTH_HEIGHT + TOP_MARGIN + IMAGE_MARGIN);
		double yStart = NORTH_HEIGHT + TOP_MARGIN + IMAGE_MARGIN + friendLabel.getAscent();
		Iterator<String> it = profile.getFriends();
		while (it.hasNext()) {
			GLabel friend = new GLabel(it.next());
			friend.setFont(PROFILE_FRIEND_FONT);
			add(friend, getWidth()/2, yStart);
			yStart += friend.getAscent();
		}
	}
	
	private GLabel applicationMessage;
	private GLabel profileName;
	private GRect imageBox;
	private GLabel imageLabel;
	private GImage profileImage;
	private GLabel statusLabel;
}
