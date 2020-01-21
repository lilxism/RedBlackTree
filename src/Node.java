/**
 * File name: Node.java
 * Author: Lily Chua Li Nee
 * Date:11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This is a class for BSTNode<E>
 * @param <E>
 */
public class Node<E> {
	private E element;
	private Node<E> next;

	/** Creates a new node with a null next field
      @param dataItem  The data stored
	 */
	public Node(E dataItem) {
		element = dataItem;
		next = null;
	}

	/** Creates a new node that references another node
      @param dataItem  The data stored
      @param nodeRef  The node referenced by new node
	 */
	public Node(E dataItem, Node<E> nodeRef) {
		element = dataItem;
		next = nodeRef;
	}


	/** sets reference to given node
      @param nodeRef  The node referenced by current node
	 */
	public void setNext(Node<E> nodeRef) {
		next = nodeRef;
	}
	/** gets reference to next node
      @return next  The node referenced by next node
	 */
	public Node<E> getNext() {
		return next;
	}
	/** sets element
       @param dataItem  The data stored
	 */
	public void setElement(E dataItem) {
		element = dataItem;
	}
	/** gets data store in node
      @return data item  stored in node
	 */
	public E getElement() {
		return element;
	}
}

