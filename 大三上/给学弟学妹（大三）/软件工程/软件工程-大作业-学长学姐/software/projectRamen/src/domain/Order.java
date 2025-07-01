package domain;

import java.util.Objects;

/**
*  The Order class is an entity class for the order object.
*  Title      : Order.java
*  <p>
*  Description:
*  <p>
*  The Order class is an entity class for the order object.
*  <p>
*  Order contains the order information, like price and in-store/take-out
*  @author  Tianyi Liu
*  @version 3.3
*/
public class Order {
	private String orderID; 
	private int soup;
	private int noodles;
	private int springonion;
	private int nori;
	private int chashu;
	private int boiledegg;
	private int spiciness;
	private int noriadd;
	private int eggadd;
	private int shootsadd;
	private int chashuadd;
	private String inOrout;
	
	/**
	 * The default constructor of the Order
	 * Please be careful to use this method
	 */
	public Order() {
		
	}
	

	/**
	 * The constructor of the Order
	 * @param orderID     The generated ID of the order
	 * @param soup        The choice of the soup
	 * @param noodles     The choice of the noodle
	 * @param springonion The choice of springonion
	 * @param nori        The choice of the nori
	 * @param chashu      The choice of the chashu
	 * @param boiledegg   The choice of the boiled egg
	 * @param spiciness   The choice of the spiciness
	 * @param noriadd     The choice of the add-on nori
	 * @param eggadd      The choice of the add-on egg
	 * @param shootsadd   The choice of the add-on shoots
	 * @param chashuadd   The choice of the add-on chashu
	 * @param inOrout     The choice of the way of eating
	 */
	public Order(String orderID, int soup, int noodles, int springonion, int nori, int chashu, int boiledegg,
			int spiciness, int noriadd, int eggadd, int shootsadd, int chashuadd, String inOrout) {
		this.orderID = orderID;
		this.soup = soup;
		this.noodles = noodles;
		this.springonion = springonion;
		this.nori = nori;
		this.chashu = chashu;
		this.boiledegg = boiledegg;
		this.spiciness = spiciness;
		this.noriadd = noriadd;
		this.eggadd = eggadd;
		this.shootsadd = shootsadd;
		this.chashuadd = chashuadd;
		this.inOrout = inOrout;
	}


	/**
	 * @return the orderID
	 */
	public String getOrderID() {
		return orderID;
	}
	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	/**
	 * @return the soup
	 */
	public int getSoup() {
		return soup;
	}
	/**
	 * @param soup the soup to set
	 */
	public void setSoup(int soup) {
		this.soup = soup;
	}
	/**
	 * @return the noodles
	 */
	public int getNoodles() {
		return noodles;
	}
	/**
	 * @param noodles the noodles to set
	 */
	public void setNoodles(int noodles) {
		this.noodles = noodles;
	}
	/**
	 * @return the springonion
	 */
	public int getSpringonion() {
		return springonion;
	}
	/**
	 * @param springonion the springonion to set
	 */
	public void setSpringonion(int springonion) {
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
	 * @return the spiciness
	 */
	public int getSpiciness() {
		return spiciness;
	}
	/**
	 * @param spiciness the spiciness to set
	 */
	public void setSpiciness(int spiciness) {
		this.spiciness = spiciness;
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

	@Override
	public int hashCode() {
		return Objects.hash(boiledegg, chashu, chashuadd, eggadd, inOrout, noodles, nori, noriadd, orderID, shootsadd,
				soup, spiciness, springonion);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Order)) {
			return false;
		}
		Order other = (Order) obj;
		return boiledegg == other.boiledegg && chashu == other.chashu && chashuadd == other.chashuadd
				&& eggadd == other.eggadd && Objects.equals(inOrout, other.inOrout) && noodles == other.noodles
				&& nori == other.nori && noriadd == other.noriadd && Objects.equals(orderID, other.orderID)
				&& shootsadd == other.shootsadd && soup == other.soup && spiciness == other.spiciness
				&& springonion == other.springonion;
	}


	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", soup=" + soup + ", noodles=" + noodles + ", springonion=" + springonion
				+ ", nori=" + nori + ", chashu=" + chashu + ", boiledegg=" + boiledegg + ", spiciness=" + spiciness
				+ ", noriadd=" + noriadd + ", eggadd=" + eggadd + ", shootsadd=" + shootsadd + ", chashuadd="
				+ chashuadd + ", inOrout=" + inOrout + "]";
	}


	/**
	 * @return the inOrout
	 */
	public String getInOrout() {
		return inOrout;
	}


	/**
	 * @param inOrout the inOrout to set
	 */
	public void setInOrout(String inOrout) {
		this.inOrout = inOrout;
	}

}
