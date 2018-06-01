import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * <p>Title: Sprites</p>
 * <p>Description: Sprites for BolderDash</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author Ronald Ning and Frank Rietta
 * @version 1.0
 */
public class HowToPlay extends JFrame {

    /**
     * JOptionPlane
     */
    private JProgressBar temp;

    /**
     * Private variable which stores the top panel
     */
    private JPanel topText;

    /**
     * Private variable which stores the top panel
     */
    private JPanel bottomText;

    /**
     * Sprite class
     */
    private Sprites spritesObject;

    /**
     * Default constructor
     */
    public HowToPlay() {
        spritesObject = new Sprites();

        /* each panel */
        topText = new JPanel();
        bottomText = new JPanel() {
            /*  overrides JPanel's default paint component */
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSprites(g);
            }
        };
        topText.setLayout(new GridLayout(4, 1));
        bottomText.setLayout(new GridLayout(10, 1));

        /* close listener */
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                setVisible(false);
                dispose();
            }
        });

        this.populatePanels();
    }

    /**
     * Draws the help menu
     */
    private void populatePanels() {

        Container content = this.getContentPane();

        JLabel jLabel1 = new JLabel("How to play:");
        JLabel jLabel2 = new JLabel("You have to collect the gold.");
        JLabel jLabel3 = new JLabel("Avoid the falling rocks!");
        JLabel jLabel4 = new JLabel();

        temp = new JProgressBar();
        temp.setIndeterminate(true);

        content.setLayout(new BorderLayout());
        this.setTitle("How to play");

        topText.add(jLabel1);
        topText.add(jLabel2);
        topText.add(jLabel3);
        topText.add(temp);

        content.add(topText, BorderLayout.NORTH);
        content.add(bottomText, BorderLayout.CENTER);

        this.setSize(166, 290);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * The Window event listener
     * @param we the window event
     */
    public void windowClosing(WindowEvent we) {
        System.out.println("Closing help box");
        this.setVisible(false);
        this.dispose();
    }

    /**
     * Draws the sprites
     * @param g draws onto the JPanel
     */
    public void drawSprites(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        spritesObject.drawHeroSprite(g2, 140, 0);
        g2.drawString("This is you:", 0, 15);
        spritesObject.drawOutlineWallSprite(g2, 140, 20);
        g2.drawString("The Wall:", 0, 35);
        spritesObject.drawDirtSprite(g2, 140, 40);
        g2.drawString("Dirt you dig through:", 0, 55);
        spritesObject.drawInsideWallSprite(g2, 140, 60);
        g2.drawString("Distructable wall:", 0, 75);
        spritesObject.drawEnemyFireFlySprite(g2, 140, 80);
        g2.drawString("Firefly Enemy:", 0, 95);
        spritesObject.drawEnemyButterFlySprite(g2, 140, 100);
        g2.drawString("Butterfly Enemy", 0, 115);
        spritesObject.drawGoldSprite(g2, 140, 120);
        g2.drawString("Gold to collect", 0, 135);
        spritesObject.drawBoulderSprite(g2, 140, 140);
        g2.drawString("Boulder to avoid", 0, 155);
        spritesObject.drawOutlineWallSprite(g2, 140, 160);
        g2.drawString("Closed Exit", 0, 175);
        spritesObject.drawExitSprite(g2, 140, 180);
        g2.drawString("Opened Exit", 0, 195);
    }

    /**
     * Main method that creates a new BasicGraphics
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        HowToPlay howToPlay = new HowToPlay();
    }
}