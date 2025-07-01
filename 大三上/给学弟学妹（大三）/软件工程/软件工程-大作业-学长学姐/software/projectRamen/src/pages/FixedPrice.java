package pages;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import domain.DataSource;
import domain.Stock;
import service.OrderService;
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The {@code SpicyDialog} class wraps a value of the primitive type
 * {@code SpicyDialog} in an object. And this is the <code>spicy</code> dialog
 * of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. Prompt user attention
 * when spiciness is too high.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
class SpicyDialog extends JDialog {

	/** Default serial version UID bar {@value} */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the spicy promote dialog.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param page4 Fixed Price
	 */
	public SpicyDialog(FixedPrice page4) {
		super(page4, "Wait", true);

		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setBounds(200, 200, 350, 200);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width;
		int height = dimension.height;
		int x = (width - 350) / 2;
		int y = (height - 200) / 2;
		this.setLocation(x, y);

		JLabel label1 = new JLabel("Warning!! You have chosen the highest spiciness!");
		label1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		label1.setForeground(new Color(139, 69, 19));
		label1.setBounds(20, 20, 300, 20);
		contentPane.add(label1);

		for (int i = 0; i < 5; i++) {
			JLabel img = new JLabel();
			img.setLocation(60 + 45 * i, 60);
			Icon icon = new ImageIcon("src/Chili.png");
			img.setIcon(icon);
			img.setSize(35, 35);
			contentPane.add(img);
		}

		JButton buttonConfirm = new JButton("Confirm");
		buttonConfirm.setForeground(Color.WHITE);
		buttonConfirm.setBackground(new Color(205, 133, 63));
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonConfirm.setBounds(140, 120, 80, 17);
		contentPane.add(buttonConfirm);
	}
}

/**
 * The {@code FixedPrice} class wraps a value of the primitive type
 * {@code FixedPrice} in an object. And this is the <code>fixed price</code>
 * page of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. Users can choose the
 * suitable type of ramen and some free food.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class FixedPrice extends PageBase implements ActionListener {

	private OrderService os;
	private JLabel label;
	
	/** Default progress of progress bar {@value} */
	private static int currentProgress = 0;
	private JPanel contentPane;
	private JButton buttonTonkkotu, buttonShoyu, buttonShiyo, buttonSoft, buttonMedium, buttonFirm, buttonNoplease,
			buttonJustalittle, buttonAlot, HelpButton;
	private JSlider spicySlider;
	private JComboBox noriBox, chashuBox, eggBox;
	private String Tonkkotu = "", Shoyu = "", Shiyo = "", Soft = "", Medium = "", Firm = "", Noplease = "",
			Justalittle = "", Alot = "";

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FixedPrice frame = new FixedPrice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FixedPrice() {
		this(null, null);
	}

	/**
	 * Create the fixed price frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public FixedPrice(DataSource ds, PageController pc) {
		super(ds, pc);
		this.os = new OrderService(ds, DataSource.orderfilename);
		this.setTitle("Self-service Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel panel = new JLabel("Raman Price:");
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.setBounds(12, 11, 130, 17);
		contentPane.add(panel);

		label = new JLabel("£9.9");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		label.setForeground(new Color(220, 20, 60));
		label.setBounds(110, 8, 59, 21);
		contentPane.add(label);

		JButton button = new JButton("Return");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(205, 133, 63));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(null, "Would you want to give up the order?",
						"Wait for a moment", JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					pc.nowstop();
					pc.nostop = false;
				}
			}
		});
		button.setBounds(213, 217, 85, 17);
		contentPane.add(button);

		JButton button_1 = new JButton("Next Step");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(205, 133, 63));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (os.checkValidate() && os.checkAvalible()) {
					pc.nextpage();
				} else {
					JOptionPane.showInternalMessageDialog(contentPane,
							"You should complete all options before the next step!", "Wait for a moment",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		button_1.setBounds(310, 217, 90, 17);
		contentPane.add(button_1);

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
				if (currentProgress > 20) {
					currentProgress = 20;
				}
				progressBar.setValue(currentProgress);
			}
		}).start();

		buttonTonkkotu = new JButton("Tonkotsu");
		buttonTonkkotu.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonTonkkotu.setBackground(new Color(255, 228, 225));
		buttonTonkkotu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonTonkkotu.setBounds(116, 44, 91, 29);
		contentPane.add(buttonTonkkotu);

		buttonShoyu = new JButton("Shoyu");
		buttonShoyu.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonShoyu.setBackground(new Color(255, 228, 225));
		buttonShoyu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonShoyu.setBounds(204, 44, 96, 29);
		contentPane.add(buttonShoyu);

		buttonShiyo = new JButton("Shio");
		buttonShiyo.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonShiyo.setBackground(new Color(255, 228, 225));
		buttonShiyo.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonShiyo.setBounds(299, 44, 91, 29);
		contentPane.add(buttonShiyo);

		buttonTonkkotu.addActionListener(this);
		buttonShoyu.addActionListener(this);
		buttonShiyo.addActionListener(this);

		buttonSoft = new JButton("Soft");
		buttonSoft.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonSoft.setBackground(new Color(245, 222, 179));
		buttonSoft.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonSoft.setBounds(116, 72, 91, 29);
		contentPane.add(buttonSoft);

		buttonMedium = new JButton("Medium");
		buttonMedium.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonMedium.setBackground(new Color(245, 222, 179));
		buttonMedium.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonMedium.setBounds(204, 72, 96, 29);
		contentPane.add(buttonMedium);

		buttonFirm = new JButton("Firm");
		buttonFirm.setBackground(new Color(245, 222, 179));
		buttonFirm.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonFirm.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonFirm.setBounds(299, 72, 91, 29);
		contentPane.add(buttonFirm);

		buttonSoft.addActionListener(this);
		buttonMedium.addActionListener(this);
		buttonFirm.addActionListener(this);

		buttonNoplease = new JButton("No please");
		buttonNoplease.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonNoplease.setBackground(new Color(255, 228, 225));
		buttonNoplease.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonNoplease.setBounds(116, 100, 91, 29);
		contentPane.add(buttonNoplease);

		buttonJustalittle = new JButton("Just a little");
		buttonJustalittle.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonJustalittle.setBackground(new Color(255, 228, 225));
		buttonJustalittle.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonJustalittle.setBounds(204, 100, 96, 29);
		contentPane.add(buttonJustalittle);

		buttonAlot = new JButton("A lot");
		buttonAlot.setBackground(new Color(255, 228, 225));
		buttonAlot.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonAlot.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		buttonAlot.setBounds(299, 100, 91, 29);
		contentPane.add(buttonAlot);

		buttonNoplease.addActionListener(this);
		buttonJustalittle.addActionListener(this);
		buttonAlot.addActionListener(this);
		noriBox = new JComboBox();

		noriBox.setBackground(new Color(245, 222, 179));
		noriBox.setBounds(116, 129, 50, 27);
		noriBox.addItem("No");
		noriBox.addItem("Yes");
		contentPane.add(noriBox);

		noriBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (noriBox.getSelectedItem().toString().equals("No")) {
					ds.getOrder().setNori(0);
				} else {
					ds.getOrder().setNori(1);
				}
			}
		});

		chashuBox = new JComboBox();
		chashuBox.setBackground(new Color(245, 222, 179));
		chashuBox.setBounds(217, 129, 50, 27);
		chashuBox.addItem("No");
		chashuBox.addItem("Yes");
		contentPane.add(chashuBox);

		chashuBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chashuBox.getSelectedItem().toString().equals("No")) {
					ds.getOrder().setChashu(0);
				} else {
					ds.getOrder().setChashu(1);
				}
			}
		});

		eggBox = new JComboBox();
		eggBox.setBackground(new Color(245, 222, 179));
		eggBox.setBounds(340, 129, 50, 27);
		eggBox.addItem("No");
		eggBox.addItem("Yes");
		contentPane.add(eggBox);

		eggBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (eggBox.getSelectedItem().toString().equals("No")) {
					ds.getOrder().setBoiledegg(0);
				} else {
					ds.getOrder().setBoiledegg(1);
				}
			}
		});

		spicySlider = new JSlider();
		spicySlider.setBackground(new Color(255, 228, 225));
		spicySlider.setBounds(116, 160, 184, 45);
		spicySlider.setValue(0);
		spicySlider.setMaximum(5);
		spicySlider.setMinimum(0);
		spicySlider.setMajorTickSpacing(1);
		spicySlider.setPaintTicks(true);
		spicySlider.setPaintLabels(true);
		contentPane.add(spicySlider);

		spicySlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				spicyChange();
			}
		});

		JLabel labelSoup = new JLabel("Soup");
		labelSoup.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelSoup.setBounds(81, 47, 59, 17);
		contentPane.add(labelSoup);

		JLabel labelNodles = new JLabel("Noodles");
		labelNodles.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelNodles.setBounds(62, 73, 65, 21);
		contentPane.add(labelNodles);

		JLabel labelSpringOnion = new JLabel("Spring onion");
		labelSpringOnion.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelSpringOnion.setBounds(36, 101, 104, 21);
		contentPane.add(labelSpringOnion);

		JLabel labelNori = new JLabel("Nori");
		labelNori.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelNori.setBounds(85, 132, 81, 21);
		contentPane.add(labelNori);

		JLabel labelChashu = new JLabel("Chashu");
		labelChashu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelChashu.setBounds(170, 132, 59, 21);
		contentPane.add(labelChashu);

		JLabel labelBoiledEgg = new JLabel("Boiled egg");
		labelBoiledEgg.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelBoiledEgg.setBounds(275, 132, 65, 21);
		contentPane.add(labelBoiledEgg);

		JLabel labelSpiciness = new JLabel("Spiciness");
		labelSpiciness.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelSpiciness.setForeground(new Color(220, 20, 60));
		labelSpiciness.setBounds(58, 172, 60, 21);
		contentPane.add(labelSpiciness);

		HelpButton = new JButton("Help");
		HelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.changePage(12);
			}
		});
		HelpButton.setForeground(UIManager.getColor("JButton.black"));
		HelpButton.setBackground(new Color(255, 204, 153));
		HelpButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		HelpButton.setBounds(355, 8, 65, 23);
		contentPane.add(HelpButton);

	}

	/**
	 * Prompt user attention when spiciness is too high.
	 */
	private void spicyChange() {
		int spiciness = spicySlider.getValue();
		ds.getOrder().setSpiciness(spiciness);
		if (spiciness == 5) {
			new SpicyDialog(this).setVisible(true);
		}
	}

	/**
	 * <p>
	 * Including many choices for ramen.
	 * 
	 * @param event JButton listener
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == buttonTonkkotu) {
			active1(buttonTonkkotu);
			inactive1(buttonShoyu);
			inactive1(buttonShiyo);
			ds.getOrder().setSoup(0);
			if (Shoyu.equals("null")) {
				setEnable(buttonShoyu);
			}
			if (Shiyo.equals("null")) {
				setEnable(buttonShiyo);
			}
		} else if (source == buttonShoyu) {
			active1(buttonShoyu);
			inactive1(buttonTonkkotu);
			inactive1(buttonShiyo);
			ds.getOrder().setSoup(1);
			if (Tonkkotu.equals("null")) {
				setEnable(buttonTonkkotu);
			}
			if (Shiyo.equals("null")) {
				setEnable(buttonShiyo);
			}
		} else if (source == buttonShiyo) {
			active1(buttonShiyo);
			inactive1(buttonTonkkotu);
			inactive1(buttonShoyu);
			ds.getOrder().setSoup(2);
			if (Tonkkotu.equals("null")) {
				setEnable(buttonTonkkotu);
			}
			if (Shoyu.equals("null")) {
				setEnable(buttonShoyu);
			}
		} else if (source == buttonSoft) {
			active2(buttonSoft);
			inactive2(buttonMedium);
			inactive2(buttonFirm);
			ds.getOrder().setNoodles(0);
			if (Medium.equals("null")) {
				setEnable(buttonMedium);
			}
			if (Firm.equals("null")) {
				setEnable(buttonFirm);
			}
			
		} else if (source == buttonMedium) {
			active2(buttonMedium);
			inactive2(buttonSoft);
			inactive2(buttonFirm);
			ds.getOrder().setNoodles(1);
			if (Soft.equals("null")) {
				setEnable(buttonSoft);
			}
			if (Firm.equals("null")) {
				setEnable(buttonFirm);
			}
		} else if (source == buttonFirm) {
			active2(buttonFirm);
			inactive2(buttonSoft);
			inactive2(buttonMedium);
			ds.getOrder().setNoodles(2);
			if (Soft.equals("null")) {
				setEnable(buttonSoft);
			}
			if (Medium.equals("null")) {
				setEnable(buttonMedium);
			}
		} else if (source == buttonNoplease) {
			active1(buttonNoplease);
			inactive1(buttonJustalittle);
			inactive1(buttonAlot);
			ds.getOrder().setSpringonion(0);
			if (Justalittle.equals("null")) {
				setEnable(buttonJustalittle);
			}
			if (Alot.equals("null")) {
				inactive1(buttonJustalittle);
			}
		} else if (source == buttonJustalittle) {
			active1(buttonJustalittle);
			inactive1(buttonNoplease);
			inactive1(buttonAlot);
			ds.getOrder().setSpringonion(1);
			if (Noplease.equals("null")) {
				setEnable(buttonNoplease);
			}
			if (Alot.equals("null")) {
				setEnable(buttonAlot);
			}
		} else if (source == buttonAlot) {
			active1(buttonAlot);
			inactive1(buttonNoplease);
			inactive1(buttonJustalittle);
			ds.getOrder().setSpringonion(2);
			if (Noplease.equals("null")) {
				setEnable(buttonNoplease);
			}
			if (Justalittle.equals("null")) {
				setEnable(buttonJustalittle);
			}
		}
	}

	/**
	 * JButton selection state setting.
	 * 
	 * @param button
	 */
	private void active1(JButton button) {
		button.setForeground(UIManager.getColor("JButton.black"));
		button.setBackground(new Color(205, 92, 92));
		button.setFont(new Font("Times New Roman", Font.BOLD, 13));
	}

	/**
	 * JButton selection state setting.
	 * 
	 * @param button
	 */
	private void active2(JButton button) {
		button.setForeground(UIManager.getColor("JButton.black"));
		button.setBackground(new Color(210, 105, 30));
		button.setFont(new Font("Times New Roman", Font.BOLD, 13));
	}

	/**
	 * JButton unchecked state setting.
	 * 
	 * @param button
	 */
	private void inactive1(JButton button) {
		button.setForeground(UIManager.getColor("JButton.darkShadow"));
		button.setBackground(new Color(255, 228, 225));
		button.setFont(new Font("Times New Roman", Font.PLAIN, 13));
	}

	/**
	 * JButton unchecked state setting.
	 * 
	 * @param button
	 */
	private void inactive2(JButton button) {
		button.setForeground(UIManager.getColor("JButton.darkShadow"));
		button.setBackground(new Color(245, 222, 179));
		button.setFont(new Font("Times New Roman", Font.PLAIN, 13));
	}

	/**
	 * JButton checked state setting.
	 * 
	 * @param button
	 */
	private void setEnable(JButton button) {
		button.setEnabled(false);
		button.setText("Unavailable");
		button.setFont(new Font("Times New Roman", Font.BOLD, 10));
		button.setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * JButton unchecked state setting.
	 * 
	 * @param button
	 */
	private void setEnableComboBox(JComboBox comboBox2) {
		comboBox2.setEnabled(false);
		comboBox2.setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * Restore all the initial settings.
	 */
	public void clearAll() {
		inactive1(buttonShoyu);
		inactive1(buttonShiyo);
		inactive1(buttonTonkkotu);
		inactive2(buttonSoft);
		inactive2(buttonMedium);
		inactive2(buttonFirm);
		inactive1(buttonNoplease);
		inactive1(buttonJustalittle);
		inactive1(buttonAlot);
		spicySlider.setValue(0);
		this.noriBox.setSelectedIndex(0);
		this.chashuBox.setSelectedIndex(0);
		this.eggBox.setSelectedIndex(0);
	}

	/**
	 * Set countdown timer. And set the button state.
	 */
	public void init() {
		Stock sck = ds.getStock();
		if ((sck.getSoup())[0] == 0) {
			setEnable(buttonTonkkotu);
			this.Tonkkotu = "null";
		}
		if ((sck.getSoup())[1] == 0) {
			setEnable(buttonShoyu);
			this.Shoyu = "null";
		}
		if ((sck.getSoup())[2] == 0) {
			setEnable(buttonShiyo);
			this.Shiyo = "null";
		}
		if ((sck.getNoodles())[0] == 0) {
			setEnable(buttonSoft);
			this.Soft = "null";
		}
		if ((sck.getNoodles())[1] == 0) {
			setEnable(buttonMedium);
			this.Medium = "null";
		}
		if ((sck.getNoodles())[2] == 0) {
			setEnable(buttonFirm);
			this.Firm = "null";
		}
		if ((sck.getSpringonion())[0] == 0) {
			setEnable(buttonNoplease);
			this.Noplease = "null";
		}
		if ((sck.getSpringonion())[1] == 0) {
			setEnable(buttonJustalittle);
			this.Justalittle = "null";
		}
		if ((sck.getSpringonion())[2] == 0) {
			setEnable(buttonAlot);
			this.Alot = "null";
		}
		if (sck.getNori() == 0) {
			setEnableComboBox(noriBox);
		}
		if (sck.getChashu() == 0) {
			setEnableComboBox(chashuBox);
		}
		if (sck.getBoiledegg() == 0) {
			setEnableComboBox(eggBox);
		}
		label.setText("\u00A3"+ds.getConfig().getNoodlePirce());
		setTimer(120);
	}
}
