    import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 * CS2335 Lab 6
 *
 * <PRE>
 * TimerPanel.java
 * <PRE>
 * Keeps track of the time
 * @author Ronald Ning and Frank Rietta
 * @version Version 1.0, February 25, 2003
 */
public class TimerPanel extends JPanel {

    /**
     * Variable which stores the maze data
     */
    private SpriteFrame currentLevel;

    /**
     * Variable to tell if timer is running
     */
    private boolean running;

    /**
     * font type
     */
    private Font mfont = new Font("Arial", Font.PLAIN, 10);

    /**
     * Variable which stores the current actionListener
     */
    private RockHopperListener rockListener;

    /**
     * Variable which stores the time elipased in seconds before dying
     */
    private int timeElapsed;

    /**
     * Variable which stores the total diamond count for exit to open
     */
    private int diamondTotal;

    /**
     * Variable which stores the time elipased in seconds before dying
     */
    private int timeTotal;

    /**
     * Diamond count from mazeLevel
     */
    private int collectedDiamondCount;

    /**
     * Variable which stores the diamond Label text
     */
    private String diamondText = "Gold got";

    /**
     * Variable which stores the timer Label text
     */
    private String timerText = "  Time left:";

    /**
     * Variable to store the status of time
     */
    private JProgressBar diamondBar;

    /**
     * Variable to store the status of diamonds collect
     */
    private JProgressBar timerBar;

    /**
     * Store the timing
     */
    private Timer animation;

    /**
     * Default constructor
     * @param level of maze
     * @param action the listener of controls
     */
    public TimerPanel(SpriteFrame level, RockHopperListener action) {
        this.currentLevel = level;
        this.rockListener = action;
        int number = currentLevel.getMazeLevel().getJemsOnMap();
        double numberF = number * 0.75;
        this.diamondTotal = (int) numberF;

        this.timeTotal = 180;
        this.timeElapsed = timeTotal;
        this.collectedDiamondCount = -1;
        this.animateGraphics();

        this.setLayout(new GridLayout(1, 4));
        this.populatePanel();
    }

    /**
     * Populate the panel
     */
    public void populatePanel() {
        /* labels */
        Font mfont = new Font("Arial", Font.BOLD, 12);
        JLabel diamondC = new JLabel(diamondText);
        diamondC.setFont(mfont);
        JLabel timeC = new JLabel(timerText);
        timeC.setFont(mfont);
        JProgressBar space = new JProgressBar();
        space.setIndeterminate(true);

        /* progress bars */
        timerBar = new JProgressBar(0, timeTotal);
        timerBar.setValue(timeTotal);
        timerBar.setFont(mfont);
        timerBar.setString(this.getTime());
        timerBar.setStringPainted(true);
        diamondBar = new JProgressBar(0, diamondTotal);
        diamondBar.setFont(mfont);
        diamondBar.setValue(0);
        diamondBar.setString("0/" + diamondTotal);
        diamondBar.setStringPainted(true);

        /* add them to the bar */
        this.add(diamondC);
        this.add(diamondBar);
        this.add(space);
        this.add(timeC);
        this.add(timerBar);
    }

    /**
     * isRunning();
     * @return boolean of run
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Animates the square
     */
    public void animateGraphics() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /* timer update */
                if (timeElapsed > 0) {
                    Maze current = currentLevel.getMazeLevel();
                    timeElapsed = current.getCurrentTime();
                    timeElapsed--;
                    current.setCurrentTime(timeElapsed);
                    diamondTotal = current.getJewsRequireToExit();
                    diamondBar.setMaximum(diamondTotal);
                    collectedDiamondCount = current.getJemsCollected();
                    diamondBar.setString(
                        collectedDiamondCount + "/" + diamondTotal);
                    diamondBar.setValue(collectedDiamondCount);
                    timerBar.setValue(timeElapsed);
                    timerBar.setString(getTime());
                } else {
                    /* timer is gone and you autodie */
                    rockListener.timekillRockford();
                    running = false;
                    animation.stop();
                }
            }
        };

        running = true;
        this.animation = new Timer(1000, listener);
        animation.start();
    }

    /**
     * Displays the time in proper format
     * @return String of the time
     */
    private String getTime() {
        String secs = " secs";
        String mins = " mins";

        return timeElapsed + secs;
    }

    /**
     * stop()
     */
    public void stop() {
        running = false;
        animation.stop();
    }

    /**
     * start()
     */
    public void start() {
        running = true;
        animation.restart();
    }

}
