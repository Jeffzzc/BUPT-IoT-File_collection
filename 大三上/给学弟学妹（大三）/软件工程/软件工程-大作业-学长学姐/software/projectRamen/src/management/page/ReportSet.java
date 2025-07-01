package management.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

import management.report.Report;
import management.service.*;

/**
 * This is the page for showing last week sales chart.
 * 
 * @author Xiaotian Wang
 *
 */
public class ReportSet extends JFrame {
	
	private Report rpt;

	/**
	 * Create the application.
	 */
	public ReportSet() {
		setTitle("Report Generation");
		getContentPane().setBackground(new Color(240, 220, 140));
		getContentPane().setForeground(new Color(0, 0, 0));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		rpt=new Report();
		setBounds(100, 100, 444, 292);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JToggleButton btnrpt = new JToggleButton("");
		btnrpt.setBounds(173, 93, 76, 43);
		btnrpt.setContentAreaFilled(false);
		btnrpt.setBorderPainted(false);
		btnrpt.setContentAreaFilled(false);
		btnrpt.setBorderPainted(false);		
		btnrpt.setSelectedIcon(getImageIcon("/management/on.png",49,31));
		btnrpt.setIcon(getImageIcon("/management/off.png",49,31));
		initbtn(btnrpt);
		btnrpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rpt.isStop()) {
					if(rpt.setStop(1)==false) {
						JOptionPane.showInternalMessageDialog(getContentPane(),
								"File read/write error!", "Error",
								JOptionPane.INFORMATION_MESSAGE);	
					} else {
						JOptionPane.showInternalMessageDialog(getContentPane(),
								"<html>"+"Auto generation is on.<br/>"
								+ "And please keep the generator program running.<br/>"
								+ "Otherwise the report will not be generated properly!</html>", "Notice",
								JOptionPane.INFORMATION_MESSAGE);
						JOptionPane.showInternalMessageDialog(getContentPane(),
								"One more reminder: open the generator program", "Notice",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					if(rpt.setStop(0)==false) {
						JOptionPane.showInternalMessageDialog(getContentPane(),
								"File read/write error!", "Error",
								JOptionPane.INFORMATION_MESSAGE);	
					} else {
						JOptionPane.showInternalMessageDialog(getContentPane(),
								"<html>"+"Auto generation is off.</html>", "Notice",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		getContentPane().add(btnrpt);
		
		JLabel lblNewLabel = new JLabel("Automatic Report Generation");
		lblNewLabel.setForeground(new Color(139, 0, 0));
		lblNewLabel.setFont(new Font("Bell MT", Font.BOLD, 20));
		lblNewLabel.setBounds(90, 10, 278, 45);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>When the switch is turned on, <br/>the system automatically generates a report every Monday</html>");
		lblNewLabel_1.setForeground(new Color(139, 69, 19));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(10, 32, 422, 76);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Generate ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rpt.generateReport();
				JOptionPane.showInternalMessageDialog(getContentPane(),
						"<html>"+"The report has been generated, please check.</html>", "Notice",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(205, 133, 63));
		btnNewButton.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		btnNewButton.setBounds(50, 207, 125, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mm=new MainMenu();
				mm.setVisible(true);
				ReportSet.this.dispose();
			}
		});
		btnNewButton_1.setBounds(310, 207, 93, 23);
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(205, 133, 63));
		btnNewButton_1.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 15));
		getContentPane().add(btnNewButton_1);
		
		JLabel lblYouCanAlso = new JLabel("<html>You can also directly click the button below to generate the report<html>");
		lblYouCanAlso.setForeground(new Color(139, 69, 19));
		lblYouCanAlso.setFont(new Font("Arial", Font.PLAIN, 15));
		lblYouCanAlso.setBounds(10, 146, 245, 50);
		getContentPane().add(lblYouCanAlso);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	
		

	}
	
	/**
	 * This method is used to initialize the toggle button according to
	 * current stock status.
	 * 
	 * @param tglbtn the toggle button you want to initialization
	 */
	private void initbtn(JToggleButton tglbtn) {
		if(rpt.isStop())tglbtn.setSelected(false);
		else tglbtn.setSelected(true);
	}
	
	private ImageIcon getImageIcon(String path, int width, int height) {
		if (width == 0 || height == 0) {
			return new ImageIcon(this.getClass().getResource(path));
		}
		ImageIcon icon = new ImageIcon(this.getClass().getResource(path));
		icon.setImage(icon.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT));
		return icon;
	}
	
	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportSet window = new ReportSet();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
