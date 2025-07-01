package management.report;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import datautil.IOController;
import management.util.*;
import management.service.*;

/**
 * This is a class for periodic report generation and save .
 * 
 * <p>This class implements {@code TimerTask} and implements {@code run} method.
 *  
 * @author Xiaotian Wang and Tianyi Liu
 *
 */
public class Report extends TimerTask{
	
	public void run() {
		System.out.println("Try to generate report...");
		if (isStop()) {
			System.out.println("Report auto generation not open!");
			return;
		}
		if (isMonday()) {
			generateReport();
		}
	}
	
	/**
	 * Check if the report generator be stopped
	 * 
	 * @return {@code true} if yes; {@code false} if not
	 */
	public boolean isStop() {
		IOController fio=new IOController("rpconfig.txt");
		String now=fio.directRead();
		if(now.equals("0"))return true;
		return false;
	}
	
	/**
	 * Stop or start the report generator
	 * @param x 1 if start, 0 if not
	 * @return {@code true} if set finish; {@code false} if not
	 */
	public boolean setStop(int x) {
		IOController fio=new IOController("rpconfig.txt");
		return fio.directWrite(""+x);
	}

	/**
	 * Check if today is Monday
	 * 
	 * @return {@code true} if yes; {@code false} if not
	 */
	private boolean isMonday() {
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		int day  = calendar.get(Calendar.DAY_OF_WEEK);// 2 if Monday
		if (day == 2) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Generate report and save it.
	 * 
	 * <p>The report is a folder named by date range. A "txt" report and a "png" 
	 * sales chart are in it.
	 */
	public void generateReport() {
		
		System.out.println("Generator report....");
		StatsService ss = new StatsService("orderlist.csv", "paymentlist.csv",7);
		
		Calendar calendar1 = Calendar.getInstance();  
		calendar1.setTime(new Date());
		int day_of_week = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		day_of_week = 7;
		calendar1.add(Calendar.DATE, -day_of_week-6);
		Date lastMon = calendar1.getTime(); //last Monday 
		
		Calendar calendar2 = Calendar.getInstance();  
		calendar2.setTime(new Date());
        calendar2.add(Calendar.DATE, -day_of_week+1);
        Date lastSun = calendar2.getTime();//last Sunday
		
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String monday = new SimpleDateFormat("yyyy-MM-dd").format(lastMon);
		String sunday = new SimpleDateFormat("yyyy-MM-dd").format(lastSun);
		String dirName = monday+"---"+sunday+ " Report";
		
		File file=new File("./" + dirName);
		if(!file.exists()){
			file.mkdir();//create folder
		}
		
		File txt = new File("./"+ dirName +"/Report.txt");
		
		if(!txt.exists()){
			try {
				txt.createNewFile();//create txt
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String report ="Last Week Statistics"+"\n"
				+ "Generated on "+today+"\n\n"
				+ "Soup:    Tonkotsu:"+ss.getStats().getSoup().get(0)+"  Shoyu:"+ss.getStats().getSoup().get(1)+"  Shio:"+ss.getStats().getSoup().get(2)+"\n"
				+ "Noodles:    Soft:"+ss.getStats().getNoodles().get(0)+"  Medium:"+ss.getStats().getSoup().get(1)+"  Firm:"+ss.getStats().getSoup().get(2)+"\n"
				+ "Spring onion:    No please:"+ss.getStats().getSpringonion().get(0)+"  Just a little:"+ss.getStats().getSpringonion().get(1)+"  A lot!:"+ss.getStats().getSpringonion().get(2)+"\n\n"
				+ "Nori:    Yes:"+ss.getStats().getNori().get(0)+"  No:"+ss.getStats().getNori().get(1)+"\n"
				+ "Chashu:    Yes:"+ss.getStats().getChashu().get(0)+"  No:"+ss.getStats().getChashu().get(1)+"\n"
				+ "Boiled egg:    Yes:"+ss.getStats().getBoiledegg().get(0)+"  No:"+ss.getStats().getBoiledegg().get(1)+"\n\n"
				+ "Extra Nori:"+ss.getStats().getNoriadd()+"\n"
				+ "Extra Chashu:"+ss.getStats().getChashuadd()+"\n"
				+ "Extra boiled egg:"+ss.getStats().getEggadd()+"\n"
				+ "Bamboo shoots:"+ss.getStats().getShootsadd()+"\n\n"
				+ "Spiciness:\n"+"0:"+ss.getStats().getSpiciness().get(0)+"  1:"+ss.getStats().getSpiciness().get(1)+"  2:"+ss.getStats().getSpiciness().get(2)+"\n"
				+"3:"+ss.getStats().getSpiciness().get(3)+"  4:"+ss.getStats().getSpiciness().get(4)+"  5:"+ss.getStats().getSpiciness().get(5)+"\n"
				+"Most popular spiciness:"+ss.getMPSpiciness()+"\n\n"
				+"Payment Methods:\n"+"Card:"+ss.getStats().getPyMethods().get("Card")+"  Cash:"+ss.getStats().getPyMethods().get("Cash")+"  Stamp:"+ss.getStats().getPyMethods().get("Stamp");
		
		FileWriter fw = null;
		BufferedWriter bw = null;
			try {
				fw = new FileWriter(txt);
				bw = new BufferedWriter(fw);
				bw.write(report);
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		Chart chart = new Chart("./" + dirName + "/");
		chart.makeBarChart();//generate chart
		
	}
	
	
	public static void main(String[] args) {
		Report report = new Report();
		report.generateReport();
	}
}
