/*
 * File: Breakout.java
 * --------------------
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Width of brick row including separations */
	private static final int BRICK_ROW_WIDTH = 
	  (NBRICKS_PER_ROW * BRICK_WIDTH) + ((NBRICKS_PER_ROW-1)*BRICK_SEP);
	
/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Initial amount of time in between ball movements */
	private static final int DELAY = 20;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		turnsLeft = NTURNS;
		setUp();
		addMouseListeners();
		waitForClick();
		while (turnsLeft > 0 && bricksRemaining > 0) {
			gamePlay();
		}
		GLabel endGreeting = new GLabel("Game Over");
		add(endGreeting, getWidth()/2 - endGreeting.getWidth()/2, getHeight()/2 + 2*endGreeting.getAscent() - endGreeting.getAscent()/2);
	}	
	
/** Sets up game before game play. */
	private void setUp() {
		//Creates brick layout.
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				addBrick(i, j);
			}
		}
		//Creates paddle and two GLabels to keep track of number of turns remaining and points scored
		setPaddle();
		setTurnCounter();
		setScoreCounter();
		add(introLabel = new GLabel("Welcome to Breakout. Click to Begin!"), getWidth()/2 - introLabel.getWidth()/2, getHeight()/2 - introLabel.getAscent()/2);
	}
	
/** Creates single brick given row number and column number. */ 
	private void addBrick(int rowNumber, int columnNumber) {
		setColor(rowNumber);
		double xpos = (getWidth() - BRICK_ROW_WIDTH)/2 + (BRICK_WIDTH + BRICK_SEP)*columnNumber;
		double ypos = BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP)*rowNumber;
		GRect brick = new GRect (xpos, ypos, BRICK_WIDTH, BRICK_HEIGHT);
		brick.setFilled(true);
		brick.setFillColor(color);
		brick.setColor(color);
		add(brick);
		}
	
/** Sets color given specific rowNumber. */
	private void setColor(int rowNumber) {
		if (rowNumber == 0 || rowNumber == 1) {
			color = Color.RED;
		} else if (rowNumber == 2 || rowNumber == 3) {
			color = Color.ORANGE;
		} else if (rowNumber == 4 || rowNumber == 5) {
			color = Color.YELLOW;
		} else if (rowNumber == 6 || rowNumber == 7) {
			color = Color.GREEN;
		} else if (rowNumber == 8 || rowNumber == 9) {
			color = Color.CYAN;
		}
	}
	
	private void setPaddle() {
		paddle = new GRect(getWidth()/2 - PADDLE_WIDTH/2, getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	private void setTurnCounter() {
		//GLabel to keep track of turns remaining. Lower left area of console window.
		turnCounter = new GLabel("Turns Left: " + turnsLeft);
		add(turnCounter, 0, getHeight() - turnCounter.getAscent()/2);
	}
	
	private void setScoreCounter() {
		//GLabel to keep track of score. Lower right area of console window.
		scoreCounter = new GLabel ("Score: " + score);
		add(scoreCounter, getWidth() - scoreCounter.getWidth() - 10, getHeight() - scoreCounter.getAscent()/2);
	}
	
/**	Tracks mouse so paddle's x-coordinate updates as mouse moves.
* 	If mouse exits console window paddle x-coordinate set to edge of console.
*/
	public void mouseMoved(MouseEvent e) {
		mouseLocation = new GPoint(e.getPoint());
		//Conditionals to prevent paddle from moving off screen when mouse nears edge or leaves console
		if (mouseLocation.getX() < PADDLE_WIDTH/2) {
			paddle.setLocation(0, getHeight() - PADDLE_Y_OFFSET);
		} else if (mouseLocation.getX() > getWidth() - PADDLE_WIDTH/2) {
			paddle.setLocation(getWidth() - PADDLE_WIDTH, getHeight() - PADDLE_Y_OFFSET);
		} else {
			paddle.setLocation(mouseLocation.getX() - PADDLE_WIDTH/2, getHeight() - PADDLE_Y_OFFSET);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		//Removes the welcome greeting when user clicks
		introLabel.setVisible(false);
	}
	
/** Method to run game. */
	private void gamePlay() {
		//Creates ball and initializes ball movement with random x-velocity 
		initializeBall();
		waitForClick();
		initializeVelocity();
		while(true) {
			//At each time step ball moves one position, checks for collisions, and pauses
			moveBall();
			checkForCollision();
			pause(DELAY);
			//When player has no bricks remaining they have won the game and the loop breaks
			if (bricksRemaining == 0) {
				GLabel winGreeting = new GLabel("Congratulations, you won!");
				add(winGreeting, getWidth()/2 - winGreeting.getWidth()/2, getHeight()/2 - winGreeting.getAscent()/2);
				break;
			}
			//When ball hits bottom edge of console window player loses a turn and game restarts
			if (ball.getY() > getHeight() - 2*BALL_RADIUS) {
				turnsLeft --;
				turnCounter.setLabel("Turns Left: " + turnsLeft);
				paddleCounter = 0;
				break;
			}
		}
		remove(ball);
	}
	
	private void initializeBall() {
		ball = new GOval(getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS, BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball);
	}
	
	private void initializeVelocity() {
		// Initial x-velocity is random and will move left or right with 50% probability
		vx = rgen.nextDouble(1.0,3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = 4.0;
	}
	
	private void moveBall() {
		//Adds in the twist that after 10 paddle hits x velocity will double making the game more challenging
		if (paddleCounter > 10) {
			ball.move(vx*2, vy);
		} else {
			ball.move(vx, vy);
		}
	}
	
/**	At each time step checks if ball has collided with top, left, and right edges of console window,
* 	a brick, or the paddle, and if so reverses the ball's direction. If ball collides with a brick
* 	that brick is removed and player receives 1 point.
*/
	private void checkForCollision() {
		//Checks top and side walls for collisions.
		if (ball.getX() < 0 || ball.getX() > getWidth() - 2*BALL_RADIUS) {
			vx = -vx;
		}
		else if (ball.getY() < 0) {
			vy = -vy;
		} 
		//Checks brick and paddle collisions and forces ball to ignore GLabel collisions.
		GObject collider = getCollidingObject();
		if (collider == paddle) {
			paddleCollision();
		} else if (collider == turnCounter || collider == scoreCounter || collider == introLabel) {
		} else if (collider != null) {
			vy = -vy;
			remove(collider);
			bricksRemaining --;
			score ++;
			scoreCounter.setLabel("Score: " + score);
		}
	}

/** Changes x velocity according to direction ball is moving and place on paddle the ball hits. 
 * 	If ball is moving right and hits left third of paddle ball will switch x direction. Similar case
 * 	for ball moving left and hitting right third of paddle. Middle third of paddle left unchanged.
 */
	
	private void paddleCollision() {
		vy = -vy;
		paddleCounter ++;
		if (vx > 0) {
			//Case when ball moving right and hits left section of paddle.
			if (lastCollideX < paddle.getX() + PADDLE_WIDTH/3) {
				vx = -vx;
			}
		}
		if (vx < 0) {
			//Case when ball moving left and hits right section of paddle.
			if (lastCollideX > paddle.getX() + PADDLE_WIDTH/3)  {
				vx = -vx;
			}
		}
	}
	
/** At each time step returns the object present at the ball's current location
* 	checking each corner of the ball's containing square starting from top left
* 	and moving clockwise. If no object is present method returns null. Also sets 
* 	lastCollideX to the x value of the collision point.
*/
	private GObject getCollidingObject() {
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			lastCollideX = ball.getX();
			return getElementAt(ball.getX(), ball.getY());
		}
		else if (getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY()) != null) {
			lastCollideX = ball.getX() + 2*BALL_RADIUS;
			return getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY());
		}			
		else if (getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS) != null) {
			lastCollideX = ball.getX();
			return getElementAt(ball.getX(), ball.getY() + 2*BALL_RADIUS);
		}			
		else if (getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS) != null) {
			lastCollideX = ball.getX() + 2*BALL_RADIUS;
			return getElementAt(ball.getX() + 2*BALL_RADIUS, ball.getY() + 2*BALL_RADIUS);
		}
		else {
			return null;
		}
	}
	
/* Private instance variables */
	private Color color;
	private GLabel introLabel;
	private GRect paddle;
	private GPoint mouseLocation;
	private GOval ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx, vy;
	private int bricksRemaining = NBRICK_ROWS * NBRICKS_PER_ROW;
	private GLabel turnCounter;
	private int turnsLeft;
	private GLabel scoreCounter;
	private int score;
	private double lastCollideX;
	private int paddleCounter = 0;
}
