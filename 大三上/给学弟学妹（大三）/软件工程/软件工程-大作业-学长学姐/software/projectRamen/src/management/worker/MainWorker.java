package management.worker;

import java.awt.EventQueue;

import management.page.*;
/**
*  The MainWorker class is the application entry point of the management system.
*  Title      : MainWorker.java
*  <p>
*  Description: 
*  <p>
*  The MainWorker class is the application entry point of the management system
*  @author  Xiaotian Wang
*  @version 1.0
*/
public class MainWorker {
	/***
	 * application entry point of the management system
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		System.out.println("Management system start up...");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}