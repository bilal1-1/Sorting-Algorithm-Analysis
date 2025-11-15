package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class CSVWriter {
	  private BufferedWriter writer;

	    public CSVWriter(String filePath) {
	        try {
	            writer = new BufferedWriter(new FileWriter(filePath));
	            writer.write("Algorithm,n,trial,TimeMs");
	            writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void writeLine(String line) {
	        try {
	            writer.write(line);
	            writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void close() {
	        try {
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}