package example.demo.readers;

import example.demo.exceptions.*;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteFromFile {

	private String inputFilePath;
	private File inputFile;
	private File outputFile;
	private FileReader fileReader = null;

	private BufferedReader reader = null;

	public ReadWriteFromFile(String filePath) {
		this.inputFilePath = filePath;
		this.inputFile = new File(filePath);
		this.outputFile = new File("src\\main\\resources\\results.txt");
	}

	public ReadWriteFromFile() {
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public void setInputFile(File input) {
		this.inputFile = input;
	}

	public File getInputFile() {
		return this.inputFile;
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public FileReader getFileReader() {
		return fileReader;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public ArrayList<String> readFromFile() {
		ArrayList<String> text = new ArrayList<String>();
		String line;
		boolean isEmpty = true;

		try {
			// if fileReader is not initialized properly FileNotFoundException is thrown
			fileReader = new FileReader(this.inputFile);
			// if we don't initialize our reader properly we get NullPointerException
			reader = new BufferedReader(fileReader);
			while ((line = reader.readLine()) != null) {
				if (line != "")
					isEmpty = false;
				text.add(line);
			}
			// if every line was empty this variable will still have the initial value
			// "true"
			if (isEmpty)
				throw new EmptyFileException("Specified file is empty");
		} catch (NullPointerException | IOException | EmptyFileException e) {
			try {
				if (fileReader == null)
					throw new IncorrectFilePathException("Error in input file path!", e);
				if (reader == null)
					throw new ReaderNotInitializedException("Reader not initialized!", e);
				System.out.println(e.getMessage());

			} catch (ReaderNotInitializedException | IncorrectFilePathException ex) {
				System.out.println(ex.getMessage());

			}
		} finally {
			try {
				if (reader != null)
					reader.close();
				else
					throw new ReaderNotInitializedException("Reader not initialized!");
			} catch (ReaderNotInitializedException | IOException e) {
				if (reader == null)
					e.getMessage();
				else
					System.out.println("File not closed.");
			}
		}
		return text;
	}

	public void writeInFile(String text) {
		FileWriter fileWriter = null;
		BufferedWriter writer = null;

		try {
			fileWriter = new FileWriter(this.outputFile, true);
			writer = new BufferedWriter(fileWriter);
			writer.write(text);
			writer.newLine();

		} catch (NullPointerException | IOException e) {
			try {
				if (fileWriter == null)
					throw new IncorrectFilePathException("Error in output file path!", e);
				if (writer == null)
					throw new ReaderNotInitializedException("Writer not initialized!", e);
				System.out.println(e.getMessage());
				e.getMessage();
			} catch (ReaderNotInitializedException | IncorrectFilePathException ex) {
				System.out.println(ex.getMessage());
				ex.getMessage();
			}
		} finally {
			try {
				if (writer != null)
					writer.close();
				else
					throw new ReaderNotInitializedException("Writer not initialized!");
			} catch (ReaderNotInitializedException | IOException e) {
				if (writer == null)
					e.getMessage();
				else
					System.out.println("File not closed.");
			}
		}

	}
}
