package example.demo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

import example.demo.readers.ReadWriteFromFile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoServlet extends HttpServlet {
	// initializing Solution object
	Solution solution = new Solution();
	// initializing object used for reading from and writing in a file
	ReadWriteFromFile rw = new ReadWriteFromFile();

	/**
	 * empty constructor that calls parent class constructor
	 */
	public DemoServlet() {
		super();

	}

	/**
	 * method that makes a POST request. As a result we can use the request object
	 * to access values entered by user in HTML form. Response object is used to
	 * display solution for entered data
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// parameters are received using the post method and the request object
		// we use those parameters to initialize our solution object
		solution.setWord(request.getParameter("wordToLookFor").toUpperCase());
		solution.setText(request.getParameter("textToSearch"));
		String result = "The word: " + solution.getWord() + " can be found " + solution.solution()
				+ " times in the text: " + solution.getText();
		response.getWriter().println(result);
		rw.setOutputFile(new File(
				"C:\\\\Users\\\\kneumova\\\\eclipse-workspace\\\\ServletDemo\\\\src\\\\main\\\\java\\\\results.txt"));
		rw.writeInFile(result);
	}

	/**
	 * This method is called when the GET method is used to make a request. User
	 * selects one of two radio buttons and different response is displayed
	 * accordingly
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// setting response content type, title and docType
		response.setContentType("text/html");
		String title = "Results from all previous searches";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		// get value selected by user
		String yesNo = request.getParameter("radioBtn");
		// initializing result string
		String result = "";
		// setting up input file for reading previous searches
		rw.setInputFile(new File(
				"C:\\\\Users\\\\kneumova\\\\eclipse-workspace\\\\ServletDemo\\\\src\\\\main\\\\java\\\\results.txt"));
		// creating HashSet object from read ArrayList object
		HashSet<String> resultSet = new HashSet<String>(rw.readFromFile());
		// adding read strings to result string
		for (String line : resultSet) {
			result += line + "</br>";
		}
		// displaying read text using PrintWriter object
		PrintWriter out = response.getWriter();
		if ("yes".equals(yesNo)) {
			out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
					+ "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">" + title + "</h1>\n" + "<label>"
					+ result + "</label>\n" + "</body>" + "</html>");

		} else if ("no".equals(yesNo)) {
			out.println(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
					+ "<body bgcolor = \"#f0f0f0\">\n" + "<h1 align = \"center\">" + title + "</h1>\n" + "<label>"
					+ "You have chosen not to view content of " + rw.getInputFile().getPath() + "</label> </br> <a href=\"\\ServletDemo\"> Click here to go back </a></body></html>");

		}

	}

}
