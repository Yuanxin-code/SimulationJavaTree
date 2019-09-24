package edu.neu.csye6200.bg;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author Yuanxin Zhang
 * NUID: 001405356
 * BGRule.java
 */

public class BGRule {

	private static Logger log = Logger.getLogger(BGRule.class.getName());
	private BGStem stem; // The base stem
	private double radians, X, Y, length;// To renew the stem info
	private double altLen, altMidLen;
	
	/**
	 * Constructor
	 */
	public BGRule() {
		log.info("Created An instance of BGRule!");
	}
	
	/**
	 * CurrentStem
	 * @param bgs :the ArrayList of BGStem
	 * @param preID : the previous id 
	 */
	private void currentStem(ArrayList<BGStem> bgs, int preID) {
		radians = bgs.get(preID).getRadians();
		X = bgs.get(preID).getLocationX();
		Y = bgs.get(preID).getLocationY();
		length = bgs.get(preID).getLength();
	}
 
	/**
	 * GrowthRule1 : to gain a new stem via rule1
	 * @param generation: int
	 * @param len : the length of new part of stem
	 * @param rad : the rotate radian
	 * @param bgs :the ArrayList of BGStem
	 * @param i : to count the id
	 * @return stem
	 */
	public BGStem growthRule(int generation, double len, double rad, ArrayList<BGStem> bgs, int i) {
		altLen = Math.pow(len, ((int) (Math.log(i + 1) / Math.log(4))));
		if (i % 2 == 1) { // grow to one side -- the first child stem
			currentStem(bgs, (i - 1) / 2);
			stem = new BGStem((X + length * Math.cos(radians)),
					(Y + length * Math.sin(radians)),
					(length / altLen), radians + rad);
		}
		else if (i % 2 == 0) { // grow to another side -- the second child stem
			currentStem(bgs, (i - 2) / 2);
			stem = new BGStem((X + length * Math.cos(radians)), 
					(Y + length * Math.sin(radians)),
					(length / altLen), radians - rad);
		}
		return stem;
	}
	
	/**
	 * GrowthRule2 : to gain a new stem via rule2
	 * @param generation: int
	 * @param len : the length of new part of stem
	 * @param mLen : the length of new part of middle stem
	 * @param rad : the rotate radian
	 * @param bgs :the ArrayList of BGStem
	 * @param i : to count the id
	 * @return stem
	 */
	public BGStem growthRule(int generation, double len, double mLen, double rad, ArrayList<BGStem> bgs, int i) {
		altLen = Math.pow(len, ((int) (Math.log(2 * i + 1) / Math.log(3))));
		altMidLen = Math.pow(mLen, ((int) (Math.log(2 * i + 1) / Math.log(3))));
		if (i % 3 == 1) { // the first child stem
			currentStem(bgs, (i - 1) / 3);
			stem = new BGStem((X + length * Math.cos(radians)), (Y + length * Math.sin(radians)),
					(length / altLen),
					radians + rad);
		}
		else if (i % 3 == 2) { // the second child stem
			currentStem(bgs, (i - 2) / 3);
			stem = new BGStem((X + length * Math.cos(radians)), (Y + length * Math.sin(radians)),
					(length / altMidLen), radians);
		}
		else if (i % 3 == 0) { // the third child stem
			currentStem(bgs, (i - 3) / 3);
			stem = new BGStem((X + length * Math.cos(radians)), (Y + length * Math.sin(radians)),
					(length / altLen),
					radians - rad);
		}
		return stem;
	}
	

	/**
	 * GrowthRule3: to gain a new stem via rule3
	 * @param generation: int
	 * @param len : the length of new part of stem
	 * @param mLen : the length of new part of middle stem
	 * @param rad : the rotate radian
	 * @param mRad : 
	 * @param bgs : the ArrayList of BGStem
	 * @param i : to count the id
	 * @return stem
	 */
	public BGStem growthRule(int generation, double len, double mLen, double rad, double mRad, ArrayList<BGStem> bgs, int i) {
		altLen = Math.pow(len, (int) (Math.log(3 * i + 1) / Math.log(4)));
		altMidLen = Math.pow(mLen, (int) (Math.log(3 * i + 1) / Math.log(4)));
		if (i % 4 == 1) { // the first child stem
			currentStem(bgs, (i - 1) / 4);
			stem = new BGStem((X + length * Math.cos(radians)), (Y + length * Math.sin(radians)),
					(length / altLen), radians + rad);
		}
		else if (i % 4 == 2) { // the second child stem
			currentStem(bgs, (i - 2) / 4);
			stem = new BGStem((X + length * Math.cos(radians)), (Y + length * Math.sin(radians)),
					(length / altMidLen), radians + mRad);
		}
		else if (i % 4 == 3) { //  the third child stem
			currentStem(bgs, (i - 2) / 4);
			stem = new BGStem((X + length * Math.cos(radians)), (Y + length * Math.sin(radians)),
					(length / altMidLen), radians - mRad);
		}
		else if (i % 4 == 0) { // the fourth child stem
			currentStem(bgs, (i - 2) / 4);
			stem = new BGStem((X + length * Math.cos(radians)), (Y + length * Math.sin(radians)),
					(length / altLen), radians - rad);
		}
		return stem;
	}
}