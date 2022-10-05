package dmv_quiz;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.*;


public class Parser {
	
	/* json files should be of the format 
		[
			{
				"question":questionText,
				"answers": [	
					correctAnswer, 
					falseAnswer, 
					falseAnswer
				]
			},
			{
				"question":questionText,
				"answers": [	
					correctAnswer, 
					falseAnswer, 
					falseAnswer
				]
			}
		]
	 */
	
	
	//returns and array of question objects from the json filename
	public static Question[] parse(String filename) {
		JSONArray arr = new JSONArray(readFile(filename));
		Question[] qArr = new Question[arr.length()];
		for (int i = 0; i < arr.length(); i++) {
			qArr[i] = makeQuestion(arr.getJSONObject(i));
		}
		return qArr;
	}
	
	private static String readFile(String filename) {
		String returnString = "";
		try {
			File myObj = new File(filename);
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
	
	private static Question makeQuestion(JSONObject j) {
		
		
		JSONArray answerArr = j.getJSONArray("answers");
		int k = answerArr.length();
		String text = j.getString("question");
		String[] answers = new String[k];
		
		for (int i = 0; i < k; i++) {
			answers[i] = answerArr.getString(i);
		}
		Question q = new Question(text, answers);
		return q;
	}
	
	
}
