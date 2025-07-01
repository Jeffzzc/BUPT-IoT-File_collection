package pages;

import javax.swing.JButton;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import datautil.Mathutil;
import domain.DataSource;
import domain.Stock;
import service.OrderService;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The {@code AddOnes} class wraps a value of the primitive type
 * {@code AddOnes} in an object. And this is the
 * <code>add-ones</code> page of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. 
 * Users can choose the type of feed they need and the corresponding quantity.
 * Each feed has a maximum limit.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class AddOns extends PageBase implements ActionListener {

	private JPanel contentPane;
	private int Noriadd = 0;
	private int egg = 0;
	private int shoots = 0;
	private int Chashuadd = 0;
	private double NoriaddP = 0;
	private double eggP = 0;
	private double shootsP = 0;
	private double ChashuaddP = 0;
	
	/** Default progress of progress bar {@value} */
	private static int currentProgress = 20;
	private JButton buttonNoriPlus, buttonBoiledeggPlus, buttonBambooPlus, buttonChashuPlus, buttonNoriMinus,
			buttonBoiledeggMinus, buttonChashuMinus, buttonBambooMinus, HelpButton;
	private JLabel labelNoriNum, labelBoiledeggNum, labelBambooNum, labelChashuNum, labelNori, labelBoiledegg,
			labelBamboo, labelChashu, labelNoriTotal, labelBoiledeggTotal, labelBambooTotal, labelChashuTotal;
	private int SellOut1 = 0;
	private int SellOut2 = 0;
	private int SellOut3 = 0;
	private int SellOut4 = 0;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOns frame = new AddOns();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddOns() {
		this(null, null);
	}

	/**
	 * Create the add-ones frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public AddOns(DataSource ds, PageController pc) {
		super(ds,pc);
		NoriaddP = ds.getConfig().getNoriaddPirce();
		eggP = ds.getConfig().getEggaddPirce();
		shootsP = ds.getConfig().getShootsaddPirce();
		ChashuaddP = ds.getConfig().getChashuaddPirce();
		
		this.setTitle("Self-service Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel panel = new JLabel("Add-ons");
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel.setBounds(170, 15, 130, 17);
		contentPane.add(panel);

		JButton buttonReturn = new JButton("Return");
		buttonReturn.setForeground(Color.WHITE);
		buttonReturn.setBackground(new Color(205, 133, 63));
		buttonReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.pastpage();
			}
		});
		buttonReturn.setBounds(213, 217, 85, 17);
		contentPane.add(buttonReturn);

		JButton buttonNextStep = new JButton("Next Step");
		buttonNextStep.setForeground(Color.WHITE);
		buttonNextStep.setBackground(new Color(205, 133, 63));
		buttonNextStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.nextpage();
			}
		});
		buttonNextStep.setBounds(310, 217, 90, 17);
		contentPane.add(buttonNextStep);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(52, 220, 104, 14);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setValue(currentProgress);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);

		new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentProgress++;
				if (currentProgress > 40) {
					currentProgress = 40;
				}
				progressBar.setValue(currentProgress);
			}
		}).start();

		JLabel imgHot = new JLabel();
		imgHot.setLocation(40, 50);
		Icon icon_1 = new ImageIcon("src/hot.png");
		imgHot.setIcon(icon_1);
		imgHot.setSize(35, 35);
		contentPane.add(imgHot);

		JLabel imgTop = new JLabel();
		imgTop.setLocation(40, 80);
		Icon icon_2 = new ImageIcon("src/Top.png");
		imgTop.setIcon(icon_2);
		imgTop.setSize(35, 35);
		contentPane.add(imgTop);

		labelNoriNum = new JLabel("0");
		labelNoriNum.setForeground(new Color(105, 105, 105));
		labelNoriNum.setFont(new Font("Times New Roman", Font.BOLD, 18));
		labelNoriNum.setBounds(325, 53, 44, 21);
		contentPane.add(labelNoriNum);

		labelBoiledeggNum = new JLabel("0");
		labelBoiledeggNum.setForeground(new Color(105, 105, 105));
		labelBoiledeggNum.setFont(new Font("Times New Roman", Font.BOLD, 18));
		labelBoiledeggNum.setBounds(325, 89, 44, 21);
		contentPane.add(labelBoiledeggNum);

		labelBambooNum = new JLabel("0");
		labelBambooNum.setForeground(new Color(105, 105, 105));
		labelBambooNum.setFont(new Font("Times New Roman", Font.BOLD, 18));
		labelBambooNum.setBounds(325, 125, 44, 21);
		contentPane.add(labelBambooNum);

		labelChashuNum = new JLabel("0");
		labelChashuNum.setForeground(new Color(105, 105, 105));
		labelChashuNum.setFont(new Font("Times New Roman", Font.BOLD, 18));
		labelChashuNum.setBounds(325, 161, 44, 21);
		contentPane.add(labelChashuNum);

		labelNori = new JLabel("Extra Nori(\u00A3" + NoriaddP + ")");
		labelNori.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelNori.setBounds(90, 55, 150, 21);
		contentPane.add(labelNori);

		labelBoiledegg = new JLabel("Extra boiled egg(\u00A3" + eggP + ")");
		labelBoiledegg.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelBoiledegg.setBounds(90, 90, 150, 21);
		contentPane.add(labelBoiledegg);

		labelBamboo = new JLabel("Bamboo shoots(\u00A3" + shootsP + ")");
		labelBamboo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelBamboo.setBounds(90, 125, 150, 21);
		contentPane.add(labelBamboo);

		labelChashu = new JLabel("Extra Chashu(\u00A3" + ChashuaddP + ")");
		labelChashu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelChashu.setBounds(90, 160, 150, 21);
		contentPane.add(labelChashu);

		buttonNoriPlus = new JButton("+");
		buttonNoriPlus.setForeground(new Color(205, 92, 92));
		buttonNoriPlus.setContentAreaFilled(false);
		buttonNoriPlus.setBorderPainted(false);
		buttonNoriPlus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		buttonNoriPlus.setBounds(330, 40, 50, 50);
		buttonNoriPlus.addActionListener(this);
		contentPane.add(buttonNoriPlus);

		buttonBoiledeggPlus = new JButton("+");
		buttonBoiledeggPlus.setForeground(new Color(210, 105, 30));
		buttonBoiledeggPlus.setContentAreaFilled(false);
		buttonBoiledeggPlus.setBorderPainted(false);
		buttonBoiledeggPlus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		buttonBoiledeggPlus.setBounds(330, 75, 50, 50);
		buttonBoiledeggPlus.addActionListener(this);
		contentPane.add(buttonBoiledeggPlus);

		buttonBambooPlus = new JButton("+");
		buttonBambooPlus.setForeground(new Color(205, 92, 92));
		buttonBambooPlus.setContentAreaFilled(false);
		buttonBambooPlus.setBorderPainted(false);
		buttonBambooPlus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		buttonBambooPlus.setBounds(330, 110, 50, 50);
		buttonBambooPlus.addActionListener(this);
		contentPane.add(buttonBambooPlus);

		buttonChashuPlus = new JButton("+");
		buttonChashuPlus.setForeground(new Color(210, 105, 30));
		buttonChashuPlus.setContentAreaFilled(false);
		buttonChashuPlus.setBorderPainted(false);
		buttonChashuPlus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		buttonChashuPlus.setBounds(330, 145, 50, 50);
		buttonChashuPlus.addActionListener(this);
		contentPane.add(buttonChashuPlus);

		buttonNoriMinus = new JButton("-");
		buttonNoriMinus.setForeground(new Color(210, 105, 30));
		buttonNoriMinus.setContentAreaFilled(false);
		buttonNoriMinus.setBorderPainted(false);
		buttonNoriMinus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		buttonNoriMinus.setBounds(280, 40, 50, 50);
		buttonNoriMinus.addActionListener(this);
		contentPane.add(buttonNoriMinus);

		buttonBoiledeggMinus = new JButton("-");
		buttonBoiledeggMinus.setForeground(new Color(205, 92, 92));
		buttonBoiledeggMinus.setContentAreaFilled(false);
		buttonBoiledeggMinus.setBorderPainted(false);
		buttonBoiledeggMinus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		buttonBoiledeggMinus.setBounds(280, 75, 50, 50);
		buttonBoiledeggMinus.addActionListener(this);
		contentPane.add(buttonBoiledeggMinus);

		buttonBambooMinus = new JButton("-");
		buttonBambooMinus.setForeground(new Color(210, 105, 30));
		buttonBambooMinus.setContentAreaFilled(false);
		buttonBambooMinus.setBorderPainted(false);
		buttonBambooMinus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		buttonBambooMinus.setBounds(280, 110, 50, 50);
		buttonBambooMinus.addActionListener(this);
		contentPane.add(buttonBambooMinus);

		buttonChashuMinus = new JButton("-");
		buttonChashuMinus.setForeground(new Color(205, 92, 92));
		buttonChashuMinus.setContentAreaFilled(false);
		buttonChashuMinus.setBorderPainted(false);
		buttonChashuMinus.setFont(new Font("Times New Roman", Font.BOLD, 25));
		buttonChashuMinus.setBounds(280, 145, 50, 50);
		buttonChashuMinus.addActionListener(this);
		contentPane.add(buttonChashuMinus);

		labelNoriTotal = new JLabel("\u00A30.0");
		labelNoriTotal.setForeground(new Color(220, 20, 60));
		labelNoriTotal.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelNoriTotal.setBounds(250, 55, 78, 21);
		contentPane.add(labelNoriTotal);

		labelBoiledeggTotal = new JLabel("\u00A30.0");
		labelBoiledeggTotal.setForeground(new Color(220, 20, 60));
		labelBoiledeggTotal.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelBoiledeggTotal.setBounds(250, 91, 78, 21);
		contentPane.add(labelBoiledeggTotal);

		labelBambooTotal = new JLabel("\u00A30.0");
		labelBambooTotal.setForeground(new Color(220, 20, 60));
		labelBambooTotal.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelBambooTotal.setBounds(250, 127, 78, 21);
		contentPane.add(labelBambooTotal);

		labelChashuTotal = new JLabel("\u00A30.0");
		labelChashuTotal.setForeground(new Color(220, 20, 60));
		labelChashuTotal.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelChashuTotal.setBounds(250, 163, 78, 21);
		contentPane.add(labelChashuTotal);
		
		HelpButton = new JButton("Help");
		HelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.changePage(13);
			}
		});
		HelpButton.setForeground(UIManager.getColor("JButton.black"));
		HelpButton.setBackground(new Color(255, 204, 153));
		HelpButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		HelpButton.setBounds(355, 8, 65, 23);
		contentPane.add(HelpButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 228, 196));
		panel_1.setBounds(20, 40, 390, 150);
		contentPane.add(panel_1);
	}

	/**
	 * Determine the type and number of products clicked by the user.
	 * 
	 * @param event Action Listener
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == buttonNoriPlus)
			increase("Nori", Noriadd);
		else if (source == buttonBoiledeggPlus)
			increase("boiled egg", egg);
		else if (source == buttonBambooPlus)
			increase("Bamboo shoots", shoots);
		else if (source == buttonChashuPlus)
			increase("Chashu", Chashuadd);
		else if (source == buttonNoriMinus)
			decrease("Nori", Noriadd);
		else if (source == buttonBoiledeggMinus)
			decrease("boiled egg", egg);
		else if (source == buttonBambooMinus)
			decrease("Bamboo shoots", shoots);
		else if (source == buttonChashuMinus)
			decrease("Chashu", Chashuadd);
	}

	/**
	 * The type and quantity selected by the user. 
	 * And determine whether the quantity {@code food} is within a reasonable range.
	 * 
	 * @param name food category
	 * @param food number of food
	 */
	public void increase(String name, int food) {
		if (food >= 3) {
			JOptionPane.showInternalMessageDialog(contentPane,
					"The food limit has been reached, \nyou cannot increase it anymore!", "notification",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (name.equals("Nori") && SellOut1 == 0) {
				Noriadd++;
				ds.getOrder().setNoriadd(Noriadd);
				labelNoriNum.setText(Noriadd + "");
				labelNoriTotal.setText("\u00A3" + Mathutil.round2(Noriadd * NoriaddP));
			} else if (name.equals("boiled egg") && SellOut2 == 0) {
				egg++;
				ds.getOrder().setEggadd(egg);
				labelBoiledeggNum.setText(egg + "");
				labelBoiledeggTotal.setText("\u00A3" + Mathutil.round2(egg * eggP));
			} else if (name.equals("Bamboo shoots") && SellOut3 == 0) {
				shoots++;
				ds.getOrder().setShootsadd(shoots);
				labelBambooNum.setText(shoots + "");
				labelBambooTotal.setText("\u00A3" + Mathutil.round2(shoots * shootsP));
			} else if (name.equals("Chashu") && SellOut4 == 0) {
				Chashuadd++;
				ds.getOrder().setChashuadd(Chashuadd);
				labelChashuNum.setText(Chashuadd + "");
				labelChashuTotal.setText("\u00A3" + Mathutil.round2(Chashuadd * ChashuaddP));
			} else {
				JOptionPane.showInternalMessageDialog(contentPane, "The food if sell out, \nso you cannot change it!",
						"notification", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * The type and quantity selected by the user. 
	 * And determine whether the quantity {@code food} is within a reasonable range.
	 * 
	 * @param name food category
	 * @param food number of food
	 */
	public void decrease(String name, int food) {
		if (food <= 0) {
			JOptionPane.showInternalMessageDialog(contentPane,
					"The food limit has been reached, \nyou cannot decrease it anymore!", "notification",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (name.equals("Nori") && SellOut1 == 0) {
				Noriadd--;
				ds.getOrder().setNoriadd(Noriadd);
				labelNoriNum.setText(Noriadd + "");
				labelNoriTotal.setText("\u00A3" + Mathutil.round2(Noriadd * NoriaddP));
			} else if (name.equals("boiled egg") && SellOut2 == 0) {
				egg--;
				ds.getOrder().setEggadd(egg);
				labelBoiledeggNum.setText(egg + "");
				labelBoiledeggTotal.setText("\u00A3" + Mathutil.round2(egg * eggP));
			} else if (name.equals("Bamboo shoots") && SellOut3 == 0) {
				shoots--;
				ds.getOrder().setShootsadd(shoots);
				labelBambooNum.setText(shoots + "");
				labelBambooTotal.setText("\u00A3" + Mathutil.round2(shoots * shootsP));
			} else if (name.equals("Chashu") && SellOut4 == 0) {
				Chashuadd--;
				ds.getOrder().setChashuadd(Chashuadd);
				labelChashuNum.setText(Chashuadd + "");
				labelChashuTotal.setText("\u00A3" + Mathutil.round2(Chashuadd * ChashuaddP));
			} else {
				JOptionPane.showInternalMessageDialog(contentPane, "The food if sell out, \nso you cannot change it!",
						"notification", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * Determine the type of product sold out.
	 * 
	 * @param num the category of sell out food
	 */
	public void sellOut(int num) {
		if (num == 1) {
			SellOut1 = 1;
			JLabel img = new JLabel();
			img.setLocation(40, 50);
			Icon icon = new ImageIcon("src/SellOut.png");
			img.setIcon(icon);
			img.setSize(35, 35);
			contentPane.add(img);
			labelNoriTotal.setForeground(new Color(255, 255, 255));
			buttonNoriPlus.setForeground(new Color(255, 255, 255));
			buttonNoriMinus.setForeground(new Color(255, 255, 255));
			labelNoriNum.setForeground(new Color(255, 255, 255));
			labelNori.setForeground(new Color(255, 255, 255));
			labelNoriNum.setText("0");
			labelNoriTotal.setText("\u00A30");
		} else if (num == 2) {
			SellOut2 = 1;
			JLabel img = new JLabel();
			img.setLocation(40, 85);
			Icon icon = new ImageIcon("src/SellOut.png");
			img.setIcon(icon);
			img.setSize(35, 35);
			contentPane.add(img);
			labelBoiledeggTotal.setForeground(new Color(255, 255, 255));
			buttonBoiledeggPlus.setForeground(new Color(255, 255, 255));
			buttonBoiledeggMinus.setForeground(new Color(255, 255, 255));
			labelBoiledeggNum.setForeground(new Color(255, 255, 255));
			labelBoiledegg.setForeground(new Color(255, 255, 255));
			labelBoiledeggNum.setText("0");
			labelBoiledeggTotal.setText("\u00A30");
		} else if (num == 3) {
			SellOut3 = 1;
			JLabel img = new JLabel();
			img.setLocation(40, 120);
			Icon icon = new ImageIcon("src/SellOut.png");
			img.setIcon(icon);
			img.setSize(35, 35);
			contentPane.add(img);
			labelBambooTotal.setForeground(new Color(255, 255, 255));
			buttonBambooPlus.setForeground(new Color(255, 255, 255));
			buttonBambooMinus.setForeground(new Color(255, 255, 255));
			labelBambooNum.setForeground(new Color(255, 255, 255));
			labelBamboo.setForeground(new Color(255, 255, 255));
			labelBambooNum.setText("0");
			labelBambooTotal.setText("\u00A30");
		} else if (num == 4) {
			SellOut4 = 1;
			JLabel img = new JLabel();
			img.setLocation(40, 155);
			Icon icon = new ImageIcon("src/SellOut.png");
			img.setIcon(icon);
			img.setSize(35, 35);
			contentPane.add(img);
			labelChashuTotal.setForeground(new Color(255, 255, 255));
			buttonChashuPlus.setForeground(new Color(255, 255, 255));
			buttonChashuMinus.setForeground(new Color(255, 255, 255));
			labelChashuNum.setForeground(new Color(255, 255, 255));
			labelChashu.setForeground(new Color(255, 255, 255));
			labelChashuNum.setText("0");
			labelChashuTotal.setText("\u00A30");
		}
	}

	/**
	 * Establish the relationship between commodity types and labels.
	 * And use the countdown timer.
	 */
	public void init() {
		Stock sck=ds.getStock();
		if(sck.getNoriadd()==0) {
			sellOut(1);
		}
		if(sck.getEggadd()==0) {
			sellOut(2);
		}
		if(sck.getShootsadd()==0) {
			sellOut(3);
		}
		if(sck.getChashuadd()==0) {
			sellOut(4);
		}
		setTimer(120);
	}
}
