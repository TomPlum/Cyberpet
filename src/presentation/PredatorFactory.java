package presentation;

import application.Frog;
import application.Shark;
/**--------------------------------------------------------------- 
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 28/02/17
 * @description This class is responsible the creation of Predator
 * 				Object instances.
 * -------------------------------------------------------------*/
public class PredatorFactory {
	public IAnimal getPredator(String predatorType, String name) {
		if (predatorType.equalsIgnoreCase("FROG")) {
			return new Frog(name, false);
		} else if (predatorType.equalsIgnoreCase("SHARK")) {
			return new Shark(name, false);
		}
		return null;
	}
}
