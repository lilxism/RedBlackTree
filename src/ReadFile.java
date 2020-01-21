/**
 * File name: ReadFile.java
 * Name: Lily Chua Li Nee
 * Date: 11/26/2017
 * Purpose of the code: Homework 4 Comparing Trees
 * Description: This java file is created to read file and tokenize the places from zips.txt.
 * It will read in zipcode and townState. New Place will be created using the zipcode and townState read from file.
 * The place will then added into the arraylist.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class ReadFile {
	
	/**
	 * Read the zipcode and townState
	 * @param filename
	 * @return placeArr-ArrayList containing places
	 */
	public ArrayList<Place> readFile(String filename) {
		ArrayList<Place> placeArr = new ArrayList<Place>();
		String line = null;
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line=bufferedReader.readLine())!=null) {
				if(line.charAt(0)!='#') {
					String[]token=line.split("\\t");
					String zipcode=token[0];
					String townState=token[3];
					ArrayList<String>zipcodes=new ArrayList<String>();
					zipcodes.add(zipcode);
					Place place = new Place(zipcodes,townState);
					placeArr.add(place);
					for(int i=0;i<placeArr.size();i++) {
						if(townState.equals(placeArr.get(i).getTownState())&&place!=placeArr.get(i)) {
							placeArr.get(i).getZipcodes().add(zipcode);
							placeArr.remove(place);
						}
					}
				}
			}
			bufferedReader.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return placeArr;
	}
}
