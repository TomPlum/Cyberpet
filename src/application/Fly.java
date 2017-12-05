package application;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**--------------------------------------------------------------- 
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 21/02/17
 * @description This class is responsible for the Fly Objects.
 * -------------------------------------------------------------*/

public class Fly extends Prey implements Runnable{
	/**
	 * Default Constructor - Creates a new Prey Object with null fields.
	 */
	public Fly() {
		preyID = counter.incrementAndGet();
	}
	
	/**
	 * Parameterised Constructor - Creates a new Prey Object with the specified predator name
	 * @param predator
	 */
	public Fly(String predator) {
		setPredator(predator);
		generateCoordinates();
		setAngles();
		setRotatedAngle();
		preyID = counter.incrementAndGet();
		if (rotatedAngle == 0) {
			image = new ImageIcon(getClass().getResource("/images/fly-50x45-0.png"));
		} else if (rotatedAngle == 90) {
			image = new ImageIcon(getClass().getResource("/images/fly-50x45-90.png"));
		} else if (rotatedAngle == 180) {
			image = new ImageIcon(getClass().getResource("/images/fly-50x45-180.png"));
		} else if (rotatedAngle == 270) {
			image = new ImageIcon(getClass().getResource("/images/fly-50x45-270.png"));
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

	/**
	 * Overrides the default toString() from java.lang
	 * Provides an appropiate, suitably formatted string of information regarding the Prey
	 */
	@Override
	public String toString() {
		String s = new String();
		s += "Predator: " + predator;
		s += "Fly ID: " + preyID;
		s += "\nCoords: (" + x + ", " + y + ").";
		return s;
	}
}
