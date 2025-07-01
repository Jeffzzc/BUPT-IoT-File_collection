package management.service;

import datautil.CSVController;
import datautil.JSONController;
import domain.Order;
import domain.Payment;
import management.domain.*;
import java.util.*;

/**
 * This is a control class for statistics.
 *
 * <p> This class provides service related to statistics, like calculating 
 * each item on the menu how many times it has been sold in the past week
 * and other operations.
 * 
 * @author Xiaotian Wang
 *
 */
public class StatsService {
	
	private Stats stats = new Stats();
	private JSONController json_stats = new JSONController("stats.txt");
	
	/**
	 * Initialize StatsService
	 */
	public StatsService() {
		
	}
	
	/**
	 * Initialize StatsService (automatically do statistics)
	 * 
	 * @param odFile order file
	 * @param payFile payment file
	 * @param day when is statistics (7: last week; 30: last month; 1: yesterday)
	 */
	public StatsService(String odFile, String payFile, int day) {
		doStats(odFile, payFile, day);
	}
	
	
	
	/**
	 * Do statistics
	 * 
	 * @param odFile order file
	 * @param payFile payment file
	 * @param day when is statistics (7: last week; 30: last month; 1: yesterday)
	 */
	public void doStats(String odFile, String payFile, int day) {
		CSVController csv_od = new CSVController(odFile);
		CSVController csv_py = new CSVController(payFile);
		
		List<Order> list_od = csv_od.read(Order.class);
		List<Payment> list_py = csv_py.read(Payment.class);
		
		for (int i=0;i<list_py.size();i++) {
			Payment py = list_py.get(i);
			if (timeCheck(py.getTime(), day)){
				stats.addPayment(py);
			}
		}
		
		List<Payment> lw_pys = stats.getPayments();
		
		
		for (int i=0;i<list_od.size();i++) {
			Order od = list_od.get(i);
			
			for (int j=0;j<lw_pys.size();j++) {
				
				if (od.getOrderID().equals(
						lw_pys.get(j).getOrderID())) {
					stats.addOrder(od);
				}
			}
		}
		
		
		calOrders();
		calPayments();
		if (day==7) {
			calLastWeekSale();
		}
				
	}
	
	/**
	 * Calculate order statistics
	 */
	private void calOrders() {
		List<Order> lw_ods = stats.getOrders();
		
		
		int soup0Num=0;
		int noodles0Num=0;
		int springonion0Num=0;
		int soup1Num=0;
		int noodles1Num=0;
		int springonion1Num=0;
		int soup2Num=0;
		int noodles2Num=0;
		int springonion2Num=0;
		
		int nori0Num=0;
		int chashu0Num=0;
		int boiledegg0Num=0;
		int nori1Num=0;
		int chashu1Num=0;
		int boiledegg1Num=0;
		
		int noriaddNum=0;
		int eggaddNum=0;
		int shootsaddNum=0;
		int chashuaddNum=0;
		
		int spiciness0=0;
		int spiciness1=0;
		int spiciness2=0;
		int spiciness3=0;
		int spiciness4=0;
		int spiciness5=0;
		
		
		
		for (int i=0;i<lw_ods.size();i++) {
			Order od = lw_ods.get(i);
			
			switch (od.getSoup()) {
				case 0:
					soup0Num+=1;
					break;
				case 1:
					soup1Num+=1;
					break;
				case 2:
					soup2Num+=1;
					break;
			}

			
			switch (od.getNoodles()) {
			case 0:
				noodles0Num+=1;
				break;
			case 1:
				noodles1Num+=1;
				break;
			case 2:
				noodles2Num+=1;
				break;
			}
			

			switch (od.getSpringonion()) {
			case 0:
				springonion0Num+=1;
				break;
			case 1:
				springonion1Num+=1;
				break;
			case 2:
				springonion2Num+=1;
				break;
			}
			
			if(od.getNori()==0) {
				nori0Num+=1;
			}else if(od.getNori()==1) {
				nori1Num+=1;
			}
			
			if(od.getChashu()==0) {
				chashu0Num+=1;
			}else if(od.getChashu()==1) {
				chashu1Num+=1;
			}
			
			if(od.getBoiledegg()==0) {
				boiledegg0Num+=1;
			}else if(od.getBoiledegg()==1) {
				boiledegg1Num+=1;
			}
			
			noriaddNum+=od.getNoriadd();
			eggaddNum+=od.getEggadd();
			shootsaddNum+=od.getShootsadd();
			chashuaddNum+=od.getChashuadd();
			
			switch (od.getSpiciness()) {
			case 0:
				spiciness0+=1;
				break;
			case 1:
				spiciness1+=1;
				break;
			case 2:
				spiciness2+=1;
				break;
			case 3:
				spiciness3+=1;
				break;
			case 4:
				spiciness4+=1;
				break;
			case 5:
				spiciness5+=1;
				break;
			}
		}
		
		
		stats.getSoup().put(0,soup0Num);
		stats.getSoup().put(1,soup1Num);
		stats.getSoup().put(2,soup2Num);
		
		stats.getNoodles().put(0,noodles0Num);
		stats.getNoodles().put(1,noodles1Num);
		stats.getNoodles().put(2,noodles2Num);
		
		stats.getSpringonion().put(0,springonion0Num);
		stats.getSpringonion().put(1,springonion1Num);
		stats.getSpringonion().put(2,springonion2Num);
		
		stats.getNori().put(0,nori0Num);
		stats.getNori().put(1,nori1Num);
		
		stats.getChashu().put(0,chashu0Num);
		stats.getChashu().put(1,chashu1Num);
		
		stats.getBoiledegg().put(0, boiledegg0Num);
		stats.getBoiledegg().put(1, boiledegg1Num);
		
		stats.setNoriadd(noriaddNum);
		stats.setEggadd(eggaddNum);
		stats.setShootsadd(shootsaddNum);
		stats.setChashuadd(chashuaddNum);
			
		stats.getSpiciness().put(0,spiciness0);
		stats.getSpiciness().put(1,spiciness1);
		stats.getSpiciness().put(2,spiciness2);
		stats.getSpiciness().put(3,spiciness3);
		stats.getSpiciness().put(4,spiciness4);
		stats.getSpiciness().put(5,spiciness5);
	}
	
	/**
	 * Calculate payment statistics
	 */
	private void calPayments() {
		List<Payment> lw_pys = stats.getPayments();
		
		int card = 0;
		int cash = 0;
		int stamp = 0;
		
		for (int i=0; i<lw_pys.size(); i++) {
			Payment payment = lw_pys.get(i);
			
			switch (payment.getMethod()) {
			case ("Card"):
				card++;
				break;
			case("Cash"):
				cash++;
				break;
			case("Stamp"):
				stamp++;
				break;
				
			}
		}
		
		stats.getPyMethods().put("Card", card);
		stats.getPyMethods().put("Cash", cash);
		stats.getPyMethods().put("Stamp", stamp);
	}
	
	/**
	 * Get statistics as a {@code Stats} object
	 * 
	 * @return a {@code Stats} object
	 */
	public Stats getStats() {
		return this.stats;
	}
	
	/**
	 * Get most popular spiciness
	 * 
	 * @return most popular spiciness
	 */
	public int getMPSpiciness() {
		int mps = 0;
		int max = 0;
		
		for (Integer key:stats.getSpiciness().keySet()) {
			if (stats.getSpiciness().get(key)>=max) {
				mps = key;
				max = stats.getSpiciness().get(key);
			}
		}
				
		return mps;
		
	}
	
	/**
	 * Check whether payment time is in a specific range
	 * 
	 * @param pyDate payment date (a {@code Date} object)
	 * @param day 7: last week; 30: last month; 1: yesterday
	 * @return {@code true} if in this range; {@code false} if not
	 */
	private boolean timeCheck (Date pyDate, int day) {
		
		if (day==7) {
			Calendar calendar1 = Calendar.getInstance();  
			calendar1.setTime(new Date());
	        calendar1.set(Calendar.HOUR_OF_DAY, 0);
	        calendar1.set(Calendar.MINUTE, 0);
	        calendar1.set(Calendar.SECOND, 0);//get today 0:00 time 
			int day_of_week = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
			if (day_of_week == 0)
			day_of_week = 7;
			calendar1.add(Calendar.DATE, -day_of_week-6);
			Date lastMon = calendar1.getTime();//Last Monday 0:00
			
			Calendar calendar2 = Calendar.getInstance();  
			calendar2.setTime(new Date());
	        calendar2.set(Calendar.HOUR_OF_DAY, 0);
	        calendar2.set(Calendar.MINUTE, 0);
	        calendar2.set(Calendar.SECOND, 0);
	        calendar2.add(Calendar.DATE, -day_of_week+1);
	        Date lastSun = calendar2.getTime();//Last Sunday 24:00
	        if(lastMon.getTime() < pyDate.getTime() && pyDate.getTime() < lastSun.getTime()){
				return true;
			}else{
				return false;
			}
			
		}else if (day==30) {
			
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(new Date());
	        calendar1.set(Calendar.HOUR_OF_DAY, 0);
	        calendar1.set(Calendar.MINUTE, 0);
	        calendar1.set(Calendar.SECOND, 0);
			calendar1.add(Calendar.MONTH, -1);
			calendar1.set(Calendar.DAY_OF_MONTH, 1);
			Date lastM1st = calendar1.getTime();//Last Month 1st 0:00
			
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(new Date());
	        calendar2.set(Calendar.HOUR_OF_DAY, 0);
	        calendar2.set(Calendar.MINUTE, 0);
	        calendar2.set(Calendar.SECOND, 0);
			calendar2.set(Calendar.DAY_OF_MONTH, 1);
			Date thisM1st = calendar2.getTime();//This Month 1st 0:00 i.e. Last Month last day 24:00
			
			 if(lastM1st.getTime() < pyDate.getTime() && pyDate.getTime() < thisM1st.getTime()){
					return true;
				}else{
					return false;
				}
			
		}else {
 			Calendar calendar = Calendar.getInstance();  
			calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date todayZero = calendar.getTime();//get today 0:00 time (not consider today's sale)
			calendar.add(Calendar.DATE, -day);  
			Date beforeXdays = calendar.getTime();//get X days before today  
			if(beforeXdays.getTime() < pyDate.getTime() && pyDate.getTime() < todayZero.getTime()){
				return true;
			}else{
				return false;
			}
		}
	

	}
	
	/**
	 * Calculate last week sales (money) for generate chart {@link management.util.Chart}
	 */
	private void calLastWeekSale() {
		List<Payment> lw_pys = stats.getPayments();
		
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);//get today 0:00 time 
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		day_of_week = 7;
		calendar.add(Calendar.DATE, -day_of_week-6);
		Date monday = calendar.getTime();
		
		calendar.add(Calendar.DATE, 1);
		Date tuesday = calendar.getTime();
		
		calendar.add(Calendar.DATE, 1);
		Date wednesday = calendar.getTime();
		
		calendar.add(Calendar.DATE, 1);
		Date thursday = calendar.getTime();
		
		calendar.add(Calendar.DATE, 1);
		Date friday = calendar.getTime();
		
		calendar.add(Calendar.DATE, 1);
		Date saturday = calendar.getTime();
		
		calendar.add(Calendar.DATE, 1);
		Date sunday = calendar.getTime();
		
		calendar.add(Calendar.DATE, 1);
		Date nextMonday = calendar.getTime();
		
		
		for (int i=0; i<lw_pys.size(); i++) {
			Payment payment = lw_pys.get(i);
			if (payment.getTime().getTime()>monday.getTime()&&payment.getTime().getTime()<tuesday.getTime()) {
				stats.getLastWeekSale()[0][0] += payment.getTotalPrice();
			}
			if (payment.getTime().getTime()>tuesday.getTime()&&payment.getTime().getTime()<wednesday.getTime()) {
				stats.getLastWeekSale()[0][1] += payment.getTotalPrice();
			}
			if (payment.getTime().getTime()>wednesday.getTime()&&payment.getTime().getTime()<thursday.getTime()) {
				stats.getLastWeekSale()[0][2] += payment.getTotalPrice();
			}
			if (payment.getTime().getTime()>thursday.getTime()&&payment.getTime().getTime()<friday.getTime()) {
				stats.getLastWeekSale()[0][3] += payment.getTotalPrice();
			}
			if (payment.getTime().getTime()>friday.getTime()&&payment.getTime().getTime()<saturday.getTime()) {
				stats.getLastWeekSale()[0][4] += payment.getTotalPrice();
			}
			if (payment.getTime().getTime()>saturday.getTime()&&payment.getTime().getTime()<sunday.getTime()) {
				stats.getLastWeekSale()[0][5] += payment.getTotalPrice();
			}
			if (payment.getTime().getTime()>sunday.getTime()&&payment.getTime().getTime()<nextMonday.getTime()) {
				stats.getLastWeekSale()[0][6] += payment.getTotalPrice();
			}
		}
	}
	
	

}
