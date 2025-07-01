package service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.*;
import domain.DataSource;
import domain.Member;
import domain.Order;
import domain.Payment;
import junit.framework.TestCase;
import service.ConfigService;
import service.MemberService;
import service.OrderService;
import service.PaymentService;
import datautil.CSVController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaymentServiceTest {

	private static DataSource ds;
	private static PaymentService ps;
	private static OrderService os;
	private static Payment temp;
	private static ConfigService cs;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ds=new DataSource();
		ps=new PaymentService("testPaymentList.csv",ds);
		os=new OrderService(ds,"testOrderList.csv");
		cs=new ConfigService("price.txt","stock.txt","rec.txt",ds);
		os.newOrder();
		Order od=os.getOrder();
		od.setInOrout("Take-out");
		od.setBoiledegg(1);
		od.setChashu(1);
		od.setNoodles(1);
		od.setNori(2);
		od.setSoup(1);
		od.setSpiciness(5);
		od.setSpringonion(1);
		od.setChashuadd(1);
		ds.setOrder(od);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public void test01_getPrice() {
		ps.newPayment("Take-out");
		double price = ps.getPrice();
		assertEquals(7.1,price,1e-7);
		ps.setDiscount();
		price = ps.getPrice();
		assertEquals(0.0,price,1e-7);
	}
	
	@Test
	public void test02_getNoDisPrice() {
		double price = ps.getNoDisPrice();
		assertEquals(7.1,price,1e-7);
		
	}

	@Test
	public void test03_savePayment() {
		boolean ret=ps.savePayment();
		assertEquals(true,ret);
		CSVController csv=new CSVController(ps.getFilename());
		List<Payment> ls=csv.read(Payment.class);
		Payment nowPayment=ls.get(ls.size()-1);
		assertEquals(true,nowPayment.equals(ps.getPayment()));
	}

}
