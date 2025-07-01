package management.page;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import management.service.ModifyService;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;

/**
 * This is the page for modifying stock statuses of different dishes.
 * 
 * <p>You can click the toggle button of the dish you want to set and save your modification.
 * 
 * @author Xiaotian Wang
 *
 */
public class ModifyStock extends JFrame{

	private static final long serialVersionUID = 5119291180125677903L;
	
	//JButton
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	//JToggleButton
	private JToggleButton tglbtnSoup0 = new JToggleButton("");
	private JToggleButton tglbtnSoup1 = new JToggleButton("");
	private JToggleButton tglbtnSoup2 = new JToggleButton("");
	private JToggleButton tglbtnNoodles0 = new JToggleButton("");
	private JToggleButton tglbtnNoodles1 = new JToggleButton("");
	private JToggleButton tglbtnNoodles2 = new JToggleButton("");
	private JToggleButton tglbtnSpringOnion0 = new JToggleButton("");
	private JToggleButton tglbtnSpringOnion1 = new JToggleButton("");
	private JToggleButton tglbtnSpringOnion2 = new JToggleButton("");
	private JToggleButton tglbtnNori = new JToggleButton("");
	private JToggleButton tglbtnChashu = new JToggleButton("");
	private JToggleButton tglbtnEgg = new JToggleButton("");
	private JToggleButton tglbtnExtraNori = new JToggleButton("");
	private JToggleButton tglbtnExtraChashu = new JToggleButton("");
	private JToggleButton tglbtnExtraEgg = new JToggleButton("");
	private JToggleButton tglbtnShoots = new JToggleButton("");
	
	//JLabel
	private JLabel lblTitle = new JLabel("MODIFY STOCK");

	private JLabel lblSoup = new JLabel("Soup:");
	private JLabel lblTonkotsu = new JLabel("Tonkotsu");
	private JLabel lblShoyu = new JLabel("Shoyu");
	private JLabel lblShio = new JLabel("Shio");
	
	private JLabel lblNoodles = new JLabel("Noodles:");
	private JLabel lblSoft = new JLabel("Soft");
	private JLabel lblMedium = new JLabel("Medium");
	private JLabel lblFirm = new JLabel("Firm");
	
	private JLabel lblSpringOnion = new JLabel("Spring onion:");
	private JLabel lblSpringOnion0 = new JLabel("No please");
	private JLabel lblSpringOnion1 = new JLabel("Just a little");
	private JLabel lblSpringOnion2 = new JLabel("A lot!");
	
	private JLabel lblNori = new JLabel("Nori");
	private JLabel lblChashu = new JLabel("Chashu");
	private JLabel lblBoiledEgg = new JLabel("Boiled egg");

	private JLabel lblExtraNori = new JLabel("Extra Nori");
	private JLabel lblExtraEgg = new JLabel("Extra boiled egg");
	private JLabel lblExtraChashu = new JLabel("Extra Chashu");
	private JLabel lblShoots = new JLabel("Bamboo shoots");
	
	private ModifyService ms = new ModifyService();

	

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyStock window = new ModifyStock();
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
	public ModifyStock() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		getContentPane().setBackground(new Color(240, 220, 140));
		setBounds(100, 100, 1212, 784);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		

		
		
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(205, 133, 63));
		btnSave.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 40));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(196, 668, 202, 53);
		getContentPane().add(btnSave);
		btnSave.addActionListener(new SaveListener());
		
		
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(205, 133, 63));
		btnCancel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 40));
		btnCancel.setBounds(811, 668, 202, 53);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnCancel) {
				
				ModifyMenu window = new ModifyMenu();
				window.setVisible(true);
				dispose();
			}
				
		}});
		
		
		//labels
		
		lblTitle.setForeground(new Color(128, 0, 0));
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Bell MT", Font.BOLD, 40));
		lblTitle.setBounds(421, 8, 347, 57);
		getContentPane().add(lblTitle);

		lblNori.setForeground(new Color(160, 82, 45));
		lblNori.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblNori.setBounds(98, 387, 71, 35);
		getContentPane().add(lblNori);
		
		lblChashu.setForeground(new Color(160, 82, 45));
		lblChashu.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblChashu.setBounds(436, 387, 141, 35);
		getContentPane().add(lblChashu);
				
		lblSoup.setForeground(new Color(160, 82, 45));
		lblSoup.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblSoup.setBounds(79, 93, 90, 53);
		getContentPane().add(lblSoup);
		
		lblTonkotsu.setForeground(new Color(30, 144, 255));
		lblTonkotsu.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblTonkotsu.setBounds(227, 103, 133, 35);
		getContentPane().add(lblTonkotsu);
		
		
		lblShoyu.setForeground(new Color(139, 0, 0));
		lblShoyu.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblShoyu.setBounds(560, 103, 96, 35);
		getContentPane().add(lblShoyu);
		
		
		lblShio.setForeground(new Color(255, 127, 80));
		lblShio.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblShio.setBounds(879, 103, 62, 35);
		getContentPane().add(lblShio);
		
		
		lblNoodles.setForeground(new Color(160, 82, 45));
		lblNoodles.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblNoodles.setBounds(55, 195, 175, 53);
		getContentPane().add(lblNoodles);
		
		
		lblSoft.setForeground(new Color(128, 128, 128));
		lblSoft.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSoft.setBounds(271, 205, 62, 35);
		getContentPane().add(lblSoft);
		
		
		lblMedium.setForeground(new Color(105, 105, 105));
		lblMedium.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblMedium.setBounds(545, 194, 144, 35);
		getContentPane().add(lblMedium);
		
		
		lblFirm.setForeground(new Color(0, 0, 0));
		lblFirm.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblFirm.setBounds(883, 194, 71, 35);
		getContentPane().add(lblFirm);
		
		
		lblSpringOnion.setForeground(new Color(160, 82, 45));
		lblSpringOnion.setFont(new Font("Bell MT", Font.ITALIC, 35));
		lblSpringOnion.setBounds(0, 305, 213, 53);
		getContentPane().add(lblSpringOnion);
		
		
		lblExtraNori.setForeground(new Color(160, 82, 45));
		lblExtraNori.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblExtraNori.setBounds(111, 488, 202, 35);
		getContentPane().add(lblExtraNori);
		
		
		lblBoiledEgg.setForeground(new Color(160, 82, 45));
		lblBoiledEgg.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblBoiledEgg.setBounds(834, 387, 146, 35);
		getContentPane().add(lblBoiledEgg);
		
		

		
		lblSpringOnion0.setForeground(Color.GRAY);
		lblSpringOnion0.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSpringOnion0.setBounds(223, 315, 137, 35);
		getContentPane().add(lblSpringOnion0);
		
		
		lblSpringOnion1.setForeground(SystemColor.controlDkShadow);
		lblSpringOnion1.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSpringOnion1.setBounds(533, 315, 156, 35);
		getContentPane().add(lblSpringOnion1);
		
		
		lblSpringOnion2.setForeground(Color.BLACK);
		lblSpringOnion2.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblSpringOnion2.setBounds(883, 315, 71, 35);
		getContentPane().add(lblSpringOnion2);
		
		
		lblExtraEgg.setForeground(new Color(160, 82, 45));
		lblExtraEgg.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblExtraEgg.setBounds(62, 585, 230, 35);
		getContentPane().add(lblExtraEgg);
		
		
		lblExtraChashu.setForeground(new Color(160, 82, 45));
		lblExtraChashu.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblExtraChashu.setBounds(602, 488, 202, 35);
		getContentPane().add(lblExtraChashu);
		
	
		lblShoots.setForeground(new Color(160, 82, 45));
		lblShoots.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblShoots.setBounds(614, 585, 226, 35);
		getContentPane().add(lblShoots);
		
		
		//toggle buttons

		
		tglbtnSoup0.setBounds(359, 65, 141, 92);
		tglbtnSoup0.setBorderPainted(false);
		tglbtnSoup0.setContentAreaFilled(false); 
		tglbtnSoup0.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnSoup0.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnSoup0);
		initTglbtn(tglbtnSoup0, "Soup0");
		
		tglbtnSoup1.setBounds(699, 75, 141, 92);
		tglbtnSoup1.setBorderPainted(false);
		tglbtnSoup1.setContentAreaFilled(false); 
		tglbtnSoup1.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnSoup1.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnSoup1);
		initTglbtn(tglbtnSoup1, "Soup1");
		
		
		tglbtnSoup2.setBounds(1019, 75, 141, 92);
		tglbtnSoup2.setBorderPainted(false);
		tglbtnSoup2.setContentAreaFilled(false); 
		tglbtnSoup2.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnSoup2.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnSoup2);
		initTglbtn(tglbtnSoup2, "Soup2");
		
		
		tglbtnNoodles0.setContentAreaFilled(false);
		tglbtnNoodles0.setBorderPainted(false);
		tglbtnNoodles0.setBounds(359, 167, 141, 92);
		tglbtnNoodles0.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnNoodles0.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnNoodles0);
		initTglbtn(tglbtnNoodles0, "Noodles0");
		
		tglbtnNoodles1.setContentAreaFilled(false);
		tglbtnNoodles1.setBorderPainted(false);
		tglbtnNoodles1.setBounds(699, 167, 141, 92);
		tglbtnNoodles1.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnNoodles1.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnNoodles1);
		initTglbtn(tglbtnNoodles1, "Noodles1");
	
		tglbtnNoodles2.setContentAreaFilled(false);
		tglbtnNoodles2.setBorderPainted(false);
		tglbtnNoodles2.setBounds(1019, 167, 141, 92);
		tglbtnNoodles2.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnNoodles2.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnNoodles2);
		initTglbtn(tglbtnNoodles2, "Noodles2");
		
		
		tglbtnSpringOnion0.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnSpringOnion0.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		tglbtnSpringOnion0.setContentAreaFilled(false);
		tglbtnSpringOnion0.setBorderPainted(false);
		tglbtnSpringOnion0.setBounds(359, 280, 141, 92);
		getContentPane().add(tglbtnSpringOnion0);
		initTglbtn(tglbtnSpringOnion0, "Spring onion0");
		
	
		tglbtnSpringOnion1.setContentAreaFilled(false);
		tglbtnSpringOnion1.setBorderPainted(false);
		tglbtnSpringOnion1.setBounds(710, 280, 141, 92);
		tglbtnSpringOnion1.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnSpringOnion1.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnSpringOnion1);
		initTglbtn(tglbtnSpringOnion1, "Spring onion1");
	
		tglbtnSpringOnion2.setContentAreaFilled(false);
		tglbtnSpringOnion2.setBorderPainted(false);
		tglbtnSpringOnion2.setBounds(1019, 280, 141, 92);
		tglbtnSpringOnion2.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnSpringOnion2.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnSpringOnion2);
		initTglbtn(tglbtnSpringOnion2, "Spring onion2");
		
		tglbtnNori.setContentAreaFilled(false);
		tglbtnNori.setBorderPainted(false);
		tglbtnNori.setBounds(168, 360, 141, 92);
		tglbtnNori.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnNori.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnNori);
		initTglbtn(tglbtnNori, "Nori");
		

		tglbtnChashu.setContentAreaFilled(false);
		tglbtnChashu.setBorderPainted(false);
		tglbtnChashu.setBounds(560, 360, 141, 92);
		tglbtnChashu.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnChashu.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnChashu);
		initTglbtn(tglbtnChashu, "Chashu");
	
		tglbtnEgg.setContentAreaFilled(false);
		tglbtnEgg.setBorderPainted(false);
		tglbtnEgg.setBounds(1019, 368, 141, 92);
		tglbtnEgg.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnEgg.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnEgg);
		initTglbtn(tglbtnEgg, "Boiled egg");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(139, 69, 19));
		separator.setBackground(new Color(160, 82, 45));
		separator.setBounds(0, 453, 1198, 11);
		tglbtnSpringOnion2.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnSpringOnion2.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(separator);
		
		
		
		tglbtnExtraNori.setContentAreaFilled(false);
		tglbtnExtraNori.setBorderPainted(false);
		tglbtnExtraNori.setBounds(283, 453, 141, 92);
		tglbtnExtraNori.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnExtraNori.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnExtraNori);
		initTglbtn(tglbtnExtraNori, "Extra Nori");
		
		
		tglbtnExtraChashu.setContentAreaFilled(false);
		tglbtnExtraChashu.setBorderPainted(false);
		tglbtnExtraChashu.setBounds(826, 453, 141, 92);
		tglbtnExtraChashu.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnExtraChashu.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnExtraChashu);
		initTglbtn(tglbtnExtraChashu, "Extra Chashu");
		
		
		tglbtnExtraEgg.setContentAreaFilled(false);
		tglbtnExtraEgg.setBorderPainted(false);
		tglbtnExtraEgg.setBounds(283, 551, 141, 92);
		tglbtnExtraEgg.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnExtraEgg.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnExtraEgg);
		initTglbtn(tglbtnExtraEgg, "Extra boiled egg");
		
	
		tglbtnShoots.setContentAreaFilled(false);
		tglbtnShoots.setBorderPainted(false);
		tglbtnShoots.setBounds(826, 555, 141, 92);
		tglbtnShoots.setSelectedIcon(new ImageIcon(ModifyStock.class.getResource("/management/on.png")));
		tglbtnShoots.setIcon(new ImageIcon(ModifyStock.class.getResource("/management/off.png")));
		getContentPane().add(tglbtnShoots);
		initTglbtn(tglbtnShoots, "Bamboo shoots");

		//checkRecadd();
		
		
	}
	
	/**
	 * This method is used to initialize the toggle button according to
	 * current stock status.
	 * 
	 * @param tglbtn the toggle button you want to initialization
	 * @param name the name of the dish
	 */
	private void initTglbtn(JToggleButton tglbtn, String name) {
		
			if (ms.getOneStock(name) == 1) {
				tglbtn.setSelected(true);
			}else {
				tglbtn.setSelected(false);
			}

		
	}
	
	/**
	 * This method is used to change the stock state of a specific dish
	 * according to its corresponding stock.
	 * 
	 * @param tglbtn the toggle button of the dish
	 * @param name the name of the dish
	 */
	private void setStock(JToggleButton tglbtn, String name) {
		if (tglbtn.isSelected()==true) {
			ms.changeStock(name, 1);
		}else {
			ms.changeStock(name, 0);
		}
	}
	
	/**
	 * This method is used to check the current recommended add-on and
	 * the current recommended add-on can't be set to unavailable.
	 */
	private void checkRecadd(){
		String recadd = ms.getRec().get("addon");
		switch (recadd) {
			case("Extra Nori"):
				tglbtnExtraNori.setEnabled(false);
				break;
			case("Bamboo shoots"):
				tglbtnShoots.setEnabled(false);
				break;
			case("Extra boiled egg"):
				tglbtnExtraEgg.setEnabled(false);
				break;
			case("Extra Chashu"):
				tglbtnExtraChashu.setEnabled(false);
				break;
		}
	}
	
	/**
	 * This is a inner class implements {@code ActionListener}.
	 * 
	 * <p> When the "save" button is clicked, {@code actionPerformed} method will be invoked to
	 * save the modified stock.
	 * 
	 * @author Xiaotian Wang
	 *
	 */
	public class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnSave) {
				
				
					
						int n = JOptionPane.showConfirmDialog(null, "Are you sure to save?", "Confirm", JOptionPane.YES_NO_OPTION);
						
						if (n == JOptionPane.YES_OPTION) {
						
							setStock(tglbtnSoup0, "Soup0");
							setStock(tglbtnSoup1, "Soup1");
							setStock(tglbtnSoup2, "Soup2");
							
							setStock(tglbtnNoodles0, "Noodles0");
							setStock(tglbtnNoodles1, "Noodles1");
							setStock(tglbtnNoodles2, "Noodles2");
							
							setStock(tglbtnSpringOnion0, "Spring onion0");
							setStock(tglbtnSpringOnion1, "Spring onion1");
							setStock(tglbtnSpringOnion2, "Spring onion2");
							
							setStock(tglbtnNori, "Nori");
							setStock(tglbtnChashu, "Chashu");
							setStock(tglbtnEgg, "Boiled egg");
							
							setStock(tglbtnExtraNori, "Extra Nori");
							setStock(tglbtnExtraChashu, "Extra Chashu");
							setStock(tglbtnExtraEgg, "Extra boiled egg");
							setStock(tglbtnShoots, "Bamboo shoots");
							
							ms.saveStock();
							
							ModifyMenu window = new ModifyMenu();
							window.setVisible(true);
							dispose();
						}
					
			}
		}
	}
}
