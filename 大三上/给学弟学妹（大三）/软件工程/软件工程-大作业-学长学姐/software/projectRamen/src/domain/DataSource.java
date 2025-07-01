package domain;
/**
*  The DataSource class is an entity class to aggregate other entity classes.
*  Title      : DataSource.java
*  <p>
*  Description:
*  <p>
*  The DataSource class is an entity class to aggregate other entity classes.
*  <p>
*  @author  Tianyi Liu
*  @version 1.0
*/
public class DataSource {
	private Order order;
	private Member member;
	private Payment payment;
	private Stock stock;
	private Config config;
	public static final String orderfilename="orderlist.csv";
	public static final String paymentfilename="paymentlist.csv";
	public static final String memberfilename="member.csv";
	/**
	 * The default constructor of the DataSource
	 * @param order   The order object
	 * @param member  The member object
	 * @param payment The payment object
	 * @param stock   The stock object
	 * @param config  The config object
	 */
	public DataSource(Order order, Member member, Payment payment, Stock stock, Config config) {
		this.order = order;
		this.member = member;
		this.payment = payment;
		this.stock = stock;
		this.config = config;
	}
	/**
	 * The default constructor of the DataSource
	 */
	public DataSource() {
		
	}
	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}
	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}
	/**
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}
	/**
	 * @param payment the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	/**
	 * @return the config
	 */
	public Config getConfig() {
		return config;
	}
	/**
	 * @param config the config to set
	 */
	public void setConfig(Config config) {
		this.config = config;
	}
}
