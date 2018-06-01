import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * CS2335 Lab 6
 *
 * <PRE>
 * RockHopper.java
 * <PRE>
 *
 * @author Ronald Ning and Frank Rietta
 * @version Version 1.4, February 25, 2003
 */
public class RockHopper {
    /**
     * JFrame to which everything is rendered
     */
    private JFrame guiFrame;

    /**
     * Content Pane of the JFrame
     */
    private Container contentPane;

    /**
     * Content Pane of the JFrame
     */
    private SpriteFrame spritePane;

    /**
     * The timer panel
     */
    private TimerPanel timerPanel;

    /**
     * The menu bar
     */
    private JMenuBar menuBar = new JMenuBar();

    /**
     * The fileMenu
     */
    private JMenu fileMenu = new JMenu();

    /** Menu item */
    private JMenuItem newGame = new JMenuItem();
    /** Menu item */
    private JMenuItem resetLevel = new JMenuItem();
    /** Menu item */
    private JMenuItem exitGame = new JMenuItem();

    /** Help item */
    private JMenu helpMenu = new JMenu();
    /** Menu item */
    private JMenuItem howToPlay = new JMenuItem();
    /** Menu item */
    private JMenuItem about = new JMenuItem();

    /** Options item */
    private JMenu optionsMenu = new JMenu();
    /** Menu item */
    private JMenuItem previousLevel = new JMenuItem();
    /** Menu item */
    private JMenuItem nextLevel = new JMenuItem();

    /**
     * RockHopperListener for handling the action events
     */
    private RockHopperListener rhListen;

    /**
     * A modifier for guiFrame
     * @param guiFrame new guiFrame
     */
    public void setGuiFrame(JFrame guiFrame) {
        this.guiFrame = guiFrame;
    }

    /**
     * A modifier for contentPane
     * @param contentPane new contentPane
     */
    public void setContentPane(Container contentPane) {
        this.contentPane = contentPane;
    }


    /**
     * Modifier for the timerPanel
     * @param panel to set it to
     */
    public void setTimerPanel(TimerPanel panel) {
        this.timerPanel = panel;
    }

    /**
     * An accessor for guiFrame
     * @return JFrame guiFrame
     */
    public JFrame getGuiFrame() {
        return this.guiFrame;
    }

    /**
     * An accessor for contentPane
     * @return Container contentPane
     */
    public Container getContentPane() {
        return this.contentPane;
    }

    /**
     * Gets the TimePanel
     * @return TimerPanel
     */
    public TimerPanel getTimerPanel() {
        return this.timerPanel;
    }

    /**
     * An accessor for spriteFrame
     * @return Container contentPane
     */
    public SpriteFrame getSpriteFrame() {
        return this.spritePane;
    }

    /**
     * Default constructor which makes the maze
     * @param levelFile level to load
     */
    public RockHopper(String levelFile) {
        spritePane = new SpriteFrame();
        spritePane.getMazeLevel().loadSettings(levelFile);
        rhListen = new RockHopperListener(spritePane, this);
        spritePane.setListener(rhListen);
        this.makeWindow();
    }

    /**
     * Create the window and sets it up for drawing
     */
    public void makeWindow() {
        guiFrame = new JFrame("RockHopper");

        /* Handle Closing the Window */
        timerPanel = new TimerPanel(spritePane, rhListen);
        guiFrame.addWindowListener(rhListen);
        contentPane = guiFrame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        guiFrame.addKeyListener(rhListen);

        /* menu stuff */
        populateFileMenu();
        populateOptionsMenu();
        populateHelpMenu();
        menuBar.setVisible(true);
        guiFrame.setJMenuBar(menuBar);

        /* the maze content */
        contentPane.add(timerPanel, BorderLayout.SOUTH);
        contentPane.add(spritePane, BorderLayout.CENTER);
        guiFrame.setSize(
                spritePane.getWidth() + 8,
                spritePane.getLength() + 70);
        guiFrame.setVisible(true);
    }

    /**
     * Restarts the panel
     */
    public void reset() {
        timerPanel.start();
    }

    /**
     * Populate the menu File bar
     */
    public void populateFileMenu() {
        /* file items */
        fileMenu.setText("File");
        newGame.setText("New Game");
        newGame.addActionListener(rhListen);

        resetLevel.setText("Reset Level");
        resetLevel.addActionListener(rhListen);
        exitGame.setText("Quit");

        menuBar.add(fileMenu);
        fileMenu.add(newGame);
        fileMenu.add(resetLevel);
        fileMenu.add(exitGame);
        exitGame.addActionListener(rhListen);
    }

    /**
     * Populate the help menu
     */
    public void populateHelpMenu() {
        helpMenu.setText("Help");
        howToPlay.setText("How to play");
        about.setText("About RockHopper");
        about.addActionListener(rhListen);

        menuBar.add(helpMenu);
        helpMenu.add(howToPlay);
        howToPlay.addActionListener(rhListen);

        helpMenu.add(about);
    }

    /**
     * Populate the options menu
     */
    public void populateOptionsMenu() {
        optionsMenu.setText("Options");
        previousLevel.setText("Go to previous level");
        previousLevel.addActionListener(rhListen);
        nextLevel.setText("Go to next level");
        nextLevel.addActionListener(rhListen);

        menuBar.add(optionsMenu);
        optionsMenu.add(previousLevel);
        optionsMenu.add(nextLevel);
    }


    /**
     * Main method that creates a new BasicGraphics
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        String levelFile;
        if (args.length >= 1) {
            levelFile = args[0];
        } else {
            levelFile = "level1.xml";
        }

        RockHopper test = new RockHopper(levelFile);
    }

}