package management.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import datautil.JSONController;

import management.domain.AdministratorAccount;

/**
 * This is a control class for administrator account.
 *
 * <p> This class provides service related to administrator account, like
 * login, format checking, change save.
 * 
 * @author Xiaotian Wang
 *
 */
public class AccountService {
	private AdministratorAccount account;
	private JSONController json = new JSONController("account.txt");
	
	/**
	 *  Initialize AccountService
	 */
	public AccountService() {
		account = (AdministratorAccount)json.read(AdministratorAccount.class);
	}
	
	/**
	 * Check the username and password when log in.
	 * 
	 * @param name username entered
	 * @param password password entered
	 * @return 1 if right; 0 if wrong
	 */
	public int loginAccount(String name, String password) {
		if (name.equals(account.getName()) && password.equals(account.getPassword())) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * Check username format when change it
	 * 
	 * @param name username entered
	 * @return {@code true} if right; {@code false} if wrong
	 */
	public boolean checkAccountName(String name) {
		String rex = "^[0-9A-Za-z]{3,10}$";
		Pattern p = Pattern.compile(rex);
		Matcher m = p.matcher(name);
		return m.find(); 
	}
	
	/**
	 * Check password format when change it
	 * 
	 * @param password password entered
	 * @return {@code true} if right; {@code false} if wrong
	 */
	public boolean checkAccountPassword(String password) {
		String rex = "^[0-9A-Za-z]{6,18}$";
		Pattern p = Pattern.compile(rex);
		Matcher m = p.matcher(password);
		return m.find(); 
	} 
	
	/**
	 * Change administrator account username 
	 * 
	 * @param name new username
	 * @return 1 if successful; 0 if unsuccessful
	 */
	public int changeAccountName(String name) {
		account.setName(name);
		if (saveAccount() == 1) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * Change administrator account password
	 * 
	 * @param password new password
	 * @return 1 if successful; 0 if unsuccessful
	 */
	public int changeAccountPassword(String password) {
		
		account.setPassword(password);
		if (saveAccount() == 1) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**
	 * Save new account information into json file.
	 * 
	 * @return 1 if successful; 0 if unsuccessful
	 */
	public int saveAccount() {
		
		try{
			json.write(account);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	/**
	 * Get current account information as a {@code AdministratorAccount} object
	 * 
	 * @return a {@code AdministratorAccount} object
	 */
	public AdministratorAccount getAccount() {
		return this.account;
	}
	
}
