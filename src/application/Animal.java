package application;
import java.awt.Font;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JLabel;

/**---------------------------------------------------------------
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 24/02/17
 * @description This class represents an Animal Component on the
 * 				GUI. It's fields and methods are common between
 * 				all animals. I.e. Frog, Fly, Shark, Fish etc...
 * -------------------------------------------------------------*/

public abstract class Animal {
	/**Protected Fields - Inherited by Animals*/
	protected int x, y, dx, dy;
	protected Icon image;
	protected JLabel jLabel, nameLabel, preyIdLabel;
	protected Font labelFont = new Font("Calibri", Font.PLAIN, 15);
	
	/**
	 * Randomly generates a random x and y ordinate for the starting position
	 */
	public void generateCoordinates() {
		Random rand = new Random();		
		setX(rand.nextInt(641)); //Random starting x-ordinate
		setY(rand.nextInt(481)); //"             " y-ordinate
	}
	
	/**
	 * Sets the x-ordinate to the passed int.
	 * x must be >= 0 and <= 640
	 * @param x x-ordinate
	 */
	public void setX(int x) {
		if (x <= 640 && x >= 0) {
			this.x = x;
		}
	}
	
	/**
	 * Sets the y-ordinate to the passed int.
	 * y must be >= 0 and <= 480
	 * @param y y-ordinate
	 */
	public void setY(int y) {
		if (y <= 480 && y >= 0) {
			this.y = y;
		}
	}
	
	/**
	 * Gets the x-ordinate
	 * @return x-ordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the y-ordinate
	 * @return y-ordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Sets the delta x-ordinate value to the passed int
	 * @param dx delta x-ordinate
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	/**
	 * Sets the delta y-ordinate value to the passed int
	 * @param dy delta y-ordinate
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	/**
	 * Returns the Animals ImageIcon sprite
	 * @return Animal ImageIcon
	 */
	public Icon getImage() {
		return image;
	}
	
	/**
	 * Returns the Animals JLabel Object
	 * @return Animal JLabel
	 */
	public JLabel getjLabel() {
		return jLabel;
	}
	
	/**
	 * Returns the Animals Name JLabel
	 * @return Animal Name JLabel
	 */
	public JLabel getNameLabel() {
		return nameLabel;
	}
	
	/**
	 * Sets the Animals Name JLabel to the passed JLabel Object
	 * @param nameLabel Animal Name JLabel
	 */
	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}
}