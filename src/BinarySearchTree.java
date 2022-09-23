/**
 * The BinarySearchTree class uses various methods to insert states into the proper
 * nodes within the tree, delete nodes, find nodes, print top states regarding DR, 
 * and print bottom states regarding DR.
 * 
 * @author Brian Gerkens
 * @version 11/19/2021
 */

public class BinarySearchTree {

	private Node root;
	
	/**
	 * This constructor for the binary search tree contains a null root which creates the empty tree.
	 */
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * The insert method includes 2 parameters (name, DR) that produces nodes based on 
	 * each parameter that is passed through.
	 * 
	 * @param name    the name is the name of the state
	 * @param DR      the DR is the death rate of the state
	 */
	public void insert(String name, double DR) {
		Node node = new Node();
		node.name = name;
		node.DR = DR;
		
		if (root == null) {
			root = node;
		}
		else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (name.compareTo(current.name) < 0) {
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = node;
						return;
					}
				}
				else {
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = node;
						return;
					}
				}
			}
		}
	}
	
	/**
	 * The find method will search through the tree based on the name, and return the
	 * death rate of that state.
	 * 
	 * @param name
	 * @return current.DR      The death rate of the current node
	 */
	public double find(String name) {
		//int size = 1;
		Node current = root;
		//State[] stack = new State[size];
		
		
		System.out.println(current.name);
		while(current.name != name) {
			if (name.compareTo(current.name) < 1) {
				current = current.leftChild;
			}
			else {
				current = current.rightChild;
			}
			if (current == null) {
			
				return -1;
			}	
		}
		return current.DR;
	}
	
	/**
	 * The delete method takes the user entered state name parameter and
	 * deletes that node with the same state name.
	 * 
	 * @param name     name of the user entered state
	 */
	public void delete(String name) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		
		while (current.name != name) {
			parent = current;
			if (name.compareTo(current.name) < 1) {
				
				current = current.leftChild;
			}
			else {
				current = current.rightChild;
			}
			
			if (current == null) {
				return;
			}
		}
		
			// If there are no children...
			if (current.leftChild == null && current.rightChild == null) {
				if (current == root) {
					root = null;
				}
				else if (isLeftChild) {
					parent.leftChild = null;
				}
				else {
					parent.rightChild = null;
				}
			}
			// If there is no right child, replace with the left subtree
			else if (current.rightChild == null) {
				if (current == root) {
					root = current.leftChild;
				}
				else if (isLeftChild) {
					parent.leftChild = current.leftChild;
				}
				else {
					parent.rightChild = current.leftChild;
				}
			}
			// If there is no left child, replace with the right subtree
			else if (current.leftChild == null) {
				if (current == root) {
					root = current.rightChild;
				}
				else if (isLeftChild) {
					parent.leftChild = current.rightChild;
				}
				else {
					parent.rightChild = current.rightChild;
				}
			}
			// There are two children, so we must replace with inorder successor
			else {
				//get the successor
				Node successor = getSuccessor(current);
				
				if (current == root) {
					root = successor;
				}
				else if (isLeftChild) {
					parent.leftChild = successor;
				}
				else {
					parent.rightChild = successor;
				}
				//connect successor to current's left child
				successor.leftChild = current.leftChild;
			}
		
	}
	
	/**
	 * the getSuccessor method allows us to get the successor after the previous node has been deleted.
	 * 
	 * @param temp            the temporary node
	 * @return successor      the successor node
	 */
	private Node getSuccessor(Node temp) {
		Node successorParent = temp;
		Node successor = temp;
		Node current = temp.rightChild;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		if (successor != temp.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = temp.rightChild;
		}
		return successor;
 }
	
	/**
	 * the printInorder method allows us to print the binary search tree
	 *  through inorder traversal.
	 * 
	 * @param localRoot   the local root used
	 */
	public void printInorder(Node localRoot) {
		if (localRoot != null) {
			printInorder(localRoot.leftChild);
			System.out.printf("%-17s%-17s%n" ,localRoot.name, String.format("%.2f", localRoot.DR));
			printInorder(localRoot.rightChild);
		}
		else {
			Node newNode = new Node();
			localRoot = newNode;
		}
	}
	
	/**
	 * the printPreorder method allows us to print the binary search tree 
	 * through preorder traversal
	 * 
	 * @param localRoot    the local root used
	 */
	public void printPreorder(Node localRoot) {
		if (localRoot != null) {
			System.out.printf("%-17s%-17s%n" ,localRoot.name, String.format("%.2f", localRoot.DR));
			printPreorder(localRoot.leftChild);
			printPreorder(localRoot.rightChild);
		}
		else {
			Node newNode = new Node();
			localRoot = newNode;
		}
	}
	
	/**
	 * the printPostorder method allows us to print the binary search tree
	 * through postorder traversal
	 * 
	 * @param localRoot     the local root used.
	 */
	public void printPostorder(Node localRoot) {
		if (localRoot != null) {
			printPostorder(localRoot.leftChild);
			printPostorder(localRoot.rightChild);
			System.out.printf("%-17s%-17s%n" , localRoot.name, String.format("%.2f", localRoot.DR));
		}
		else {
			Node newNode = new Node();
			localRoot = newNode;
		}
	}
	
	/**
	 * the printBottomStates method allows us to traverse through the tree and select
	 * the states with the highest death rates.
	 * 
	 * @param c      the user entered number of states.
	 */
	public void printBottomStates(int c) {
		State[] bottomStateArray = new State[c];
	}
	
	/**
	 * the printTopStates method allows us to traverse through the tree and select
	 * the states with the lowest death rates.
	 * 
	 * @param c     the user entered number of states
	 */
	public void printTopStates(int c) {
		State[] topStateArray = new State[c];
		
		
	}
	
	 /**
	  * the getRoot method allows us to grab the root from the BinarySearchTree class
	  * and use it in other classes.
	  * 
	  * @return root      the root of the tree
	  */
	public Node getRoot() {
		return root;
	}
}
