import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COP 3530: Project 4 – Binary Search Trees
 * <p>
 * The Project4 class reads in a file containing states and covid-related information creating state objects,
 * and inserts these state objects into nodes that are placed in a binary search tree. There is a menu containing
 * a variety of options to perform on the tree.
 * 
 * @author Brian Gerkens
 * @version 11/19/2021
 */
public class Project4 {

	/**
	 * default constructor for Project4
	 */
	public Project4() {
		
	}
	
	/**
	 * The main method initializes objects, reads in the csv file, creates state objects, and calls methods to 
	 * create a binary search tree.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int size = 50;
		String line = "";
		boolean running = true;
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);
		
		BinarySearchTree tree = new BinarySearchTree();
		Node treeNode = new Node();
		State state = null; 			//states read from file
		
		
		
		
		printHeader();
		System.out.println("Enter the file name: ");
		
		while(running) {
			String fileName = scan.next();
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
					
				 
				String headLine = br.readLine();
				while((line = br.readLine()) != null) {
							
					String[] data = line.split(",");
					//parse data from file
					String name = data[0];
					String capitol = data[1];
					String region = data[2];
					int usHouseSeats = Integer.parseInt(data[3]);
					int population = Integer.parseInt(data[4]);
					double covidCases = Double.parseDouble(data[5]);
					double covidDeaths = Double.parseDouble(data[6]);
					int income = Integer.parseInt(data[7]);
					double crimeRate = Double.parseDouble(data[8]);
					double CFR = covidCases / covidDeaths;
					double caseRate = covidCases / population * 100000;
					double deathRate = covidDeaths / population * 100000;
					
					state = new State(name, capitol, region, usHouseSeats, population,					//Create state objects from file, line by line.
							covidCases, covidDeaths, income, crimeRate, CFR, caseRate, deathRate);
					
					tree.insert(state.getName(), state.getDeathRate());
					
				}
				running = false;			//end main while loop		
			}
			catch (FileNotFoundException e) {
				System.out.println("YIKES!!! Incorrect file name. Please try again.\nEnter the file name: ");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	
		
		System.out.println("\nThere were 50 records read to build a binary search tree.\n ");	
		
		running = true;
		int menuNumber = 0;
		while(running) {
			printMenu();
			try {
				menuNumber = scan.nextInt();
			}
			catch(InputMismatchException e) {
				scan.next();
			}
			if(menuNumber == 1) {
				//print tree in order
				System.out.println("Inorder traversal: ");
				System.out.printf("%-17s%-17s%n", "Name", "Death Rate");
				for(int j = 0; j < 28; j++) {
					System.out.print("-");
				}
				System.out.println();
				tree.printInorder(tree.getRoot());
				System.out.println();
				continue;
			}
			else if(menuNumber == 2) {
				//print tree pre-order
				System.out.println("Preorder traversal: ");
				System.out.printf("%-17s%-17s%n", "Name", "Death Rate");
				for(int j = 0; j < 28; j++) {
					System.out.print("-");
				}
				System.out.println();
				tree.printPreorder(tree.getRoot());
				System.out.println();
				continue;
			}
			else if(menuNumber == 3) {
				//print tree post-order
				System.out.println("Postorder traversal: ");
				System.out.printf("%-17s%-17s%n", "Name", "Death Rate");
				for(int j = 0; j < 28; j++) {
					System.out.print("-");
				}
				System.out.println();
				tree.printPostorder(tree.getRoot());
				System.out.println();
				continue;
			}
			else if(menuNumber == 4) {
				//delete state for given name
				//States4.csv
				System.out.println("Enter state name to be deleted: ");
				String stateName = scan2.nextLine();
				
	
				tree.delete(stateName);
				System.out.println(stateName + " has been deleted from the binary search tree.\n");
//				
				continue;
			}
			else if(menuNumber == 5) {
				System.out.println("\nPlease enter state name: \n");
				String stateName = scan2.nextLine();
				
				System.out.println(stateName + " is found with a death rate of " + tree.find(stateName));
				System.out.println("The path to " + stateName + " is ");
				continue;
			}
			else if(menuNumber == 6) {
				//Print bottom states regarding DR
				int numBotStates;
				System.out.println("\nPlease enter number of states: \n");
				try {
					numBotStates = scan3.nextInt();
					System.out.println("\nBottom " + numBotStates + " states regarding DR: \n");
					tree.printBottomStates(numBotStates);
				}
				catch (InputMismatchException e) {
					System.out.println("\nERROR!!!  You must enter a whole positive integer.\n");
					continue;
				}
				
				continue;
			}
			else if(menuNumber == 7) {
				//Print top states regarding DR
				int numTopStates;
				System.out.println("\nPlease enter number of states: \n");
				try {
					numTopStates = scan3.nextInt();
					System.out.println("\nBottom " + numTopStates + " states regarding DR: \n");
					tree.printTopStates(numTopStates);
				}
				catch (InputMismatchException e) {
					System.out.println("\nERROR!!!  You must enter a whole positive integer.\n");
					continue;
				}
				
				continue;
			}
			else if(menuNumber == 8) {
				System.out.println("File closed.\nProgram terminated.\nHave a nice day!");
				scan.close();
				running = false;
			}
			else {
				System.out.println("\nYIKES!!!\nInvalid entry. Please try again: \n");
			}	
		}
	}
	
	/**
	 * the printHeader method prints the header for project 4.
	 */
	public static void printHeader() {
		System.out.println("COP3530 Project 4\nInstructor: Xudong Liu\n");
		System.out.println("Binary Search Tree");
	}
	
	/**
	 * The printMenu() method prints the menu to the user with options 1-8.
	 */
	public static void printMenu() {
		System.out.println("1.) Print tree inOrder.\n2.) Print tree preOrder.\n3.) Print tree postOrder.");
		System.out.println("4.) Delete a state for a given name.\n5.) Search and print a state and its path for a given name.");
		System.out.println("6.) Print bottom states regarding DR.\n7.) Print top states regarding DR.\n8.) Exit\nPlease enter your choice: ");
	}

}
