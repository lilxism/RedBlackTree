/**
 * File name: RBNode.java
 * Author: Lily Chua Li Nee
 * Date:11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This is a class for RBNode<E>
 * @param <E>
 */

public class RBNode<E>{
	private E element;
	private RBNode<E> left;
	private RBNode<E> right;
	private RBNode<E> parent;
	private boolean isRed;

	/**
	 * Constructors with parameter
	 * @param it - element to be added into node
	 */
	public RBNode(E it) {
		element = it;
		left = null;
		right = null;
		isRed = true;
	}
	
	/**
	 * Get the color of the node
	 * @return boolean
	 */
	public boolean isRed() {
		return isRed;
	}
	
	/**
	 * Set the color of the node
	 * @param isRed
	 */
	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}
	
	/**
	 * Set the right element
	 * @param element
	 */
	public void setRight(RBNode<E>child) {
		RBNode<E> childNode = child;
		// break old links, reconnect properly.
		if (right != null) {
			right.parent = null;
		}
		if (childNode != null) {
			childNode.removeFromParent();
			childNode.parent = this;
		}
		this.right = childNode;
	}
	
	/**
	 * method to node to left child
	 * @param child
	 */
	public void setLeft(RBNode<E> child) {
		RBNode<E> childNode = child;
		// break old links, reconnect properly.
		if (this.left != null) {
			left.parent = null;
		}
		if (childNode != null) {
			childNode.removeFromParent();
			childNode.parent = this;
		}
		this.left = childNode;
	}
	
	/**
	 * method to remove a child from the parent node.
	 */
	public void removeFromParent() {
		if (parent != null) {
			if (parent.left == this) {
				parent.left = null;
			} else if (parent.right == this) {
				parent.right = null;
			}
			this.parent = null;
		}
	}
	
	/**
	 * Get the right element
	 * @return right
	 */
	public RBNode<E> getRight() {
		return right;
	}
	
	/**
	 * Get the left element 
	 * @return left
	 */
	public RBNode<E> getLeft() {
		return left;
	}
	
	/**
	 * check if the node has parent
	 * @return boolean
	 */
	public boolean hasParent() {
		if(parent!=null) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * to string method
	 * @return string
	 */
	public String toString(){
		if(isRed) {
			return "(R:"+element.toString()+") ";
		}else {
			return "(B:"+element.toString()+") ";
		}
	}

	/**
	 * method to return element in node.
	 * @return
	 */
	public E getElement() {
		return element;
	}
	/**
	 * method that passes in param it and returns element as it.
	 * @param it
	 * @return
	 */
	public E setElement(E it) {
		return element = it;
	}
	
	/**
	 * Get the parent of the node
	 * @return parent
	 */
	public RBNode<E> getParent() {
		return parent;
	}

	/**
	 * Set the parent with new parent
	 * @param new parent
	 */
	public void setParent(RBNode<E> parent) {
		this.parent = parent;
	}
}
