/**
 * File name: RBTree.java
 * Author: Lily Chua Li Nee
 * Date:11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This is a class for Red Black Tree. 
 * @param <E>
 */

public class RBTree<E extends Comparable<E>> {
	private RBNode<E> root ;
	private int size=0;
	int count = 0;

	//constructor 
	public RBTree() {
		root = null;
		size = 0;
	}

	public RBTree(RBNode<E> node) {
		root = node;
		size = 1;
		node.setRed(false);
	}

	/**
	 * Clear all node
	 */
	public void clear() {
		root=null;
		size=0;

	}
	/** searches for a node that contains it.
	 * if it finds it, it returns that node
	 * else it returns null
	 *@param it - the element to look for
	 *@return the node that contains it
	 */
	public RBNode<E> search(E it){
		RBNode<E> node = root;

		while (node !=null) {
			int result = it.compareTo(node.getElement());
			if (result <0) {
				node = node.getLeft();
			}else if (result >0) {
				node = node.getRight();
			}else {
				return node; 

			}
			count++;
		}	
		return null;
	}

	/**
	 * Method to insert item into RBTree
	 * @param - new item to insert
	 */
	public void insert(E it) {
		if (root == null) {
			root = new RBNode<E>(it);
			root.setRed(false);
		}
		RBNode<E> n = root;
		while (true) {
			int comparisonResult = it.compareTo(n.getElement());
			if (comparisonResult == 0) {
				n.setElement(it);
				size++;
				return;
			} else if (comparisonResult < 0) {
				if (n.getLeft() == null) {
					n.setLeft(new RBNode<E>(it));
					colorChangeAfterInsertion(n.getLeft());
					break;
				}
				n = n.getLeft();
			} else {
				if (n.getRight() == null) {
					n.setRight(new RBNode<E>(it));
					colorChangeAfterInsertion(n.getRight());
					break;
				}
				n = n.getRight();
			}
		}
		size++;
	}

	/**
	 * This is that method to remove element
	 * @param it - the element to be removed
	 */
	public void remove(E it) {
		RBNode<E> node = (RBNode<E>) nodeContaining(it);
		if (node == null) { // doesn't exist
			return;
		} else if (node.getLeft() != null && node.getRight() != null) { // has
			// has two children, get predecessor data
			RBNode<E> predecessor = predecessor(node);
			node.setElement(predecessor.getElement());
			node = predecessor;
		}
		// node has zero or one child
		RBNode<E> pullUp = leftOf(node) == null ? rightOf(node) : leftOf(node);
		if (pullUp != null) {
			// adjust and check for double black.
			if (node == root) {
				setRoot(pullUp);
			} else if (node.getParent().getLeft() == node) {
				node.getParent().setLeft(pullUp);
			} else {
				node.getParent().setRight(pullUp);
			}
			if (!node.isRed()) {
				colorChangeAfterRemoval(pullUp);
			}
		} else if (node == root) { // deleted root, empty tree
			setRoot(null);
		} else {
			if (!node.isRed()) {
				colorChangeAfterRemoval(node);
			}
			node.removeFromParent();
		}
		size--;
	}

	/**
	 * This method is a helper method to insert to adjust the color of the node. when first inserting the node n,
	 * set the color to red. Other if/ else if scenarios to recolor node if necessary.
	 * @param n
	 */
	private void colorChangeAfterInsertion(RBNode<E> n) {
		// color node red
		n.setRed(true);
		// fix double red problems
		if (n != null && n!=root && n.getParent().isRed()) {
			// recolor and move node
			if (siblingOf(n.getParent())!=null&&siblingOf(n.getParent()).isRed()) {
				n.getParent().setRed(false);
				siblingOf(n.getParent()).setRed(false);
				grandparentOf(n).setRed(true);
				colorChangeAfterInsertion(grandparentOf(n));
			} else if (n.getParent() == leftOf(grandparentOf(n))) {
				if (n == rightOf(n.getParent())) {
					rotateLeft(n =n.getParent());
				}
				n.getParent().setRed(false);
				grandparentOf(n).setRed(true);
				rotateRight(grandparentOf(n));
			} else if (n.getParent() == rightOf(grandparentOf(n))) {
				if (n == leftOf(n.getParent())) {
					rotateRight(n = n.getParent());
				}
				n.getParent().setRed(false);
				grandparentOf(n).setRed(true);
				rotateLeft(grandparentOf(n));
			}
		}
		root.setRed(false);
	}

	/**
	 * this method is a helper method to the remove method to help recolor after 
	 * a removal.
	 * @param n
	 */
	private void colorChangeAfterRemoval(RBNode<E> n) {
		while (n != root && !n.isRed()) {
			if (n == leftOf(n.getParent())) {
				// node is a left child
				RBNode<E> sibling = rightOf(n.getParent());
				if (sibling.isRed()) {
					sibling.setRed(false);
					n.getParent().setRed(true);
					rotateLeft(n.getParent());
					sibling = rightOf(n.getParent());
				}
				if (!leftOf(sibling).isRed() && !rightOf(sibling).isRed()) {
					sibling.setRed(true);
					n =n.getParent();
				} else {
					if (!rightOf(sibling).isRed()) {
						leftOf(sibling).setRed(false);
						sibling.setRed(true);
						rotateRight(sibling);
						sibling = rightOf(n.getParent());
					}
					if (!n.getParent().isRed()) {
						sibling.setRed(false);
					} else {
						sibling.setRed(true);
					}
					n.getParent().setRed(false);
					rightOf(sibling).setRed(false);
					rotateLeft(n.getParent());
					n = root;
				}
			} else {
				// node is a right child
				RBNode<E> sibling = leftOf(n.getParent());
				if (sibling.isRed()) {
					sibling.setRed(false);
					n.getParent().setRed(true);
					rotateRight(n.getParent());
					sibling = leftOf(n.getParent());
				}
				if (!leftOf(sibling).isRed() && !rightOf(sibling).isRed()) {
					sibling.setRed(true);
					n = n.getParent();
				} else {
					if (!leftOf(sibling).isRed()) {
						rightOf(sibling).setRed(false);
						sibling.setRed(true);
						rotateLeft(sibling);
						sibling = leftOf(n.getParent());
					}
					if (!n.getParent().isRed()) {
						sibling.setRed(false);
					} else {
						sibling.setRed(true);
					}
					n.getParent().setRed(false);
					leftOf(sibling).setRed(false);
					rotateRight(n.getParent());
					n = root;
				}
			}
		}
		n.setRed(false);
	}
	/**
	 * passes in parameter n and returns grandparent of n.
	 * @param n
	 * @return
	 */
	private RBNode<E> grandparentOf(RBNode<E> n) {
		return (n == null || n.getParent() == null) ? null : n.getParent().getParent();
	}
	/**
	 * passes in param n and returns sibling of n
	 * @param n
	 * @return
	 */
	private RBNode<E> siblingOf(RBNode<E> n) {
		return (n == null || n.getParent() == null) ? null
				: (n == n.getParent().getLeft()) ? n.getParent().getRight() : n.getParent().getLeft();
	}

	/**
	 * this method rotates the tree left
	 * @param n
	 */
	private void rotateLeft(RBNode<E> n) {
		if (n.getRight() == null) {
			return;
		}
		RBNode<E> oldRight = n.getRight();
		n.setRight(oldRight.getLeft());
		if (n.getParent() == null) {
			root = oldRight;
		} else if (n.getParent().getLeft() == n) {
			n.getParent().setLeft(oldRight);
		} else {
			n.getParent().setRight(oldRight);
		}
		oldRight.setLeft(n);
	}

	/**
	 * this method rotates the tree right
	 * @param n
	 */
	private void rotateRight(RBNode<E> n) {
		if (n.getLeft() == null) {
			return;
		}
		RBNode<E> oldLeft = n.getLeft();
		n.setLeft(oldLeft.getRight());
		if (n.getParent() == null) {
			root = oldLeft;
		} else if (n.getParent().getLeft() == n) {
			n.getParent().setLeft(oldLeft);
		} else {
			n.getParent().setRight(oldLeft);
		}
		oldLeft.setRight(n);
	}

	private void setRoot(RBNode<E> node) {
		if (node != null) {
			node.removeFromParent();
		}
		root = node;
	}
	/**
	 * method to get predecessor of a node. n is set to node.getLeft and if n is not null, while n.getRight is not null
	 * keep going right.
	 * @param node
	 * @return
	 */
	private RBNode<E> predecessor(RBNode<E> node) {
		RBNode<E> n = node.getLeft();
		if (n != null) {
			while (n.getRight() != null) {
				n = n.getRight();
			}
		}
		return n;
	}
	/**
	 * node that passes in data and looks for node cotaining this data.
	 * @param data
	 * @return
	 */
	private RBNode<E> nodeContaining(E data) {
		for (RBNode<E> n = root; n != null;) {
			int comparisonResult = data.compareTo(n.getElement());
			if (comparisonResult == 0) {
				return n;
			} else if (comparisonResult < 0) {
				n = n.getLeft();
			} else {
				n = n.getRight();
			}
		}
		return null;
	}
	/**
	 * returns node left of n.
	 * @param n
	 * @return
	 */
	private RBNode<E> leftOf(RBNode<E> n) {
		return n == null ? null : n.getLeft();
	}
	/**
	 * returns node right of n.
	 * @param n
	 * @return
	 */
	private RBNode<E> rightOf(RBNode<E> n) {
		return n == null ? null : n.getRight();
	}
	
	
	/**prints each level of the tree on its own line 
	 */
	public void printLevelOrder(){
		QueueList<RBNode<E>>queue=new QueueList<RBNode<E>>();
		if(size==0) {
			System.out.println("The tree is empty.");
		}else {
			queue.enqueue(root);
			while(!queue.isEmpty()) {
				int nodeCount = queue.size();
				while(nodeCount>0) {
					RBNode<E>node=queue.dequeue();
					System.out.print(node.toString()+" ");
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
	}
	/**
	 * Get the size of the tree
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**Returns the height of the tree
	 * if tree is empty, height is -1
	 * if tree only has one node, height is 0
	 * @return the integer height of the tree
	 *
	 */
	public int getHeight(){
		int height = -1;
		QueueList <RBNode<E>> Q = new QueueList<RBNode<E>>(); 
		if (root == null) { 
			return height;
		}else {
			Q.enqueue(root);
			while (!Q.isEmpty()) {
				int nodeCount = Q.size();
				height++;
				while(nodeCount>0) {
					RBNode<E> node = Q.dequeue();
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
	}



	/**
	 * Get the count
	 * @return count of the search
	 */
	public int getCount() {
		return count;
	}

}
