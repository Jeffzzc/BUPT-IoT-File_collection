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
import service.MemberService;
import datautil.CSVController;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberServiceTest {

	private static DataSource ds;
	private static MemberService ms;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ds=new DataSource();
		ms=new MemberService("testMemberList.csv",ds);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test01_memberRegTest() {

		CSVController csv=new CSVController(ms.getFilename());
		List<Member> memlist=csv.read(Member.class);
		
		ms.memberReg("Li","Hua","123@qq.com","1234");
		String id1 = String.format("%08d", memlist.size()+1);
		assertEquals(id1,ds.getMember().getMemberID());
		
		ms.memberReg("Lsdasfasfasfasf","Hua","123@qq.com","1234");
		String id2 = String.format("%08d", memlist.size()+2);
		assertEquals(id2,ds.getMember().getMemberID());

		ms.memberReg("Ls陈哦哦哦fasf","afasf好","12lll3@qq.com","1234");
		String id3 = String.format("%08d", memlist.size()+3);
		assertEquals(id3,ds.getMember().getMemberID());
		
		ms.memberReg("Ls❤++sfasf","Hu+++-!@a","123q@q.com","1234");
		String id4 = String.format("%08d", memlist.size()+4);
		assertEquals(id4,ds.getMember().getMemberID());
		
		ms.memberReg("来来来","略略略","123@qq.com","1llll4");
		String id5 = String.format("%08d", memlist.size()+5);
		assertEquals(id5,ds.getMember().getMemberID());
	}
	
	@Test
	public void test02_memberLoginTest() {
		
		CSVController csv=new CSVController(ms.getFilename());
		List<Member> memlist=csv.read(Member.class);
		String id1 = String.format("%08d", memlist.size());	
		
	
		if(memlist.size()<=10) {
		for(int i=1;i<=memlist.size();i++) {
			String id=String.format("%08d",i);
			assertEquals(true,ms.memberLogin(id));
			}
		}
		else{
			for(int i=1;i<=10;i++) {
				String id=String.format("%08d",i);
				assertEquals(true,ms.memberLogin(id));;
			}
		assertEquals(false,ms.memberLogin("88888888"));
		assertEquals(false,ms.memberLogin("99999999"));
		assertEquals(false,ms.memberLogin("82456879"));
		assertEquals(false,ms.memberLogin("45678135"));
		assertEquals(false,ms.memberLogin("23594684"));
		assertEquals(false,ms.memberLogin("23176282"));
		assertEquals(false,ms.memberLogin("95324841"));
		}
	}
	
	@Test
	public void test03_StampTest() {
		assertEquals(0,ms.getStamp());
		assertEquals(false,ms.useStamp());
		for(int i=0;i<10;i++) {
		ms.addStamp();
		}
		assertEquals(10,ms.getStamp());
		assertEquals(true,ms.useStamp());
		assertEquals(0,ms.getStamp());
		
	}
}