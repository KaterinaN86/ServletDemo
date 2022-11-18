package example.demo;

import java.util.ArrayList;

public class Solution {
	
	/**
	 * inner class to help better track results by creating an object that has both the shortened string and a boolean that indicates weather the whole word has been found
	 * @author kneumova
	 *
	 */

	class Result {
		public String result;
		public boolean isShortened;

		public Result(String result, boolean isShortened) {
			this.result = result;
			this.isShortened = isShortened;
		}
	}

	/**
	 * the word that is being searched for
	 */
	private String word;
	/**
	 * the String to search through
	 */
	private String text;

	public Solution() {
		super();
	}

	public Solution(String word) {
		this.word = word.toUpperCase();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Helper method that removes a letter from the input string by using the index
	 *
	 * @param s     (input String)
	 * @param index (position of letter to be removed)
	 * @return (String without specified letter
	 */
	private String trimInputString(String s, int index) {
		return s.substring(0, index) + s.substring(index + 1);
	}

	Result shortenInputString(String input, String word) {
		// variable that stores the shortened string
		String result = input;
		// letters counter
		int count = 0;
		// looping over the specified word
		for (char letter : word.toCharArray()) {
			int index = result.indexOf(letter);
			// if letter is not found index is -1
			if (index < 0) {
				// return value when a letter form the specified word is not found
				return new Result(input, false);
			}
			// input string without found letter
			result = trimInputString(result, index);
			// incrementing counter of found letters
			count++;
		}
		// control variable that will be set to true if all letters have been found
		boolean shortenWholeWord = word.length() == count;
		// checking if the value of the control variable is false
		if (!shortenWholeWord) {
			// return value when the whole word has not been found
			return new Result(input, false);
		}
		// return value when specified word has been found in the string and the
		// corresponding letters have been removed
		return new Result(result, true);
	}

	private boolean validateInputString(String in) {
		// the length of the string is validated first
		if (in.length() >= 0 && in.length() <= 200000) {
			// checking if the string is made up of letters only
			if (in.matches("[A-Z]+"))
				return true;
			else {
				System.out.println("String must contain only uppercase letters!");
				return false;
			}
		}
		System.out.println("String length is out of bounds!");
		return false;
	}

	/**
	 * Method that counts appearances of specified word in the specified text
	 * If the string is invalid -1 is returned	 
	 * @return int (number of times the word has been found)
	 */
	public int solution() {
		//we initialize this variable to the specified text to search through
		//all changes will be performed on this variable, not on the class property
		String s = this.text;
		if (!validateInputString(s)) {
			System.out.println("The string " + s + " is not valid as input!");
			return -1;
		}
		// control variable for counting how many times the specified word has been
		// found
		int result = 0;
		// the loop will run until the if statement is entered
		while (true) {
			Result r = shortenInputString(s, this.word);
			// if a letter is missing isShortened property of Result object will be false
			// and the loop is exited
			if (!r.isShortened) {
				break;
			}
			// incrementing variable that counts how many times the word has been found
			result++;
			// input string is reinitialized to the trimmed string without found letters
			s = r.result;
		}
		// number of times the word has been found
		return result;
	}

}
