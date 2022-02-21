package main.java.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Is used to define regular needed Regex-Patterns.
 * It also provides a method for validating Strings.
 * @author Leander
 *
 */

public class Regex {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	
	public static final Pattern VALID_DATE_FORMAT_DD_MM_YYYY = 
			Pattern.compile("^([1-9]|0[1-9]|[12][0-9]|3[01])[-\\.]([1-9]|0[1-9]|1[012])[-\\.](19|20)\\d\\d$");
	
	// accepts dd-MM-YYYY; dd.MM.YYYY; dd/MM/YYYY; dd-MM-YY,....
//			Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|"
//					+ "^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|"
//					+ "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
	
	
	
	public static final Pattern VALID_TIME_FORMAT_HH_MM = 
			Pattern.compile("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$|^([0-1]?[0-9]|2[0-3]).[0-5][0-9]$");
	
	// 0,75; 0.75; 1.5
	public static final Pattern VALID_TIME_FORMAT_H_DECIMAL = 
			Pattern.compile("^\\d+[,]\\d{2}$|^\\d+[.]\\d{2}$");
	
	/**
	 * Checks if a given String matches a given Pattern.
	 * @param value The String to check against the pattern.
	 * @param pattern The Regex-Pattern to run against the given String.
	 * @return true if String matches Pattern, false if not.
	 * @author Leander
	 */
	public static boolean validate(String value, Pattern pattern) {
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}
			
}