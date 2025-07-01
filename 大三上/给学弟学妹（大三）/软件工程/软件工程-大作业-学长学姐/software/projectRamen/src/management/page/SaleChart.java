package management.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;


import management.service.*;

/**
 * This is the page for showing last week sales chart.
 * 
 * @author Xiaotian Wang
 *
 */
public class SaleChart extends JFrame {
	
	private JLabel lblChart;
	
	

	/**
	 * Create the application.
	 */
	public SaleChart() {
		getContentPane().setBackground(new Color(240, 220, 140));
		getContentPane().setForeground(new Color(0, 0, 0));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 608, 593);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		lblChart = new JLabel("");
		lblChart.setIcon(new ImageIcon("src/management/Last Week Sale.png"));
		lblChart.setBounds(39, 10, 533, 530);
		getContentPane().add(lblChart);
		
	
		

	}
	

	/**
	 * Launch the application.
	 * @param args no need to use
	 */
	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleChart window = new SaleChart();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}