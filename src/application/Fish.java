package application;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**--------------------------------------------------------------- 
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 28/02/17
 * @description This class is responsible for the Fish Objects.
 * -------------------------------------------------------------*/
public class Fish extends Prey implements Runnable {
	/**
	 * Default Constructor - Creates new Fish Object & Instantiates the preyID
	 */
	public Fish() {
		preyID = counter.incrementAndGet();
	}
	
	/**
	 * Parameterised Constructor - Creates a new Prey Object with the specified predator name
	 * @param predator
	 */
	public Fish(String predator) {
		setPredator(predator);
		generateCoordinates();
		setAngles();
		setRotatedAngle();
		preyID = counter.incrementAndGet();
		if (rotatedAngle == 0) {
			image = new ImageIcon(getClass().getResource("/images/fish-85x50-0.png"));
		} else if (rotatedAngle == 90) {
			image = new ImageIcon(getClass().getResource("/images/fish-85x50-90.png"));
		} else if (rotatedAngle == 180) {
			image = new ImageIcon(getClass().getResource("/images/fish-85x50-180.png"));
		} else if (rotatedAngle == 270) {
			image = new ImageIcon(getClass().getResource("/images/fish-85x50-270.png"));
		}
		nameLabel = new JLabel(predator);
		preyIdLabel = new JLabel(String.valueOf(preyID));
		preyIdLabel.setFont(labelFont);
		jLabel = new JLabel(image);
		isPredatorHungry = false;
	}
	
	/**
	 * Overrides the run() method from the Runnable Interface. 
	 * Causes the Prey's sprite to move randomly while isPredator boolean is false
	 */
	@Override
	public void run() {
		try {
			while(!isPredatorHungry) {
				Thread.sleep(100);
				moveRandomly(); 
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
