//
//  BST.h
//  CPP_BST
//
//  Created by Anton Velikodnyy on 7/7/17.
//  Copyright © 2017 Anton Velikodnyy. All rights reserved.
//

#ifndef BST_h
#define BST_h

/**
 *	Container for the storage of data given.
 *	node contains the links to the left and right children node
 *	instances in the tree of nodes generated by BST.
 */
template <class T>
class node {
	
public:
	
	/**
	 *	Stores a generic object.
	 */
	T data;
	
	/**
	 *	Shorthand for unique ptr managing node.
	 */
	using node_ptr = std::unique_ptr<node>;
	
	/**
	 *	Ownership pointer to left node of the tree
	 */
	node_ptr left_child;
	
	/**
	 *	Ownership pointer to right node of the tree
	 */
	node_ptr right_child;
	//----------------------------------------------------------------------------
	/**
	 *	Constructor of this {@code node} class.
	 *
	 *	@param data, reference to the object given
	 *	@param next, sets another node as the next reference of this node.
	 *	@param prev, sets another Node as the prev raw pointer reference of this Node.
	 */
	node (T &data) {
		this->data = data;
	}
	
	/**
	 *	Converts the data stored in this node to a printble string.
	 *
	 *	@return data in string form.
	 */
	std::string to_string () {
		return std::to_string(data);
	}
};


//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------
/**
 *	BST is an object which creates and manages a tree of nodes stored
 *	in memory.
 *	<p>
 *	This class accepts any data type and can only initialy access the
 *	head  of the chain of nodes it manages, and then follows the through the 
 *	tree to reach the leaf nodes.
 */
template <class T>
class BST {
	/**
	 *	Shorthand for unique ptr managing node.
	 */
	using node_ptr = std::unique_ptr<node<T>>;
public:
	
	/**
	 *	Inserts given data into the tree.
	 */
	void insert_node ( T&& data );
	
	/**
	 *	Deletes node specified by user.
	 */
	void delete_node ( T&& data );
	
	/**
	 *	Searches this tree for specific node.
	 */
	bool search_tree ( T&& data );
	
	/**
	 *	Deletes the entire tree.
	 */
	void delete_tree ()
	{		head_node.reset();	}
	
	/**
	 *	Creates a string that the data stored in this tree.
	 */
	std::string to_string ()  {
		return in_order (head_node) + "\n";
		
	}
	
private:

	/**
	 *	Ownership pointer to start of chain.
	 */
	node_ptr head_node = nullptr;
	
	/**
	 *	Recursively searches through this tree to find storage location of given data.
	 */
	node_ptr& search_node_ ( T const& data, node_ptr& current );
	
	/**
	 *	Recursively goes through the string and converts the tree to a string.
	 */
	std::string in_order ( node_ptr const& current );
};

//----------------------------------------------------------------------------
/**
 *	Recieves data which needs to be placed into the tree.
 *	Calls search_node_ to find the location at which to insert in this tree.
 *	Creates new node instance at that location.
 *
 *	@param data object given by user.
 */
template < class T >
void BST<T>::insert_node ( T&& data )  {
	auto& current = this->search_node_ ( std::forward<T> (data), head_node );
	if (!current)
		current = std::make_unique< node<T> > ( data );
}

/**
 *	Deletes the object, if found, in this tree.
 *
 *	@param data  object used as the matching key.
 */
template < class T >
void BST<T>::delete_node( T&& data )  {
	auto& current = this->search_node_ ( std::forward<T> (data), head_node );
	if ( current )
		current = nullptr;
}

/**
 *	Searches through the tree for given object.
 *
 *	@param data  object used as the matching key.
 *	@return boolean  true if found, false otherwise.
 */
template < class T >
bool BST<T>::search_tree( T&& data )  {
	if (  this->search_node_ ( std::forward<T> (data), head_node ) )
		return true;
	return false;
}


//----------------------------------------------------------------------------
/** 
 *	Recursively searches through this tree to find the given node.
 *
 *	@param data  object used as the matching key.
 *	@param current  reference to a node instance which is used to recurse through.
 *	@return location  reference of the node found, nullptr if not found.
 */
template < class T >
std::unique_ptr<node<T>>& BST<T>::search_node_ ( T const& data, node_ptr& current )  {
	if ( current )  {
		if ( current -> data > data )
			return search_node_(data, current -> left_child );
		else if ( current -> data < data )
			return search_node_( data, current -> right_child );
		else
			return current;
	}
	return current;
}

/**
 *	Recursively goes through the string and converts the tree to a string.
 *
 *	@param current  the node currently operating on.
 *	@return string  the string version of the tree.
 */
template < class T >
std::string BST<T>::in_order(node_ptr const& current)  {
	if ( current )  {
		return
				in_order(current -> left_child) +
				current -> to_string() + " " +
				in_order(current -> right_child);
	}
	return "";
}

#endif /* BST_h */