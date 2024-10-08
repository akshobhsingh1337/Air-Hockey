/**
 * Models a simple solid sphere.
 * This class represents a Ball object. When combined with the GameArena class,
 * instances of the Ball class can be displayed on the screen.
 */
public class Ball {
	// The following instance variables define the
	// information needed to represent a Ball
	// Feel free to declare more instance variables if you think it will support
	// your work.

	private double xPosition; // The X coordinate of this Ball
	private double yPosition; // The Y coordinate of this Ball
	private double size; // The diameter of this Ball
	private int layer; // The layer of this ball is on.
	private String colour; // The colour of this Ball
	private boolean moveState = true;
	private double x_startSpeed = 0.001;
	private double y_startSpeed = -0.001;

	// Permissible colours are:
	// BLACK, BLUE, CYAN, DARKGREY, GREY,
	// GREEN, LIGHTGREY, MAGENTA, ORANGE,
	// PINK, RED, WHITE, YELLOW, BROWN

	/**
	 * Constructor for the Ball class. Sets a default layer of 0.
	 * 
	 * @param x        The x co-ordinate of centre of the Ball (in pixels)
	 * @param y        The y co-ordinate of centre of the Ball (in pixels)
	 * @param diameter The diameter of the Ball (in pixels)
	 * @param col      The colour of the Ball (Permissible colours are: BLACK, BLUE,
	 *                 CYAN, DARKGREY, GREY, GREEN, LIGHTGREY, MAGENTA, ORANGE,
	 *                 PINK, RED, WHITE, YELLOW or BROWN)
	 */
	public Ball(double x, double y, double diameter, String col) {
		this.xPosition = x;
		this.yPosition = y;
		this.size = diameter;
		this.colour = col;
		this.layer = 0;
	}

	/**
	 * Constructor for the Ball class, with a layer specified.
	 * 
	 * @param x        The x co-ordinate of centre of the Ball (in pixels)
	 * @param y        The y co-ordinate of centre of the Ball (in pixels)
	 * @param diameter The diameter of the Ball (in pixels)
	 * @param col      The colour of the Ball (Permissible colours are: BLACK, BLUE,
	 *                 CYAN, DARKGREY, GREY, GREEN, LIGHTGREY, MAGENTA, ORANGE,
	 *                 PINK, RED, WHITE, YELLOW or BROWN)
	 * @param layer    The layer this ball is to be drawn on. Objects with a higher
	 *                 layer number are always drawn on top of those with lower
	 *                 layer numbers.
	 */
	public Ball(double x, double y, double diameter, String col, int layer) {
		this.xPosition = x;
		this.yPosition = y;
		this.size = diameter;
		this.colour = col;
		this.layer = layer;
	}

	/**
	 * Obtains the current position of this Ball.
	 * 
	 * @return the X coordinate of this Ball within the GameArena.
	 */
	public double getXPosition() {
		return xPosition;
	}

	/**
	 * Obtains the current position of this Ball.
	 * 
	 * @return the Y coordinate of this Ball within the GameArena.
	 */
	public double getYPosition() {
		return yPosition;
	}

	/**
	 * Moves the current position of this Ball to the given co-ordinates
	 * 
	 * @param x the new x co-ordinate of this Ball
	 */
	public void setXPosition(double x) {
		this.xPosition = x;
	}

	/**
	 * Moves the current position of this Ball to the given co-ordinates
	 * 
	 * @param y the new y co-ordinate of this Ball
	 */
	public void setYPosition(double y) {
		this.yPosition = y;
	}

	/**
	 * Obtains the size of this Ball.
	 * 
	 * @return the diameter of this Ball,in pixels.
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Sets the diameter of this Ball to the given size.
	 * 
	 * @param s the new diameter of this Ball, in pixels.
	 */
	public void setSize(double s) {
		size = s;
	}

	/**
	 * Obtains the layer of this Ball.
	 * 
	 * @return the layer of this Ball.
	 */
	public int getLayer() {
		return layer;
	}

	/**
	 * Sets the layer of this Ball.
	 * 
	 * @param l the new layer of this Ball. Higher layer numbers are drawn on top of
	 *          low layer numbers.
	 */
	public void setLayer(int l) {
		layer = l;
	}

	/**
	 * Obtains the colour of this Ball.
	 * 
	 * @return a textual description of the colour of this Ball.
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * Sets the colour of this Ball.
	 * 
	 * @param c the new colour of this Ball, as a String value. Permissable colours
	 *          are: BLACK, BLUE, CYAN, DARKGREY, GREY, GREEN, LIGHTGREY, MAGENTA,
	 *          ORANGE, PINK, RED, WHITE, YELLOW or #RRGGBB.
	 */
	public void setColour(String c) {
		colour = c;
	}

	/**
	 * Moves this Ball by the given amount.
	 * 
	 * @param dx the distance to move on the x axis (in pixels)
	 * @param dy the distance to move on the y axis (in pixels)
	 */
	public void move(double dx, double dy) {
		xPosition += dx;
		yPosition += dy;
	}

	/**
	 * Determines if this Ball is overlapping a given ball.
	 * If the two balls overlap, they have collided.
	 * 
	 * @param b the ball to test for collision
	 * @return true of this ball is overlapping the ball b, false otherwise.
	 */
	public boolean collides(Ball b) {
		double dx = b.xPosition - xPosition;
		double dy = b.yPosition - yPosition;
		double distance = Math.sqrt(dx * dx + dy * dy);

		return distance < size / 2 + b.size / 2;
	}

	/**
	 * Calculates the deflection of two balls after a collision and updates their
	 * trajectories.
	 *
	 * @param xPosition1 The x-position of the first ball.
	 * @param yPosition1 The y-position of the first ball.
	 * @param xPosition2 The x-position of the second ball.
	 * @param yPosition2 The y-position of the second ball.
	 * @param xSpeed1    The x-speed of the first ball.
	 * @param ySpeed1    The y-speed of the first ball.
	 * @param xSpeed2    The x-speed of the second ball.
	 * @param ySpeed2    The y-speed of the second ball.
	 */
	public void deflect(double xPosition1, double yPosition1, double xPosition2, double yPosition2, double xSpeed1,
			double ySpeed1, double xSpeed2, double ySpeed2) {

		// Calculate initial momentum of the vall.. We assume unit mass here.
		double p1InitialMomentum = Math.sqrt(xSpeed1 * xSpeed1 + ySpeed1 * ySpeed1);
		double p2InitialMomentum = Math.sqrt(xSpeed2 * xSpeed2 + ySpeed2 * ySpeed2);

		// Calculate motion vectors
		double[] p1Trajectory = { xSpeed1, ySpeed1 };
		double[] p2Trajectory = { xSpeed2, ySpeed2 };

		// Calculate impact vectors
		double[] impactVector = { xPosition2 - xPosition1, yPosition2 - yPosition1 };
		double[] impactVectorNorm = normalizeVector(impactVector);

		// Calculate scalar product of each trajectory and impact vector
		double p1dotImpact = Math.abs(p1Trajectory[0] * impactVectorNorm[0] + p1Trajectory[1] * impactVectorNorm[1]);
		double p2dotImpact = Math.abs(p2Trajectory[0] * impactVectorNorm[0] + p2Trajectory[1] * impactVectorNorm[1]);

		// Calculate the deflection vectors - the amount of energy transferred from one
		// ball to the other in each axis
		double[] p1Deflect = { -impactVectorNorm[0] * p2dotImpact, -impactVectorNorm[1] * p2dotImpact };
		double[] p2Deflect = { impactVectorNorm[0] * p1dotImpact, impactVectorNorm[1] * p1dotImpact };

		// Calculate the final trajectories
		double[] p1FinalTrajectory = { p1Trajectory[0] + p1Deflect[0] - p2Deflect[0],
				p1Trajectory[1] + p1Deflect[1] - p2Deflect[1] };
		double[] p2FinalTrajectory = { p2Trajectory[0] + p2Deflect[0] - p1Deflect[0],
				p2Trajectory[1] + p2Deflect[1] - p1Deflect[1] };

		// Calculate the final energy in the system
		double p1FinalMomentum = Math
				.sqrt(p1FinalTrajectory[0] * p1FinalTrajectory[0] + p1FinalTrajectory[1] * p1FinalTrajectory[1]);
		double p2FinalMomentum = Math
				.sqrt(p2FinalTrajectory[0] * p2FinalTrajectory[0] + p2FinalTrajectory[1] * p2FinalTrajectory[1]);

		// Scale the resultant trajectories if we've accidentally broken the laws of
		// physics.
		double mag = (p1InitialMomentum + p2InitialMomentum) / (p1FinalMomentum + p2FinalMomentum);

		// Calculate the final x and y speed settings for the two balls after collision.
		xSpeed1 = p1FinalTrajectory[0] * mag;
		ySpeed1 = p1FinalTrajectory[1] * mag;
		xSpeed2 = p2FinalTrajectory[0] * mag;
		ySpeed2 = p2FinalTrajectory[1] * mag;

		x_startSpeed = xSpeed2;
		y_startSpeed = ySpeed2;

	}

	/**
	 * Converts a vector into a unit vector.
	 * Used by the deflect() method to calculate the resultant direction after a
	 * collision.
	 *
	 * @param vec The vector to be normalized.
	 * @return The normalized vector.
	 */
	private double[] normalizeVector(double[] vec) {
		double mag = 0.0;
		int dimensions = vec.length;

		double[] result = new double[dimensions];

		for (int i = 0; i < dimensions; i++)
			mag += vec[i] * vec[i];

		mag = Math.sqrt(mag);

		if (mag == 0.0) {
			result[0] = 1.0;
			for (int i = 1; i < dimensions; i++)
				result[i] = 0.0;
		} else {
			for (int i = 0; i < dimensions; i++)
				result[i] = vec[i] / mag;
		}
		return result;
	}

	/**
	 * Pauses the ball movement for a short duration.
	 */
	public void ballPause() {
		try {
			Thread.sleep(20);
		} catch (Exception e) {
		}
		;
	}

	/**
	 * Starts the ball movement with the initial speed.
	 */
	public void start() {
		move(x_startSpeed, y_startSpeed);
	}

	/**
	 * Changes the vertical direction of the ball's movement.
	 */
	public void bounceUpDown() {
		y_startSpeed *= -1;
	}

	/**
	 * Changes the horizontal direction of the ball's movement.
	 */
	public void bounceLeftRight() {
		x_startSpeed *= -1;
	}

	/**
	 * Returns the current x-speed of the ball.
	 *
	 * @return The x-speed of the ball.
	 */
	public double getXSpeed() {
		return x_startSpeed;
	}

	/**
	 * Returns the current y-speed of the ball.
	 *
	 * @return The y-speed of the ball.
	 */
	public double getYSpeed() {
		return y_startSpeed;
	}

	/**
	 * Returns the current movement state of the ball.
	 *
	 * @return True if the ball is moving, false otherwise.
	 */
	public boolean getMoveState() {
		return moveState;
	}

	/**
	 * Resets the position and speed of the ball when the left player scores a goal.
	 */
	public void leftGoalReset() {
		xPosition = 800;
		yPosition = 400;
		x_startSpeed = 0;
		y_startSpeed = 0;
	}

	/**
	 * Resets the position and speed of the ball when the right player scores a
	 * goal.
	 */
	public void rightGoalReset() {
		xPosition = 700;
		yPosition = 400;
		x_startSpeed = 0;
		y_startSpeed = 0;
	}

	/**
	 * Resets the position and speed of the ball when a goal is scored.
	 */
	public void goalReset() {
		xPosition = 750;
		yPosition = 400;
		x_startSpeed = 0;
		y_startSpeed = 0;
	}

	/**
	 * Applies friction to the ball's movement.
	 */
	public void applyFriction() {
		double friction = 0.008;

		x_startSpeed *= (1 - friction);
		y_startSpeed *= (1 - friction);
	}

	/**
	 * Resets the position of the left mallet.
	 */
	public void resetLeftMallet() {
		this.xPosition = 400;
		this.yPosition = 400;
	}

	/**
	 * Resets the position of the right mallet.
	 */
	public void resetRightMallet() {
		this.xPosition = 1100;
		this.yPosition = 400;
	}

}
