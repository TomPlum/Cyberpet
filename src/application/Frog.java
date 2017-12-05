package application;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**-------------------------------------------------------------
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 21/02/17
 * @description This class is responsible for the Frog Objects.
 * ------------------------------------------------------------*/

public class Frog extends Predator implements Runnable {
	/**Fly Field - Belonging to a Frog Object*/
	private Fly fly;
	
	/**
	 * Default Constructor - Creates a new Pet object with null fields
	 */
	public Frog() {}
	
	/**
	 * Parameterised Constructor - Creates a new Pet object with the specified name and hungry status
	 * @param name
	 * @param hungry
	 */
	public Frog(String name, boolean hungry) {
		setName(name);
		fed = false;
		nameLabel = new JLabel(name);
		nameLabel.setFont(labelFont);
		setNameLabel(nameLabel);
		setHungry(hungry);
		setInverted();
		generateCoordinates();
		if (inverted == false) {
			image = new ImageIcon(getClass().getResource("/images/frog-110x120.png"));
		} else if (inverted == true){
			image = new ImageIcon(getClass().getResource("/images/frog-110x120-inverted.png"));
		}
		jLabel = new JLabel(image);
	}
	
	/**
	 * Overrides random movement and causes the Frog sprite to start moving to it's fly in the parallel thread
	 */
	public void catchFly() {
		setDx(15);
		setDy(15);
		distance = (int) Math.sqrt(Math.pow((x - fly.getX()), 2) + Math.pow((y - fly.getY()), 2));
		if (x > fly.getjLabel().getX()) {
			setX(x - dx);
		} else {
			setX(x + dx);
		}
		
		if (y > fly.getjLabel().getY()) {
			setY(y - dy);
		} else {
			setY(y + dy);
		}
		
		if (distance < 100) {
			drawTongue();
		}
		
		jLabel.setLocation(x, y);
		nameLabel.setLocation(x + 110, y + 40);
	}
	
	/**
	 * Sets the Frog's Fly in which it should catch
	 * @param fly
	 */
	public void setFly(Fly fly) {
		this.fly = fly;
	}
	
	/**
	 * Draws a pink line representing a frogs tongue from it's current position to the position of it's fly.
	 */
	public void drawTongue() {
		Graphics2D g2d = (Graphics2D) getTongue().create();
		g2d.setColor(Color.PINK);
		g2d.setStroke(new BasicStroke(4));
		g2d.drawLine(x + 55, y + 55, fly.getX() + 25, fly.getY() + 22);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getStoredMovementSpacePanel().remove(fly.getjLabel());
		getStoredMovementSpacePanel().remove(fly.getPreyIdLabel());
		getStoredMovementSpacePanel().validate();
		getStoredMovementSpacePanel().repaint();
		storedConsole.append("\n" + name + " is no longer hungry!");
		setHungry(false); //Frog is no longer hungry
		fed = true;
		fly.setPredatorHungry(false);
		run(); //Call run() so the loop notices a change in the boolean
	}

	/**
	 * Overrides run() from the Runnable Interface. Causes the Frog sprites to move randomly
	 * or catch their fly dependent on it's hungry boolean
	 */
	@Override
	public void run() {
		try {
			while(!hungry) {
				Thread.sleep(1000);
				moveRandomly(); 
			}
			while(hungry) {
				Thread.sleep(750);
				catchFly();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add's a Frog to the GUI JPanel and it's JLabels.
	 * Also sets the size, visibility and starting location.
	 */
	public void addPredator() {
		jLabel.setSize(110, 120);
		nameLabel.setSize(50, 15);
		nameLabel.setVisible(false);
		storedMovementSpacePanel.add(jLabel);
		storedMovementSpacePanel.add(nameLabel);
		nameLabel.setLocation(x + 110, y + 40);
		jLabel.setLocation(x, y);	
	}
	
	/**
	 * Add's a Fly to the GUI JPanel and it's JLabels.
	 * Also sets the size, visibility and starting location.
	 */
	public void addPrey() {
		fly.getjLabel().setSize(50, 45);
		fly.getPreyIdLabel().setSize(50, 15);
		fly.getPreyIdLabel().setVisible(false);
		storedMovementSpacePanel.add(fly.getjLabel());
		storedMovementSpacePanel.add(fly.getPreyIdLabel());
		fly.getjLabel().setLocation(fly.getX(), fly.getY());
		fly.getPreyIdLabel().setLocation(fly.getX() + 60, fly.getY() + 20);
	}
	
	/**
	 * Sets the Prey to the passed Object and casts to Fly.
	 */
	public void setPrey(Object prey) {
		fly = (Fly) prey;
	}
	
	/**
	 * Gets the Frog's Fly Object
	 */
	public Object getPrey() {
		return (Fly) fly;
	}
	
	/**
	 * Returns the Preys ID JLabel
	 * @return Prey ID JLabel
	 */
	public JLabel getPreyIdLabel() {
		return fly.getPreyIdLabel();
	}
}
