package management.service.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import management.service.ModifyService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModifyServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test01_savePrice() {
		ModifyService service1 = new ModifyService();
		service1.changePrice("Egg", "1.0");
		assertEquals(1,service1.savePrice());
		
		ModifyService service2 = new ModifyService();
		service2.changePrice("Ramen", "6.6");
		assertEquals(1,service2.savePrice());
		
		ModifyService service3 = new ModifyService();
		service3.changePrice("Chashu", "0.5");
		assertEquals(1,service3.savePrice());
		
		ModifyService service4 = new ModifyService();
		service4.changePrice("Bintang", "1.0");
		assertEquals(1,service4.savePrice());	
		
		ModifyService service5 = new ModifyService();
		service5.changePrice("Xiangcong", "1.0");
		assertEquals(1,service5.savePrice());	
		
		ModifyService service6 = new ModifyService();
		service5.changePrice("Zhacai", "1.0");
		assertEquals(1,service6.savePrice());	
		
		ModifyService service7 = new ModifyService();
		service5.changePrice("Hongshu", "2.0");
		assertEquals(1,service7.savePrice());	
		
		ModifyService service8 = new ModifyService();
		service5.changePrice("Tudou", "1.5");
		assertEquals(1,service8.savePrice());	
		
		ModifyService service9 = new ModifyService();
		service5.changePrice("Xiangchang", "1.0");
		assertEquals(1,service9.savePrice());	
		
		ModifyService service10 = new ModifyService();
		service5.changePrice("Luobo", "1.0");
		assertEquals(1,service10.savePrice());	
	}
	
	@Test
	public void test02_saveStcok() {
		ModifyService service1 = new ModifyService();
		service1.changeStock("Noodles1",100);
		assertEquals(1,service1.saveStock());
		
		ModifyService service2 = new ModifyService();
		service2.changeStock("Spring onion2",100);
		assertEquals(1,service2.saveStock());

		ModifyService service3 = new ModifyService();
		service3.changeStock("Noodles0",200);
		assertEquals(1,service3.saveStock());
		
		ModifyService service4 = new ModifyService();
		service4.changeStock("Spring onion0",400);
		assertEquals(1,service4.saveStock());
		
		ModifyService service5 = new ModifyService();
		service5.changeStock("Chashu",100);
		assertEquals(1,service5.saveStock());
		
	}
	
	@Test
	public void test03_savePicture() {
		ModifyService service1 = new ModifyService();
		ModifyService service2 = new ModifyService();
		
	/*	service1.changePicture("poster", "C:\\Users\\1\\projectRamen\\srcsrc/1.png");
		assertEquals(1,service1.savePicture());
		
		service2.changePicture("poster", "C:\\Users\\1\\projectRamen\\srcsrc/2.png");
		assertEquals(1,service2.savePicture());
	*/	
	}

}
