package edu.neu.csye6200.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.IOException;

/**
 * TreeApp: Biological Plant Growth Simulation application
 * @author Yuanxin Zhang
 * NUID 001405356
 * TreeApp.java
 */

public class TreeApp extends BGApp {
	/**
	 * Constructor
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public TreeApp() throws SecurityException, IOException {
		try {
			log.info("JavaTree is building");
			Handler handler = new FileHandler(logFile);
			Logger.getLogger("").addHandler(handler); // Add a fileHandler
		} catch (SecurityException e) {
			log.warning("SecurityException in the constructor of TreeApp");
			e.printStackTrace();
		} catch (IOException e) {
			log.warning("IOException in the constructor of TreeApp");
			e.printStackTrace();
		}
		frame.setTitle("JavaTree");
		frame.setSize(800, 800); // initial Frame size
		
		
		menuMgr.createDefaultActions(); // Set up default menu items
		showUI(); // Cause the Swing Dispatch thread to display the JFrame
	}

	
	/**
     * Create a main panel that will hold the bulk of our application display
     * @return
     */
	@SuppressWarnings("deprecation")
	@Override
	public JPanel getMainPanel() {
		mainPanel = new JPanel(); 
		bgPanel = new BGCanvas();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.SOUTH, getMenuPanel());
		mainPanel.add(BorderLayout.CENTER, bgPanel);
		bgGs.addObserver(bgPanel); /// Add an observer
		return mainPanel;
	}

	/**
	 * Create a top panel that will hold control buttons
	 * @return
	 */
	public JPanel getMenuPanel() {
		menuPanel = new JPanel(); // Create a new JPanel instance
		menuPanel.setLayout(null); // Initialize the layout
		menuPanel.setPreferredSize(new Dimension(800, 160)); // Set the size of menu panel
		menuPanel.setBackground(Color.WHITE); // Set the background color of the menu panel
		
		/**
		 * SET THE START BUTTON
		 */
		startBtn = new JButton(" Start "); // Create a start button instance
		startBtn.setBounds(120, 80, 100, 60);// Set the location and the size of start button
					// (x ,y ,length, width)
		startBtn.addActionListener(e -> { // Add the action to start button
			isPause = false;
			isDone = false; 
			bgGs.genrationSet(rule); // Create stems and collect them
			bgPanel.paint(bgPanel.getGraphics()); // Draw the stems
		});
		
		/**
		 * SET THE STOP BUTTON
		 */
		stopBtn = new JButton("STOP"); // Create a stop button instance
		stopBtn.setBounds(250, 80, 100, 60);// Set the location and the size of stop button
					// (x ,y ,length, width)
		stopBtn.addActionListener(e -> { // Add the action to stop button
			frame.setResizable(true);	
			bgPanel.pause();
		});
		
		/**
		 * SET THE RESUME BUTTON
		 */
		resumeBtn = new JButton("RESUME"); // Create a resume button instance
		resumeBtn.setBounds(380, 80, 100, 60);// Set the location and the size of resume button
						// (x ,y ,length, width)
		resumeBtn.addActionListener(e -> {// Add the action to resume button
			frame.setResizable(true);	
			bgPanel.resume();
		});
		
		/**
		 * SET THE END BUTTON
		 */
		endBtn = new JButton("EXIT"); // Create a end button instance
		endBtn.setBounds(510, 80, 100, 60);// Set the location and the size of end button
					// (x ,y ,length, width)
		endBtn.addActionListener(e -> {// Add the action to end button
			exit(); // Exit the application
		});

		
		/**
		 * SET THE RULE COMBOBOX
		 */
		ruleLabel = new JLabel("Type:"); // Create a rule JLabel instance
		ruleBox = new JComboBox<String>(rules);// Create a rule ComboBox instance
		ruleLabel.setBounds(50, 20, 40, 60); // Set the location and the size of rule label
					//(x ,y ,length, width)
		ruleBox.setBounds(90, 20, 150, 60); // Set the location and the size of rule box
		ruleBox.setModel(new DefaultComboBoxModel<String>(rules)); // Add items into the box
		ruleBox.addActionListener(e -> { // Add the action to rule ComboBox
			switch (ruleBox.getSelectedItem().toString()) {
				case "BinaryTree":
					rule = "BinaryTree"; 
					break;
				case "TrinaryTree":
					rule = "TrinaryTree"; 
					break;
				case "QuaternaryTree":
					rule = "QuaternaryTree"; 
					break;
			}
		});
		
		
		/**
		 * SET THE COLOR COMBOBOX
		 */
		colorLabel = new JLabel("Color:"); // Create a color JLabel instance
		colorBox = new JComboBox<String>(); // Create a color ComboBox instance
		colorLabel.setBounds(270, 20, 40, 60); // Set the location and the size of color label
						//(x ,y ,length, width)
		colorBox.setBounds(310, 20, 150, 60);// Set the location and the size of color box
		colorBox.setModel(new DefaultComboBoxModel<String>(colors)); // Add items into the box
		colorBox.addActionListener(e -> { // Add the action to color ComboBox
			switch (colorBox.getSelectedIndex()) {
			case 0:
				color = Color.pink; 
				break;
			case 1:
				color = Color.white; 
				break;
			case 2:
				color = Color.red; 
				break;
			case 3:
				color = Color.blue; 
				break;
			case 4:
				color = Color.green; 
				break;
			case 5:
				color = Color.yellow; 
				break;

			}
		});
		
		/**
		 * SET THE ANIMATION COMBOBOX
		 */
		aniLabel = new JLabel("Animation:");// Create a animation JLabel instance
		aniBox = new JComboBox<String>(); // Create a animation ComboBox instance
		aniLabel.setBounds(500, 20, 80, 60); // Set the location and the size of animation label
		aniBox.setBounds(580, 20, 100, 60); // Set the location and the size of animation box
		
		aniBox.addItem("True"); // Add items into the box
		aniBox.addItem("False");
		
		aniBox.addActionListener(e->{  // Add the action to animation ComboBox
			switch (aniBox.getSelectedIndex()) {
			case 0:
				growthRate = 1; // Set the grow rate -- show the process animation
				break; 
			case 1:
				growthRate = 0; // Set 0 -- DON'T show the process animation
				break;
			}
		});

		/**
		 * Add into the menu panel
		 */
		menuPanel.add(startBtn);
		menuPanel.add(stopBtn);
		menuPanel.add(resumeBtn);
		menuPanel.add(endBtn);
		menuPanel.add(ruleLabel);
		menuPanel.add(ruleBox);
		menuPanel.add(colorLabel);
		menuPanel.add(colorBox);
		menuPanel.add(aniLabel);
		menuPanel.add(aniBox);

		return menuPanel;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

	}

	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		log.info("Window closing");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		log.info("Window closed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		log.info("Window deiconified");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		log.info("Window deactivated");
	}

	/**
	 * Sample Tree application starting point
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws SecurityException, IOException {
		@SuppressWarnings("unused")
		TreeApp tapp = new TreeApp();
		log.info("TreeApp started!");
	}

}