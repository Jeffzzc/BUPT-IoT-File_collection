package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import datautil.CSVController;
import domain.DataSource;
import domain.Order;
import domain.DataSource;

/**
*  The OrderService class is a control class for the orders.
*  Title      : OrderService.java
*  <p>
*  Description: 
*  <p>
*  The OrderService class is a control class for the orders
*  <p>
*  This class provides order generation and other operations
*  @author  Tianyi Liu
*  @version 1.1
*/
public class OrderService {
	private DataSource ds;
	private String filename;

	/**
	 * @param filename the file name of the order list
	 * @param ds The datasource object
	 */
	public OrderService(DataSource ds, String filename) {
		this.ds = ds;
		this.filename = filename;
	}

	/**
	 * @return The order object
	 */
	public Order getOrder() {
		return ds.getOrder();
	}

	/**
	 * Generates the new order with ID and default values
	 * 
	 * @return The ID of the new order
	 */
	public String newOrder() {
		Date IDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Random r = new Random();
		int nowrd = r.nextInt(10) + 10;
		String newID = df.format(IDate) + nowrd;
		Order newod = new Order(newID, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, null);
		ds.setOrder(newod);
		return newID;
	}

	/**
	 * Check whether the values of the order are valid.
	 *  
	 * @return The boolean value of the result of checking
	 */
	public boolean checkValidate() {
		Order nowod = ds.getOrder();
		if (nowod.getSoup() < 0 || nowod.getSoup() > 2)
			return false;
		if (nowod.getNoodles() < 0 || nowod.getNoodles() > 2)
			return false;
		if (nowod.getBoiledegg() < 0 || nowod.getBoiledegg() > 2)
			return false;
		if (nowod.getChashu() < 0 || nowod.getChashu() > 2)
			return false;
		if (nowod.getNori() < 0 || nowod.getNori() > 2)
			return false;
		if (nowod.getSpiciness() < 0 || nowod.getSpiciness() > 5)
			return false;
		if (nowod.getSpringonion() < 0 || nowod.getSpringonion() > 2)
			return false;
		return true;
	}

	public boolean checkAvalible() {
		return true;
	}

	/**
	 * Saves the order into the file.
	 * 
	 * @return The boolean value of the result of saving
	 */
	public boolean saveOrder() {
		CSVController csv = new CSVController(filename);
		if (!this.checkValidate() && !this.checkAvalible())
			return false;
		return csv.appendWriteOne(ds.getOrder(), CSVController.getFileHeader(ds.getOrder().getClass()));
	}

	/**
	 * Sets the order object.
	 * 
	 * @param od A order object
	 */
	public void changeOrder(Order od) {
		ds.setOrder(od);
	}

	/**
	 * @return The name of the file
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename The name of the file
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

}
