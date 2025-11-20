package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class CSVWriter {
	  private BufferedWriter writer;  // writer used to write text to the CSV file

	    public CSVWriter(String filePath) {
	        try {
	            // create the directory if it does not exist
	            File file = new File(filePath);
	            file.getParentFile().mkdirs();    
	            
	            writer = new BufferedWriter(new FileWriter(filePath));  // open the file for writing
	            writer.write("Algorithm,n,trial,TimeMs"); // write the CSV header line
	            writer.newLine(); // move to the next line
	        } catch (IOException e) {
	            e.printStackTrace(); // print error if file creation fails
	        }
	    }

	    public void writeLine(String line) {
	        try {
	            writer.write(line); // write one line to the CSV file
	            writer.newLine(); // go to the next line
	        } catch (IOException e) {
	            e.printStackTrace(); // error writing the line
	        }
	    }

	    public void close() {
	        try {
	            writer.close(); // close the file when done
	        } catch (IOException e) {
	            e.printStackTrace(); // error closing the file
	        }
	    }

}