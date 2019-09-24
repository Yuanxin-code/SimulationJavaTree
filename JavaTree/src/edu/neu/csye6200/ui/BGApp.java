package edu.neu.csye6200.ui;

import edu.neu.csye6200.bg.BGGenerationSet;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

/**
 * @author Yuanxin Zhang
 * NUID: 001405356
 * BGApp.java
 */

public abstract class BGApp implements ActionListener, WindowListener {
	
	// Logger declaration
	protected static Logger log = Logger.getLogger(TreeApp.class.getName());
	protected String logFile = "src/server.log";
	
	public static JFrame frame = null;
	protected MenuManager menuMgr = null;
	protected JPanel menuPanel = null;
	protected JPanel mainPanel = null;
	protected BGCanvas bgPanel = null;
	
	//The number of generation
	public static int genNum1 = 9;
	public static int genNum2 = 7;
	public static int genNum3 = 5;
	
	//Declare and set the default value
	public static String rule = "BinaryTree";
	public static Color color = Color.pink;
	public static BGGenerationSet bgGs  = BGGenerationSet.generationSet();//Singleton
	public static int growthRate = 1;// The growth rate
	public static boolean isPause = false;	// to note the process status
	public static boolean isRestart = true;	// to note the process status whether can be restart
	public static boolean isDone = false; // to note the process status
	
	
	// JButton, JLabel and ComboBox
	protected JButton startBtn = null;
	protected JButton stopBtn = null;
	protected JButton resumeBtn = null;
	protected JButton endBtn = null;
	protected JLabel ruleLabel = null;
	protected JLabel colorLabel = null;
	protected JLabel aniLabel = null;
	protected String rules[] = {"BinaryTree", "TrinaryTree", "QuaternaryTree"};
	protected String colors[] = {"pink", "white", "red", "blue", "green", "yellow"};
	protected JComboBox<String> ruleBox = null;
	protected JComboBox<String> colorBox = null;
	protected JComboBox<String> aniBox = null;

	/**
	 * The Biological growth constructor
	 */
	public BGApp() {
		initGUI();
	}
	
	/**
	 * Initialize the Graphical User Interface
	 */
    public void initGUI() {
    	frame = new JFrame();
		frame.setTitle("BGApp");

		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //JFrame.DISPOSE_ON_CLOSE)
		
		// Permit the app to hear about the window opening
		frame.addWindowListener(this); 
		
		menuMgr = new MenuManager(this);
		
		frame.setJMenuBar(menuMgr.getMenuBar()); // Add a menu bar to this application
		
		frame.setLayout(new BorderLayout());
		frame.add(getMainPanel(), BorderLayout.CENTER);
    }
    
    /**
     * Override this method to provide the main content panel.
     * @return a JPanel, which contains the main content of of your application
     */
    public abstract JPanel getMainPanel();
   
    
    /**
     * A convenience method that uses the Swing dispatch threat to show the UI.
     * This prevents concurrency problems during component initialization.
     */
    public void showUI() {
    	
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
            	frame.setVisible(true); // The UI is built, so display it;
            }
        });
    	
    }
    
    /**
     * Shut down the application
     */
    public void exit() {
    	frame.dispose();
    	System.exit(0);
    }

    /**
     * Override this method to show a About Dialog
     */
    public void showHelp() {
    	
    }
}