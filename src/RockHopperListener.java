import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * CS2335 Lab 6
 *
 * <PRE>
 * RockHopperListener.java
 * <PRE>
 * The listener events for RockHopper.  Handles all the events
 * @author Ronald Ning and Frank Rietta
 * @version Version 1.4, February 25, 2003
 */
public class RockHopperListener
    implements KeyListener, ActionListener, WindowListener {

    /**
     * Variable to store the sprites
     */
    private SpriteFrame spritePane;

    /**
     * Variable to store the maze levels
     */
    private MazeLevels mazeLevels;

    /**
     * Variable which stores the RockHopper program
     */
    private RockHopper program;

    /**
     * Keylock
     */
    private boolean keyLock;

    /**
     * Killed already
     */
    private boolean killedAlready;

    /**
     * Default constructor
     */
    public RockHopperListener() {
        spritePane = null;
        keyLock = false;
        program = null;
        killedAlready = false;
        mazeLevels = new MazeLevels(spritePane);
    }

    /**
     * Constructor which takes in the sprite frames
     * @param sp the SpriteFrame to modify to
     * @param program the program to listen to
     */
    public RockHopperListener(SpriteFrame sp, RockHopper program) {
        this.spritePane = sp;
        this.keyLock = false;
        this.program = program;
        mazeLevels = new MazeLevels(spritePane);
    }

    /**
     * Action listener for buttons
     * @param e the action fired by an unit
     */
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();

        if (source.getText().equals("Quit")) {
            System.exit(0);
        } else if (source.getText().equals("How to play")) {
            new HowToPlay();
        } else if (source.getText().equals("About RockHopper")) {
            new About();
        } else if (source.getText().equals("New Game")) {
            mazeLevels.startNewGame();
            this.reset();
            program.reset();
        } else if (source.getText().equals("Reset Level")) {
            spritePane.getMazeLevel().initMap();
            program.reset();
            this.reset();
        } else if (source.getText().equals("Go to previous level")) {
            String level = spritePane.getMazeLevel().getPreviousLevelFile();
            System.out.println("Going to previous level: " + level);
            mazeLevels.setPreviousLevel(level);
            mazeLevels.goPreviousLevel();
            program.reset();
            this.reset();
        } else if (source.getText().equals("Go to next level")) {
            String level = spritePane.getMazeLevel().getNextLevelFile();
            System.out.println("Going to next level: " + level);
            mazeLevels.setNextLevel(level);
            mazeLevels.goNextLevel();
            program.reset();
            this.reset();
        }
    }

    /**
     * accessor for keylock
     * @return boolean of keylock status
     */
    public boolean isKeyLocked() {
        return this.keyLock;
    }

    /**
     * accessor for killing
     * @return boolean killing
     */
    public boolean isKilled() {
        return this.killedAlready;
    }

    /**
     * Modifier for the keylock
     * @param status of keylock
     */
    public void setKeyLock(boolean status) {
        this.keyLock = status;
    }

    /**
     * @param keyData The event.
     */
    public void keyPressed(KeyEvent keyData) {
        if (keyLock == false) {
            int keyCode = keyData.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_DOWN :

                    //        System.out.println("Key Down Pressed");
                    spritePane.getMazeLevel().moveDown();
                    break;
                case KeyEvent.VK_UP :

                    //        System.out.println("Key Up Pressed");
                    spritePane.getMazeLevel().moveUp();
                    break;
                case KeyEvent.VK_LEFT :

                    //        System.out.println("Key Left Pressed");
                    spritePane.getMazeLevel().moveLeft();
                    break;
                case KeyEvent.VK_RIGHT :

                    //        System.out.println("Key Right Pressed");
                    spritePane.getMazeLevel().moveRight();
                    break;
                default :

                    }

            if (spritePane.getMazeLevel().isLevelCompleted()) {
                JOptionPane.showMessageDialog(
                    null,
                    "You have successfully conquered this level!",
                    "Congratulations Mr. RockHopper",
                    JOptionPane.INFORMATION_MESSAGE);

                String level = spritePane.getMazeLevel().getNextLevelFile();
                System.out.println("Going to next level: " + level);
                mazeLevels.setNextLevel(level);
                mazeLevels.goNextLevel();
                program.reset();
                this.reset();
            }

        }
    } // end keyPressed

    /**
     * @param keyData The event.
     */
    public void keyReleased(KeyEvent keyData) {
        //        System.out.println("Key Released");
    } // end keyReleased

    /**
     * @param keyData The event.
     */
    public void keyTyped(KeyEvent keyData) {
        //        System.out.println("Key Typed");
    } // end keyTyped

    /**
     * @see java.awt.event.WindowListener#windowActivated(WindowEvent)
     */
    public void windowActivated(WindowEvent e) {
    } //end windowActivated

    /**
     *
     * @see java.awt.event.WindowListener#windowClosed(WindowEvent)
     */
    public void windowClosed(WindowEvent e) {
        System.exit(0);
    } //end windowClosed

    /**
     *
     * @see java.awt.event.WindowListener#windowClosing(WindowEvent)
     */
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    } //end windowClosing

    /**
     *
     * @see java.awt.event.WindowListener#windowDeactivated(WindowEvent)
     */
    public void windowDeactivated(WindowEvent e) {
    } //end windowDeactivated

    /**
     *
     * @see java.awt.event.WindowListener#windowDeiconified(WindowEvent)
     */
    public void windowDeiconified(WindowEvent e) {
    } //end windowDeiconified

    /**
     *
     * @see java.awt.event.WindowListener#windowIconified(WindowEvent)
     */
    public void windowIconified(WindowEvent e) {
    } //emd windowInonified

    /**
     *
     * @see java.awt.event.WindowListener#windowOpened(WindowEvent)
     */
    public void windowOpened(WindowEvent e) {
    } //end windowsOpened

    /**
     * If Rockford dies display a killed message panel
     */
    public void killedRockford() {
        if (killedAlready == false) {
            JOptionPane.showMessageDialog(
                null,
                "Rockford was killed!",
                "You have died",
                JOptionPane.INFORMATION_MESSAGE);
            this.keyLock = true;
            this.killedAlready = true;
            program.getTimerPanel().stop();
        }
    }

    /**
     * If time kill message panel
     */
    public void timekillRockford() {
        if (killedAlready == false) {
            JOptionPane.showMessageDialog(
                null,
                "You did not complete the maze on time.\nThis "
                    + "means you have died!",
                "Time expired",
                JOptionPane.INFORMATION_MESSAGE);
            this.keyLock = true;
            this.killedAlready = true;
            program.getTimerPanel().stop();
        }
    }

    /**
     * Resets the status of the listener
     */
    public void reset() {
        this.keyLock = false;
        this.killedAlready = false;
    }

}
