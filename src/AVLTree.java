/**
 * File name: AVLTree.java
 * Author: Lily Chua Li Nee
 * Date:11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This is a class for AVLTree. It contains constructors, insert method, remove method, 
 * search method, printLevelOrder method and other helper methods. 
 * @param <E>
 */
public class AVLTree<E extends Comparable <E>>{
	private AVLNode<E> root;//root of the tree
	private int size;//size of the tree
	private static final int ALLOWED_IMBALANCE= 1;
	private int count=0;

	/**
	 * Constructor
	 */
	public AVLTree(){
		root = null;
		size = 0;
	}
	/**
	 * Constructor
	 * @param node - new node
	 */
	public AVLTree(AVLNode<E> node){
		root = node;
		size = 1;
	}

	/**determines if the tree is empty
	 */
	public boolean isEmpty(){
		return (root == null);
	}

	/** searches for a node that contains it.
	 * if it finds it, it returns that node
	 * else it returns null
	 *@param it - the element to look for
	 *@return the node that contains it
	 */
	public AVLNode<E> search(E it){
		AVLNode<E> node=root;
		while(node!=null) {
			count++;
			if(it.compareTo(node.getElement())==0) {
				return node;
			}else if(it.compareTo(node.getElement())>0){
				node=node.getRight();
			}else {
				node=node.getLeft();
			}
		}
		return null;
	}

	/**determines is the tree contains the element 
	 * @return true if it is in the tree
	 */
	public boolean contains(E it){
		boolean b=false;
		if(search(it)==null) {
			System.out.println(it+" is not in the tree.");
		}else if(it==search(it).getElement()) {
			System.out.println(it+" is in the tree.");
			b= true;
		}
		return b;
	}

	/**Add the element to the correct location
	 * all elements to the left are less than the parent
	 * all elements to the rights are greater than the parent
	 * Do not allow duplicates
	 * @param it the element to insert
	 */
	public void insert(E it){
		root=insert(it, root);
		size++;
	}

	/**
	 * Internal method to insert into a subtree
	 * @param it
	 * @param node
	 * @return the new root
	 */
	public AVLNode<E> insert(E it, AVLNode<E>node){
		if(node==null) {
			return new AVLNode<E>(it);
		}
		int compareResult =it.compareTo(node.getElement());
		if(compareResult<0) {
			node.setLeft(insert(it,node.getLeft()));
		}else if(compareResult>0) {
			node.setRight(insert(it,node.getRight()));
		}else {
			//duplicate
		}

		return balance(node);
	}

	/**
	 * Assume node is wither balanced or within one of being unbalanced
	 * @param node
	 * @return node
	 */
	public AVLNode<E>balance(AVLNode<E>node){
		if(node==null) {
			return node;
		}
		if(height(node.getLeft())-height(node.getRight())>ALLOWED_IMBALANCE) {
			if(height(node.getLeft().getLeft())>=height(node.getLeft().getRight())) {
				node = rotateWithLeftChild(node);
			}else {
				node=doubleWithLeftChild(node);
			}
		}else if(height(node.getRight())-height(node.getLeft())>ALLOWED_IMBALANCE) {
			if(height(node.getRight().getRight())>=height(node.getRight().getLeft())){
				node=rotateWithRightChild(node);
			}else {
				node=doubleWithRightChild(node);
			}
		}
		node.setHeight(Math.max(height(node.getLeft()),height(node.getRight()))+1);
		return node;
	}

	/**
	 * Rotate binary tree node with left child
	 * For AVL trees, this is a single rotation for case 1
	 * Update heights, then return new root
	 */
	public AVLNode<E>rotateWithLeftChild(AVLNode<E>k2){
		AVLNode<E>k1=k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		k2.setHeight(Math.max(height(k2.getLeft()),height(k2.getRight()))+1);
		k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight())+1);
		return k1;
	}

	/**
	 * Rotate binary tree node with right child
	 * For AVL trees, this is a single rotation for case 1
	 * Update heights, then return new root
	 */
	public AVLNode<E>rotateWithRightChild(AVLNode<E>k1){
		AVLNode<E>k2=k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		k1.setHeight(Math.max(height(k1.getRight()),height(k1.getLeft()))+1);
		k2.setHeight(Math.max(height(k2.getRight()), k1.getHeight())+1);
		return k2;
	}

	/** 
	 * Double rotate binary tree node: first right child
	 * with its right child then node k3 with new left child
	 * For AVL Trees, this is a double rotation for case 2
	 * Update heights then return new node
	 */
	public AVLNode<E> doubleWithRightChild(AVLNode<E>k1){
		k1.setRight(rotateWithLeftChild(k1.getRight()));
		return rotateWithRightChild(k1);
	}

	/**
	 * Double rotate binary tree node: first left child
	 * with its right child then node k3 with new left child
	 * For AVL Trees, this is a double rotation for case 2
	 * Update heights then return new node
	 */
	public AVLNode<E> doubleWithLeftChild(AVLNode<E>k3){
		k3.setLeft(rotateWithRightChild(k3.getLeft()));
		return rotateWithLeftChild(k3);
	}

	/**
	 * Remove method
	 * @param it
	 */
	public void remove(E it){
		root=remove(it, root);
		size--;
	}

	/**
	 * Helper method
	 * Removes the node that contains it.  
	 * If the tree does not contain it, it prints that to 
	 * the user and does nothing else.
	 * Otherwise it removes the node and maintains the 
	 * BST properties
	 * if removing a node with two children, replace it 
	 * with its inorder predecessor.
	 * @param it
	 * @param node
	 * @return
	 */
	public AVLNode<E>remove(E it, AVLNode<E>node){
		if(node==null) {
			return node;
		}
		int compareResult = it.compareTo(node.getElement());
		if(compareResult<0) {
			node.setLeft(remove(it, node.getLeft()));
		}else if(compareResult>0) {
			node.setRight(remove(it,node.getRight()));
		}else if(node.getLeft()!=null && node.getRight()!=null) {
			node.setElement(findMax(node.getLeft()).getElement());
			node.setLeft(remove(node.getElement(),node.getLeft()));
		}else {
			node=(node.getLeft()!=null)?node.getLeft():node.getRight();
		}
		return balance(node);
	}

	/**
	 * helper method to find the maximum node
	 * @param node
	 * @return node
	 */
	public AVLNode<E> findMax(AVLNode<E>node) {
		if(node==null) {
			return node;
		}
		while(node.getRight()!=null) {
			node=node.getRight();
		}
		return node;
	}

	/**Returns the height of the tree
	 * if tree is empty, height is -1
	 * if tree only has one node, height is 0
	 * @return the integer height of the tree
	 *
	 */
	public int getHeight(){
		if(root==null) {
			return -1;
		}else if(size==1) {
			return 0;
		}else {
			return Math.max(height(root.getLeft())+1, height(root.getRight()))+1;
		}
	}

	/**
	 * Return the height of node
	 */
	public int height(AVLNode<E>node) {
		return node==null?-1:node.getHeight();
	}

	/**prints each level of the tree on its own line 
	 * use your Queue class
	 */
	public void printLevelOrder(){
		QueueList<AVLNode<E>>queue=new QueueList<AVLNode<E>>();
		if(isEmpty()) {
			System.out.println("The tree is empty.");
		}
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			int nodeCount = queue.size();
			while(nodeCount>0) {
				AVLNode<E>node=queue.dequeue();
				System.out.print(node.getElement().toString()+" ");
				if(node.getLeft()!=null) {
					queue.enqueue(node.getLeft());
				}
				if(node.getRight()!=null) {
					queue.enqueue(node.getRight());
				}
				nodeCount--;
			}
			System.out.println(" ");
		}
	}
	/**
	 * TO get the size
	 * @return size
	 */
	public int getSize(){
		return size;
	}

	/**
	 * TO get the count
	 * @return count
	 */
	public int getCount(){
		return count;
	}
}