/**
 * File name: BinarySearchTree.java
 * Author: Lily Chua Li Nee
 * Date:11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This is a class for BinarySearchTree
 * @param <E>
 */

public class BinarySearchTree<E extends Comparable <E>>{
	protected BSTNode<E> root;
	private int size;
	int count = 0;

	public BinarySearchTree(){
		root = null;
		size = 0;
	}

	public BinarySearchTree(BSTNode<E> node){
		root = node;
		size = 1;
	}

	/** searches for a node that contains it.
	 * if it finds it, it returns that node
	 * else it returns null
	 *@param it - the element to look for
	 *@return the node that contains it
	 */
	public BSTNode<E> search(E it){
		BSTNode<E> node = root;
		while (node !=null) {
			int result = it.compareTo(node.getElement());
			if (result <0) {
				node = node.getLeft();
			}else if (result >0) {
				node = node.getRight();
			}else {
				return node; 
			} 
			count ++;
		}
		return null;
	}

	/**determines if the tree contains the element 
	 * @return true if it is in the tree
	 */
	public boolean contains(E it){
		boolean b = false;
		if (search(it) == null) {
			System.out.println(it + " is not in the tree.");

		}else if (it == search(it).getElement()){
			System.out.println(it + " is not in the tree.");
			b = true;
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
		BSTNode<E> newNode = new BSTNode<E>(it);
		if(root == null){
			root = newNode;
			return;
		}
		BSTNode<E> parent = null;
		BSTNode<E> node = root;
		while(node != null){
			parent = node;
			int compareResult = it.compareTo(node.getElement());
			if(compareResult < 0){
				node = node.getLeft();
			}
			else if(compareResult > 0){
				node = node.getRight();
			}
			else{
				//duplicate
				return;
			}
		}

		int result = it.compareTo(parent.getElement());
		if (result < 0){
			parent.setLeft(newNode);
		}
		else{
			parent.setRight(newNode);	
		}
		size++;
	}


	/**Removes the node that contains it.  
	 * If the tree does not contain it, it prints that to 
	 * the user and does nothing else.
	 * Otherwise it removes the node and maintains the 
	 * BST properties
	 * if removing a node with two children, replace it 
	 * with its inorder predecessor.
	 * @param the element of the node you want to remove.
	 */
	public void remove(E it){
		BSTNode<E> node = root;
		BSTNode<E>parent = null;
		BSTNode<E>child=null;
		while(node!=null&&node.getElement()!=it) {
			parent = node;
			int result = it.compareTo(node.getElement());
			if(result<0) {
				node=node.getLeft();
			}else {
				node=node.getRight();
			}
		}
		if(node==null) {
			return;
		}

		if(node.isLeaf()) {
			if(parent==null) {
				node=null;
			}else if(it.compareTo(parent.getElement())<0) {
				parent.setLeft(null);
			}else {
				parent.setRight(null);
			}
		}else if(node.getLeft()==null) {
			child=node.getRight();
			swapElements(node, child);
			node.setLeft(child.getLeft());
			node.setRight(child.getRight());
		}else if(node.getRight()==null) {
			child=node.getLeft();
			swapElements(node, child);
			node.setLeft(child.getLeft());
			node.setRight(child.getRight());
		}else {
			child=node.getLeft();
			parent=null;
			while(child.getRight()!=null) { 
				parent=child;
				child=parent.getRight();
			}
			if(parent==null) { 
				swapElements(node, child);
				node.setLeft(child.getLeft());
			}else { 
				swapElements(node, child);
				parent.setRight(child.getLeft());
			}
		}
		size--;
	}

	/**Returns the height of the tree
	 * if tree is empty, height is -1
	 * if tree only has one node, height is 0
	 * @return the integer height of the tree
	 *
	 */
	public int getHeight(){
		int height = -1;
		QueueList <BSTNode<E>> Q = new QueueList<BSTNode<E>>(); 
		if (empty()) { 
			return height;
		}
		Q.enqueue(root);
		while (!Q.isEmpty()) {
			int nodeCount = Q.size();
			height++;
			while(nodeCount>0) {
				BSTNode<E> node = Q.dequeue();
				if (node.getLeft() != null) {
					Q.enqueue(node.getLeft());
				}
				if (node.getRight() !=null) {
					Q.enqueue(node.getRight());
				}
				nodeCount--;
			}
		}
		return height;
	}

	/**
	 * check if tree is empty
	 * @return
	 */
	private boolean empty() {
		if (root == null) {
			return true;
		}else {
			return false;
		}

	}

	/** Helper method
	 * For removal you need to swap elements of nodes
	 * @param node1 , node2 the nodes whose contents you are swapping
	 */
	private void swapElements(BSTNode<E> node1, BSTNode<E> node2){
		node1.setElement(node2.getElement());
		node2.setElement(null);
	}

	/**prints each level of the tree on its own line 
	 * use your Queue class
	 */
	public void printLevelOrder(){
		QueueList<BSTNode<E>>queue=new QueueList<BSTNode<E>>();
		if(size==0) {
			System.out.println("The tree is empty.");
		}
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			int nodeCount = queue.size();
			while(nodeCount>0) {
				BSTNode<E>node=queue.dequeue();
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


	/**prints the tree in a depth-first fashion
	 * use your Stack class
	 */
	public void printByDepth(){
		StackList<BSTNode<E>> stack = new StackList<BSTNode<E>>();
		if(size==0) {
			System.out.println("The tree is empty.");
		}
		stack.push(root);
		while(!stack.isEmpty()) {
			int nodeCount = stack.size();
			while(nodeCount>0) {
				BSTNode<E>node=stack.pop();
				System.out.println(node.getElement().toString());
				if(node.getRight()!=null) {
					stack.push(node.getRight());
				}
				if(node.getLeft()!=null) {

					stack.push(node.getLeft());
				}
				nodeCount--;
			}
		}
	}

	/**prints the tree in an inorder fashion. 
	 * uses a stack to push left children onto the stack
	 */
	public void printInOrder(){
		StackList<BSTNode<E>> stack = new StackList<BSTNode<E>>();
		BSTNode<E>node=root;
		if(size==0) {
			System.out.println("The tree is empty.");
		}
		stack.push(node);
		node=node.getLeft();
		while(!stack.isEmpty()||node!=null) {
			if(node!=null) {
				stack.push(node);
				node=node.getLeft();
			}else{
				BSTNode<E>temp=stack.pop();
				System.out.println(temp.getElement().toString());
				node=temp.getRight();
			}
		}
	}

	/**
	 * get the size of the tree
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * get the count of the search
	 * @return count
	 */
	public int getCount() {
		return count;
	}


}


