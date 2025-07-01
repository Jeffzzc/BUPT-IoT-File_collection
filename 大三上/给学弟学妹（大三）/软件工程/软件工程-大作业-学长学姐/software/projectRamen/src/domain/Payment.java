package domain;

import java.util.Date;
import java.util.Objects;

import datautil.Mathutil;

/**
*  The Payment class is an entity class for the payment object.
*  Title      : Payment.java
*  <p>
*  Description:
*  <p>
*  The Payment class is an entity class for the payment object.
*  <p>
*  Payment contains the payment information, like time and discount
*  @author  Tianyi Liu
*  @version 3.3
*/
public class Payment {
	private String orderID; 
	private String method;
	private double totalPrice;
	private String memberID; 
	private Date time; 
	private int isDiscount;
	
	/**
	 * The default constructor of the Payment
	 */
	public Payment() {
		
	}
	
	/**
	 * The constructor of the Payment
	 * @param orderID    The generated ID of the order
	 * @param method     The choice of the payment method
	 * @param totalPrice The total price of the order
	 * @param memberID   The account number of the member
	 * @param time       The time of the payment 
	 * @param isDiscount The choice of using the stamps
	 */
	public Payment(String orderID, String method, double totalPrice, String memberID, Date time, int isDiscount) {
		this.orderID = orderID;
		this.method = method;
		this.totalPrice =  Mathutil.round2(totalPrice);
		this.memberID =memberID;
		this.time = time;
		this.isDiscount = isDiscount;
	}
	/**
	 * @return The generated ID of the order
	 */
	public String getOrderID() {
		return orderID;
	}
	/**
	 * @param orderID The generated ID of the order
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	/**
	 * @return The choice of the payment method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method The choice of the payment method
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return The total price of the order
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice The total price of the order
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return The account number of the member
	 */
	public String getMemberID() {
		return memberID;
	}
	/**
	 * @param memberID The account number of the member
	 */
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	/**
	 * @return The time of the payment
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * @param time The time of the payment
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * @return The choice of using the stamps
	 */
	public int getIsDiscount() {
		return isDiscount;
	}
	/**
	 * @param isDiscount The choice of using the stamps
	 */
	public void setIsDiscount(int isDiscount) {
		this.isDiscount = isDiscount;
	}
	@Override
	public String toString() {
		return "Payment [orderID=" + orderID + ", method=" + method + ", totalPrice=" + totalPrice + ", memberID="
				+ memberID + ", time=" + time + ", isDiscount=" + isDiscount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(isDiscount, memberID, method, orderID, time, totalPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Payment)) {
			return false;
		}
		Payment other = (Payment) obj;
		return isDiscount == other.isDiscount && Objects.equals(memberID, other.memberID)
				&& Objects.equals(method, other.method) && Objects.equals(orderID, other.orderID)
				&& Objects.equals(time, other.time)
				&& Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice);
	} 
}
