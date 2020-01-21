/**
 * File name: AVLNode.java
 * Author: Lily Chua Li Nee
 * Date:11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This is a class for AVLNode<E>
 *  It includes the constructor and get and set methods for variables. It also have a toString method.
 * @param <E>
 */
public class AVLNode<E> {
	E element;        
	AVLNode<E> left;     
	AVLNode<E> right;	
	int height;  

	//Constructors
	public AVLNode( E it, AVLNode<E> l, AVLNode<E> r)  { 
		element = it;  
		left = l;
		right = r;
		height = 0;

	}

	/**
	 * constructor with parameter
	 * @param it
	 */
	public AVLNode(E it){
		element = it;
		left = null;
		right = null;
		height=0;

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
	public AVLNode<E> getLeft() {
		return left;
	}

	/**
	 * Set the left Element
	 * @param left
	 */
	public void setLeft(AVLNode<E> left) {
		this.left = left;
	}

	/**
	 * Get the right element
	 * @return right
	 */
	public AVLNode<E> getRight() {
		return right;
	}
	
	/**
	 * Set the right element
	 * @param right
	 */
	public void setRight(AVLNode<E> right) {
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
	 * get the height of the node
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the node with new height
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * to string method
	 * @return string
	 */
	public String toString(){
		return element.toString();
	}

}



