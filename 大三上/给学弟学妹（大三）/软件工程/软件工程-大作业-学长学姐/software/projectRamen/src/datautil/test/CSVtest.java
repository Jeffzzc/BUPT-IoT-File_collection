package datautil.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datautil.CSVController;
import domain.Order;
import domain.Payment;

public class CSVtest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCover() {
		CSVController x=new CSVController("456.csv");
		
		String[] fileHeader = CSVController.getFileHeader(TestObj.class);
        List<TestObj> list = new ArrayList<TestObj>();
        for (int i = 0; i < 100; i++) {
            list.add(new TestObj("tom"+i,i+100,new Date(),1.0+(double)i/10));
        }
        x.coverWrite(list,fileHeader);
        List<TestObj> m = x.read(TestObj.class);
        assertEquals(m.size(),list.size());
        for(int i=0;i<m.size();i++) {
        	TestObj now=m.get(i);
        	TestObj before=list.get(i);
        	assertEquals(now.getAge(),before.getAge());
        	assertEquals(now.getName(),before.getName());
        	assertEquals(now.getDt(),before.getDt());
        	assertEquals(now.getXx(),before.getXx(),1e-7);
        }
	}
	
	@Test
	public void testAppend() {
		CSVController x=new CSVController("456.csv");
		
		String[] fileHeader = CSVController.getFileHeader(TestObj.class);
        List<TestObj> list = new ArrayList<TestObj>();
        for (int i = 0; i < 100; i++) {
            list.add(new TestObj("tom"+i,i+100,new Date(),1.0+(double)i/10));
        }
        x.coverWrite(list,fileHeader);
        List<TestObj> list2 = new ArrayList<TestObj>();
        for (int i = 0; i < 20; i++) {
        	list2.add(new TestObj("jack"+i,i+5,new Date(),5.0+(double)i/10));
        }
        x.appendWrite(list2, fileHeader);
        List<TestObj> m = x.read(TestObj.class);
        assertEquals(m.size(),list.size()+list2.size());
        list.addAll(list2);
        for(int i=0;i<m.size();i++) {
        	TestObj now=m.get(i);
        	TestObj before=list.get(i);
        	assertEquals(now.getAge(),before.getAge());
        	assertEquals(now.getName(),before.getName());
        	assertEquals(now.getDt(),before.getDt());
        	assertEquals(now.getXx(),before.getXx(),1e-7);
        }
	}
	
	@Test
	public void testChooseHeader() {
		CSVController x=new CSVController("456.csv");
		
		String[] myHeader= {"age","name"};
        List<TestObj> list = new ArrayList<TestObj>();
        for (int i = 0; i < 100; i++) {
            list.add(new TestObj("tom"+i,i+100,new Date(),1.0+(double)i/10));
        }
        x.coverWrite(list,myHeader);
        List<TestObj> m = x.read(TestObj.class);
        assertEquals(m.size(),list.size());
        for(int i=0;i<m.size();i++) {
        	TestObj now=m.get(i);
        	TestObj before=list.get(i);
        	assertEquals(now.getAge(),before.getAge());
        	assertEquals(now.getName(),before.getName());
        }
	}
	
	@Test
	public void testOd() {
		Order od = new Order();
		Payment pay = new Payment();
		
		Date date = new Date();
		
		od.setBoiledegg(1);
		od.setChashu(1);
		od.setNoodles(1);
		od.setNori(2);
		od.setSoup(1);
		od.setSpiciness(5);
		od.setSpringonion(1);
	
		
		pay.setOrderID("123456789");
		pay.setMethod("cash");
		pay.setTotalPrice(100);
		pay.setTime(date);
		pay.setIsDiscount(0);
		pay.setMemberID("123213213");

		
		
		//CSVController csv1 = new CSVController("testOrder.csv");
		CSVController csv2 = new CSVController("testPayment.csv");
		
		//csv1.appendWriteOne(od, CSVController.getFileHeader(Order.class));
		csv2.appendWriteOne(pay, CSVController.getFileHeader(Payment.class));
	}
}



class TestObj{

	public TestObj() {
	
	}
	
    public TestObj(String name, int age, Date dt,double xx) {
        this.name = name;
        this.age = age;
        this.setDt(dt);
        this.setXx(xx);
    }

    private String name;
    private int age;
    private Date dt;
    private double xx;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public double getXx() {
		return xx;
	}

	public void setXx(double xx) {
		this.xx = xx;
	}
}
