package edu.neu.csye6200.ui;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import edu.neu.csye6200.bg.*;

/**
 * @author Yuanxin Zhang
 * NUID: 001405356
 * BGCanvas.java
 */

@SuppressWarnings("deprecation")
public class BGCanvas extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(BGCanvas.class.getName());
	private long counter = 0L;
	
	
	/**
	 * CellAutCanvas constructor
	 */
	public BGCanvas() {
		this.setLayout(null);
		this.setBackground(Color.black);
	}

	/**
	 * The UI thread calls this method when the screen changes, or in response to a
	 * user initiated call to repaint();
	 */
	public void paint(Graphics g) {
		super.paint(g);
		drawBG(g); // Our Added-on drawing
	}

	/**
	 * Draw the CA graphics panel
	 * @param g
	 */
	public void drawBG(Graphics g) {
		log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		
		if (BGApp.isDone == false) { // if the drawing process is not finished
			new Thread(new Runnable() { // this thread is used to draw the tree
				public void run() {
					drawTree(g2d); // Draw the Tree
				}
			}).start();
		}

		if (BGApp.isDone == true) { //if the drawing process is done, keep the BG we created
			for (int i = 0; i < BGApp.bgGs.getBgSet().get(0).getStemList().size(); i++) {
				BGStem st = BGApp.bgGs.getBgSet().get(0).getStemList().get(i);
				paintLine(g2d, BGApp.color, st);
			}
		}
	}

	private void drawTree(Graphics2D g2d) {
		try {
			// the first time, the canvas is initialized without stem data
			if (BGApp.bgGs.getBgSet().isEmpty() == false) {
				for (int i = 0; i < BGApp.bgGs.getBgSet().get(0).getStemList().size(); i++) {
					BGStem st = BGApp.bgGs.getBgSet().get(0).getStemList().get(i); // Get the current BGStem;
					paintLine(g2d, BGApp.color, st); // paint on the canvas
					Thread.sleep(BGApp.growthRate); // To show the animation of process
					synchronized(this) {
						while (BGApp.isPause == true) {
							wait(); //pause the thread
						}
					}
				}
				//The status after the drawing done.
				BGApp.isPause = false;
				BGApp.frame.setResizable(true);
				BGApp.isDone = true;
				BGApp.isRestart = true;
			}
		}
		catch (InterruptedException e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Pause the Thread
	 */
	synchronized void pause() {
		BGApp.isPause = true;
	}
	
	/**
	 * Resume the Thread
	 */
	synchronized void resume() {
		BGApp.isPause = false;
		notifyAll();
	}
	

	/**
	 * Draw the stems
	 * @param g2d : the 2D Graphics context
	 * @param stem : the line of stem
	 * @param color : the line color
	 */
	private void paintLine(Graphics2D g2d, Color color, BGStem stem) {
		Dimension size = getSize();
		g2d.setColor(BGApp.color); //Set the color
		Line2D line;
		line = new Line2D.Double(stem.getLocationX() + size.getWidth() / 2,
				-stem.getLocationY() + size.getHeight(),
				(stem.getLocationX() + stem.getLength() 
				* Math.cos(stem.getRadians()) + size.getWidth() / 2),
				-(stem.getLocationY() + stem.getLength() 
				* Math.sin(stem.getRadians())) + size.getHeight());
		g2d.draw(line);
	}

	/**
	 * @param obr
	 * @param arg
	 */
	@Override
	public void update(Observable obr, Object arg) {
	}
}