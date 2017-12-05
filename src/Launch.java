import presentation.UserInterface;
/**--------------------------------------------------------------
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 27/02/17
 * @description This class is responsible for running the program.
 * ------------------------------------------------------------*/
public class Launch {
	public static void main (String[] args) {
		new Thread(new UserInterface()).start();
	}
}
