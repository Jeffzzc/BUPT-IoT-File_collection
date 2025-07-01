package management.util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
/**
 * This is custom circle button class implements {@code JButton}
 * 
 * @author Xiaotian Wang
 *
 */
public class CircleButton extends JButton {
	
	
	  Shape shape;
	
	/**
	 * Initialize the circle button
	 * 
	 * @param label the lable need to be initialized
	 */
    public CircleButton(String label) {
        super(label);
         
        Dimension size = getPreferredSize();
        size.width = size.height =100;
        setPreferredSize(size);
       
        setContentAreaFilled(false);
        this.setBorderPainted(false); 
        this.setFocusPainted(false); 
    }
 
    
    protected void paintComponent(Graphics g) {
 
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray); 
        } else {
            g.setColor(getBackground());
        }

        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
    }
 
  
    protected void paintBorder(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
 

    public boolean contains(int x, int y) {
 
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
 
 
}
