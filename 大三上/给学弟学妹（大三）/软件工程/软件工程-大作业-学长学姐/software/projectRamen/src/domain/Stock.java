package domain;

import java.util.Arrays;


/**
*  The Stock class is an entity class for the stock object.
*  Title      : Stock.java
*  <p>
*  Description:
*  <p>
*  The Stock class is an entity class for the stock object.
*  <p>
*  Stock object contains the stock information, 
*  can control whether each option is available.
*  @author  Tianyi Liu
*  @version 2.0
*/
public class Stock {
	private int[] soup;
	private int[] noodles;
	private int[] springonion;
	private int nori;
	private int chashu;
	private int boiledegg;
	//private int spiciness;
	private int noriadd;
	private int eggadd;
	private int shootsadd;
	private int chashuadd;
	
	/**
	 * The default constructor of the Stock
	 */
	public Stock() {

	}
	/**
	 * The constructor of the Stock
	 * @param soup the avalibility of soup
	 * @param noodles the avalibility of noodles
	 * @param springonion the avalibility of spring onion
	 * @param nori the avalibility of nori
	 * @param chashu the avalibility of chashu
	 * @param boiledegg the avalibility of boiled egg
	 * @param noriadd the avalibility of extra nori
	 * @param egg the avalibility of extra egg
	 * @param shoots the avalibility of extra shoots
	 * @param chashuadd the avalibility of extra chashu
	 */
	public Stock(int[] soup, int[] noodles, int[] springonion, int nori, int chashu, int boiledegg, int noriadd,
			int egg, int shoots, int chashuadd) {
		this.soup = soup;
		this.noodles = noodles;
		this.springonion = springonion;
		this.nori = nori;
		this.chashu = chashu;
		this.boiledegg = boiledegg;
		this.noriadd = noriadd;
		this.eggadd = egg;
		this.shootsadd = shoots;
		this.chashuadd = chashuadd;
	}
	/**
	 * @return the soup
	 */
	public int[] getSoup() {
		return soup;
	}
	/**
	 * @param soup the soup to set
	 */
	public void setSoup(int[] soup) {
		this.soup = soup;
	}
	/**
	 * @return the noodles
	 */
	public int[] getNoodles() {
		return noodles;
	}
	/**
	 * @param noodles the noodles to set
	 */
	public void setNoodles(int[] noodles) {
		this.noodles = noodles;
	}
	/**
	 * @return the springonion
	 */
	public int[] getSpringonion() {
		return springonion;
	}
	/**
	 * @param springonion the springonion to set
	 */
	public void setSpringonion(int[] springonion) {
		this.springonion = springonion;
	}
	/**
	 * @return the nori
	 */
	public int getNori() {
		return nori;
	}
	/**
	 * @param nori the nori to set
	 */
	public void setNori(int nori) {
		this.nori = nori;
	}
	/**
	 * @return the chashu
	 */
	public int getChashu() {
		return chashu;
	}
	/**
	 * @param chashu the chashu to set
	 */
	public void setChashu(int chashu) {
		this.chashu = chashu;
	}
	/**
	 * @return the boiledegg
	 */
	public int getBoiledegg() {
		return boiledegg;
	}
	/**
	 * @param boiledegg the boiledegg to set
	 */
	public void setBoiledegg(int boiledegg) {
		this.boiledegg = boiledegg;
	}
	/**
	 * @return the noriadd
	 */
	public int getNoriadd() {
		return noriadd;
	}
	/**
	 * @param noriadd the noriadd to set
	 */
	public void setNoriadd(int noriadd) {
		this.noriadd = noriadd;
	}
	/**
	 * @return the eggadd
	 */
	public int getEggadd() {
		return eggadd;
	}
	/**
	 * @param eggadd the eggadd to set
	 */
	public void setEggadd(int eggadd) {
		this.eggadd = eggadd;
	}
	/**
	 * @return the shootsadd
	 */
	public int getShootsadd() {
		return shootsadd;
	}
	/**
	 * @param shootsadd the shootsadd to set
	 */
	public void setShootsadd(int shootsadd) {
		this.shootsadd = shootsadd;
	}
	/**
	 * @return the chashuadd
	 */
	public int getChashuadd() {
		return chashuadd;
	}
	/**
	 * @param chashuadd the chashuadd to set
	 */
	public void setChashuadd(int chashuadd) {
		this.chashuadd = chashuadd;
	}
}
