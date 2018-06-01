/*
 * Maze.java
 * RockHopper Settings Manager
 * with XML Saving and Loading Support
 * Uses the XMLEncoder and XMLDecoder
 * functions of Java 1.4.
 *
 *  The file format is as follows:
 *  Space seperated columns, one row per line.
 *
 *  - Outer wall
 *  = Brick
 *  D Dirt
 *  J Jem (The Diamond)
 *  B Boulder
 *  R Rockford
 *  * Empty Square
 *  X Enemy
 *  E Exit
 *
 */

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * <p>Parses a XML file and generates a maze</p>
 * @author Ronald Ning and Frank Rietta
 * @version 1.0
 */
public class Maze {

    /**
     * For simplicity of loading levels from
     * the XML settings files, a map is stored
     * as a block of text inside a string.  This
     * string will hold that information.  The
     * initMap function takes the info from this
     * variable and attempts to translate it into
     * the dataArray array.
     */
    private String mapLayout;

    /**
     * The gameboard, maze, is stored in memory
     * as a 2d array of chars.
     */
    private char dataArray[][];

    /**
     * Maze length
     */
    private int mazeLength;

    /**
     * Maze current time
     */
    private int currentTime;

    /**
     * Maze width
     */
    private int mazeWidth;

    /**
     * is RockFord dead
     */
    private boolean rockfordDead;

    /**
     * Has the level been completed.
     */
    private boolean levelCompleted;

    /**
     * Rockford's location is accessed frequently,
     * so it is a good idea to remember where he
     * is.
     */
    private int rockfordX;

    /** Rockford's Y location */
    private int rockfordY;

    /**
     * Rockford's last dig location is protected!
     */
    private int digX;

    /** Protected Y */
    private int digY;

    /**
     * Exit X coordinate
     */
    private int exitX;

    /**
     * Exit y
     */
    private int exitY;

    /**
     * When rockford collects jems, the count
     * increases.
     */
    private int jemsCollected;

    /**
     * A certain percentage of jems on the map,
     * must be collected before the exit is shown.
     */
    private int jemsOnMap;

    /**
     * Jews required to pass the level
     */
    private int jewsRequireToExit;

    /**
     * The Enemies
     */
    private Vector theEnemies;

    /**
     * The last level (according to settings file)
     */
    private String previousLevelFile;

    /**
     * The next level (according to settings file)
     */
    private String nextLevelFile;

    /**
     * Create a new, blank map.
     */
    public Maze() {
        int i, j;

        this.mazeWidth = 400;
        this.mazeLength = 400;

        /*
         * Fill the default map with outside walls.
         * This gives the UI something to draw,
         * while not allowing the user to play.
         */
        this.dataArray = new char[20][20];
        for (i = 0; i < this.dataArray.length; i++) {
            for (j = 0; j < this.dataArray[0].length; j++) {
                this.dataArray[i][j] = '-';
            }
        }

        /*
         * Rockford is not on the map
         */
        rockfordX = -1;
        rockfordY = -1;

        /*
         * No Jems
         */
        jemsCollected = 0;
        jemsOnMap = 0;

        this.mapLayout = "";
        this.rockfordDead = false;

        previousLevelFile = "";
        nextLevelFile = "";

        this.currentTime = 180;
        levelCompleted = false;
    }

    /**
     * Constructor which takes in width and length
     * @param width of maze
     * @param length of maze
     */
    public Maze(int width, int length) {
        this.mazeWidth = width;
        this.mazeLength = length;
        this.dataArray = new char[this.mazeWidth / 20][this.mazeLength / 20];
        this.rockfordDead = false;
        this.currentTime = 180;
        levelCompleted = false;
    }

    /**
     * isRockfordDead()
     * @return boolean
     */
    public boolean isRockfordDead() {
        return this.rockfordDead;
    }

    /**
     * @return True if level completed, false otherwise.
     */
    public boolean isLevelCompleted() {
        return this.levelCompleted;
    }

    /**
     * getMapLayout
     * @return String of mapLayout
     */
    public String getMapLayout() {
        return this.mapLayout;
    }

    /**
     * getMapLayout
     * @param layout of mapLayout
     */
    public void setMapLayout(String layout) {
        this.mapLayout = layout;
    }

    /**
     * Initialize the mpa from the mapLayout,
     * which is loaded from a file.
     */
    public void initMap() {

        rockfordDead = false;
        jemsCollected = 0;
        jemsOnMap = 0;
        levelCompleted = false;
        this.currentTime = 180;
        levelCompleted = false;

        /*
         * Nested string tokenizers will be used, fetch lines
         * from the input text (the rows) and one to split the
         * columns by the spaces.
         */
        int nRow, nCol;
        String rowText, colText;

        theEnemies = new Vector();

        /*
         * Start Parsing the Rows
         */
        StringTokenizer rowTok = new StringTokenizer(this.mapLayout, "\n");
        nRow = -1;
        while (rowTok.hasMoreTokens()) {
            if ((rowText = rowTok.nextToken()) != null
                && rowText.trim().equals("") == false) {
                //                System.out.println("Loading: " + rowText);
                nRow++;

                /*
                 * Parse the Columns
                 */
                StringTokenizer colTok = new StringTokenizer(rowText, " ");
                nCol = -1;
                while (colTok.hasMoreTokens()) {
                    if ((colText = colTok.nextToken()) != null
                        && colText.trim().equals("") == false) {
                        nCol++;

                        // Check the bounds and place the char into the grid
                        if (nRow < this.dataArray.length
                            && nCol < this.dataArray[0].length) {
                            this.dataArray[nRow][nCol] = colText.charAt(0);

                            /**
                             *  Determine if this is a
                             *  tracked token (jem or rockford)
                             */
                            char current = this.dataArray[nRow][nCol];
                            if (current == 'J') {
                                jemsOnMap++;
                            } else if (current == 'R') {
                                rockfordY = nRow;
                                // Set Rockford's X,Y location
                                rockfordX = nCol;
                            } else if (current == 'E') {
                                exitY = nRow;
                                exitX = nCol;
                                dataArray[exitY][exitX] = '-';
                            } else if (current == 'W' || current == 'X') {
                                theEnemies.add(
                                    new Enemy(nCol, nRow, current, this));
                            }

                        } // end bounds check

                    } // end is a token check
                } // end while cols

            }
        } // end while rows

        digX = rockfordX;
        digY = rockfordY;
        jewsRequireToExit = (int) (jemsOnMap * 0.75);

    } // end initMap

    /**
     * The timer will trigger an action event ;-)
     * @return True if something moved.
     */
    public boolean moveAutoSprites() {
        boolean somethingMoved = false;
        for (int i = this.dataArray.length - 1; i >= 0; i--) {
            if (fallBoulders(i)) {
                somethingMoved = true;
            }
        }

        /* exit check */
        if (jemsCollected >= jewsRequireToExit) {
            dataArray[exitY][exitX] = 'E';
        }

        return somethingMoved;
    }

    /**
     * Move the enemies
     */
    public void moveEnemies() {
        int i;
        for (i = 0; i < theEnemies.size(); i++) {
            Object temp = theEnemies.get(i);
            if (temp != null) {
                Enemy evilBug = (Enemy) temp;
                evilBug.move();
            }
        }
    }

    /**
     * Other processes can kill rockford
     */
    public void killRockford() {
        rockfordDead = true;
    }

    /**
     * Go through a row and make all of the boulders and jems
     * fall.  This causes boulders with space below them to fall
     * down one level.
     * @param row The row to process.
     * @return True if something moved.
     */
    public boolean fallBoulders(int row) {
        int j;
        boolean somethingMoved = false;
        /*
         * Make sure the row is inside of the vertical bounds.
         * The bottom row is the farthest things can fall and the
         * zeroth row is always a boulder.
         */
        if (row >= 1 && row < this.dataArray.length - 1) {

            for (j = 0; j < this.dataArray[row].length; j++) {

                if (getAt(j, row) == 'B' || getAt(j, row) == 'J') {

                    // Fall into a blank space
                    char nextSpace = getAt(j, row + 1);
                    if (nextSpace == '*') {
                        setAt(j, row + 1, getAt(j, row));
                        setAt(j, row, '*');

                        somethingMoved = true;

                        // When another boulder is hit, fall left or right
                    } else if (nextSpace == 'R' && row + 1 != digY) {
                        /* rock falls on rockford and kills him */
                        rockfordDead = true;
                        somethingMoved = true;
                    } else if (nextSpace == '=') {
                        /* falls on to a distructable wall */
                        somethingMoved = true;
                    } else if (nextSpace == 'B' || nextSpace == 'J') {

                        if (getAt(j - 1, row + 1) == '*'
                            && getAt(j - 1, row) == '*') {
                            setAt(j - 1, row, getAt(j, row)); // Roll left
                            setAt(j, row, '*');

                            somethingMoved = true;

                        } else if (
                            getAt(j + 1, row + 1) == '*'
                                && getAt(j + 1, row) == '*') {
                            setAt(j + 1, row, getAt(j, row)); // Roll right
                            setAt(j, row, '*');

                            somethingMoved = true;
                        }

                    }
                } // end if boulder or jem

            } // end for

        } // end bounds check

        return somethingMoved;
    } // end fallBoulders

    /**
     * A Boulder and a Jem is a rock
     * @param piece the char to check
     * @return boolean of rock or not
     */
    private boolean isRock(char piece) {
        if (piece == 'B' || piece == 'J') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Move rockford to a specified point
     * @param x move RockFord to x
     * @param y move RockFord to y
     */
    private void moveRockford(int x, int y) {

        char jumpLocation = getAt(x, y);

        if (jumpLocation == 'J') {
            jemsCollected++;
        } else if (jumpLocation == 'E') {
            levelCompleted = true;
        }

        setAt(rockfordX, rockfordY, '*');
        setAt(x, y, 'R');
        rockfordX = x;
        rockfordY = y;
        digX = -1;
        digY = -1;

        //        fallBoulders(this.dataArray.length);
    }

    /**
     * Move Rockford up
     */
    public void moveUp() {

        char nextTile = getAt(rockfordX, rockfordY - 1);
        if (nextTile == '*' || nextTile == 'E') {
            moveRockford(rockfordX, rockfordY - 1);
        } else if (nextTile == 'D' || nextTile == 'J') {
            moveRockford(rockfordX, rockfordY - 1);
            if (getAt(rockfordX, rockfordY - 1) != '*') {
                digX = rockfordX;
                digY = rockfordY;
            }
        }
    }

    /**
     * Move Rockford down
     */
    public void moveDown() {
        char nextTile = getAt(rockfordX, rockfordY + 1);
        if (nextTile == '*' || nextTile == 'E') {
            moveRockford(rockfordX, rockfordY + 1);
        } else if (nextTile == 'D' || nextTile == 'J') {
            moveRockford(rockfordX, rockfordY + 1);
            if (getAt(rockfordX, rockfordY - 1) != '*') {
                digX = rockfordX;
                digY = rockfordY;
            }
        }
    }

    /**
     * Move Rockford left
     */
    public void moveLeft() {

        char nextTile = getAt(rockfordX - 1, rockfordY);
        if (nextTile == '*' || nextTile == 'E') {
            moveRockford(rockfordX - 1, rockfordY);
        } else if (nextTile == 'D' || nextTile == 'J') {
            moveRockford(rockfordX - 1, rockfordY);
            digX = rockfordX;
            digY = rockfordY;

            // If the next sprite is a boulder that can be moved
        } else if (nextTile == 'B' && getAt(rockfordX - 2, rockfordY) == '*') {

            setAt(rockfordX - 2, rockfordY, 'B');
            moveRockford(rockfordX - 1, rockfordY);

        }
    }

    /**
     * Move Rockford right
     */
    public void moveRight() {
        char nextTile = getAt(rockfordX + 1, rockfordY);
        if (nextTile == '*' || nextTile == 'E') {
            moveRockford(rockfordX + 1, rockfordY);
        } else if (nextTile == 'D' || nextTile == 'J') {
            moveRockford(rockfordX + 1, rockfordY);
            digX = rockfordX;
            digY = rockfordY;

            // If the next sprite is a boulder that can be moved
        } else if (nextTile == 'B' && getAt(rockfordX + 2, rockfordY) == '*') {

            setAt(rockfordX + 2, rockfordY, 'B');
            moveRockford(rockfordX + 1, rockfordY);

        }

    }

    /**
     * Gets the Maze dataArray
     * @return char[][]
     */
    public char[][] getdataArray() {
        return this.dataArray;
    }

    /**
     * Gets the mazeLength
     * @return int
     */
    public int getmazeLength() {
        return this.mazeLength;
    }

    /**
     * Gets the mazeWidth
     * @return int
     */
    public int getmazeWidth() {
        return this.mazeWidth;
    }

    /**
     * Rockford's location is tracked for efficiency.
     * @return Rockford's X location.
     */
    public int getRockfordX() {
        return this.rockfordX;
    }

    /**
     * Rockford's location is tracked for efficiency.
     * @return Rockford's Y location.
     */
    public int getRockfordY() {
        return this.rockfordY;
    }

    /**
     * Jems Originally on the Map when Loaded
     * @return The total number of jems.
     */
    public int getJemsOnMap() {
        return this.jemsOnMap;
    }

    /**
     * Jems Collected by Rockford on his
     * escapades.
     * @return Number of jems collected.
     */
    public int getJemsCollected() {
        return this.jemsCollected;
    }

    /**
     * Return the board char at the coordinate.
     * @param x Column
     * @param y Row
     * @return The location sprite.
     */
    public char getAt(int x, int y) {
        if (y >= 0
            && y < this.dataArray.length
            && x >= 0
            && x < this.dataArray[y].length) {

            return this.dataArray[y][x];
        } else {
            return ' ';
        }

    }

    /**
     * Assign a sprite at a location
     * @param x Column
     * @param y Row
     * @param spritedataArray The data.
     */
    public void setAt(int x, int y, char spritedataArray) {
        if (y >= 0
            && y < this.dataArray.length
            && x >= 0
            && x < this.dataArray[y].length) {

            this.dataArray[y][x] = spritedataArray;

        }
    }

    /**
     * Sets the data.
     * @param data The data to set
     */
    public void setdataArray(char[][] data) {
        this.dataArray = data;
    }

    /**
     * Sets the length.
     * @param length The length to set
     */
    public void setmazeLength(int length) {
        this.mazeLength = length;
    }

    /**
     * Sets the width.
     * @param width The width to set
     */
    public void setmazeWidth(int width) {
        this.mazeWidth = width;
    }

    /**
     * Retrieve the next level file, which is specified
     * by the currently loaded XML config.
     * @return File name
     */
    public String getNextLevelFile() {
        return this.nextLevelFile;
    }

    /**
     * Retrieve the previous level file, which is specified
     * by the currently loaded XML config.
     * @return File name
     */
    public String getPreviousLevelFile() {
        return this.previousLevelFile;
    }

    /**
     * Prints the maze
     */
    public void printMaze() {

        /* rows */
        for (int i = 0; i < this.dataArray.length; i++) {
            /* columns */
            for (int j = 0; j < this.dataArray[i].length; j++) {
                System.out.print(this.dataArray[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * Save the map to an XML file.
     * @param outputFile Filename to save as (and overwrite).
     */
    public void saveSettings(String outputFile) {
        Hashtable outdataArray = new Hashtable();
        outdataArray.put("mazeLength", new Integer(this.mazeLength));
        outdataArray.put("mazeWidth", new Integer(this.mazeLength));
        outdataArray.put("MapLayout", this.mapLayout);

        try {

            XMLEncoder e =
                new XMLEncoder(
                    new BufferedOutputStream(new FileOutputStream(outputFile)));
            e.writeObject(outdataArray);
            e.close();

        } catch (Exception errorInfo) {
            System.out.println("Error writting info: " + errorInfo);
        }
    } // end saveSettings

    /**
     * Load the settings from an XML file.
     * @param inputFile The filename to load.
     * @return True on success, False on failure.
     */
    public boolean loadSettings(String inputFile) {

        try {

            XMLDecoder d =
                new XMLDecoder(
                    new BufferedInputStream(new FileInputStream(inputFile)));
            Object result = d.readObject();
            d.close();

            if (result != null) {
                Hashtable configInfo = (Hashtable) result;

                Integer nmazeLength = (Integer) configInfo.get("mazeLength");
                if (nmazeLength != null) {
                    this.mazeLength = nmazeLength.intValue();
                }

                Integer nmazeWidth = (Integer) configInfo.get("mazeWidth");
                if (nmazeWidth != null) {
                    this.mazeWidth = nmazeWidth.intValue();
                }

                this.previousLevelFile =
                    (String) configInfo.get("Previous Level");
                this.nextLevelFile = (String) configInfo.get("Next Level");
                this.mapLayout = (String) configInfo.get("MapLayout");

                initMap();

                return true;
            } // end if

        } catch (Exception errorInfo) {
            System.err.println(
                "Error reading settings file " + inputFile + ": " + errorInfo);
        }

        return false;
    } // end loadSettings

    /**
     * getCurrentTime
     * @return int of current time
     */
    public int getCurrentTime() {
        return this.currentTime;
    }

    /**
     * setCurrentTime
     * @param time of current time
     */
    public void setCurrentTime(int time) {
        this.currentTime = time;
    }

    /**
     * return the maze requirement to exit
     * @return int of required to exit
     */
    public int getJewsRequireToExit() {
        return this.jewsRequireToExit;
    }

    /**
     * Main method
     * @param argv Command line paramaters
     */
    public static void main(String argv[]) {
        Maze maze1 = new Maze();

        if (argv.length >= 1) {
            maze1.loadSettings(argv[0]);
            maze1.initMap();
            maze1.printMaze();
        }
    }

}
