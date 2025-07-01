package service;

import java.util.List;

import datautil.CSVController;
import datautil.IOController;
import domain.DataSource;
import domain.Member;
/**
*  The MemberService class is a control class for the members.
*  Title      : MemberService.java
*  <p>
*  Description: 
*  <p>
*  The MemberService class is a control class for the members
*  <p>
*  This class provides registration, login and other functions
*  @author  Tianyi Liu
*  @version 3.1
*/
public class MemberService {
	private String filename;
	private DataSource ds;
	/**
	 * @param filename the file name of memeber list
	 * @param ds The datasource object
	 */
	public MemberService(String filename, DataSource ds) {
		this.filename = filename;
		this.ds = ds;
	}
	/**
	 * Checks whether the entered ID exists in the system.
	 * 
	 * @param ID The entered 8-digit ID number
	 * @return The boolean value of the checking result 
	 */
	public boolean memberLogin(String ID) {
		CSVController csv=new CSVController(filename);
		@SuppressWarnings("unchecked")
		List<Member> memlist= csv.read(Member.class);
		for(Member x:memlist) {
			if(x.getMemberID().equals(ID)) {
				ds.setMember(x);
				return true;
			}	
		}
		return false;
	}
	
	private boolean memberReg(Member newMem) {
		CSVController csv=new CSVController(filename);
		if(csv.appendWriteOne(newMem, CSVController.getFileHeader(Member.class))) {
			ds.setMember(newMem);
			return true;
		} else return false;
	}
	/**
	 * Adds the new member into the system.
	 * 
	 * @param fName The first name of the user
	 * @param sName The family name of the user
	 * @param email The email address of the user
	 * @param phone The phone number of the user
	 * @return The boolean value of the adding result
	 */
	public boolean memberReg(String fName,String sName,String email,String phone) {
		CSVController csv=new CSVController(filename);
		List<Member> memlist= csv.read(Member.class);
		String memID=String.format("%08d", memlist.size()+1);
		for (int i = 0; i < phone.length(); i++) {
			if (!Character.isDigit(phone.charAt(i))) {
			return false;
			}
			}
		Member mem=new Member(memID, email, phone, fName, sName, 0);
		
		return this.memberReg(mem);
	}
	
	private boolean setStamp(int num) {
		Member nowmem=ds.getMember();
		int stm=nowmem.getStamp();
		nowmem.setStamp(num);
		CSVController csv=new CSVController(filename);
		List<Member> memlist= csv.read(Member.class);
		boolean flag=false;
		for(int i=0;i<memlist.size();i++) {
			if(memlist.get(i).getMemberID().equals(nowmem.getMemberID())) {
				memlist.get(i).setStamp(num);
				flag=true;
				break;
			}
		}
		if(!flag) {
			return false;
		}
		return csv.coverWrite(memlist, CSVController.getFileHeader(Member.class));
	}
	
	/**
	 * Adds one stamp.
	 * 
	 * @return The boolean value of the result of adding
	 */
	public boolean addStamp() {
		if(ds.getMember().getMemberID().equals("")) {
			return false;
		}
		return setStamp(ds.getMember().getStamp()+1);
	}
	
	/**
	 * Reduces ten stamps.
	 * 
	 * @return The boolean value of the result of reducing
	 */

	public boolean useStamp() {
		if(ds.getMember().getMemberID().equals("")) {
			return false;
		}
		if(ds.getMember().getStamp()<10) return false;
		return setStamp(ds.getMember().getStamp()-10);
	}
	/**
	 * Gets the number of the stamps.
	 * 
	 * @return The number of the stamps
	 */
	public int getStamp() {
		if(ds.getMember().getMemberID().equals("")) {
			return -1;
		}
		return ds.getMember().getStamp();
	}
	
	/**
	 * @return The member object
	 */
	public Member getMember() {
		return ds.getMember();
	}
	/**
	 * @return The name of the file
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * Prints the member ticket with detail information.
	 */
	public void printTicket() {
		Member x=getMember();
		String cont="---------Member Ticket---------\n";
		cont+="Your memberID: "+x.getMemberID()+"\n";
		cont+="Please remember your memberID!\n";
		cont+="Your info:\n";
		cont+="Name: "+x.getfName()+" "+x.getsName()+"\n";
		if(x.getPhone().equals("")) {
			cont+="Phone: No phone number\n";
		} else {
			cont+="Phone: "+x.getPhone()+"\n";
		}
		if(x.getEmail().equals("")) {
			cont+="Email: No Email address\n";
		} else {
			cont+="Email: "+x.getEmail()+"\n";
		}
		cont+="-----------End-----------\n";
		IOController ioc=new IOController(x.getMemberID()+".txt");
		ioc.directWrite(cont);
	}
	/**
	 * Logout the member account.
	 */
	public void logout() {
		ds.setMember(null);
	}
}
