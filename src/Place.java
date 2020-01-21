/**
 * File name: Place.java
 * Author: Lily Chua Li Nee
 * Date: 11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This is a class for object, Place. 
 * A place include the detail of zipcodes and name of town and State.
 * There are constructors, get and set methods , comparable method and toString method.
 * @param <E>
 */
import java.util.ArrayList;

public class Place implements Comparable<Place>{
	private ArrayList<String> zipcodes;
	private String townState;
	
	/**
	 * Constructor with parameter
	 * @param zipcode
	 * @param townState
	 */
	public Place(ArrayList<String> zipcode, String townState) {
		this.zipcodes=zipcode;
		this.townState=townState;
	}
	
	/**
	 * Constructor with parameter
	 * @param townState
	 */
	public Place(String townState) {
		this.townState=townState;
	}
	
	/**
	 * Get townState
	 * @return townState
	 */
	public String getTownState() {
		return townState;
	}
	
	/**
	 * Set townState with new townState
	 * @param townState
	 */
	public void setTownState(String townState) {
		this.townState = townState;
	}
	
	/**
	 * Compare method for townState 
	 */
	public int compareTo(Place p) {
		return p.getTownState().compareTo(townState);
	}
	
	/**
	 * Get the zipcodes of the place
	 * @return
	 */
	public ArrayList<String> getZipcodes() {
		return zipcodes;
	}
	/**
	 * 
	 */
	public void setZipcode(ArrayList<String> zipcodes) {
		this.zipcodes=zipcodes;
	}
	/**
	 * add zipcode into the ArrayList
	 * @param zipcode
	 */
	public void addZipcode(String zipcode) {
		zipcodes.add(zipcode);
	}
	
	/**
	 * toString method
	 * @return string
	 */
	public String toString() {
		return "The zip code(s) that belong to "+townState+" are:"+zipcodes.toString();
	}
	
}
