package management.service.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import management.domain.AdministratorAccount;
import management.service.AccountService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test_01_AccountService() {
		AccountService account = new AccountService();
		assertEquals(1,account.loginAccount("wxt", "123456"));
		assertEquals(0,account.loginAccount("wsdadd", "123456"));
		assertEquals(0,account.loginAccount("wxt", "1521123.121."));
		assertEquals(0,account.loginAccount("", ""));
		assertEquals(0,account.loginAccount("好123", "asdasd"));
		assertEquals(0,account.loginAccount("♂21345", "♀ashofl"));
		
	}
	
	@Test
	public void test_02_SaveAccount() {
		AccountService account1 = new AccountService();
		assertEquals(1,account1.saveAccount());
		
		AccountService account2 = new AccountService();
		assertEquals(1,account2.saveAccount());
		
		AccountService account3 = new AccountService();
		assertEquals(1,account3.saveAccount());
		
		AccountService account4 = new AccountService();
		assertEquals(1,account4.saveAccount());
		
		AccountService account5 = new AccountService();
		assertEquals(1,account5.saveAccount());
	}
}