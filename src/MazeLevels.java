import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser; /**

* CS2335 Lab 6
*
* <PRE>
* RockHopperListener.java
* <PRE>
* The listener events for RockHopper.  Handles all the events
* @author Ronald Ning and Frank Rietta
* @version Version 1.4, February 25, 2003
*/
public class MazeLevels {

   /**
    * stores the framepane
    */
   private SpriteFrame spritePane;

   /**
    * previous level file name
    */
   private String previousLevel;

   /**
    * current level file name
    */
   private String currentLevel;

   /**
    * next level file name
    */
   private String nextLevel;

   /**
    * Default constructor
    * @param sprite the sprites
    */
   public MazeLevels(SpriteFrame sprite) {
       this.spritePane = sprite;
       this.previousLevel = "";
       this.currentLevel = "";
       this.nextLevel = "";
   }

   /**
    * Modify the previous level
    * @param file the file
    */
   public void setPreviousLevel(String file) {
       System.out.println(file);
       this.previousLevel = file;
   }

   /**
    * Modify the current level
    * @param file the file
    */
   public void setCurrentLevel(String file) {
       this.currentLevel = file;
   }

   /**
    * Modify the previous level
    * @param file the file
    */
   public void setNextLevel(String file) {
       System.out.println(file);
       this.nextLevel = file;
   }

   /**
    * Go to the previous level
    */
   public void goPreviousLevel() {
       Maze gameBoard = spritePane.getMazeLevel();
       if (gameBoard != null) {
           gameBoard.loadSettings(this.previousLevel);
           spritePane.setVisible(false);
           spritePane.setVisible(true);
       }
   }

   /**
    * Go to next level
    */
   public void goNextLevel() {
       Maze gameBoard = spritePane.getMazeLevel();
       if (gameBoard != null) {
           gameBoard.loadSettings(this.nextLevel);
           spritePane.setVisible(false);
           spritePane.setVisible(true);
       }
   }

   /**
    * Show a Directory File Chooser
    */
   public void startNewGame() {
       System.out.println("New game started");
       String inputFile = promptForFile();

       if (inputFile != null && inputFile.equals("") == false) {
           Maze gameBoard = spritePane.getMazeLevel();
           if (gameBoard != null) {
               gameBoard.loadSettings(inputFile);
               spritePane.setVisible(false);
               spritePane.setVisible(true);
           } else {
               System.err.println(
                       "The game board is not initialized.");
           }

       } else {
           System.err.println("No file has been chosen.");
       }

   }

   /**
    * Show a Directory File Chooser
    * @return A selected path
    */
   private String promptForFile() {
       JFileChooser chooser = new JFileChooser();
       chooser.setDialogType(JFileChooser.OPEN_DIALOG);
       chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

       try {
           int result = chooser.showDialog(new JDialog(), null);
           if (result == JFileChooser.APPROVE_OPTION) {
               File theFile = chooser.getSelectedFile();
               if (theFile.isFile()) {
                   return theFile.toString();
               }
           }
       } catch (Exception fileError) {
           System.err.println("Error showing file dialog: " + fileError);
       }

       return null;
   }

}