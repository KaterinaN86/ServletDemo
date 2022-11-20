package example.demo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

import example.demo.readers.ReadWriteFromFile;

/**
 * Servlet implementation class NewSearchServlet, called when user sends request
 * to enter data for new search. implementation of doPost method
 */

public class NewSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// initializing Solution object
	Solution solution = new Solution();
	// initializing object used for reading from and writing in a file
	ReadWriteFromFile rw = new ReadWriteFromFile();

	public NewSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * called when POST request is made. As a result we can use the request object
	 * to access values entered by user in HTML form. Response object is used to
	 * display solution for entered data
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setting response content type, title and docType
		response.setContentType("text/html");
		String title = "Results from new search";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		// parameters are received using the post method and the request object
		// we use those parameters to initialize our solution object
		solution.setWord(request.getParameter("wordToLookFor").toUpperCase());
		solution.setText(request.getParameter("textToSearch"));

		// String variable used to display result
		String result = "The word: " + solution.getWord() + " can be found " + solution.solution()
				+ " times in the text: " + solution.getText();
		// String variable used to format response
		String output = docType + "<html><head><title>" + title + "</title></head><body></br>"
				+ "<h1 align = \"center\">" + title + "</h1></br><label>" + result + "</label></br>";
		// link to previous page
		String goBack = "<a href=\"\\ServletDemo\"> Click here to go back </a></body></html>";
		// check if input data is correct
		System.out.println(result.contains("-1"));
		if (!result.contains("-1")) {
			// check if user wants to add new search to results file
			if ("add".equals(request.getParameter("radioBtn")))

			{
				// adding data to specified file
				rw.setOutputFile(new File(
						"C:\\\\Users\\\\kneumova\\\\eclipse-workspace\\\\ServletDemo\\\\src\\\\main\\\\java\\\\results.txt"));
				rw.writeInFile(result);
				// adding information to response
				output += "</br></br> New search added to text file.";
				output += goBack;
			} else {
				// information that will be displayed when user chooses not to save data for new
				// search
				output += goBack;
				result = "";
			}
			// displaying output
			response.getWriter().println(output);

		} else {
			// information that will be displayed when input is invalid
			output = docType + "<html><head><title>" + title + "</title></head><body></br>" + "<h1 align = \"center\">"
					+ title + "</h1></br><label>Wrong input! Please try again</label></br></br>";
			response.getWriter().println(output + goBack);
		}

	}
}
