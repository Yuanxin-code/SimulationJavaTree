package edu.neu.csye6200.bg;

/**
 * @author Yuanxin Zhang
 * NUID: 001405356
 * BGStem.java
 */

public class BGStem {
	
	private int stemID;	
	private static int idCounter = 0;
	private double locationX; // The x-axis index of start point
	private double locationY;// The y-axis index of start point
	private double length;		
	private double radians;		
	 
	
	// Constructor
	public BGStem(double locationX, double locationY, double length, double radians) {
		this.stemID = idCounter++; // Create a unique stemID
		setLocationX(locationX);
		setLocationY(locationY);
		setLength(length);
		setRadians(radians);
	}
	public BGStem() {
		
	}

	/**
	 * Getter of stemID
	 * @return stemID
	 */
	public int getStemID() {
		return stemID;
	}

	/**
	 * Getter and setter
	 * LocationX
	 */
	public double getLocationX() {
		return locationX;
	}
	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}
	
	/**
	 * Getter and setter
	 * LocationY
	 */
	public double getLocationY() {
		return locationY;
	}
	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}
	
	
	/**
	 * Getter and setter
	 * Length
	 */
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		if (length <= 0)length = 0; // Make sure the length is NOT negative
		this.length = length;
	}
	
	/**
	 * Getter and setter
	 * Radians
	 */
	public double getRadians() {
		return radians;
	}
	public void setRadians(double radians) {
		this.radians = radians;
	}


	/**
	 * The print format of stem information
	 */
	public String toString() {
		return String.format("%1$-16d %2$-16.2f %3$-16.2f %4$-16.2f %5$-16.2f", 
				this.stemID, this.locationX, this.locationY, this.length, this.radians);
	}
}
