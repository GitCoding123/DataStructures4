/**
 * The Node class creates a node based on the state name and death rate being passed through.
 * It also contains a left child and a right child node, as well as a displayNode method.
 * 
 * @author Brian Gerkens
 * @version 11/16/2021
 */
public class Node {
	
	State state = new State();
	String name = state.getName();
	double DR = state.getDeathRate();
	
	Node leftChild;
	Node rightChild;
	
	/**
	 * default constructor for Node.
	 */
	public Node() {
		
	}
	
	/**
	 * The displayNode method displays the node containing the state name and death rate.
	 */
	public void displayNode() {
		System.out.printf("%-15s%-17s%n", name, DR);
	}
}
