package management.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a entity class contains information about menu.
 * 
 * <p>This class contains menu informations including these attributes and corresponding get and set methods: <br>
 * {@code price}: a {@code HashMap<String, Double>} contains prices of different dishes<br>
 * {@code stock}: a {@code HashMap<String, Integer>} contains stock statuses of different dishes<br>
 * {@code rec}: a {@code HashMap<String, String>} contains the path of the poster image and recommended add-ons. 
 * They are stored in two keys: "poster" and "addon". 
 * 
 * @author Xiaotian Wang
 *
 */
public class Menu {
	private Map<String, Double> price = new HashMap<>();
	private Map<String, Integer> stock = new HashMap<>();
	private Map<String, String> rec = new HashMap<>();
	

	/**
	 * get method of {@code price} attribute 	
	 *
	 * @return a {@code Map<String, Double>} contains prices of dishes
	 */
	public Map<String, Double> getDishesPrice() {
		return price;
	}
	/**
	 * 
	 * get method of {@code stock} attribute 	
	 * 
	 * @return a {@code Map<String, Integer>} contains stock statuses of dishes
	 */
	public Map<String, Integer> getStock() {
		return stock;
	}
	
	/**
	 * set method of {@code price} attribute
	 * 
	 * @param price a {@code Map<String, Double>} contains prices of dishes you want to set
	 */
	public void setDishesPrice(Map<String, Double> price) {
		this.price = price;
	}
	
	/**
	 * set method of {@code stock} attribute 
	 * 
	 * @param stock a {@code Map<String, Integer>} contains stock statuses of dishes you want to set 
	 */
	public void setStock(Map<String, Integer> stock) {
		this.stock = stock;
	}
	/**
	 * 	get method of price of a specific dish 
	 * 
	 * @param  name a {@code String} represents the name of a dish
	 * @return the {@code double} price of the dish
	 */
	public double getOnePrice(String name) {
		return price.get(name);
	}
	/**
	 * get method of stock status of a specific dish	
	 * 
	 * @param name a {@code String} represents the name of a dish
	 * @return the {@code int} stock status of the dish (1: available; 0: unavailable)
	 */
	public int getOneStock(String name) {
		return stock.get(name);
	}
	/**
	 * set method of price of a specific dish 	
	 * 
	 * @param name a {@code String} represents the name of a dish
	 * @param price the {@code double} price of the dish you want to set
	 */
	public void setOnePrice(String name, double price) {
		this.price.put(name, price);
	}
	/**
	 * 	set method of stock status of a specific dish	
	 * 
	 * @param name a {@code String} represents the name of a dish
	 * @param status the {@code int} stock status of the dish you want to set (1:available, 0:unavailable)
	 */
	public void setOneStock(String name, int status) {
		this.stock.put(name, status);
	}

	/**
	 * get method of {@code rec} attribute
	 * 
	 * @return a {@code Map<String, String>} contains information about poster and recommended dishes
	 */
	public Map<String, String> getRec() {
		return rec;
	}

	/**
	 * set method of {@code rec} attribute
	 * 
	 * @param rec a {@code Map<String, String>} contains information about poster and recommended dishes you want to set
	 */
	public void setRec(Map<String, String> rec) {
		this.rec = rec;
	}


}
