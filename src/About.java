import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
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
public class About extends JFrame {

    /**
     * JFrame to which everything is rendered
     */
    private JFrame guiFrame;

    /**
     * JOptionPlane
     */
    private JProgressBar temp;

    /**
     * Content Pane of the JFrame
     */
    private Container contentPane;

    /**
     * The panel to which all the graphics will be drawn
     */
    private JPanel graphicsPanel;

    /**
     * message of marquee
     */
    private String msg =
            "RockHopper created by Ronald Ning and Frank Rietta for CS2335.";

    /**
     * font type
     */
    private Font mfont = new Font("Arial", Font.BOLD, 12);

    /**
     * X Start positioin
     */
    private int xPosition = 200;

    /**
     * Moves text to the left
     */
    private boolean moveLeft = true;

    /**
     * Create the window and sets it up for drawing
     */
    public About() {
        temp = new JProgressBar();
        temp.setIndeterminate(true);
        guiFrame = new JFrame("About RockRunner");
        guiFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                setVisible(false);
                dispose();
            }
        });
        contentPane = guiFrame.getContentPane();
        graphicsPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraphics(g);
            }
        };
        contentPane.add(graphicsPanel);
        guiFrame.setSize(200, 115);
        guiFrame.setVisible(true);
        graphicsPanel.setBackground(Color.lightGray);

        this.populatePanels();
        this.animateGraphics();
    }

    /**
     * Draws the help menu
     */
    private void populatePanels() {
        Container content = guiFrame.getContentPane();

        JPanel topText = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(graphicsPanel, BorderLayout.CENTER);
        content.add(temp, BorderLayout.NORTH);
    }

    /**
     * Paint the graphics on graphic g
     * @param g the graphic to paint to
     */
    public void drawGraphics(Graphics g) {
        temp.setVisible(true);

        g.setColor(Color.BLACK);
        g.setFont(mfont);
        g.drawString(msg, xPosition, 30);

        /* direction movement */
        if (moveLeft) {
            xPosition--;
        } else {
            xPosition++;
        }

        /* hits end so change direction */
        if (xPosition < -350) {
            moveLeft = false;
        } else if (xPosition > 200) {
            moveLeft = true;
        }

    }

    /**
     * Animates the square
     */
    public void animateGraphics() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphicsPanel.repaint();
            }
        };

        javax.swing.Timer animation = new javax.swing.Timer(50, listener);
        animation.start();
    }

    /**
     * Creates a new SimpleAnimation and begins the animation
     * @param args Command Line Arguments
     */
    public static void main(String[] args) {
        About test = new About();
    }

}
