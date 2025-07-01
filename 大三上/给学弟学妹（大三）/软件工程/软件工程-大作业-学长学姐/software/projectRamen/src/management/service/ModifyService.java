package management.service;

import management.domain.Menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import datautil.JSONController;

/**
 * This is a control class for modify account.
 *
 * <p> This class provides service related to modification, like changing 
 * prices, stocks, poster and recommended add-on and other operations .
 * 
 * @author Xiaotian Wang
 *
 */
public class ModifyService {
	
	private Menu menu = new Menu();
	private JSONController json_price = new JSONController("price.txt");
	private JSONController json_stock = new JSONController("stock.txt");
	private JSONController json_rec = new JSONController("rec.txt");
	
	/**
	 * Initialize ModifyService
	 */
	public ModifyService() {
		Map<String, Double> price = (Map<String, Double>) json_price.read(Map.class);
		Map<String, Integer> stock = (Map<String, Integer>) json_stock.read(Map.class);
		Map<String, String> rec = (Map<String, String>) json_rec.read(Map.class);
		
		menu.setDishesPrice(price);
		menu.setStock(stock);
		menu.setRec(rec);
	}
	
	
	/**
	 * Get price of a specific dish directly
	 * 
	 * @param name name of a dish
	 * @return price
	 */
	public double getOnePrice(String name) {
		return menu.getOnePrice(name);
	}
	
	/**
	 * Get stock of a specific dish directly
	 * 
	 * @param name name of a dish
	 * @return stock (1 if available; 0 if unavailable)
	 */
	public int getOneStock(String name) {
		return menu.getOneStock(name);
	}
	
	/**
	 * Get all the price information as a map
	 * 
	 * @return a {@code Map<String, Double>} contains price information
	 */
	public Map<String, Double> getAllPrice(){
		return menu.getDishesPrice();
	}
	
	/**
	 * Get all the stock information as a map
	 * 
	 * @return a {@code Map<String, Integer>} contains stock information
	 */
	public Map<String, Integer> getAllStock(){
		return menu.getStock();
	}
	
	/**
	 * Get poster and recommended add-on informationn as a map
	 * 
	 * @return a {@code Map<String, String>} contains two keys:
	 * "poster": the path of poster; "addon": the recommended add-on.
	 */
	public Map<String, String> getRec(){
		return menu.getRec();
	}
	
	/**
	 * Check price format
	 * 
	 * @param number price entered
	 * @return {@code true} if right; {@code false} if wrong
	 */
	public boolean checkPriceFormat(String number) {
		String rex = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";

		Pattern p = Pattern.compile(rex);
		Matcher m = p.matcher(number);
		return m.find(); 
	}
	
	/**
	 * Check price range
	 * 
	 * @param number price entered
	 * @param max max value of price
	 * @return {@code true} if right; {@code false} if wrong
	 */
	public boolean checkPriceRange(String number, double max) {
		Double price = Double.parseDouble(number);
		
		return (price<=max);
	}
	
	/**
	 * Change price
	 * 
	 * @param name the name of a dish
	 * @param input price entered
	 * @return 1
	 */
	public int changePrice(String name, String input) {
		Double price = Double.parseDouble(input);
		menu.setOnePrice(name, price);
		return 1;
	}
	
	/**
	 * Change stock
	 * 
	 * @param name the name of a dish
	 * @param stock stock state entered (1 if available; 0 if unavailable)
	 * @return 1 
	 */
	public int changeStock(String name,  int stock) {
		menu.setOneStock(name, stock);
		return 1;
	}
	
	/**
	 * Change information in rec
	 * 
	 * @param name key ("poster" or "addon")
	 * @param value value (poster path or recommended add-on name)
	 * @return 1
	 */
	public int changeRec(String name, String value) {
		menu.getRec().put(name, value);
		return 1;
	}
	
	/**
	 * File copy operation
	 * 
	 * @param source source file
	 * @param dest destination file
	 * @throws IOException
	 */
	private static void cpFile(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}
	
	/**
	 * Save price
	 * 
	 * @return 1 if successful; 0 if unsuccessful
	 */
	public int savePrice() {
		
		try {
			json_price.write(menu.getDishesPrice());
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
			
		
	}
	
	/**
	 * Save stock
	 * 
	 * @return 1 if successful; 0 if unsuccessful
	 */
	public int saveStock() {
		try {	
			json_stock.write(menu.getStock());
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * Save recommendation add-on
	 * 
	 * @return 1 if successful; 0 if unsuccessful
	 */
	public int saveRec() {
		try {
			json_rec.write(menu.getRec());
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}		
	}
	
	/**
	 * Save poster path
	 * 
	 * @return 1 if successful; 0 if unsuccessful
	 */
	public int savePoster() {
		saveRec();
		File f1=new File(menu.getRec().get("poster"));
		File f2=new File("src/Ramen.png");
		try {
			cpFile(f1,f2);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	

}