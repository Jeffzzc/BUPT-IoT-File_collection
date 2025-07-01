package worker;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import domain.DataSource;
import pages.PageController;
import service.ConfigService;

/**
*  The MainWorker class is the Application entry point of the main system.
*  Title      : MainWorker.java
*  <p>
*  Description: 
*  <p>
*  The MainWorker class is the Application entry point of the main system
*  @author  Tianyi Liu
*  @version 2.0
*/
public class MainWorker {
	/***
	 * application entry point of the main system
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		System.out.println("Main system start up...");
        try {
            String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
            UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		while(true) {
			DataSource ds=new DataSource();
			ConfigService cs=new ConfigService("price.txt","stock.txt","rec.txt",ds);
			PageController pc = new PageController(ds);
			while(true) {
				if(pc.nostop==false)break;
			}
		}
	}
}