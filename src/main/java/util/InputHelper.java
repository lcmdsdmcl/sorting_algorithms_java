package util;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputHelper {
	// Static methods typically take all they data from parameters 
	// and compute something from those parameters, with no reference 
	// to variables. 
	public static String getInput(String prompt) {
		// An InputStreamReader is a bridge from byte streams to character streams: 
		// It reads bytes and decodes them into characters using a specified charset.
		// BufferedReader: Reads text from a character-input stream, buffering characters so as  
		// to provide for the efficient reading of characters, arrays, and lines.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print(prompt);
		System.out.flush();
		
		try {
			// readLine(): returns the string containing the line read from the console
			return br.readLine();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public static double getDoubleInput(String prompt) {
		String input = getInput(prompt);
		return Double.parseDouble(input); // or valueOf()
	}
	
	public static int getIntegerInput(String prompt) {
		String input = getInput(prompt);
		return Integer.parseInt(input);
	}
}