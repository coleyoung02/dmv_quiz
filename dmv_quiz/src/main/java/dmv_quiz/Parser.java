package dmv_quiz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.*;



public class Parser {
	
	private static String readFile(String filename) {
		String returnString = "";
		try {
			File myObj = new File("q.json");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				returnString += data;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File error occured.");
			e.printStackTrace();
		}
		return returnString;
	}
	
	
	public static Question[] parse(String filename) {
		JSONArray arr = new JSONArray(readFile(filename));
		Question[] qArr = new Question[arr.length()];
		for (int i = 0; i < arr.length(); i++) {
			
			qArr[i] = new Question();
		}
		return qArr;
	}
}
