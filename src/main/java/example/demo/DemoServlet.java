package example.demo;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoServlet extends HttpServlet {

	Solution solution = new Solution();
	ArrayList<String> inputStrings = new ArrayList<String>();

	/**
	 * empty constructor that calls parent class constructor
	 */
	public DemoServlet() {
		super();

	}

	/**
	 * method that makes a POST request. As a result we can use the request object
	 * to get the values entered by the user in the HTML form
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// parameters are received from URL using the post method and the request object
		// we use those parameters to initialize our solution object
		solution.setWord(request.getParameter("wordToLookFor"));
		solution.setText(request.getParameter("textToSearch"));
		response.getWriter().println("The word: " + solution.getWord() + " can be found " + solution.solution()
				+ " times in the text: " + solution.getText());
	}

}
