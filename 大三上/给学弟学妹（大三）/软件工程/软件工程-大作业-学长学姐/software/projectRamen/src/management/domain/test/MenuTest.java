package management.domain.test;

import static org.junit.Assert.*;


import org.junit.Test;

import management.domain.Menu;


public class MenuTest {

	@Test
	public void testMenu() {
		Menu menu1 = new Menu();
		Double price1= menu1.getOnePrice("Ramen");
		assertEquals(9.99,price1,0.00);
		
		Menu menu2 = new Menu();
		Double price2= menu2.getOnePrice("Egg");
		assertEquals(1.0,price2,0.00);
		
		Menu menu3 = new Menu();
		int availability1= menu3.getOneStock("Nori");
		assertEquals(1,availability1);
		
		Menu menu4 = new Menu();
		int availability2= menu4.getOneStock("Chasu");
		assertEquals(1,availability2);
		
		
	}

}
