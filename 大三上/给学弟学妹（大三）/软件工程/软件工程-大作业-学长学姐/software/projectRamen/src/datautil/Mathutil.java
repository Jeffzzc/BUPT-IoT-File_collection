package datautil;

/**
*  The Mathutil class provides some simple static methods about math
*  Title      : Mathutil.java
*  <p>
*  Description:
*  <p>
*  The Mathutil class provides some simple static methods about math.
*  @author  Tianyi Liu
*  @version 1.0
*/
public class Mathutil {

	/** 
	* Used to preserve two decimal places
	* @param x The original number
	* @return The number after keeping a two-digit decimal
	*/
	public static double round2(double x) {
		return (double)(Math.round(x*100)/100.0);
	}
}
