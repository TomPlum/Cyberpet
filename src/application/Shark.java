package application;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**-------------------------------------------------------------
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 21/02/17
 * @description This class is responsible for the Shark Objects.
 * ------------------------------------------------------------*/

public class Shark extends Predator implements Runnable {
	/**Fish Field - Belonging to a Shark Object*/
	private Fish fish;
	
	/**
	 * Default Constructor - Instantiates a new Shark Object with null fields
	 */
	public Shark() {}
	
	/**
	 * Parameterised Constructor - Creates a new Shark object with the specified name and hungry status
	 * @param name
	 * @param hungry
	 */
	public Shark(String name, boolean hungry) {
		setName(name);
		fed = false;
		nameLabel = new JLabel(name);
		nameLabel.setFont(labelFont);
		setNameLabel(nameLabel);
		setHungry(hungry);
		setInverted();
		generateCoordinates();
		if (inverted == false) {
			image = new ImageIcon(getClass().getResource("/images/shark-150x85.png"));
		} else if (inverted == true){
			image = new ImageIcon(getClass().getResource("/images/shark-150x85-inverted.png"));
		}
		jLabel = new JLabel(image);
	}
	
	/**
	 * Overrides random movement and causes the Shark sprite to start moving to it's fly in the parallel thread
	 */
	public void catchFish() {
		setDx(15);
		setDy(15);
		distance = (int) Math.sqrt(Math.pow((x - fish.getX()), 2) + Math.pow((y - fish.getY()), 2));
		if (x > fish.getjLabel().getX()) {
			setX(x - dx);
		} else {
			setX(x + dx);
		}
		
		if (y > fish.getjLabel().getY()) {
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
	 * Sets the Shark's Fish in which it should catch
	 * @param fish
	 */
	public void setFish(Fish fish) {
		this.fish = fish;
	}
	
	/**
	 * Draws a pink line representing a frogs tongue from it's current position to the position of it's fly.
	 */
	public void drawTongue() {
		Graphics2D g2d = (Graphics2D) getTongue().create();
		g2d.setColor(Color.PINK);
		g2d.setStroke(new BasicStroke(4));
		if (inverted == true) {
			g2d.drawLine(x + 95, y + 50, fish.getX() + 42, fish.getY() + 25);
		} else {
			g2d.drawLine(x + 55, y + 65, fish.getX() + 25, fish.getY() + 22);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getStoredMovementSpacePanel().remove(fish.getjLabel());
		getStoredMovementSpacePanel().remove(fish.getPreyIdLabel());
		getStoredMovementSpacePanel().validate();
		getStoredMovementSpacePanel().repaint();
		storedConsole.append("\n" + name + " is no longer hungry!");
		hungry = false; //Frog is no longer hungry
		fed = true; // Don't catch prey when user clicks hungry again
		fish.setPredatorHungry(false);
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
				catchFish();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add's a Shark to the GUI JPanel and it's JLabels.
	 * Also sets the size, visibility and starting location.
	 */
	public void addPredator() {
		jLabel.setSize(150, 85);
		nameLabel.setSize(50, 15);
		nameLabel.setVisible(false);
		storedMovementSpacePanel.add(jLabel);
		storedMovementSpacePanel.add(nameLabel);
		nameLabel.setLocation(x + 110, y + 10);
		jLabel.setLocation(x, y);	
	}
	
	/**
	 * Add's a Fish to the GUI JPanel and it's JLabels.
	 * Also sets the size, visibility and starting location.
	 */
	public void addPrey() {
		if (fish.getRotatedAngle() == 90 || fish.getRotatedAngle() == 270) {
			fish.getjLabel().setSize(50, 80);
		} else {
			fish.getjLabel().setSize(80, 50);
		}
		fish.getPreyIdLabel().setSize(50, 15);
		fish.getPreyIdLabel().setVisible(false);
		storedMovementSpacePanel.add(fish.getjLabel());
		storedMovementSpacePanel.add(fish.getNameLabel());
		fish.getjLabel().setLocation(fish.getX(), fish.getY());
		fish.getPreyIdLabel().setLocation(fish.getX() + 60, fish.getY() + 20);
	}
	
	/**
	 * Sets the Sharks prey to the passed Object and casts to Fish
	 */
	public void setPrey(Object prey) {
		fish = (Fish) prey;
	}
	
	/**
	 * Gets the Shark's Prey Fish Object
	 */
	public Object getPrey() {
		return (Fish) fish;
	}
	
	/**
	 * Returns the Preys ID JLabel
	 * @return Prey ID JLabel
	 */
	public JLabel getPreyIdLabel() {
		return fish.getPreyIdLabel();
	}
}
