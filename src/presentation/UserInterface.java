package presentation;
import javax.swing.*;
import application.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**--------------------------------------------------------------
 * @author Tom Plumpton (1500936)
 * @version 1.0.0
 * @date 14/02/17
 * @description This class is responsible for creating the GUI.
 * ------------------------------------------------------------*/

public class UserInterface implements ActionListener, Runnable {
	/**Private Fields - Used Locally by this class*/
	private JButton hungry, reset, makePet, labelToggle;
	private JPanel GUI, movementSpacePanel, buttonPanel, consolePanel, titlePanel, topContainer;
	private JTextField nameField;
	private JLabel petName, nameFieldLabel, petCountLabel, countValue, isHungryLabel;
	private JScrollPane consoleScroll;
	private JComboBox<String> selectPredator;
	private JTextArea console;
	private int petCount = 0;
	private String userGivenPetName;
	private ArrayList<IAnimal> predators = new ArrayList<IAnimal>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	private String[] listOfPredators = new String[] {"Frog", "Shark"};
	private IAnimal frog, shark;
	private Fly fly;
	private Fish fish;
	private boolean labelVisibility = true;
	private PredatorFactory predatorFactory = new PredatorFactory();
	
	/**
	 * Instantiates GUI related instance variables and sets up the layout.
	 * @return Uppermost Parent JPanel
	 */
	public JPanel createContentPane() {
		//Create JPanels & Set Layout
		GUI = new JPanel();
		buttonPanel = new JPanel();
		consolePanel = new JPanel();
		movementSpacePanel = new JPanel();
		movementSpacePanel.setPreferredSize(new Dimension(750, 600));
		titlePanel = new JPanel();
		topContainer = new JPanel();
		
		//Create Buttons
		hungry = new JButton("Hungry");
		reset = new JButton("Reset");
		makePet = new JButton("Make Pet");
		labelToggle = new JButton("Toggle Labels");
		
		//Create Combo Box
		selectPredator = new JComboBox<String>(listOfPredators);
		
		//Create Text Field & Text Area
		nameField = new JTextField(10);
		console = new JTextArea("Program information will be displayed here.", 8, 50);
		consoleScroll = new JScrollPane(console);
		
		//Create Icons & JLabels
		petName = new JLabel("Un-Assigned");
		petName.setForeground(Color.RED);
		nameFieldLabel = new JLabel("Name(s): ");
		petCountLabel = new JLabel("No. of Pets: ");
		countValue = new JLabel(String.valueOf(petCount));
		countValue.setForeground(Color.RED);
		isHungryLabel = new JLabel();
		isHungryLabel.setForeground(Color.ORANGE);
		
		//Adding Action Listeners To Buttons
		hungry.addActionListener(this);
		reset.addActionListener(this);
		makePet.addActionListener(this);
		labelToggle.addActionListener(this);
		
		//Setting LayoutManager Types
		GUI.setLayout(new BorderLayout());
		topContainer.setLayout(new BorderLayout());
		buttonPanel.setLayout(new FlowLayout());
		movementSpacePanel.setLayout(null);
		consolePanel.setLayout(new FlowLayout());
		titlePanel.setLayout(new FlowLayout());
		
		//Set JPanel Borders
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
		movementSpacePanel.setBorder(BorderFactory.createTitledBorder("Movement Space (750x600)"));
		consolePanel.setBorder(BorderFactory.createTitledBorder("Console"));
		titlePanel.setBorder(BorderFactory.createTitledBorder("Details"));
		
		//Adding Components To Panels
		buttonPanel.add(nameFieldLabel);
		buttonPanel.add(nameField);
		buttonPanel.add(makePet);
		buttonPanel.add(hungry);
		buttonPanel.add(reset);
		buttonPanel.add(labelToggle);
		buttonPanel.add(selectPredator);
		consolePanel.add(consoleScroll);
		topContainer.add(titlePanel, BorderLayout.PAGE_START);
		topContainer.add(movementSpacePanel, BorderLayout.CENTER);
		titlePanel.add(nameFieldLabel);
		titlePanel.add(petName);
		titlePanel.add(petCountLabel);
		titlePanel.add(countValue);
		titlePanel.add(isHungryLabel);	
		
		//Adding JPanel Containers To Overall GUI Container
		GUI.add(topContainer, BorderLayout.PAGE_START);
		GUI.add(buttonPanel, BorderLayout.CENTER);
		GUI.add(consolePanel, BorderLayout.PAGE_END);
		
		//Make It Visible & Return
		GUI.setOpaque(true);
		return GUI;
	}
	
	/**
	 * Adds the passed String into the console JTextArea on a new line
	 * @param info Text String to add
	 */
	public void updateConsole(String info) {
		console.append("\n" + info);
	}
	
	/**
	 * Overwrites the consoles current text with the specified String
	 * @param info Text String to insert 
	 */
	public void overwriteConsole(String info) {
		console.setText(info);
	}
	
	/**
	 * Traverses over the 'threads' ArrayList and terminates all the active Threads.
	 */
	@SuppressWarnings("deprecation")
	private void terminateRunningThreads() {
		for (Thread t : threads) {
			t.stop();
		}
		updateConsole("All Pet/Prey Threads Terminated. There are " + Thread.activeCount() + " active threads.");
	}
	
	/**
	 * Inverts the labelVisibility boolean
	 */
	private void toggleLabels() {
		if (labelVisibility == false) {
			labelVisibility = true;
		} else {
			labelVisibility = false;
		}
	}
	
	/**
	 * Creates the JFrame, add the Content Pane and packs it.
	 */
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Catch The Fly!");
		UserInterface demo = new UserInterface();
		frame.setContentPane(demo.createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
	
	/**
	 * Overrides the run() method in the Runnable Interface.
	 * It calls createAndShowGUI() which instantiates the JFrame and packs it.
	 */
	@Override
	public void run() {
		createAndShowGUI();
	}
	
	/**
	 * Responsible for actions performed on button events. MakePet, Hungry & Reset.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == makePet) { //If the user clicks 'Make Pet'			
			if (nameField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(GUI, "Please enter a name for your pet.", "Empty Name Field", JOptionPane.OK_OPTION);
			} else {
				userGivenPetName = nameField.getText();
				petCount++;				
				if (selectPredator.getSelectedItem().equals("Frog")) {
					//Add the Frog
					frog = predatorFactory.getPredator("Frog", userGivenPetName);
					frog.setStoredMovementSpacePanel(movementSpacePanel);
					frog.setStoredConsole(console);
					frog.setTongue(movementSpacePanel.getGraphics());
					frog.addPredator();
					predators.add(frog);
					
					//Add the Fly
					fly = new Fly(userGivenPetName);
					frog.setPrey(fly);
					frog.addPrey();
					
					//Create the Threads, Add to ArrayList & Start
					Thread frogThread = new Thread((Runnable) frog, "Frog-Thread");
					threads.add(frogThread);
					frogThread.start();
					
					Thread flyThread = new Thread((Runnable) fly, "Fly-Thread");
					threads.add(flyThread);
					flyThread.start();
				} else if (selectPredator.getSelectedItem().equals("Shark")) {
					//Add the Shark
					shark = predatorFactory.getPredator("Shark", userGivenPetName);
					shark.setStoredMovementSpacePanel(movementSpacePanel);
					shark.setStoredConsole(console);
					shark.setTongue(movementSpacePanel.getGraphics());
					shark.addPredator();
					predators.add(shark);
					
					//Add the Fish
					fish = new Fish(userGivenPetName);
					shark.setPrey(fish);
					shark.addPrey();
					
					//Create the Threads, Add to ArrayList & Start
					Thread sharkThread = new Thread((Runnable) shark, "Shark-Thread");
					threads.add(sharkThread);
					sharkThread.start();
					
					Thread fishThread = new Thread((Runnable) fish, "Fish-Thread");
					threads.add(fishThread);
					fishThread.start();
				}
				 
				if (petCount < 2) {
					petName.setText(userGivenPetName); //Label at top of screen
					if (selectPredator.getSelectedItem().equals("Frog")) {
						overwriteConsole("'" + userGivenPetName + "' has been added at (" + frog.getX()
						+ ", " + frog.getY() + "). It's prey is at (" + fly.getX() + ", " + fly.getY() + ") @ " + fly.getRotatedAngle() + "\u00B0");
					} else if (selectPredator.getSelectedItem().equals("Shark")) {
						overwriteConsole("'" + userGivenPetName + "' has been added at (" + shark.getX()
						+ ", " + shark.getY() + "). It's prey is at (" + fish.getX() + ", " + fish.getY() + ") @ " + fish.getRotatedAngle() + "\u00B0");
					}
					updateConsole("Pet count changed to: 1");
				} else {
					petName.setText(petName.getText() + ", " + userGivenPetName);
					if (selectPredator.getSelectedItem().equals("Frog")) {
						updateConsole(userGivenPetName + "' has been added at (" + frog.getX()
						+ ", " + frog.getY() + "). It's prey is at (" + fly.getX() + ", " + fly.getY() + ") @ " + fly.getRotatedAngle() + "\u00B0");
					} else if (selectPredator.getSelectedItem().equals("Shark")) {
						updateConsole(userGivenPetName + "' has been added at (" + shark.getX()
						+ ", " + shark.getY() + "). It's prey is at (" + fish.getX() + ", " + fish.getY() + ") @ " + fish.getRotatedAngle() + "\u00B0");
					}
					updateConsole("Pet count changed to: " + petCount);
				}
				nameField.setText("");
				countValue.setText(String.valueOf(petCount));	
				updateConsole("Number of Active Pet/Prey Threads: " + (Thread.activeCount() - 3));
			}
		} else if (e.getSource() == hungry) { //If the user clicks 'Hungry'
			if (petCount > 0) {
				for (int i = 0; i < predators.size(); i++) {
					if (predators.get(i).getFed() == false) {
						predators.get(i).setHungry(true); //Make all predators hungry
						updateConsole(predators.get(i).getName() + " is now hungry!");
					}
				}
				isHungryLabel.setText("The predators are now hungry!");
			} else {
				JOptionPane.showMessageDialog(GUI, "You do not have any pets.", "Error: No Frogs", JOptionPane.OK_OPTION);
			}
		} else if (e.getSource() == reset) { //If the user clicks 'Reset'
			//Reset the relevant text fields and pet count
			petCount = 0;
			overwriteConsole("Program information will be displayed here.");
			petName.setText("Un-Assigned");
			countValue.setText("0");
			isHungryLabel.setText("");
			fly.resetPreyIDCounter();
			
			//Empty the ArrayList
			predators.removeAll(predators);
			
			//Clear the Frog & Fly Icons
			movementSpacePanel.removeAll();
			movementSpacePanel.validate();
			movementSpacePanel.repaint();
			
			//Terminate Running Threads
			terminateRunningThreads();
		} else if (e.getSource() == labelToggle) { //If the users clicks 'Toggle Labels'
			for (int i = 0; i < predators.size(); i++) {
				IAnimal predator = predators.get(i);
				predator.getPreyIdLabel().setVisible(labelVisibility);
				predator.getNameLabel().setVisible(labelVisibility);
			}
			toggleLabels(); //Invert boolean
		}
	}
}