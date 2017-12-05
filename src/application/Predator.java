package application;

import presentation.IAnimal;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**---------------------------------------------------------------
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 24/02/17
 * @description This class represents an Animal Component on the
 * 				GUI. It's fields and methods are common between
 * 				all predator's. I.e. Frog, Shark, Cat, Lion etc...
 * -------------------------------------------------------------*/

public abstract class Predator extends Animal implements IAnimal {
	/**Protected Fields - Inherited by Predators*/
	protected String name;
	protected boolean hungry, fed, inverted;
	protected Graphics tongue;
	protected JPanel storedMovementSpacePanel;
	protected int distance;
	protected JTextArea storedConsole;
	
	/**
	 * Randomly generates dx and dy values and updates the coordinates accordingly
	 */
	public void moveRandomly() {
		Random rand = new Random();
		int randomNumberX = rand.nextInt(30) - 15; // -15 <= randomNumber >= 15
		int randomNumberY = rand.nextInt(30) - 15;
		setDx(randomNumberX);
		setDy(randomNumberY);
		setX(x + dx);
		setY(y + dy);
		jLabel.setLocation(x, y);
		//preyIdLabel.setLocation(x + 60, y + 20);
		nameLabel.setLocation(x + 70, y + 80);
	}
	
	/**
	 * Public access to the private hungry field
	 * @return Frogs state of hunger
	 */
	public boolean isHungry() {
		return hungry;
	}
	
	/**
	 * Sets the Frog's current state of hunger
	 * @param hungry Frog's hunger status
	 */
	@Override
	public void setHungry(boolean hungry) {
		this.hungry = hungry;
	}
	
	/**
	 * Public access to the protected name field
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Frog's name
	 * @param name Frog's new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Public access to the private tongue field
	 * @return Frog's tongue Grahics Object
	 */
	public Graphics getTongue() {
		return tongue;
	}
	
	/**
	 * Sets the Frog's tongue to the passsed Graphics Object
	 * @param tongue Frog's tongue Graphics Object
	 */
	public void setTongue(Graphics tongue) {
		this.tongue = tongue;
	}
	
	/**
	 * Gets the movementSpacePanel JPanel Object from the UserInterface Class upon Pet instantiation
	 * @return movementSpacePanel JPanel from UserInterface
	 */
	public JPanel getStoredMovementSpacePanel() {
		return storedMovementSpacePanel;
	}
	
	/**
	 * Sets the stored movementSpacePanel JPanel Object
	 * @param storedMovementSpacePanel The JPanel to store
	 */
	public void setStoredMovementSpacePanel(JPanel panel) {
		this.storedMovementSpacePanel = panel;
	}

	/**
	 * Randomly generates a boolean to dictate the inversion of the Frog sprite
	 */
	protected void setInverted() {
		Random rand = new Random();
		int randNo = rand.nextInt(2); // 0 <= randNo <= 1
		if (randNo == 0) {
			this.inverted = true;
		} else {
			this.inverted = false;
		}
	}
	
	/**
	 * Sets the stored console JTextArea Object
	 * @param storedConsole The JTextArea to store
	 */
	@Override
	public void setStoredConsole(JTextArea storedConsole) {
		this.storedConsole = storedConsole;
	}
	
	/**
	 * Gets the Predators fed status (Whether it's eaten or not)
	 */
	@Override
	public boolean getFed() {
		return fed;
	}
}