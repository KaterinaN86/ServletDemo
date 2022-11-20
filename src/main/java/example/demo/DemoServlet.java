package example.demo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

import example.demo.readers.ReadWriteFromFile;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * sends response by displaying content of specified file or redirects client request depending on chosen option
 * implements doGet method
 * @author kneumova
 *
 */
public class DemoServlet extends HttpServlet {

	// initializing object used for reading from and writing in a file
	ReadWriteFromFile rw = new ReadWriteFromFile();

	/**
	 * empty constructor that calls parent class constructor
	 */
	public DemoServlet() {
		super();

	}
	/**
	 * method called when GET request is made by the client
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// setting response content type, title and docType
		response.setContentType("text/html");
		//String variables used to format response
		String title = "Results from all previous searches";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
		//initializing result string
		String result = "";
		// get value selected by user
		String selected = request.getParameter("radioBtn");
				
		// setting up input file for reading previous searches
		rw.setInputFile(new File(
				"C:\\\\Users\\\\kneumova\\\\eclipse-workspace\\\\ServletDemo\\\\src\\\\main\\\\java\\\\results.txt"));
		// creating HashSet object from read ArrayList object
		HashSet<String> resultSet = new HashSet<String>(rw.readFromFile());
		// adding read strings to result string
		for (String line : resultSet) {
			result += line + "</br>";
		}
		// initializing PrintWriter object
		PrintWriter out = response.getWriter();
		//check if user wants to view previous searches using radio button value
		if ("view".equals(selected)) {
			//content of specified file is displayed using PrintWriter object
			out.println(docType + "<html><head><title>" + title + "</title></head><body></br>"
					+ "<h1 align = \"center\">" + title + "</h1></br><label>" + result
					+ "</label></br><label>You have chosen to view content of " + rw.getInputFile().getPath()
					+ "</label> </br> <a href=\" \\ServletDemo \"> Click here to go back </a></body></html>");

		} 
		//check if user has selected radio button to confirm entry of new search
		else if ("new".equals(selected)) {
			//using response object to send client request for newSearch.html
			response.sendRedirect("newSearch.html");
		}

	}

}
