package domain;

/**
*  The Member class is an entity class for the member object.
*  Title      : Member.java
*  <p>
*  Description:
*  <p>
*  The Member class is an entity class for the member object.
*  <p>
*  Member object contains the member information, like email, ID.
*  @author  Tianyi Liu
*  @version 1.7
*/
public class Member {
	private String memberID; 
	private String email; 
	private String phone; 
	private String fName; 
	private String sName; 
	private int stamp;
	
	/**
	 * The default constructor of the Member
	 */
	public Member() {
		
	}
	
	/**
	 * The constructor of the Member
	 * @param memberID The account number of the member
	 * @param email    The email of the member
	 * @param phone    The phone number of th member
	 * @param fName    The firstname of the member
	 * @param sName    The surname of the member
	 * @param stamp    The number of the stamps
	 */
	public Member(String memberID, String email, String phone, String fName, String sName, int stamp) {
		this.memberID = memberID;
		this.email = email;
		this.phone = phone;
		this.fName = fName;
		this.sName = sName;
		this.stamp = stamp;
	}
	/**
	 * Gets the account number of the member.
	 * 
	 * @return The account number of the member
	 */
	public String getMemberID() {
		return memberID;
	}
	/**
	 * Sets the account number of the member.
	 * 
	 * @param memberID The account number of the member
	 */
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	/**
	 * Gets the email account of the member.
	 * 
	 * @return The email acccount of the member
	 */
	public String getEmail() {
		return email;
	}
	/**
	 *  Sets the email account of the member.
	 *  
	 * @param email the email account of the member
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Gets the phone number of the member.
	 * 
	 * @return The phone number of the member
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * Sets the phone number of the member.
	 * 
	 * @param phone The phone number of the member
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * Gets the firstname of the member.
	 * 
	 * @return The firstname of the member
	 */
	public String getfName() {
		return fName;
	}
	/**
	 * Sets the firstname of the member.
	 * 
	 * @param fName The firstname of the member
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	/**
	 * Gets the surname of the member.
	 * 
	 * @return The surname of the member
	 */
	public String getsName() {
		return sName;
	}
	/**
	 * Sets the surname of the member.
	 * 
	 * @param sName The surname of the member
	 */
	public void setsName(String sName) {
		this.sName = sName;
	}
	/**
	 * Gets the number of the stamps.
	 * 
	 * @return The number of the stamps
	 */
	public int getStamp() {
		return stamp;
	}
	/**
	 * Sets the number of the stamps
	 * 
	 * @param stamp the stamp to set
	 */
	public void setStamp(int stamp) {
		this.stamp = stamp;
	}

	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", email=" + email + ", phone=" + phone + ", fName=" + fName
				+ ", sName=" + sName + ", stamp=" + stamp + "]";
	}
	
} 
