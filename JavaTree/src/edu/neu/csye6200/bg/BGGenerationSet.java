package edu.neu.csye6200.bg;

import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * @author Yuanxin Zhang
 * NUID: 001405356
 * BGGenerationSet.java
 */


@SuppressWarnings("deprecation")
public class BGGenerationSet extends Observable{
	
	private static Logger log = Logger.getLogger(BGGenerationSet.class.getName());	
	private BGGeneration bgGen;
	private static BGGenerationSet genSet = null;
	private ArrayList<BGGeneration> bgList = new ArrayList<BGGeneration>();
	
	
	
	/**
	 * Constructor(Singleton)
	 */
	private BGGenerationSet() {
		log.info("Created An instance of BGGenrationSet! ");
	}
	public static BGGenerationSet generationSet() {
		if(genSet == null) {
			log.info("Created An instance of BGGenrationSet! ");
			genSet = new BGGenerationSet();
		}			
		return genSet;
	}
	
	/**
	 * Getter of BGGeneration
	 * @return bgList
	 */
	public ArrayList<BGGeneration> getBgSet() {
		return bgList;
	}

	/**
	 * To determined which grow method
	 * @param type
	 */
	public void genrationSet(String type) {
		bgGen = new BGGeneration(); // Create a instance 
		switch (type) {
		case "BinaryTree": //Every stem grows two child stems symmetrically
			bgGen.growthRule("BinaryTree");
			break;

		case "TrinaryTree": //Every stem grows three child stems symmetrically
			bgGen.growthRule("TrinaryTree");
			break;
			
		case "QuaternaryTree"://Every stem grows four child stems symmetrically
			bgGen.growthRule("QuaternaryTree"); 
			break;		
		}
		bgList.add(0,bgGen);	
	}
}