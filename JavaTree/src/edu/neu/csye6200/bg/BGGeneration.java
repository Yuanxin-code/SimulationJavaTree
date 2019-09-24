package edu.neu.csye6200.bg;

import java.util.logging.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import edu.neu.csye6200.ui.*;

/**
 * @author Yuanxin Zhang
 * NUID: 001405356
 * BGGeneration.java
 */
public class BGGeneration {

	private static Logger log = Logger.getLogger(BGGeneration.class.getName());
	private BGStem bgStem;
	private ArrayList<BGStem> stemList = new ArrayList<BGStem>(); // To store bgStem
	private BGRule bgRule = new BGRule(); // To use the grow rules
	double radians, X, Y, length; 

	/**
	 * Constructor
	 */
	public BGGeneration() {
		log.info("Created an BGGenration instance! ");
	}

	/**
	 * Access to grow method: Rule1, Rule2, Rule3
	 * @param choice
	 */
	public void growthRule(String choice) {
		
		bgStem = new BGStem(0, 0, 60, Math.PI / 2); // Create the initial instance
		stemList.add(bgStem);

		/**
		 * Rule1: Every stem grows two child stems symmetrically
		 */
		if (choice.equals("BinaryTree")) {
			int stemNum = (int) (Math.pow(2, BGApp.genNum1)*2 - 1); // the number of 
			for (int i = 1; i < stemNum; i++) {
				bgStem = bgRule.growthRule(BGApp.genNum1, 
						1.08, Math.PI/8,
						stemList, i);
				stemList.add(bgStem);
			}
		}

		/**
		 * Rule2: Every stem grows three stems symmetrically
		 */
		else if (choice.equals("TrinaryTree")) {
			int stemNum = (int) (Math.pow(3, BGApp.genNum2)*3/2 - 1);
			for (int i = 1; i < stemNum; i++) {
				bgStem = bgRule.growthRule(BGApp.genNum2, 
						1.08, 1.00001, Math.PI/6, 
						stemList, i);
				stemList.add(bgStem);
			}
		}

		/**
		 * Rule3: Every stem grows four stems symmetrically
		 */
		else if (choice.equals("QuaternaryTree")) {
			int stemNum = (int) (Math.pow(4, BGApp.genNum3)*4/3 - 1);
			for (int i = 1; i < stemNum; i++) {
				bgStem = bgRule.growthRule(BGApp.genNum3, 
						1.08, 1.00001,
						Math.PI/6, Math.PI/20, 
						stemList, i);
				stemList.add(bgStem);
			}
		}
		saveStemInfo(); // Store the information of stems in to a file
	}

	/**
	 *  Save stem information -- info.txt
	 */
	private void saveStemInfo() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/info.txt"))) {
			bw.write(String.format("%1$-16s %2$-16s %3$-16s %4$-16s %5$-16s", "stemID", "LocationX", "LocationY",
					"Length", "Radians") + '\n');
			for (BGStem stem : stemList) {
				bw.write(stem.toString()+ '\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Getter of StemList
	 * @return stemList
	 */
	public ArrayList<BGStem> getStemList() {
		return stemList;
	}
}
