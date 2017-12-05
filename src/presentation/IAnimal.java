package presentation;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**--------------------------------------------------------------- 
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 21/02/17
 * @description This interface enforces implementation of it's methods
 * 				on all Predator type classes.
 * -------------------------------------------------------------*/
public interface IAnimal {	
	public abstract int getX();
	public abstract int getY();
	public abstract JLabel getNameLabel();
	public abstract void setStoredMovementSpacePanel(JPanel mspanel);
	public abstract void addPredator();
	public abstract void addPrey();
	public abstract void setStoredConsole(JTextArea storedConsole);
	public abstract void setTongue(Graphics tongue);
	public abstract void setPrey(Object prey);
	public abstract Object getPrey();
	public abstract void setHungry(boolean hungry);
	public abstract String getName();
	public abstract JLabel getPreyIdLabel();
	public abstract boolean getFed();
}
