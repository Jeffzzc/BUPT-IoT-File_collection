package management.domain.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import management.domain.AdministratorAccount;


public class AcocuntTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testAccount() {
		AdministratorAccount account = new AdministratorAccount();
		
		account.setName("boss");
		account.setPassword("ramen123");
		assertEquals("boss",account.getName());
		assertEquals("ramen123",account.getPassword());
		
		account.setName("Big");
		account.setPassword("666");
		assertEquals("Big",account.getName());
		assertEquals("666",account.getPassword());
		
		account.setName("Cat");
		account.setPassword("123");
		assertEquals("Cat",account.getName());
		assertEquals("123",account.getPassword());

		account.setName("YYY");
		account.setPassword("yyy");
		assertEquals("YYY",account.getName());
		assertEquals("yyy",account.getPassword());
		
		account.setName("Tom");
		account.setPassword("cat");
		assertEquals("Tom",account.getName());
		assertEquals("cat",account.getPassword());
		
		account.setName("lala");
		account.setPassword("555");
		assertEquals("lala",account.getName());
		assertEquals("555",account.getPassword());
		
		account.setName("lty");
		account.setPassword("666");
		assertEquals("lty",account.getName());
		assertEquals("666",account.getPassword());

		account.setName("Auto");
		account.setPassword("man");
		assertEquals("Auto",account.getName());
		assertEquals("man",account.getPassword());

		account.setName("♂");
		account.setPassword("♀");
		assertEquals("♂",account.getName());
		assertEquals("♀",account.getPassword());
		
		account.setName("&&&");
		account.setPassword("@@@");
		assertEquals("&&&",account.getName());
		assertEquals("@@@",account.getPassword());
	}

}
