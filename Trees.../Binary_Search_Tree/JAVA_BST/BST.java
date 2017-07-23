/*
 *	Created by Anton Velikodnyy & edited by Daniel Tellier on 6/27/17.
 *	CopyrightChild © 2017 Anton Velikodnyy. All rightChilds reserved.
 *	May be distributed for the use of teaching purposes.
 */

/**
 *	Container for the storage of data given.
 *	{@code Node} contains the pointer to the next instances in the tree of
 *	nodes generated by {@BST}.
 *	<p>
 *	Note, requires {@code extends Number} because < and > are used to compare
 *	generic objects
 */
class Node<U extends Number> {
	
	/**
	 *	Stores a generic immutable object.
	 */
	protected final U data;
	
	/**
	 *	Pointer to the leftChild child node of this node in the tree.
	 */
	protected  Node<U> leftChild;
	
	/**
	 *	Pointer to the rightChild child node of this node in the tree.
	 */
	protected  Node<U> rightChild;
	
	//----------------------------------------------------------------------------
	/**
	 *	Constructor of this {@code Node} class.
	 *
	 *	@param data  takes given object and stores in this Node.
	 */
	protected Node (U data) {
		this.data = data;
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
 *	BST is an object which creates and manages a tree of nodes stored
 *	in memory.
 *	<p>
 *	This class accepts any data type and can only directly access the
 *	head of the tree of nodes it manages.
 *	<p>
 *	Note, requires {@code extends Number} because < and > are used to compare
 *	generic objects
 */
public class BST<T extends Number>  {
	
	
	//----------------------------------------------------------------------------
	/**
	 *	Stores pointer to starting Node of the tree.
	 */
	private Node<T> headNode = null;
	
	//----------------------------------------------------------------------------
	/**
	 *	Inserts Node into the tree.
	 *	<p>
	 *	If the tree is empty, the following actions occur:
	 *	<p><ol>
	 *	<li>Node instance created.
	 *	<li>This class' headNode becomes a pointer to this Node.
	 *	</ol><p>
	 *	If the tree is not empty, the following actions occur:
	 *	<p><ol>
	 *	<li>tempNode is used to traverse the tree until a place to insert a new Node
	 *	is found.
	 *	<li>A new Node is created and tempNode's child now points to this new Node
	 *	</ol><p>
	 *
	 *	@param data  is an Object, who's data-type matches the initially declared
	 *	data-type of this class, to be stored.
	 */
	public void insert(T data) {
		if (headNode == null) {
			headNode = new Node<T>(data);
			return;
		}
		Node<T> parent = null, tempNode = headNode;
		while (tempNode != null) {
			parent = tempNode;
			if      (tempNode.data.longValue() > data.longValue())
				tempNode = tempNode.leftChild;
			else if (tempNode.data.longValue() < data.longValue())
				tempNode = tempNode.rightChild;
			else {
				return;
			}   // overwrite duplicate
		}
		if (parent.data.longValue() > data.longValue())
			parent.leftChild  = new Node<T>(data);
		else
			parent.rightChild = new Node<T>(data);
	}
	
	//----------------------------------------------------------------------------
	/**
	 *	Selects the Node to be removed, if it matches the search criteria given.
	 *	<p>
	 *	If isEmpty()} is false, the following actions occur.
	 *	<p></ol>
	 *	<li>tempNode is used to traverse the tree to find the match.
	 *	<li>Once match is found, removeNode is called.
	 *	<li>If no match is found, nothing happens.
	 *	</ol><p>
	 *
	 *	@param data  search criteria for the node to be removed.
	 */
	public void removeSearch (T data){
		if (headNode == null) {
			return;
		}
		Node<T> parent = null, tempNode = headNode;
		while (tempNode != null) {
			if (tempNode.data.longValue() == data.longValue()) {
				removeNode (parent, tempNode == parent.leftChild);
				return;
			}
			parent = tempNode;
			if      (tempNode.data.longValue() > data.longValue())
				tempNode = tempNode.leftChild;
			else if (tempNode.data.longValue() < data.longValue())
				tempNode = tempNode.rightChild;
		}   // overwrite duplicate
		
	}
 
	/*
	 *	Removes the node from the tree.
	 *	<p>
	 *	The first if statement is to establish which child is to be of tempNode
	 *	needs to be removed. (note: Since erasing the pointer directly does not
	 *	break the original pointer)
	 *	<p>
	 *	Three scenarios arise from removing a node
	 *	<p><]ol>
	 *	<li>If only the rightNode of the child is null, then leftNode replaces
	 *	the child.
	 *	<li>If both are null, then child becomes null.
	 *	<li>If rightNode is not null, then the next largest Node must be found,
	 *	and is replaces the child.
	 *
	 *	@param tempNode  the parent of the node to be removed.
	 *	@param leftChild  boolean that determines if the leftChild or rightChild
	 *	is to be removed
	 */
	public void removeNode (Node<T> tempNode, boolean leftChild) {
		if (leftChild) {//if leftChild child is the node to be removed
			System.out.println ("Removing at " + tempNode.leftChild.data);
			if (tempNode.leftChild.rightChild == null) {
				if ( tempNode.leftChild.leftChild == null)
					tempNode.leftChild = null;
				else
					tempNode.leftChild = tempNode.leftChild.leftChild;
			}
			else {
				Node<T> largerTemp = tempNode.leftChild.rightChild;
				if (largerTemp.leftChild != null) {
					while  ( largerTemp.leftChild.leftChild != null)
						largerTemp = largerTemp.leftChild;
					largerTemp.leftChild.leftChild = tempNode.leftChild.leftChild;
					tempNode.leftChild.leftChild = largerTemp.leftChild;
					largerTemp.leftChild = tempNode.leftChild.leftChild.rightChild;
					tempNode.leftChild.leftChild.rightChild = tempNode.leftChild.rightChild;
					tempNode.leftChild = tempNode.leftChild.leftChild;
				}
				else {
					largerTemp.rightChild = tempNode.leftChild.rightChild;
					tempNode.leftChild = tempNode.leftChild.rightChild.leftChild.rightChild;
				}
			}
			
		}
		else{	//if rightChild child is the node to be removed, same as previous,
			//but on the right child instead
			System.out.println ("Removing at " + tempNode.rightChild.data);
			if (tempNode.rightChild.rightChild == null) {
				if ( tempNode.rightChild.leftChild == null)
					tempNode.rightChild = null;
				else
					tempNode.rightChild = tempNode.rightChild.leftChild;
			}
			else {
				Node<T> largerTemp = tempNode.rightChild.rightChild;
				if (largerTemp.leftChild != null) {
					while  ( largerTemp.leftChild.leftChild != null)
						largerTemp = largerTemp.leftChild;
					largerTemp.leftChild.leftChild = tempNode.rightChild.leftChild;
					tempNode.rightChild.leftChild = largerTemp.leftChild;
					largerTemp.leftChild = tempNode.rightChild.leftChild.rightChild;
					tempNode.rightChild.leftChild.rightChild = tempNode.rightChild.rightChild;
					tempNode.rightChild = tempNode.rightChild.leftChild;
				}
				else {
					largerTemp.rightChild = tempNode.rightChild.rightChild;
					tempNode.rightChild = tempNode.rightChild.rightChild.leftChild.rightChild;
				}
			}
			
		}
	}
	
	/*
	 *	clears this tree.
	 */
	public void clear() {
		headNode = null;
	}
	
	//----------------------------------------------------------------------------
	/**
	 *	Checks to see if this class is managing a tree of Nodes.
	 *
	 *	@return true if tree of Nodes exists, false otherwise.
	 */
	private boolean isEmpty () {
		return (headNode == null);
	}
 
	//----------------------------------------------------------------------------
	/**
	 *	Converts the tree of Nodes, through itteration, into a String.
	 *	<p>
	 *	The order of the Node instances are printed in order.
	 *	<p>
	 *	Returns an emtpy string ("") if tree is empty.
	 *
	 *	@return String that contains all the values in the tree of Nodes.
	 */
	@Override
	public String toString() {
		if (!isEmpty())  {
			
			return inOrder(headNode);
		}
		return "";
	}
	
	public String inOrder (Node<T> tempNode)  {
		if (tempNode != null) {
			System.out.println (tempNode);
			return  inOrder(tempNode.leftChild) + " " +
			tempNode + " " +
			inOrder(tempNode.rightChild);
			
			
		}
		return "";
	}
	
	//----------------------------------------------------------------------------
	/**
	 *	Tests this BST class for possible errors.
	 *
	 *	@param args  command-line arguments in array form.
	 */
	public static void main (String... args){
		BST<Integer> bst = new BST<Integer>();
		bst.insert(2);
		System.out.println (bst + "!\n");
		bst.insert(3);
		System.out.println (bst + "!\n");
		bst.insert(4);
		System.out.println (bst + "!\n");
		bst.insert(5);
		System.out.println (bst + "!\n");
		bst.insert(1);
		bst.removeSearch(5);
		System.out.println (bst + "!\n");
		
		
	}
}
