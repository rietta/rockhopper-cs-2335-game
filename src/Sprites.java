import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 * <p>Gold sprite</p>
 * @author not attributable
 * @version 1.0
 */
public class Sprites {

    /**
     * @return The height, in pixils, of the sprite tile.
     */
    public int getHeight() {
        return 20;
    }

    /**
     * @return The width, in pixils, of the sprite tile.
     */
    public int getLength() {
        return 20;
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawBoulderSprite(Graphics2D g, int x, int y) {

        /* draw the background */
        g.setColor(Color.BLACK);
        g.fill3DRect(x, y, 20, 20, false);

        /* draws the circle */
        g.setColor(new Color(96, 57, 19));
        g.fillOval(x, y, 20, 20);

        /* draws the circle */
        g.setColor(new Color(117, 76, 36));
        g.fillOval(x, y, 18, 18);

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawEmptySprite(Graphics2D g, int x, int y) {
        /* Draw the background */
        g.setColor(Color.BLACK);
        g.fill3DRect(x, y, 20, 20, false);

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawGoldSprite(Graphics2D g, int x, int y) {

        g.setColor(Color.BLACK);
        g.fill3DRect(x, y, 20, 20, false);

        int xCord[] = {
                      x + 10, x, x + 10, x + 20};
        int yCord[] = {
                      y + 4, y + 14, y + 16, y + 14};
        Polygon diamond = new Polygon(xCord, yCord, 4);

        /* draws the background */
        g.setPaint(
                new GradientPaint(
                x + 10,
                y,
                Color.YELLOW,
                x + 5,
                y + 15,
                new Color(130, 123, 0)));
        g.fillPolygon(diamond);

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawInsideWallSprite(Graphics2D g, int x, int y) {

        g.setColor(new Color(225, 217, 230));
        g.fill3DRect(x, y, 20, 20, true);

        /* draws lines for bricks */
        g.setColor(new Color(128, 128, 128));
        g.drawLine(x, y + 0, x + 20, y + 0);
        g.drawLine(x + 10, y + 0, x + 10, y + 7);
        g.drawLine(x, y + 7, x + 20, y + 7);
        g.drawLine(x + 5, y + 7, x + 5, y + 14);
        g.drawLine(x + 15, y + 7, x + 15, y + 14);
        g.drawLine(x, y + 14, x + 20, y + 14);
        g.drawLine(x + 10, y + 14, x + 10, y + 20);
        g.drawLine(x, y + 20, x + 20, y + 20);

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawOutlineWallSprite(Graphics2D g, int x, int y) {
        // draws the background
        Color lightGrey = new Color(173, 173, 173);
        g.setColor(lightGrey);
        g.fill3DRect(x, y, 20, 20, true);

        // draws the pixels
        Color darkGrey = new Color(146, 147, 149);
        g.setColor(darkGrey);
        g.drawRect(x + 4, y + 4, 1, 1);
        g.drawRect(x + 14, y + 4, 1, 1);
        g.drawRect(x + 4, y + 13, 1, 1);
        g.drawRect(x + 14, y + 13, 1, 1);

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawExitSprite(Graphics2D g, int x, int y) {
        // draws the background
        g.setColor(Color.BLACK);
        g.fill3DRect(x, y, 20, 20, false);

        // draws the pixels
        g.setColor(Color.WHITE);
        g.fillOval(x + 5, y + 0, 10, 20);
        g.fill3DRect(x + 5, y + 10, 10, 10, false);

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawDirtSprite(Graphics2D g, int x, int y) {

        g.setColor(new Color(166, 124, 82));
        g.fill3DRect(x, y, 20, 20, true);

        /* complex dirt slows the frame rate down
                for (int i = 0; i < 20; i = i + 5) {
                    for (int j = 0; j < 20; j = j + 5) {
                        drawDirtHelper(g, x + i, y + j);
                    }
                }
         */
        // draws the background
        g.setColor(Color.BLACK);
        g.drawLine(x, y, x, y + 20);
        g.drawLine(x + 20, y, x + 20, y + 20);
        g.drawLine(x, y + 20, x + 20, y + 20);
        g.drawLine(x, y, x + 20, y);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    private void drawDirtHelper(Graphics2D g, int x, int y) {
        g.setColor(new Color(166, 124, 82));
        g.drawRect(x + 3, y + 0, 1, 1);
        g.drawRect(x + 4, y + 1, 1, 1);
        g.drawRect(x + 0, y + 3, 1, 1);
        g.drawRect(x + 0, y + 5, 1, 1);
        g.drawRect(x + 3, y + 4, 1, 1);

        g.setColor(new Color(48, 60, 0));
        g.drawRect(x + 5, y + 5, 1, 1);
        g.drawRect(x + 3, y + 2, 1, 1);
        g.drawRect(x + 5, y + 0, 1, 1);

        g.setColor(new Color(48, 90, 0));
        g.drawRect(x + 3, y + 3, 1, 1);
        g.drawRect(x + 5, y + 1, 1, 1);
        g.drawRect(x + 1, y + 5, 1, 1);

        g.setColor(new Color(96, 57, 19));
        g.drawRect(x + 0, y + 2, 1, 1);
        g.drawRect(x + 4, y + 3, 1, 1);

        g.setColor(new Color(51, 0, 0));
        g.drawRect(x + 1, y + 5, 1, 1);
        g.drawRect(x + 2, y + 2, 1, 1);
        g.drawRect(x + 2, y + 4, 1, 1);
        g.drawRect(x + 4, y + 1, 1, 1);
        g.drawRect(x + 5, y + 2, 1, 1);

        g.setColor(new Color(102, 51, 0));
        g.drawRect(x + 2, y + 0, 1, 1);
        g.drawRect(x + 3, y + 5, 1, 1);
        g.drawRect(x + 2, y + 3, 1, 1);
        g.drawRect(x + 0, y + 4, 1, 1);
        g.drawRect(x + 5, y + 3, 1, 1);

        g.setColor(new Color(102, 102, 51));
        g.drawRect(x + 5, y + 4, 1, 1);
        g.drawRect(x + 2, y + 5, 1, 1);
        g.drawRect(x + 0, y + 0, 1, 1);
        g.drawRect(x + 4, y + 4, 1, 1);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawHeroSprite(Graphics2D g, int x, int y) {
        // draws the background
        g.setColor(Color.BLACK);
        g.fill3DRect(x, y, 20, 20, false);

        /* head */
        g.setColor(Color.PINK);
        g.fillOval(x + 4, y + 2, 9, 9);

        /* body */
        g.drawLine(x + 8, y + 10, x + 8, y + 15);

        /* arms */
        g.drawLine(x + 8, y + 13, x + 6, y + 11);
        g.drawLine(x + 8, y + 13, x + 10, y + 11);

        /* legs */
        g.drawLine(x + 8, y + 15, x + 5, y + 17);
        g.drawLine(x + 8, y + 15, x + 11, y + 17);

        /* eyes */
        g.setColor(Color.WHITE);
        g.fillOval(x + 6, y + 5, 2, 2);
        g.fillOval(x + 9, y + 5, 2, 2);

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawEnemyFireFlySprite(Graphics2D g, int x, int y) {
        // draws the background
        g.setColor(Color.BLACK);
        g.fill3DRect(x, y, 20, 20, false);

        /* head */
        g.setColor(Color.WHITE);

        /* body */
        g.fill3DRect(x + 7, y + 7, 3, 8, false);

        /* 6 legs */
        g.drawLine(x + 8, y + 11, x + 4, y + 9);
        g.drawLine(x + 8, y + 11, x + 12, y + 9);

        g.drawLine(x + 8, y + 12, x + 4, y + 12);
        g.drawLine(x + 8, y + 12, x + 12, y + 12);

        g.drawLine(x + 8, y + 13, x + 4, y + 15);
        g.drawLine(x + 8, y + 13, x + 12, y + 15);

        /* eyes */
        g.setColor(Color.RED);
        g.fillOval(x + 4, y + 4, 5, 5);
        g.fillOval(x + 9, y + 4, 5, 5);

        g.setColor(Color.WHITE);
        g.fillOval(x + 6, y + 5, 2, 2);
        g.fillOval(x + 10, y + 5, 2, 2);

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Method that draws the Graphics2D onto the graphicsPanel
     * @param g graphics g object
     * @param x the x cordinates of the square
     * @param y the y cordinates of the square
     */
    public void drawEnemyButterFlySprite(Graphics2D g, int x, int y) {
        // draws the background
        g.setColor(Color.BLACK);
        g.fill3DRect(x, y, 20, 20, false);

        /* head */
        g.setColor(Color.WHITE);

        /* body */
        g.fill3DRect(x + 7, y + 7, 3, 8, false);

        /* 4 wings legs */
        g.fillOval(x, y + 10, 8, 3);
        g.fillOval(x + 9, y + 10, 8, 3);

        /* eyes */
        g.setColor(Color.RED);
        g.fillOval(x + 4, y + 4, 5, 5);
        g.fillOval(x + 9, y + 4, 5, 5);

        g.setColor(Color.WHITE);
        g.fillOval(x + 6, y + 5, 2, 2);
        g.fillOval(x + 10, y + 5, 2, 2);

        g.setColor(Color.BLACK);
        g.drawLine(x, y + 20, x + 20, y + 20);
    }

    /**
     * Sprite Printer
     * @param g the graphics to print onto
     * @param spriteType the character representing the sprite
     * @param x the x cordinates of the sprite
     * @param y the y cordinates of the sprite
     */
    public void spritePrinter(Graphics2D g, char spriteType, int x, int y) {

        switch (spriteType) {
        case '-':
            this.drawOutlineWallSprite(g, x, y);
            break;

        case 'R':
            this.drawHeroSprite(g, x, y);
            break;

        case 'D':
            this.drawDirtSprite(g, x, y);
            break;

        case '=':
            this.drawInsideWallSprite(g, x, y);
            break;

        case 'B':
            this.drawBoulderSprite(g, x, y);
            break;

        case 'J':
            this.drawGoldSprite(g, x, y);
            break;

        case '*':
            this.drawEmptySprite(g, x, y);
            break;

        case 'E':
            this.drawExitSprite(g, x, y);
            break;

        case 'X':
            this.drawEnemyFireFlySprite(g, x, y);
            break;

        case 'W':
            this.drawEnemyButterFlySprite(g, x, y);
            break;

        default:
            System.out.println("Invalid sprite type encountered");

        }
    }

    /**
     * Print an array of sprites
     * @param g the graphic to print to
     * @param maze the maze to print onto g
     */
    public void drawMaze(Graphics g, char maze[][]) {
        Graphics2D g2 = (Graphics2D) g;
        /* rows */
        for (int i = 0; i < maze.length; i++) {
            /* columns */
            for (int j = 0; j < maze[i].length; j++) {
                this.spritePrinter(g2, maze[i][j], j * 20, i * 20);
            } //end columns
        } //end rows
    }
}
/* end of drawing sprite objects */
