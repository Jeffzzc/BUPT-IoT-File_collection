package service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.*;

import domain.DataSource;
import domain.Order;
import service.OrderService;
import datautil.CSVController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderServiceTest {

	private static DataSource ds;
	private static OrderService os;
	private static Order temp;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ds=new DataSource();
		os=new OrderService(ds,"testOrderList.csv");
		System.out.print("test start with order:");
		String orderid=os.newOrder();
		System.out.println(orderid);
	}
	
	@Test
	public void test_01_ChooseAndBuy() {
		assertEquals(os.checkValidate(),false);
		
		Order od=os.getOrder();
		od.setInOrout("Take-out");
		od.setBoiledegg(1);
		od.setChashu(1);
		od.setNoodles(1);
		od.setNori(2);
		od.setSoup(1);
		od.setSpiciness(5);
		od.setSpringonion(1);
		
		assertEquals(true,os.checkValidate());
		
		od.setSpringonion(4);
		assertEquals(false,os.checkValidate());
		
		od.setSpringonion(0);
		od.setSpiciness(6);
		assertEquals(false,os.checkValidate());
		
		od.setSpiciness(0);
		od.setSpringonion(1);
		assertEquals(true,os.checkValidate());
		
		od.setChashuadd(4);
		od.setSpiciness(5);
		assertEquals(true,os.checkValidate());
		
		od.setChashuadd(6);
		od.setNoodles(2);
		assertEquals(true,os.checkValidate());
		
		od.setChashu(2);
		od.setNoodles(1);
		od.setSoup(5);
		assertEquals(false,os.checkValidate());
		
		od.setBoiledegg(7);
		od.setSoup(1);
		od.setChashu(1);
		assertEquals(false,os.checkValidate());
		
		od.setNori(6);
		od.setBoiledegg(1);
		assertEquals(false,os.checkValidate());
		
		od.setNoodles(2);
		od.setSpiciness(3);
		od.setNori(2);
		assertEquals(true,os.checkValidate());
		
		od.setBoiledegg(4);
		od.setSpringonion(2);
		od.setNoodles(1);
		od.setSpiciness(5);
		assertEquals(false,os.checkValidate());
		
		
		od.setBoiledegg(1);
		od.setSpringonion(1);
		temp=od;
	}
	
	@Test
	public void test_02_checkAvl() {
		//finish at last
	}
	
	@Test
	public void test_03_saveOrder() {
		assertEquals(true,os.checkValidate());
		assertEquals(true,os.saveOrder());
		CSVController csv=new CSVController(os.getFilename());
		List<Order> ls=csv.read(Order.class);
		Order nowOrder=ls.get(ls.size()-1);
		assertEquals(true,nowOrder.equals(temp));
	}
	

}
