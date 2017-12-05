package application;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JLabel;

/**---------------------------------------------------------------
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 24/02/17
 * @description This class represents an Animal Component on the
 * 				GUI. It's fields and methods are common between
 * 				all prey. I.e. Fly, Fish, Deer etc...
 * -------------------------------------------------------------*/

public abstract class Prey extends Animal {
	/**Protected Fields - Inherited by all Prey Classes*/
	protected String predator;
	protected boolean isPredatorHungry;
	protected int[] angles;
	protected int rotatedAngle;
	protected static AtomicInteger counter = new AtomicInteger(0);
	protected int preyID;
	private boolean xFlag = false;
	private boolean yFlag = false;
	
	/**
	 * Randomly generates dx and dy values and updates the coordinates accordingly
	 */
	public void moveRandomly() {
		Random rand = new Random();
		int posRandomNumberX = rand.nextInt(15); // 0 <= x >= 15
		int posRandomNumberY = rand.nextInt(15);
		int negRandomNumberX = rand.nextInt(10) - 15; // -15 <= x >= 5
		int negRandomNumberY = rand.nextInt(10) - 15; 
		
		//If prey goes too far right, invert. Else, keep moving right
		if (x + posRandomNumberX >= 640) {
			setDx(negRandomNumberX);
			xFlag = true;
			System.out.println("if-x : " + x);
		} else if (xFlag == false){
			setDx(posRandomNumberX);
			System.out.println("else-x : " + x);
		}
		
		//If prey goes too far down, invert. Else, keep moving down
		if (y + posRandomNumberY >= 480) { 
			setDy(negRandomNumberY);
			yFlag = true;
			System.out.println("if-y : " + y);
		} else if (yFlag == false){
			setDy(posRandomNumberY);
			System.out.println("else-y : " + y);
		}
		
		//If prey goes too far left, invert. Else, keep moving left
		if (x + negRandomNumberX <= 0) {
			setDx(posRandomNumberX);
			xFlag = false;
		} else if (xFlag){
			setDx(negRandomNumberX);
		}
		
		//If prey goes too far up, invert. Else, keep moving up
		if (y + negRandomNumberY <= 0) {
			setDy(posRandomNumberY);
			yFlag = false;
		} else if (yFlag) {
			setDy(negRandomNumberY);
		}
		
		//Update coordinates
		setX(x + dx);
		setY(y + dy);
		
		//Update JLabel Locations
		jLabel.setLocation(x, y);
		preyIdLabel.setLocation(x + 60, y + 20);
		nameLabel.setLocation(x + 110, y + 40);
	}
	
	/**
	 * Returns the Preys ID JLabel
	 * @return Prey ID JLabel
	 */
	public JLabel getPreyIdLabel() {
		return preyIdLabel;
	}

	/**
	 * Sets the Predator Name to the specified String. It cannot be greater than 25 characters in length
	 * @param predator PredatorName
	 */
	public void setPredator(String predator) {
		if (predator.length() < 25) {
			this.predator = predator;
		} else {
			System.out.println("Please enter a name no more than 25 characters in length");
		}
	}
	
	/**
	 * Instantiates the Array of sprite rotation angles
	 */
	protected void setAngles() {
		angles = new int[4];
		angles[0] = 0;
		angles[1] = 90;
		angles[2] = 180;
		angles[3] = 270;
	}
	
	/**
	 * Sets the Pet's sprite rotation angle to a random pre-defined rotation from an array.
	 */
	protected void setRotatedAngle() {
		Random rand = new Random();
		int no = rand.nextInt(4);
		this.rotatedAngle = angles[no];
	}
	
	/**
	 * Public access to the private rotated angle field
	 * @return Calculated Angle of Rotation of the Prey Sprite
	 */
	public int getRotatedAngle() {
		return rotatedAngle;
	}
	
	/**
	 * Sets the Prey's predator hunger status to the passed boolean
	 * @param isPredatorHungry Prey's predator hunger status
	 */
	protected void setPredatorHungry(boolean isPredatorHungry) {
		this.isPredatorHungry = isPredatorHungry;
	}
	
	/**
	 * Resets the Prey ID counter to 0
	 */
	public void resetPreyIDCounter() {
		counter = new AtomicInteger(0);
		preyID = 0;
	}
	
	/**
	 * Public access to the protected predator name field
	 * @return Predator Name
	 */
	public String getPredator() {
		return predator;
	}
}