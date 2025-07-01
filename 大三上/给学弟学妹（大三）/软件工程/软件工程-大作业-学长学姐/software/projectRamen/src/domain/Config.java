package domain;

import java.util.HashMap;
import java.util.Map;

/**
*  The Config class is an entity class for the config object.
*  Title      : Config.java
*  <p>
*  Description:
*  <p>
*  The Config class is an entity class for the config object.
*  <p>
*  Config contains the price data, add-on or dishes names, etc
*  @author  Tianyi Liu
*  @version 2.1
*/
public class Config {
	private double noodlePirce;
	private double noriaddPirce;
	private double eggaddPirce;
	private double shootsaddPirce;
	private double chashuaddPirce;
	private int rec;
	private Map<String,String> nameDic;
	
	/**
	 * The constructor of the Config
	 * @param noodlePirce The price of the noodle
	 * @param noriaddPirce The price of the add-on nori
	 * @param eggaddPirce The price of the add-on egg
	 * @param shootsaddPirce The price of the add-on shoot
	 * @param chashuaddPirce The price of the add-on chashu
	 * @param rec Recommend information
	 */
	public Config(double noodlePirce, double noriaddPirce, double eggaddPirce, double shootsaddPirce,
			double chashuaddPirce,int rec) {
		this.noodlePirce = noodlePirce;
		this.noriaddPirce = noriaddPirce;
		this.eggaddPirce = eggaddPirce;
		this.shootsaddPirce = shootsaddPirce;
		this.chashuaddPirce = chashuaddPirce;
		this.rec=rec;
		nameDic=new HashMap<>();
		nameDic.put("Soup0","Tonkotsu");
		nameDic.put("Soup1","Shoyu");
		nameDic.put("Soup2","Shio");
		nameDic.put("Noodles0","Soft");
		nameDic.put("Noodles1","Medium");
		nameDic.put("Noodles2","Firm");
		nameDic.put("Spring onion0","No please");
		nameDic.put("Spring onion1","Just a little");
		nameDic.put("Spring onion2","A lot!");
	}
	
	/**
	 * The default constructor of the Config
	 * Please be careful to use this method
	 */
	public Config() {
		nameDic=new HashMap<>();
		nameDic.put("Soup0","Tonkotsu");
		nameDic.put("Soup1","Shoyu");
		nameDic.put("Soup2","Shio");
		nameDic.put("Noodles0","Soft");
		nameDic.put("Noodles1","Medium");
		nameDic.put("Noodles2","Firm");
		nameDic.put("Spring onion0","No please");
		nameDic.put("Spring onion1","Just a little");
		nameDic.put("Spring onion2","A lot!");
	}
	/**
	 * Gets the price of the noodle.
	 * 
	 * @return The price of the noodle
	 */
	public double getNoodlePirce() {
		return noodlePirce;
	}
	/**
	 * Sets the price of the noodle.
	 * 
	 * @param noodlePirce The price of the noodle
	 */
	public void setNoodlePirce(double noodlePirce) {
		this.noodlePirce = noodlePirce;
	}
	/**
	 * Gets the price of the add-on nori.
	 * 
	 * @return The price of the add-on nori
	 */
	public double getNoriaddPirce() {
		return noriaddPirce;
	}
	/**
	 * Sets the price of the add-on nori.
	 * 
	 * @param noriaddPirce The price of the add-on nori
	 */
	public void setNoriaddPirce(double noriaddPirce) {
		this.noriaddPirce = noriaddPirce;
	}
	/**
	 * Gets the price of the add-on egg.
	 * 
	 * @return The price of the add-on egg
	 */
	public double getEggaddPirce() {
		return eggaddPirce;
	}
	/**
	 * Sets the price of the add-on egg.
	 * 
	 * @param eggaddPirce The price of the add-on egg
	 */
	public void setEggaddPirce(double eggaddPirce) {
		this.eggaddPirce = eggaddPirce;
	}
	/**
	 * Gets the price of the add-on shoots.
	 * 
	 * @return The price of the add-on shoots
	 */
	public double getShootsaddPirce() {
		return shootsaddPirce;
	}
	/**
	 * Sets the price of the add-on shoots.
	 * 
	 * @param shootsaddPirce The price of the add-on shoots
	 */
	public void setShootsaddPirce(double shootsaddPirce) {
		this.shootsaddPirce = shootsaddPirce;
	}
	/**
	 * Gets the the price of the add-on chashu.
	 * 
	 * @return The price of the add-on chashu
	 */
	public double getChashuaddPirce() {
		return chashuaddPirce;
	}
	/**
	 * Sets the price of the add-on chashu.
	 * 
	 * @param chashuaddPirce The price of the add-on chashu
	 */
	public void setChashuaddPirce(double chashuaddPirce) {
		this.chashuaddPirce = chashuaddPirce;
	}
	/**
	 * set the name in the namedic
	 * @param spN The name prefix
	 * @param x The name number
	 * @return the name of the dishes or add-ons
	 */
	public String getName(String spN, int x) {
		return nameDic.get(spN+x);
	}
	
	/**
	 * change the boolean information of namedic to string
	 * @param x the boolean information
	 * @return the string information
	 */
	public String changeBoolName(int x) {
		return x==0?"No":"Yes";
	}
	/**
	 * @return the rec
	 */
	public int getRec() {
		return rec;
	}
	/**
	 * get the recommend information
	 * @param rec the rec to set
	 */
	public void setRec(int rec) {
		this.rec = rec;
	}

	/**
	 * get the name dictionary
	 * @return the nameDic
	 */
	public Map<String, String> getNameDic() {
		return nameDic;
	}

	/**
	 * set the name dictionary
	 * @param nameDic the nameDic to set
	 */
	public void setNameDic(Map<String, String> nameDic) {
		this.nameDic = nameDic;
	}
	
}
