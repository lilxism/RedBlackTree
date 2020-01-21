/**
 * File name: TestRB.java
 * Author: Lily Chua Li Nee
 * Date:11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This java file contains the main method for testing red black tree.
 * 
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TestRB {
	public static void main(String[]args) throws IOException {

		//Read File

		ArrayList<String>list = new ArrayList<String>();
		list.add("Peculiar");
		list.add("Crapo");
		list.add("Accident");
		list.add("Eau Claire");
		list.add("Boring");
		list.add("Hell");
		list.add("Walla Walla");
		list.add("Surprise");
		list.add("Joseph");
		list.add("Romance");
		list.add("Mars");
		list.add("Nuttsville");
		list.add("Rough and Ready");
		list.add("Dynamite");
		list.add("Good Grief");
		list.add("Zarephath");

		//create RBTree
		RBTree<String> rb = new RBTree<String>();
		for(int i=0;i<list.size();i++) {
			System.out.println("Insert "+list.get(i)+" : ");
			rb.insert(list.get(i));
			System.out.println("By Level:");
			rb.printLevelOrder();
			System.out.println("size = "+rb.getSize());
			System.out.println("Height = "+rb.getHeight());
			System.out.println("");
		}

		System.out.println("REMOVE Eau Claire");
		rb.remove("Eau Claire");
		rb.printLevelOrder();
		System.out.println("size = "+rb.getSize());
		System.out.println("Height = "+rb.getHeight());
		System.out.println("");

		System.out.println("REMOVE Accident");
		rb.remove("Accident");
		rb.printLevelOrder();
		System.out.println("size = "+rb.getSize());
		System.out.println("Height = "+rb.getHeight());
		System.out.println("");

		System.out.println("REMOVE Rough and Ready");
		rb.remove("Rough and Ready");
		rb.printLevelOrder();
		System.out.println("size = "+rb.getSize());
		System.out.println("Height = "+rb.getHeight());
		System.out.println("");

		System.out.println("Clear all");
		rb.clear();
		rb.printLevelOrder();
		System.out.println("size = "+rb.getSize());
		System.out.println("Height = "+rb.getHeight());
		System.out.println("");

		//after sorting in ascending order
		System.out.println("After sorting in ascending order");
		Collections.sort(list);
		for(int i=0;i<list.size();i++) {
			System.out.println("Insert "+list.get(i)+" : ");
			rb.insert(list.get(i));
			System.out.println("By Level:");
			rb.printLevelOrder();
			System.out.println("size = "+rb.getSize());
			System.out.println("Height = "+rb.getHeight());
			System.out.println("");
		}
	}
}
