package datautil.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datautil.JSONController;

public class JSONTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testArray() {
		JSONController x=new JSONController("666.txt");
		
        List<TestObj> list = new ArrayList<TestObj>();
        for (int i = 0; i < 100; i++) {
            list.add(new TestObj("tom"+i,i+100,new Date(),1.0+(double)i/10));
        }
        x.writeArray(list);
        List<TestObj> m =  x.readArray(TestObj.class);
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
	public void testObj() {
		JSONController x=new JSONController("777.txt");
		TestObj before=new TestObj("tom",100,new Date(),1.0+(double)1.0/10.0);
        x.write(before);
        TestObj now = (TestObj) x.read(TestObj.class);
        assertEquals(now.getAge(),before.getAge());
        assertEquals(now.getName(),before.getName());
        assertEquals(now.getDt(),before.getDt());
        assertEquals(now.getXx(),before.getXx(),1e-7);
	}
	
	@Test
	public void testObjArray() {
		JSONController x=new JSONController("888.txt");
		ArrayObj before=new ArrayObj();
        x.write(before);
        ArrayObj now = (ArrayObj) x.read(ArrayObj.class);
        for(int i=0;i<3;i++) {
        	assertEquals(now.x[i],before.x[i]);
        }
	}
	
	@Test
	public void testListObjArray() {
		JSONController x=new JSONController("999.txt");
		List<ArrayObj>before=new ArrayList<ArrayObj>();
		for(int i=0;i<100;i++) {
			before.add(new ArrayObj());
		}
        x.writeArray(before);
        List<ArrayObj> now = x.readArray(ArrayObj.class);
        for(int i=0;i<100;i++) {
        	for(int j=0;j<3;j++)
        	assertEquals(now.get(i).x[j],before.get(i).x[j]);
        }
	}
	
	@Test
	public void testMp() {
		Map<String, Double> pricemp=new HashMap<>();
		pricemp.put("Chasu",1.0);
		pricemp.put("Egg",1.0);
		pricemp.put("Ramen",1.0);
		pricemp.put("Nori",2.0);
		JSONController json=new JSONController("price.json");
		json.write(pricemp);
	}
	

}


class ArrayObj {
	public int x[];
	public ArrayObj() {
		x=new int[3]; 
		for(int i=0;i<3;i++)
			x[i]=(int)(10.0*Math.random());
	}
}
