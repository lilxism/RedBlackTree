/**
 * File name: BSTNode.java
 * Author: Lily Chua Li Nee
 * Date:11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This is a class for BSTNode<E>
 * @param <E>
 */
public class BSTNode<E> {
	private E element;        // Value for this node
	private BSTNode<E> left;     // reference to left child
	private BSTNode<E> right;	//reference to right child
	public BSTNode(E it, BSTNode<E> l, BSTNode<E> r){ 
		element = it;  
		left = l;
		right = r;

	}
	
	/**
	 * constructor with parameter
	 * @param it
	 */
	public BSTNode(E it){
		element = it;
		left = null;
		right = null;

	}
	
	/**
	 * Get element
	 * @return element
	 */
	public E getElement() {
		return element;
	}

	/**
	 * Set the element
	 * @param element
	 */
	public void setElement(E element) {
		this.element = element;
	}

	/**
	 * Get the left element 
	 * @return left
	 */
	public BSTNode<E> getLeft() {
		return left;
	}

	/**
	 * Set the left Element
	 * @param left
	 */
	public void setLeft(BSTNode<E> left) {
		this.left = left;
	}

	/**
	 * Get the right element
	 * @return right
	 */
	public BSTNode<E> getRight() {
		return right;
	}
	
	/**
	 * Set the right element
	 * @param right
	 */
	public void setRight(BSTNode<E> right) {
		this.right = right;
	}


	/**
	 * also have a method to determine if the node is a leaf or has children
	 * @return boolean
	 */
	public Boolean isLeaf(){
		if(getLeft()==null&&getRight()==null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * check if the node has left
	 * @return boolean
	 */
	public boolean hasLeft(){
		if(getLeft()!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * check if the node has right
	 * @return boolean
	 */
	public boolean hasRight(){
		if(getRight()!=null) {
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
		return element.toString();
	}


}
