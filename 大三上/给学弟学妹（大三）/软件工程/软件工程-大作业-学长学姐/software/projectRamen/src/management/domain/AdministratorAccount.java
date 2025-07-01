package management.domain;

/**
 * This is a entity class contains information about administrator account.
 *
 * <p>This class contains information about administrator account. <br>
 * It has two attributes: {@code name}  and {@code password}  <br>
 * and provides corresponding get and set methods.
 * 
 * @author Xiaotian Wang
 *
 */

public class AdministratorAccount {
	private String name;
	private String password;
	

	/**
	 * get method of {@code name} attribute
	 * 
	 * @return  a {@code String} that represents administrator account name 
	 */

	public String getName() {
		return name;
	}
	/**
	 * set method of {@code name} attribute
	 * 
	 * @param name a {@code String} that represents administrator account name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * get method of {@code password} attribute 
	 * 
	 * @return a {@code String} that represents administrator account password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * set method of {@code password} attribute
	 * 
	 * @param password a {@code String} that represents administrator account password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
