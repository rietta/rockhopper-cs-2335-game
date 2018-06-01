import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

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
public class SpriteFrame extends JPanel implements ActionListener {

    /**
     * Sprite storage unit for the graphics
     */
    private Sprites sprites;

    /**
     * The panel to which all the graphics will be drawn
     */
    private JPanel graphicsPanel;

    /**
     * The current level maze
     */
    private Maze mazeLevel;

    /***/
    private Timer tmrBoulders;

    /***/
    //    private Timer tmrEnemies;

    private int nTimerCount;

    /**
     * Variable of RockHopperListener
     */
    private RockHopperListener rhListen;

    /**
     * Timer Event
     * @param evt Event info.
     */
    public void actionPerformed(ActionEvent evt) {

        nTimerCount++;

        boolean somethingMoved = false;

        somethingMoved = mazeLevel.moveAutoSprites();

        if (mazeLevel.isRockfordDead() == false) {

            if (nTimerCount > 10) {
                mazeLevel.moveEnemies();
                nTimerCount = 0;

            } else if (
                    nTimerCount == 2 || nTimerCount == 4 || nTimerCount == 6) {
                somethingMoved = true;
            }

            if (somethingMoved) {
                this.repaint();
            }
        } else {
            this.repaint();
            if (rhListen.isKilled() == false) {
                rhListen.killedRockford();
            }
        }
    }

    /**
     * Default constructor
     */
    public SpriteFrame() {
        this.sprites = new Sprites();
        this.mazeLevel = new Maze();

        /*
         * Start the Timer for Sprites
         */
        Timer tmrBoulders = new Timer(50, this);
        nTimerCount = 0;
        tmrBoulders.start();

    } // end default constructor

    /**
     * A modifier for Sprites
     * @param sprite new sprite
     */
    public void setSprites(Sprites sprite) {
        this.sprites = sprite;
    }

    /**
     * Modifier for the listener
     * @param listener The listener
     */
    public void setListener(RockHopperListener listener) {
        this.rhListen = listener;
    }

    /**
     * A modifier for maze level
     * @param level the maze level
     */
    public void setMazeLevel(Maze level) {
        this.mazeLevel = level;
    }

    /**
     * A modifier for graphicsPanel
     * @param graphicsPanel new graphicsPanel
     */
    public void setGraphicsPanel(JPanel graphicsPanel) {
        this.graphicsPanel = graphicsPanel;
    }

    /**
     * An accessor for sprites
     * @return Sprites sprites
     */
    public Sprites getSprites() {
        return this.sprites;
    }

    /**
     * An accessor for maze level
     * @return Maze current level
     */
    public Maze getMazeLevel() {
        return this.mazeLevel;
    }

    /**
     * An accessor for graphicsPanel
     * @return JPanel graphicsPanel
     */
    public JPanel getGraphicsPanel() {
        return this.graphicsPanel;
    }

    /**
     * An accessor for Sprite Frame width
     * @return int graphicsPanel
     */
    public int getWidth() {
        return this.mazeLevel.getmazeWidth();
    }

    /**
     * An accessor for Sprite Frame length
     * @return int graphicsPanel
     */
    public int getLength() {
        return this.mazeLevel.getmazeLength();
    }

    /**
     * Draws the maze in the panel
     * @param g graphic to paint on
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.sprites.drawMaze(g, this.mazeLevel.getdataArray());
    }

}