/*
 *	Created by Anton Velikodnyy & edited by Daniel Tellier on 6/27/17.
 *	Copyright © 2017 Anton Velikodnyy. All rights reserved.
 *	May be distributed for the use of teaching purposes.
 */

/**
 *	Container for the storage of data given.
 *	{@code Node} contains the pointer to the next and previous {@code Node}
 *	instances in the chain of nodes generated by {@Stack}.
 */
class Node<U> {
	
	/**
	 *	Stores a generic immutable object.
	 */
	private final U data;
	
	/**
	 *	Pointer to the next node in the chain.
	 */
	protected  Node next;

	//----------------------------------------------------------------------------
	/**
	 *	Constructor of this {@code Node} class.
	 *
	 *	@param data  takes given object and stores in this Node.
	 *	@param next  sets another Node as the next pointer of this Node.
	 */
	protected Node (U data, Node next) {
		this.data = data;
		this.next = next;
	}
	
	/**
	 *	Prints the data that is stored in this Node.
	 *
	 *	@return data in string form.
	 */
	@Override
	public String toString () {
		return data.toString();
	}
}

/**
 *	Stack is an object which creates and manages a chain of nodes stored
 *	in memory.
 *	<p>
 *	This class accepts any data type and can only directly access the
 *	head of the chain of nodes it manages.
 */
public class Stack<T>  {
	
	
	//----------------------------------------------------------------------------
	/**
	 *	Stores pointer to starting Node of the chain.
	 */
	private Node headNode = null;

	//----------------------------------------------------------------------------
	
	/**
	 *	Inserts Node instance at the end of the chain.
	 *	<p>
	 *	If the chain is empty, the following actions occur:
	 *	<p><ol>
	 *	<li>headNode becomes new Node instance which points to the original headNode.
	 *	</ol><p>
	 *
	 *	@param data  is an Object, who's data-type matches the initially declared
	 *	data-type of this class, to be stored.
	 */
	public void lastIn (T data) {
			headNode = new Node (data, headNode);
	}
	
	//----------------------------------------------------------------------------
	/**
	 *	Removes Node instance located at the end of the chain.
	 *	<p>
	 *	If isEmpty()} is false, the following actions occur.
	 *	<p></ol>
	 *	<li> headNode now points to the next headNode.
	 *	</ol><p>
	 */
	public void firstOut () {
		if (!this.isEmpty()) {
			headNode = headNode.next;
		}
	}
 
	//----------------------------------------------------------------------------
	/**
	 *	Checks to see if this class is managing a chain of Nodes.
	 *
	 *	@return true if chain of Nodes exists, false otherwise.
	 */
	private boolean isEmpty () {
		return ( headNode == null);
	}
 
	//----------------------------------------------------------------------------
	/**
	 *	Converts the chain of Nodes, through itteration, into a String.
	 *	<p>
	 *	The order of the Node instances are printed from headNode until null.
	 *	<p>
	 *	Returns an emtpy string ("") if chain is empty.
	 *
	 *	@return String that contains all the values in the chain of Nodes.
	 */
	@Override
	public String toString() {
		Node currentNode = headNode;
		StringBuilder builder = new StringBuilder("");
		while (currentNode != null) {
			builder.append(currentNode + " ");
			currentNode = currentNode.next;
		}
		builder.append("\n");
		return builder.toString();
	}
	
	//----------------------------------------------------------------------------
	/**
	 *	Tests this Stack class for possible errors.
	 *
	 *	@param args  command-line arguments in array form.
	 */
	public static void main (String... args){
		Stack<Integer> stack = new Stack<Integer>();
		System.out.println (stack);
		stack.lastIn(5);
		stack.lastIn(4);
		System.out.println (stack);
		stack.firstOut();
		System.out.println (stack);
	}
}