package management.page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

import management.service.*;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import management.util.*;

/**
 * This is a page showing statistics of last week.
 * 
 * <p>This page visualize a report showing for each item on the menu how many times it 
 * has been sold and how many times has each payment methods been used in the past week.
 * 
 * @author Xiaotian Wang
 *
 */
public class ViewStats extends JFrame{

	private static final long serialVersionUID = 2374409612928678773L;
	
	private JLabel lblSoup;
	private JLabel lblSoup_Tonkotsu;
	private JLabel lblSoup_Shoyu;
	private JLabel lblSoup_Shio;
	private JLabel lblSpiciness;
	
	private StatsService ss = new StatsService("orderlist.csv", "paymentlist.csv",7);
	private JLabel lblNoodles;
	private JLabel lblNoodles_Soft;
	private JLabel lblNoodles_Medium;
	private JLabel lblNoodles_Firm;
	private JLabel lblSpringOnion;
	private JLabel lblSpringOnion_0;
	private JLabel lblSpringOnion_1;
	private JLabel lblSpringOnion_2;
	private JLabel lblNori;
	private JLabel lblChashu;
	private JLabel lblBoiledEgg;
	private JLabel lblBambooShoots;
	private JLabel lblExtraNori;
	private JLabel lblExtraChashu;
	private JLabel lblExtraBoiledEgg;
	private JLabel lblPaymentMethod;
	private JLabel lblCard;
	private JLabel lblCash;
	private JLabel lblStamp;
	private JLabel lblOnionImg;
	private JLabel lblNoriImg;
	private JLabel lblChashuImg;
	private JLabel lblEggImg;
	private JLabel lblShootsImg;
	private JLabel lblNoodlesImg;
	private JLabel lblChefImg;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStats window = new ViewStats();
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
	public ViewStats() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		getContentPane().setBackground(new Color(240, 220, 140));
		setBounds(100, 100, 1249, 780);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		lblSoup = new JLabel("Soup:");
		lblSoup.setForeground(new Color(128, 0, 0));
		lblSoup.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblSoup.setBounds(174, 107, 123, 55);
		getContentPane().add(lblSoup);
		
		lblSoup_Tonkotsu = new JLabel("Tonkotsu: "+ss.getStats().getSoup().get(0));
		lblSoup_Tonkotsu.setForeground(new Color(30, 144, 255));
		lblSoup_Tonkotsu.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSoup_Tonkotsu.setBounds(357, 119, 242, 43);
		getContentPane().add(lblSoup_Tonkotsu);
		
		lblSoup_Shoyu = new JLabel("Shoyu: "+ss.getStats().getSoup().get(1));
		lblSoup_Shoyu.setForeground(new Color(128, 0, 0));
		lblSoup_Shoyu.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSoup_Shoyu.setBounds(660, 120, 242, 42);
		getContentPane().add(lblSoup_Shoyu);
		
		lblSoup_Shio = new JLabel("Shio: "+ss.getStats().getSoup().get(2));
		lblSoup_Shio.setForeground(new Color(255, 0, 255));
		lblSoup_Shio.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSoup_Shio.setBounds(951, 122, 245, 39);
		getContentPane().add(lblSoup_Shio);
		
		lblSpiciness = new JLabel("Most popular spiciness: "+ss.getMPSpiciness());
		lblSpiciness.setForeground(Color.RED);
		lblSpiciness.setFont(new Font("Bell MT", Font.PLAIN, 25));
		lblSpiciness.setBounds(854, 415, 389, 65);
		getContentPane().add(lblSpiciness);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(205, 133, 63));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 30));
		btnBack.setBounds(1065, 687, 131, 39);
		getContentPane().add(btnBack);
		
		lblNoodles = new JLabel("Noodles:");
		lblNoodles.setForeground(new Color(128, 0, 0));
		lblNoodles.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblNoodles.setBounds(164, 168, 144, 54);
		getContentPane().add(lblNoodles);
		
		lblNoodles_Soft = new JLabel("Soft: "+ss.getStats().getNoodles().get(0));
		lblNoodles_Soft.setForeground(new Color(169, 169, 169));
		lblNoodles_Soft.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblNoodles_Soft.setBounds(384, 173, 231, 49);
		getContentPane().add(lblNoodles_Soft);
		
		lblNoodles_Medium = new JLabel("Medium: "+ss.getStats().getNoodles().get(1));
		lblNoodles_Medium.setForeground(new Color(105, 105, 105));
		lblNoodles_Medium.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblNoodles_Medium.setBounds(660, 175, 259, 47);
		getContentPane().add(lblNoodles_Medium);
		
		lblNoodles_Firm = new JLabel("Firm: "+ss.getStats().getNoodles().get(2));
		lblNoodles_Firm.setForeground(new Color(0, 0, 0));
		lblNoodles_Firm.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblNoodles_Firm.setBounds(951, 176, 245, 43);
		getContentPane().add(lblNoodles_Firm);
		
		lblSpringOnion = new JLabel("Spring onion:");
		lblSpringOnion.setForeground(new Color(128, 0, 0));
		lblSpringOnion.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblSpringOnion.setBounds(129, 232, 224, 54);
		getContentPane().add(lblSpringOnion);
		
		lblSpringOnion_0 = new JLabel("No please: "+ss.getStats().getSpringonion().get(0));
		lblSpringOnion_0.setForeground(new Color(102, 205, 170));
		lblSpringOnion_0.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSpringOnion_0.setBounds(357, 228, 258, 48);
		getContentPane().add(lblSpringOnion_0);
		
		lblSpringOnion_1 = new JLabel("Just a little: "+ss.getStats().getSpringonion().get(1));
		lblSpringOnion_1.setForeground(new Color(50, 205, 50));
		lblSpringOnion_1.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSpringOnion_1.setBounds(639, 232, 263, 44);
		getContentPane().add(lblSpringOnion_1);
		
		lblSpringOnion_2 = new JLabel("A lot!: "+ss.getStats().getSpringonion().get(2));
		lblSpringOnion_2.setForeground(new Color(0, 128, 0));
		lblSpringOnion_2.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSpringOnion_2.setBounds(950, 236, 246, 40);
		getContentPane().add(lblSpringOnion_2);
		
		lblNori = new JLabel("Nori: Yes : "+ss.getStats().getNori().get(1)+" / No: "+ss.getStats().getNori().get(0));
		lblNori.setForeground(new Color(128, 0, 0));
		lblNori.setFont(new Font("Bell MT", Font.ITALIC, 25));
		lblNori.setBounds(54, 316, 331, 39);
		getContentPane().add(lblNori);
		
		lblChashu = new JLabel("Chashu: Yes: "+ss.getStats().getChashu().get(1)+" / No: "+ss.getStats().getChashu().get(0));
		lblChashu.setForeground(new Color(128, 0, 0));
		lblChashu.setFont(new Font("Bell MT", Font.ITALIC, 25));
		lblChashu.setBounds(436, 316, 376, 39);
		getContentPane().add(lblChashu);
		
		lblBoiledEgg = new JLabel("Boiled egg: Yes: "+ss.getStats().getBoiledegg().get(1)+" / No: "+ss.getStats().getBoiledegg().get(0));
		lblBoiledEgg.setForeground(new Color(128, 0, 0));
		lblBoiledEgg.setFont(new Font("Bell MT", Font.ITALIC, 25));
		lblBoiledEgg.setBounds(828, 316, 415, 39);
		getContentPane().add(lblBoiledEgg);
		
		lblBambooShoots = new JLabel("Bamboo shoots: "+ss.getStats().getShootsadd());
		lblBambooShoots.setForeground(new Color(128, 0, 0));
		lblBambooShoots.setFont(new Font("Bell MT", Font.ITALIC, 25));
		lblBambooShoots.setBounds(105, 556, 280, 49);
		getContentPane().add(lblBambooShoots);
		
		lblExtraNori = new JLabel("Extra Nori: "+ss.getStats().getNoriadd());
		lblExtraNori.setForeground(new Color(128, 0, 0));
		lblExtraNori.setFont(new Font("Bell MT", Font.ITALIC, 25));
		lblExtraNori.setBounds(105, 415, 280, 53);
		getContentPane().add(lblExtraNori);
		
		lblExtraChashu = new JLabel("Extra Chashu: "+ss.getStats().getChashuadd());
		lblExtraChashu.setForeground(new Color(128, 0, 0));
		lblExtraChashu.setFont(new Font("Bell MT", Font.ITALIC, 25));
		lblExtraChashu.setBounds(466, 420, 287, 53);
		getContentPane().add(lblExtraChashu);
		
		lblExtraBoiledEgg = new JLabel("Extra boiled egg: "+ss.getStats().getEggadd());
		lblExtraBoiledEgg.setForeground(new Color(128, 0, 0));
		lblExtraBoiledEgg.setFont(new Font("Bell MT", Font.ITALIC, 25));
		lblExtraBoiledEgg.setBounds(466, 556, 306, 49);
		getContentPane().add(lblExtraBoiledEgg);
		
		lblPaymentMethod = new JLabel("Payment Method:");
		lblPaymentMethod.setForeground(new Color(0, 0, 0));
		lblPaymentMethod.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblPaymentMethod.setBounds(105, 639, 286, 39);
		getContentPane().add(lblPaymentMethod);
		
		lblCard = new JLabel("Card: "+ss.getStats().getPyMethods().get("Card"));
		lblCard.setForeground(new Color(0, 0, 0));
		lblCard.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblCard.setBounds(398, 639, 201, 39);
		getContentPane().add(lblCard);
		
		lblCash = new JLabel("Cash: "+ss.getStats().getPyMethods().get("Cash"));
		lblCash.setForeground(new Color(0, 0, 0));
		lblCash.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblCash.setBounds(639, 639, 201, 39);
		getContentPane().add(lblCash);
		
		lblStamp = new JLabel("Stamp: "+ss.getStats().getPyMethods().get("Stamp"));
		lblStamp.setForeground(new Color(0, 0, 0));
		lblStamp.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblStamp.setBounds(860, 639, 218, 39);
		getContentPane().add(lblStamp);
		
		JLabel lblPepperImg = new JLabel("");
		lblPepperImg.setIcon(new ImageIcon(ViewStats.class.getResource("/management/Pepper.png")));
		lblPepperImg.setBounds(767, 407, 48, 70);
		getContentPane().add(lblPepperImg);
		
		JLabel lblPaymentImg = new JLabel("");
		lblPaymentImg.setIcon(new ImageIcon(ViewStats.class.getResource("/management/Payment.png")));
		lblPaymentImg.setBounds(54, 639, 48, 39);
		getContentPane().add(lblPaymentImg);
		
		lblOnionImg = new JLabel("");
		lblOnionImg.setIcon(new ImageIcon(ViewStats.class.getResource("/management/Onion.png")));
		lblOnionImg.setBounds(53, 218, 58, 68);
		getContentPane().add(lblOnionImg);
		
		lblNoriImg = new JLabel("");
		lblNoriImg.setIcon(new ImageIcon(ViewStats.class.getResource("/management/Nori.png")));
		lblNoriImg.setBounds(45, 407, 58, 65);
		getContentPane().add(lblNoriImg);
		
		lblChashuImg = new JLabel("");
		lblChashuImg.setIcon(new ImageIcon(ViewStats.class.getResource("/management/Chashu.png")));
		lblChashuImg.setBounds(398, 415, 58, 57);
		getContentPane().add(lblChashuImg);
		
		lblEggImg = new JLabel("");
		lblEggImg.setIcon(new ImageIcon(ViewStats.class.getResource("/management/Egg.png")));
		lblEggImg.setBounds(398, 540, 58, 57);
		getContentPane().add(lblEggImg);
		
		lblShootsImg = new JLabel("");
		lblShootsImg.setIcon(new ImageIcon(ViewStats.class.getResource("/management/Shoots.png")));
		lblShootsImg.setBounds(45, 540, 58, 65);
		getContentPane().add(lblShootsImg);
		
		lblNoodlesImg = new JLabel("");
		lblNoodlesImg.setIcon(new ImageIcon(ViewStats.class.getResource("/management/Noodles.png")));
		lblNoodlesImg.setBounds(51, 150, 58, 65);
		getContentPane().add(lblNoodlesImg);
		
		lblChefImg = new JLabel("");
		lblChefImg.setIcon(new ImageIcon(ViewStats.class.getResource("/management/Chef.png")));
		lblChefImg.setBounds(51, 90, 65, 57);
		getContentPane().add(lblChefImg);
		
		JLabel lblSpiciness1 = new JLabel("1: "+ss.getStats().getSpiciness().get(1));
		lblSpiciness1.setForeground(Color.RED);
		lblSpiciness1.setFont(new Font("Bell MT", Font.PLAIN, 25));
		lblSpiciness1.setBounds(990, 481, 123, 65);
		getContentPane().add(lblSpiciness1);
		
		JLabel lblSpiciness3 = new JLabel("3: "+ss.getStats().getSpiciness().get(3));
		lblSpiciness3.setForeground(Color.RED);
		lblSpiciness3.setFont(new Font("Bell MT", Font.PLAIN, 25));
		lblSpiciness3.setBounds(864, 556, 116, 65);
		getContentPane().add(lblSpiciness3);
		
		JLabel lblSpiciness4 = new JLabel("4: "+ss.getStats().getSpiciness().get(4));
		lblSpiciness4.setForeground(Color.RED);
		lblSpiciness4.setFont(new Font("Bell MT", Font.PLAIN, 25));
		lblSpiciness4.setBounds(990, 556, 116, 65);
		getContentPane().add(lblSpiciness4);
		
		JLabel lblSpiciness0 = new JLabel("0: "+ss.getStats().getSpiciness().get(0));
		lblSpiciness0.setForeground(Color.RED);
		lblSpiciness0.setFont(new Font("Bell MT", Font.PLAIN, 25));
		lblSpiciness0.setBounds(864, 481, 116, 65);
		getContentPane().add(lblSpiciness0);
		
		JLabel lblSpiciness2 = new JLabel("2: "+ss.getStats().getSpiciness().get(2));
		lblSpiciness2.setForeground(Color.RED);
		lblSpiciness2.setFont(new Font("Bell MT", Font.PLAIN, 25));
		lblSpiciness2.setBounds(1112, 481, 131, 65);
		getContentPane().add(lblSpiciness2);
		
		JLabel lblSpiciness5 = new JLabel("5: "+ss.getStats().getSpiciness().get(5));
		lblSpiciness5.setForeground(Color.RED);
		lblSpiciness5.setFont(new Font("Bell MT", Font.PLAIN, 25));
		lblSpiciness5.setBounds(1116, 550, 122, 65);
		getContentPane().add(lblSpiciness5);
		
		JSeparator separator1 = new JSeparator();
		separator1.setForeground(new Color(160, 82, 45));
		separator1.setBackground(new Color(160, 82, 45));
		separator1.setBounds(0, 383, 1265, 14);
		getContentPane().add(separator1);
		
		JSeparator separator2 = new JSeparator();
		separator2.setForeground(new Color(160, 82, 45));
		separator2.setBackground(new Color(160, 82, 45));
		separator2.setBounds(0, 615, 1265, 14);
		getContentPane().add(separator2);
		
		JLabel lblTitle = new JLabel("Statistics");
		lblTitle.setForeground(new Color(160, 82, 45));
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblTitle.setBounds(450, 24, 242, 65);
		getContentPane().add(lblTitle);
		
		JComboBox<String> timeSelect = new JComboBox<String>();
		timeSelect.setForeground(new Color(255, 255, 255));
		timeSelect.setFont(new Font("Arial", Font.BOLD, 15));
		timeSelect.setBackground(new Color(210, 105, 30));
		timeSelect.addItem("Last Week");
		timeSelect.addItem("Last Month");
		timeSelect.addItem("Yesterday");
		timeSelect.setBounds(660, 39, 124, 35);
		getContentPane().add(timeSelect);
		
		CircleButton btnSale = new CircleButton("<html>View Weekly<br>&nbsp;Sales Chart</html>");
		btnSale.setForeground(Color.ORANGE);
		btnSale.setBackground(Color.white);
		btnSale.setFont(new Font("Jack ", Font.BOLD, 16));
		btnSale.setBounds(1086, 15, 131, 124);
		getContentPane().add(btnSale);
		
		btnSale.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Chart chart = new Chart("./src/management/");
				chart.makeBarChart();
				SaleChart window  = new SaleChart();
				window.setVisible(true);
			}
			
		});
		
		
		
		
		
		timeSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String item = (String)timeSelect.getSelectedItem();
				if (item.equals("Last Month")) {
					LastMonthStats window = new LastMonthStats();
					window.setVisible(true);
					dispose();
				}
				
				if (item.equals("Yesterday")) {
					YesterdayStats window = new YesterdayStats();
					window.setVisible(true);
					dispose();
				}
			}
		});
		
		
		
		btnBack.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnBack) {
				
				MainMenu window = new MainMenu();
				window.setVisible(true);
				dispose();
			}
				
		}});
	}
}
