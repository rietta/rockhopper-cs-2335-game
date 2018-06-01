/**
 * Simple Enemy Data
 * @author rietta
 * @version 1.0
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code Template
 */
public class Enemy {

    /***/
    private char typeID;
    /***/
    private int lastX;
    /***/
    private int lastY;
    /***/
    private int currentX;
    /***/
    private int currentY;
    /***/
    private boolean moveLeft;
    /***/
    private boolean moveRight;
    /***/
    private boolean moveUp;
    /***/
    private boolean moveDown;

    /***/
    private boolean wasVertical;

    /** Maze to Manipulate */
    private Maze mazeLevel;

    /**
     * Create a new enemy
     * @param x Col
     * @param y Row
     * @param typeEnemy The enemy
     * @param mazeLevel The maze
     */
    public Enemy(int x, int y, char typeEnemy, Maze mazeLevel) {

        lastX = x;
        lastY = y;
        currentX = x;
        currentY = y;

        wasVertical = false;

        if (Math.random() > 0.5) {
            moveLeft = false;
            moveRight = true;
        } else {
            moveLeft = true;
            moveRight = false;
        }

        if (Math.random() > 0.75) {
            moveUp = false;
            moveDown = true;
        } else {
            moveUp = true;
            moveDown = false;
        }

        typeID = typeEnemy;
        this.mazeLevel = mazeLevel;
    }

    /**
     * @param x X
     * @param y Y
     */
    private void moveTo(int x, int y) {
        mazeLevel.setAt(currentX, currentY, '*');

        lastX = currentX;
        lastY = currentY;
        currentX = x;
        currentY = y;

        mazeLevel.setAt(currentX, currentY, typeID);
    }

    /**
     * @param x X
     * @param y Y
     * @return If the square is empty.
     */
    private boolean isEmpty(int x, int y) {
        if (mazeLevel.getAt(x, y) == '*') {
            return true;
        } else if (mazeLevel.getAt(x, y) == 'R') {
            mazeLevel.killRockford();
            return true;
        } else {
            return false;
        }
    }

//    private void changeDirection() {
//
//        moveUp = false;
//        moveDown = false;
//        moveLeft = false;
//        moveRight = false;
//
//        /*
//         * When last movement was vertical,
//         * favor horizontal
//         */
//
//        if (wasVertical) {
//
//            if (isEmpty(currentX + 1, currentY)) {
//                moveLeft = true;
//                // can i move right?
//            } else if (isEmpty(currentX - 1, currentY)) {
//                moveRight = true;
//            } else {
//                moveRight = true;
//                moveUp = true;
//            }
//
//            wasVertical = false;
//
//        } else {
//
//            if (isEmpty(currentX, currentY - 1)) {
//                moveUp = true;
//            } else if (isEmpty(currentX, currentY + 1)) {
//                moveDown = true;
//            } else {
//                moveDown = true;
//                moveLeft = true;
//            }
//
//            wasVertical = true;
//        } // end vertical favor check
//
//    }

    /**
     * @return Bug's X location
     */
    public int getCurrentX() {
        return this.currentX;
    }

    /**
     * @return Bug's Y location
     */
    public int getCurrentY() {
        return this.currentY;
    }

    /**
     * Animate the Movement
     */
    public void move() {

        if (moveUp) {

            if (isEmpty(currentX, currentY - 1)) {
                moveTo(currentX, currentY - 1);
            } else {

                //                changeDirection();
                moveUp = false;
                moveDown = true;
            }

        } else if (moveDown) {

            if (isEmpty(currentX, currentY + 1)) {
                moveTo(currentX, currentY + 1);
            } else {

                //                changeDirection();
                moveUp = true;
                moveDown = false;
            }

        } // end move up

        if (moveLeft) {

            if (isEmpty(currentX - 1, currentY)) {

                moveTo(currentX - 1, currentY);
            } else {

                //                changeDirection();
                moveLeft = false;
                moveRight = true;
            }

        } else if (moveRight) {

            if (isEmpty(currentX + 1, currentY)) {
                moveTo(currentX + 1, currentY);
            } else {

                //                changeDirection();
                moveLeft = true;
                moveRight = false;
            }

        } // end move right
    }

}