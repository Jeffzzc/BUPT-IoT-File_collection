package service;


import java.util.Date;

import datautil.CSVController;
import domain.Config;
import domain.DataSource;
import domain.Member;
import domain.Order;
import domain.Payment;

/**
*  The PaymentService class is a control class for the payment.
*  Title      : OrderService.java
*  <p>
*  Description: 
*  <p>
*  The PaymentService class is a control class for the payment
*  <p>
*  This class provides payment generation and price computing
*  @author  Tianyi Liu
*  @version 1.1
*/
public class PaymentService {
	String filename;
	private DataSource ds;
	/**
	 * @param filename the file name of the payment list
	 * @param ds The datasource object
	 */
	public PaymentService(String filename, DataSource ds) {
		this.filename = filename;
		this.ds = ds;
	}
	
	/**
	 * @return The payment object
	 */
	public Payment getPayment() {
		return ds.getPayment();
	}
	/**
	 * @param pm The payment object
	 */
	public void setPayment(Payment pm) {
		ds.setPayment(pm);
	}
	/**
	 * Generates a new payment object with order ID and other default information.
	 * 
	 * @param method The choice of the payment method
	 */
	public void newPayment(String method) {
		Order od=ds.getOrder();
		Payment npm=new Payment(od.getOrderID(), method, 0, "", new Date(), 0);
		ds.setPayment(npm);
	}
	/**
	 * Using the discount by setting to 1.
	 */
	public void setDiscount() {
		ds.getPayment().setIsDiscount(1);
	}
	/**
	 * Gets the value of whether using the discount.
	 * 
	 * @return The value of isDiscount
	 */
	public int isDiscount() {
		return ds.getPayment().getIsDiscount();
	}
	/**
	 * Calculates the total price of the order.
	 * 
	 * @return The total price
	 */
	public double getPrice() {
		Config cfg=ds.getConfig();
		Order od=ds.getOrder();
		double ret=0;
		ret+=cfg.getNoodlePirce();
		ret+=od.getChashuadd()*cfg.getChashuaddPirce();
		ret+=od.getEggadd()*cfg.getEggaddPirce();
		ret+=od.getNoriadd()*cfg.getNoriaddPirce();
		ret+=od.getShootsadd()*cfg.getShootsaddPirce();
		if(isDiscount()==1) {
			//ret=ret-cfg.getNoodlePirce();
			ret=0;
		}
		ds.getPayment().setTotalPrice(ret);
		return ret;
	}
	/**
	 * Gets the price without using the discount.
	 * 
	 * @return The price without using the discount
	 */
	public double getNoDisPrice() {
		int now=ds.getPayment().getIsDiscount();
		ds.getPayment().setIsDiscount(0);
		double ans=getPrice();
		ds.getPayment().setIsDiscount(now);
		return ans;
	}
	/**
	 * Saves the payment object into the file.
	 * 
	 * @return The boolean value of the result of saving
	 */
	public boolean savePayment() {
		CSVController csv=new CSVController(filename);
		setMember();
		return csv.appendWriteOne(getPayment(), 
				CSVController.getFileHeader(ds.getPayment().getClass()));
	}
	/**
	 * Sets the payment with member ID
	 */
	public void setMember() {
		Member mb=ds.getMember();
		if(mb==null)return;
		ds.getPayment().setMemberID(mb.getMemberID());
	}
	
	public String getFilename() {
		return filename;
	}
	
	
	
}
