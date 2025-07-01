package management.page;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import management.util.CircleButton;

/**
 * This is the main menu page after login.
 * 
 * <p>This main menu has three options: 1.Modify Account; 2.Modify Menu; 3.View Statistics
 * 
 * @author Xiaotian Wang
 *
 */
public class MainMenu extends JFrame{


	private static final long serialVersionUID = -3257423661049129678L;
	
	private JButton btnMA = new CircleButton("<html>&nbsp;&nbsp;MODIFY<br>ACCOUNT</html>");
	private JButton btnMM = new CircleButton("<html>MODIFY<br>&nbsp;&nbsp;MENU</html>");
	private JButton btnVR = new CircleButton("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;VIEW<br>STATISTICS</html>");
	
	private final JButton btnBack = new JButton("LOGOUT");
	private final JButton btnReportGeneration = new JButton("Report Generation");
	
	
	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		getContentPane().setBackground(new Color(240, 220, 140));
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		btnMA.setForeground(Color.GRAY);
		btnMA.setFont(new Font("Jack ", Font.BOLD, 30));
		btnMA.setBackground(Color.white);
		btnMA.setBounds(31, 148, 248, 243);
		getContentPane().add(btnMA);
		btnMA.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton)e.getSource();
				if (source==btnMA) {
					
					ModifyAccount window = new ModifyAccount();
					window.setVisible(true);
					MainMenu.this.dispose();
				}
					
			}});
		
		
		btnMM.setForeground(Color.ORANGE);
		btnMM.setBackground(Color.white);
		btnMM.setFont(new Font("Arial", Font.BOLD, 30));
		btnMM.setBounds(311, 148, 247, 243);
		getContentPane().add(btnMM);
		btnMM.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnMM) {
				
				ModifyMenu window = new ModifyMenu();
				window.setVisible(true);
				MainMenu.this.dispose();
			}
				
		}});
		
		
		
		btnVR.setForeground(Color.GRAY);
		btnVR.setBackground(Color.white);
		btnVR.setFont(new Font("Arial", Font.BOLD, 30));
		btnVR.setBounds(607, 148, 251, 243);
		getContentPane().add(btnVR);
		btnVR.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnVR) {
				
				ViewStats window = new ViewStats();
				window.setVisible(true);
				MainMenu.this.dispose();
			}
				
		}});
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(205,133,63));
		
		btnBack.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 30));
		btnBack.setBounds(680, 489, 196, 50);
		getContentPane().add(btnBack);
		
		getContentPane().add(btnReportGeneration);
		btnBack.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnBack) {
				
				Login window = new Login();
				window.setVisible(true);
				MainMenu.this.dispose();			
			}
				
		}});
		
		btnReportGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportSet rps=new ReportSet();
				rps.setVisible(true);
				MainMenu.this.dispose();
			}
		});
		btnReportGeneration.setForeground(Color.WHITE);
		btnReportGeneration.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 30));
		btnReportGeneration.setBackground(new Color(205, 133, 63));
		btnReportGeneration.setBounds(57, 489, 365, 50);
		
	}
}
