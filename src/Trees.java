/**
 * File name: Trees.java
 * Author: Lily Chua Li Nee
 * Date:11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This file contains main method that will compare the number of searches in trees.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Trees {
	public static void main(String[]args) throws IOException {

		Scanner input=new Scanner(System.in);
		if(args.length==1) {
			ReadFile rf = new ReadFile();
			ArrayList<Place>placeArr = rf.readFile(args[0]);

			//instantiate trees
			BinarySearchTree<Place>bst=new BinarySearchTree<Place>();
			AVLTree <Place> avl = new AVLTree<Place>();
			RBTree<Place>rb=new RBTree<Place>();

			//add places into the trees
			for(int i=0;i<placeArr.size();i++) {
				bst.insert(placeArr.get(i));
				avl.insert(placeArr.get(i));
				rb.insert(placeArr.get(i));
			}

			String name="";
			String ans = "";
			int total=0;

			//to get counts of each trees
			ArrayList<Integer> bstCount = new ArrayList<Integer>();
			ArrayList<Integer> avlCount=new ArrayList<Integer>();
			ArrayList<Integer> rbCount=new ArrayList<Integer>();

			//print height
			System.out.println("The height of Binary Search Tree: "+bst.getHeight());
			System.out.println("The height of AVLTree: "+avl.getHeight());
			System.out.println("The height of Red Black Tree: "+rb.getHeight()+"\n");

			while(!ans.equals("NO")) {
				System.out.print("You want to search for the city: ");
				name=input.nextLine();
				Place place = new Place(name);
				//performing searching method
				BSTNode<Place>bstSearch=bst.search(place);
				AVLNode<Place>avlSearch=avl.search(place);
				RBNode<Place>rbSearch=rb.search(place);

				//print the statement

				System.out.println("The number of comparisons needed to find the entry in BST: "+bst.getCount());
				System.out.println("The number of comparisons needed to find the entry in AVL: "+avl.getCount());
				System.out.println("The number of comparisons needed to find the entry in RB: "+rb.getCount());

				if(bstSearch==null && avlSearch==null && rbSearch==null) {
					System.out.println("There is no city by the name "+name);
				}else {
					System.out.println(bstSearch.toString());
				}

				bstCount.add(bst.getCount());
				avlCount.add(avl.getCount());
				rbCount.add(rb.getCount());

				System.out.println("");

				System.out.print("Do you want me to search again?(YES/NO): ");
				ans=input.nextLine().toUpperCase();

				if(ans.equals("NO")) {
					System.out.println("GOOD BYE!");
				}

				total++;

			}
			System.out.println("");
			System.out.println("Analysis Part: ");
			//analysis the data
			analysis(bst,avl,rb,bstCount, avlCount, rbCount, total);
			input.close();
		}else {
			System.out.println("no file found");
		}
	}
	
	/**
	 * This is a method to analysis the data
	 * Get the averages of the number of comparisons and the standard deviation 
	 * @param bst - the bst tree
	 * @param avl - the avl tree
	 * @param rb - the rb tree 
	 * @param bstCount - the arraylist consisting each search count in bst
	 * @param avlCount - the arraylist consisting each search count in avl
	 * @param rbCount - the arraylist consisting each search count in rb
	 * @param total - the total number of places searched by user
	 */
	public static void analysis(BinarySearchTree<Place>bst, AVLTree<Place>avl, RBTree<Place>rb,
			ArrayList<Integer> bstCount, ArrayList<Integer> avlCount, ArrayList<Integer> rbCount, int total) {

		double bstSum=0;
		double avlSum=0;
		double rbSum=0;

		//get total of the counts
		for(int i=0;i<bstCount.size();i++) {
			bstSum+=bstCount.get(i);
		}
		for(int i=0;i<avlCount.size();i++) {
			avlSum+=avlCount.get(i);
		}
		for(int i=0;i<rbCount.size();i++) {
			rbSum+=rbCount.get(i);
		}

		//get the average for each tree
		double bstAverage= bstSum/total;
		double avlAverage= avlSum/total;
		double rbAverage= rbSum/total;

		//get the standard deviation of the averages
		double bstSq = 0;
		double avlSq = 0;
		double rbSq = 0;
		for(int i=0;i<bstCount.size();i++) {
			bstSq+=Math.pow((bstCount.get(i)-bstAverage),2);
		}
		for(int i=0;i<avlCount.size();i++) {
			avlSq+=Math.pow((avlCount.get(i)-avlAverage),2);
		}
		for(int i=0;i<rbCount.size();i++) {
			rbSq+=Math.pow((rbCount.get(i)-rbAverage),2);
		}

		double bstSD = Math.sqrt(bstSq/total);
		double avlSD = Math.sqrt(avlSq/total);
		double rbSD = Math.sqrt(rbSq/total);

		System.out.println("The size of Binary Search Tree: "+bst.getSize());
		System.out.println("The size of AVLTree: "+avl.getSize());
		System.out.println("The size of Red Black Tree: "+rb.getSize());
		System.out.println("The average number of comparisons it took for BST: "+bstAverage);	
		System.out.println("The average number of comparisons it took for AVL: "+avlAverage);		
		System.out.println("The average number of comparisons it took for RB: "+rbAverage);		
		System.out.println("The standard deviation of BST is: "+bstSD);
		System.out.println("The standard deviation of AVL is: "+avlSD);
		System.out.println("The standard deviation of RB is: "+rbSD);

	}
}
