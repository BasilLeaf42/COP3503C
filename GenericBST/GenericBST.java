// Christopher Cao (edited from source code by Sean Szumlanski)
// COP 3503, Fall 2021
// ch282858

// ====================
// GenericBST: BST.java
// ====================
// Basic binary search tree (BST) implementation that supports insert() and
// delete() operations. This framework is provided for you to modify as part of
// Programming Assignment #2.

// pre-processor directives
import java.io.*;
import java.util.*;

class Node<T extends Comparable<T>>
{
	// Replaced int with T throughout the program, as T allows for any data type
	T data;
	Node<T> left, right;
	
	Node(T data)
	{
		this.data = data;
	}
}

public class GenericBST<T extends Comparable<T>>
{
	private Node<T> root;
	
	// Functions to insert nodes into the BST
	public void insert(T data)
	{
		root = insert(root, data);
	}
	
	private Node<T> insert(Node<T> root, T data)
	{
		// Statement to insert data into the root if the BST is empty
		if (root == null)
		{
			return new Node<T>(data);
		}
		
		// Declare variable to store data value
		int tempValue = data.compareTo(root.data);
		
		// Statement to insert data to the left if it is less than the root value
		if (tempValue < 0)
		{
			root.left = insert(root.left, data);
		}
		
		// Statement to insert data to the right if it is greater than the root value
		if (tempValue > 0)
		{
			root.right = insert(root.right, data);
		}
		
		return root;
	}
	
	// Functions to delete nodes from the BST
	public void delete(T data)
	{
		root = delete(root, data);
	}
	
	private Node<T> delete(Node<T> root, T data)
	{
		// Statement to return null if the BST is empty	
		if (root == null)
		{
			return null;
		}
		
		// Declare variable to store data value
		int tempValue = data.compareTo(root.data);
		
		// Statement to perform a recursive call on the left
		if (tempValue < 0)
		{
			root.left = delete(root.left, data);
		}
		
		// Statement to perform a recursive call on the right
		if (tempValue > 0)
		{
			root.right = delete(root.right, data);
		}
		
		else
		{
			// Statement to return null if the node has no children
			// The deleted node does not have to be replaced
			if (root.left == null && root.right == null)
			{
				return null;
			}
			
			// Statement to return the right child if there is no left child
			// This will replace the deleted node with the right child
			else if (root.left == null)
			{
				return root.right;
			}
			
			// Statement to return the left child if there is no right child
			// This will replace the deleted node with the left child
			else if (root.right == null)
			{
				return root.left;
			}
			
			// Statement for when the node has two children
			// This will replace the deleted node with the highest value of the left subtree
			// and delete the space that it used to occupy
			else
			{
				root.data = findMax(root.left);
				root.left = delete(root.left, root.data);
			}
		}
		
		return root;
	}
	
	// This method assumes root is non-null, since this is only called by
	// delete() on the left subtree, and only when that subtree is not empty.
	private T findMax(Node<T> root)
	{
		// Loop to keep traversing to the right until the rightmost node is reached
		// as the greatest value in a BST will be the one furthest to the right
		while (root.right != null)
		{
			root = root.right;
		}
		
		return root.data;
	}
	
	// Functions to determine if a given value is contained in the BST
	public boolean contains(T data)
	{
		return contains(root, data);
	}
	
	private boolean contains(Node<T> root, T data)
	{
		// Statement to return false if the BST is empty or the value is not in the BST
		if (root == null)
		{
			return false;
		}
		
		// Declare variable to store data value
		int tempValue = data.compareTo(root.data);
		
		// Statement to check the left subtree if the value is less than the root
		if (tempValue < 0)
		{
			return contains(root.left, data);
		}
		
		// Statement to check the right subtree if the value is greater than the root
		if (tempValue > 0)
		{
			return contains(root.right, data);
		}
		
		// Statement to return true if the value is in the BST
		// This will happen if tempValue = 0
		else
		{
			return true;
		}
	}
	
	// Functions to print out the BST using in-order traversal
	public void inorder()
	{
		System.out.print("In-order Traversal:");
		inorder(root);
		System.out.println();
	}
	
	private void inorder(Node<T> root)
	{
		// Statement to return if the BST is empty
		if (root == null)
			return;
		
		// Print the left, then root, then right
		inorder(root.left);
		System.out.print(" " + root.data);
		inorder(root.right);
	}
	
	// Functions to print out the BST using pre-order traversal
	public void preorder()
	{
		System.out.print("Pre-order Traversal:");
		preorder(root);
		System.out.println();
	}

	private void preorder(Node<T> root)
	{
		// Statement to return if the BST is empty
		if (root == null)
			return;
		
		// Print out the root, then left, then right
		System.out.print(" " + root.data);
		preorder(root.left);
		preorder(root.right);
	}
	
	// Functions to print out the BST using post-order transversal
	public void postorder()
	{
		System.out.print("Post-order Traversal:");
		postorder(root);
		System.out.println();
	}

	private void postorder(Node<T> root)
	{
		// Statement to return if the BST is empty
		if (root == null)
			return;
		
		// Print out the left, then right, then root
		postorder(root.left);
		postorder(root.right);
		System.out.print(" " + root.data);
	}

	// Main function commented out in compliance with the instructions .pdf
	/* public static void main(String [] args)
	{
		GenericBST<Integer> myTree = new GenericBST<Integer>();

		for (int i = 0; i < 5; i++)
		{
			int r = (int)(Math.random() * 150) + 1;
			System.out.println("Inserting " + r + "...");
			myTree.insert(r);
		}

		myTree.inorder();
		myTree.preorder();
		myTree.postorder();
	} */
	
	public static double difficultyRating()
	{
		return 1.0;
	}
	
	public static double hoursSpent()
	{
		return 1.0;
	}
}
