package management.domain;

import java.util.*;


import domain.Order;
import domain.Payment;

/**
 * This is a entity class contains information about statistics.
 * 
 * <p>This class contains menu informations including these attributes and corresponding get and set methods:<br>
 * {@code orders} and {@code payments} : they are {@link Order} and {@link Payment} list to be done statistics <br>
 * {@code soup}, {@code noodles} ,etc.: they are sales volume of different types of dishes 
 * {@code pyMethods}: how many times has each payment method been used
 * {@code lastWeekSale}: last week sales (money) used to generate chart {@link management.util.Chart} 
 * 
 * 
 * @author Xiaotian Wang
 *
 */

public class Stats {
	
	private List<Order> orders = new ArrayList<>();
	private List<Payment> payments = new ArrayList<>();
	
	private Map<Integer, Integer> soup = new HashMap<>();
	private Map<Integer, Integer> noodles = new HashMap<>();
	private Map<Integer, Integer> springonion = new HashMap<>();
	
	private Map<Integer, Integer> nori = new HashMap<>();
	private Map<Integer, Integer> chashu = new HashMap<>();
	private Map<Integer, Integer> boiledegg = new HashMap<>();
	
	private int noriadd;
	private int eggadd;
	private int shootsadd;
	private int chashuadd;
	
	private Map<Integer, Integer> spiciness = new HashMap<>();
	
	private Map<String, Integer> pyMethods = new HashMap<>();
	
	private double[][]  lastWeekSale = new double[1][7];

	/**
	 * a method to add an order to the {@code orders} 
	 * 
	 * @param order an order {@link Order}
	 */
	 public void addOrder(Order order) {
		 this.orders.add(order);
	 }
 
	 /**
	  * get method of {@code orders} attribute
	  * 
	  * @return a List of {@link Order}
	  */
	 public List<Order> getOrders(){
		 return this.orders;
	 }
 
	 /**
	  * set method of {@code orders} attribute
	  * 
	  * @param orders a List of {@link Order}
	  */
	 public void setOrders(List<Order> orders) {
		 this.orders = orders;
	 }
	 /**
	  * a method to add a payment to the {@code payments} 
	  * 
	  * @param payment a payment {@link Payment}
	  */
	 public void addPayment(Payment payment) {
		 this.payments.add(payment);
	 }
 
	 /**
	  * get method of {@code payments} attribute
	  * 
	  * @return a List of {@link Payment}
	  */
	 public List<Payment> getPayments(){
		 return this.payments;
	 }

	 /**
	  * set method of {@code payments} attribute
	  * 
	  * @param payments a List of {@link Payment}
	  */
	public void setPayments(List<Payment> payments) {
		 this.payments = payments;
	}

	/**
	 *get method of {@code noriadd} attribute
	 *
	 * @return the sales volume of Extra Nori
	 */
	public int getNoriadd() {
		return noriadd;
	}
	
	/**
	 * set method of {@code noriadd} attribute
	 * 
	 * @param noriadd the sales volume of Extra Nori
	 */
	public void setNoriadd(int noriadd) {
		this.noriadd = noriadd;
	}
	
	/**
	 * get method of {@code eggadd} attribute
	 * 
	 * @return the sales volume of Extra boiled egg
	 */
	public int getEggadd() {
		return eggadd;
	}
	
	/**
	 * set method of {@code eggadd} attribute
	 * 
	 * @param eggadd the sales volume of Extra boiled egg
	 */
	public void setEggadd(int eggadd) {
		this.eggadd = eggadd;
	}
	
	/**
	 *get method of {@code shootsadd} attribute
	 *
	 * @return the sales volume of Bamboo shoots
	 */
	public int getShootsadd() {
		return shootsadd;
	}
	
	/**
	 * set method of {@code shootsadd} attribute
	 * 
	 * @param shootsadd the sales volume of Bamboo shoots
	 */
	public void setShootsadd(int shootsadd) {
		this.shootsadd = shootsadd;
	}
	
	/**
	 * get method of {@code chashuadd} attribute
	 * 
	 * @return the sales volume of Extra Chashu
	 */
	public int getChashuadd() {
		return chashuadd;
	}
	
	/**
	 * set method of {@code chashuadd} attribute
	 * 
	 * @param chashuadd the sales volume of Extra Chashu
	 */
	public void setChashuadd(int chashuadd) {
		this.chashuadd = chashuadd;
	}
	
	/**
	 * get method of {@code soup} attribute
	 * 
	 * @return the sales volume of different kinds of soup
	 */
	public Map<Integer, Integer> getSoup() {
		return soup;
	}
	
	/**
	 * set method of {@code soup} attribute
	 * 
	 * @param soup the sales volume of different kinds of soup
	 */
	public void setSoup(Map<Integer, Integer> soup) {
		this.soup = soup;
	}
	
	/**
	 * get method of {@code noodles} attribute
	 * 
	 * @return the sales volume of different kinds of noodles
	 */
	public Map<Integer, Integer> getNoodles() {
		return noodles;
	}
	
	/**
	 * set method of {@code noodles} attribute
	 * 
	 * @param noodles the sales volume of different kinds of noodles
	 */
	public void setNoodles(Map<Integer, Integer> noodles) {
		this.noodles = noodles;
	}
	
	/**
	 * get method of {@code springonion} attribute
	 * 
	 * @return the sales volume of different options of spring onion
	 */
	public Map<Integer, Integer> getSpringonion() {
		return springonion;
	}
	
	/**
	 * set method of {@code springonion} attribute
	 * 
	 * @param springonion the sales volume of different options of spring onion
	 */
	public void setSpringonion(Map<Integer, Integer> springonion) {
		this.springonion = springonion;
	}
	
	/**
	 * get method of {@code nori} attribute
	 * 
	 * @return the sales volume of different options of nori
	 */
	public Map<Integer, Integer> getNori() {
		return nori;
	}
	
	/**
	 * set method of {@code nori} attribute
	 * 
	 * @param nori the sales volume of different options of nori
	 */
	public void setNori(Map<Integer, Integer> nori) {
		this.nori = nori;
	}
	
	/**
	 * get method of {@code chashu} attribute
	 * 
	 * @return the sales volume of different options of chashu
	 */
	public Map<Integer, Integer> getChashu() {
		return chashu;
	}
	
	/**
	 * set method of {@code chashu} attribute
	 * 
	 * @param chashu the sales volume of different options of chashu
	 */
	public void setChashu(Map<Integer, Integer> chashu) {
		this.chashu = chashu;
	}
	
	/**
	 * get method of {@code boiledegg} attribute
	 * 
	 * @return the sales volume of different options of boiled egg
	 */
	public Map<Integer, Integer> getBoiledegg() {
		return boiledegg;
	}
	
	/**
	 * set method of {@code boiledegg} attribute
	 * 
	 * @param boiledegg the sales volume of different options of boiled egg
	 */
	public void setBoiledegg(Map<Integer, Integer> boiledegg) {
		this.boiledegg = boiledegg;
	}
	
	/**
	 * get method of {@code spiciness} attribute
	 * 
	 * @return the sales volume of different options of spiciness
	 */
	public Map<Integer, Integer> getSpiciness() {
		return spiciness;
	}
	
	/**
	 * set method of {@code spiciness} attribute
	 * 
	 * @param spiciness the sales volume of different options of spiciness
	 */
	public void setSpiciness(Map<Integer, Integer> spiciness) {
		this.spiciness = spiciness;
	}
	
	/**
	 * get method of {@code pyMethods} attribute
	 * 
	 * @return the times different payment methods have been used
	 */
	public Map<String, Integer> getPyMethods() {
		return pyMethods;
	}
	
	/**
	 * set method of {@code spiciness} attribute
	 * 
	 * @param pyMethods the times different payment methods have been used
	 */
	public void setPyMethods(Map<String, Integer> pyMethods) {
		this.pyMethods = pyMethods;
	}
	
	/**
	 * get method of {@code lastWeekSale} attribute
	 * 
	 * @return last week sales data 
	 */
	public double[][] getLastWeekSale() {
		return lastWeekSale;
	}
	
	/**
	 * set method of {@code lastWeekSale} attribute
	 * 
	 * @param lastWeekSale last week sales data 
	 */
	public void setLastWeekSale(double[][] lastWeekSale) {
		this.lastWeekSale = lastWeekSale;
	}
	
	

}
