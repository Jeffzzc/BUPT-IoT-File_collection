package management.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * This is a timer class for report generation. 
 * 
 * <p>This timer checks whether a report should be generated every 24 hours.
 * 
 * @author Xiaotian Wang and Tianyi Liu
 *
 */
public class Generator {
	
	/**
	 * Initialize Generator
	 * 
	 * <p>Start a timer which checks whether a report should be generated every 24 hours.
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new Report(), 0, 24 * 3600 * 1000);
		System.out.println("Generator startup...");
	}
	
}
