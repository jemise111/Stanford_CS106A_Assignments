/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		setupGrid(); 
		addComponentListener(this);
	}
	
	/**
	 * Sets up black grid with lines and decade labels.
	 */
	private void setupGrid () {
		//adds top and bottom horizontal lines that serve as margins
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), 
				getHeight() - GRAPH_MARGIN_SIZE));
		int startX = 1;
		int spacer = getWidth() / NDECADES;
		int decade = START_DECADE;
		//adds vertical lines with spacer width between them. Adds decade labels after each vertical line
		for (int i = 0; i < NDECADES; i++) {
			add (new GLine(startX, 0, startX, getHeight()));
			GLabel decadeLabel = new GLabel(Integer.toString(decade));
			add (decadeLabel, startX + 1, getHeight() - 3);
			startX += spacer;
			decade += 10;
		}
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		tracker.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		tracker.add(entry);
	}
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		setupGrid();
		for (int i = 0; i < tracker.size(); i++) {
			GCompound entryCompound = new GCompound(); //each entry stored as GCompound
			int startX = 5; //spacer from vertical line
			GPoint[] labelPointArray = new GPoint[NDECADES];
			for (int decade = 0; decade < NDECADES; decade++) {
				//uses determineLabel to figure out what each decade's label should look like
				String nameRank = determineLabel(tracker.get(i), decade);
				//uses labelPoint to figure out what label's position for that decade
				GPoint labelPoint = getLabelPoint(tracker.get(i), decade, startX);
				//labelPoints are stored in an array so we can call on two labelPoints to draw a line connecting them
				labelPointArray[decade] = labelPoint;
				entryCompound.add(new GLabel(nameRank), labelPoint);
				if (decade > 0) { //add a line connecting labelPoint with previous labelPoint
					entryCompound.add(new GLine(labelPointArray[decade].getX(), labelPointArray[decade].getY(), 
							labelPointArray[decade - 1].getX(), labelPointArray[decade - 1].getY()));
				}
			}
			entryCompound.setColor(selectColor(i));
			add(entryCompound);
		}
	}
	
	/**
	 * Determines what the label should look like for a given NameSurferEntry and decade.
	 * If NameSurferEntry has no rank label is the name plus an asterisk (name *), else it is
	 * the name plus the rank for that decade (name 100). 
	 * @param NameSurferEntry entry
	 * @param decade to be checked
	 * @return string that is the appropriate label
	 */
	private String determineLabel(NameSurferEntry entry, int decade) {
		String nameRank = "";
		if (entry.getRank(decade) == 0) {
			nameRank = entry.getName() + " *";
		} else {
			nameRank = entry.getName() + " " + entry.getRank(decade);
		}
		return nameRank;
	}
	/**
	 * Determines the height (in pixels) for the label given a NameSurferEntry and decade.
	 * Label is placed at bottom of graph window if rank is 0. Otherwise height is
	 * proportional to the rank for that decade.
	 * @param NameSurferEntry entry
	 * @param decade to be checked
	 * @return height as a double 
	 */
	private GPoint getLabelPoint(NameSurferEntry entry, int decade, int startX) {
		double height = 0;
		int graphHeight = getHeight() - 2*GRAPH_MARGIN_SIZE;
		if (entry.getRank(decade) == 0) {
			height = GRAPH_MARGIN_SIZE + graphHeight;
		} else {
			height = GRAPH_MARGIN_SIZE + (entry.getRank(decade) * graphHeight) / MAX_RANK;
		}
		int spacer = getWidth() / NDECADES;
		int xPos = startX + decade * spacer;
		GPoint labelPoint = new GPoint(xPos, height);
		return labelPoint;
	}
	
	/**
	 * Selects appropriate color based on ordering of GCompounds in the tracker array.
	 * @param index is what position the new GCompound will occupy
	 * @return color
	 */
	private Color selectColor(int index) {
		if (index % 4 == 0) {return Color.black;}
		else if (index % 4 == 1) {return Color.blue;}
		else if (index % 4 == 2) {return Color.red;}
		else {return Color.green;}
	}
	
	
	
	/* Private Instance Variables */
	private ArrayList<NameSurferEntry> tracker = new ArrayList<NameSurferEntry>();
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
