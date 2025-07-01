package pages;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import domain.Config;
import domain.DataSource;
import domain.Order;
import domain.Stock;

import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 * The {@code RecDialog} class wraps a value of the primitive type
 * {@code RecDialog} in an object. And this is the <code>notify</code> dialog of
 * the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. Prompt user attention
 * when no add-ones is choose.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
class RecDialog extends JDialog implements ActionListener {

	/** Default serial version UID bar {@value} */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel labelNoriNum;
	private JLabel labelNori;
	private JLabel labelNoriTotal;
	private JLabel labelNoriTotalPrice;
	private JButton buttonNoriPlus, buttonNoriMinus;
	private int nowAddon = 0;
	private Display pg4;
	private String foodName;

	/**
	 * Create the add-ones promote dialog.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param page4 Display
	 */
	public RecDialog(Display page4) {
		super(page4, "Wait", true);
		pg4 = page4;
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

		double NoriaddP = pg4.ds.getConfig().getNoriaddPirce();
		double eggP = pg4.ds.getConfig().getEggaddPirce();
		double shootsP = pg4.ds.getConfig().getShootsaddPirce();
		double ChashuaddP = pg4.ds.getConfig().getChashuaddPirce();
		JLabel label1 = new JLabel("Do you really not need a bit of add-ons?");
		label1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		label1.setForeground(new Color(139, 69, 19));
		label1.setBounds(20, 20, 300, 20);
		contentPane.add(label1);

		JLabel img = new JLabel();
		img.setLocation(20, 50);
		Icon icon = new ImageIcon("src/Recommend.png");
		img.setIcon(icon);
		img.setSize(35, 35);
		contentPane.add(img);

		labelNoriNum = new JLabel("0");
		labelNoriNum.setForeground(new Color(105, 105, 105));
		labelNoriNum.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelNoriNum.setBounds(200, 60, 50, 20);
		contentPane.add(labelNoriNum);

		String labelText = "";
		if (pg4.ds.getConfig().getRec() == 1) {
			foodName = "Nori";
			labelText = "Extra Nori (\u00A3" + NoriaddP + ")";
		}

		if (pg4.ds.getConfig().getRec() == 2) {
			foodName = "boiled egg";
			labelText = "Extra boiled egg (\u00A3" + eggP + ")";
		}

		if (pg4.ds.getConfig().getRec() == 3) {
			foodName = "Bamboo shoots";
			labelText = "Bamboo shoots (\u00A3" + shootsP + ")";
		}

		if (pg4.ds.getConfig().getRec() == 4) {
			foodName = "Chashu";
			labelText = "Extra Chashu (\u00A3" + ChashuaddP + ")";
		}

		labelNori = new JLabel(labelText);
		labelNori.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelNori.setBounds(60, 60, 120, 20);
		contentPane.add(labelNori);

		buttonNoriPlus = new JButton("+");
		buttonNoriPlus.setForeground(Color.black);
		buttonNoriPlus.setContentAreaFilled(false);
		buttonNoriPlus.setBorderPainted(false);
		buttonNoriPlus.setFont(new Font("Times New Roman", Font.BOLD, 15));
		buttonNoriPlus.setBounds(200, 45, 50, 50);
		buttonNoriPlus.addActionListener(this);
		contentPane.add(buttonNoriPlus);

		buttonNoriMinus = new JButton("-");
		buttonNoriMinus.setForeground(Color.black);
		buttonNoriMinus.setContentAreaFilled(false);
		buttonNoriMinus.setBorderPainted(false);
		buttonNoriMinus.setFont(new Font("Times New Roman", Font.BOLD, 15));
		buttonNoriMinus.setBounds(160, 45, 50, 50);
		buttonNoriMinus.addActionListener(this);
		contentPane.add(buttonNoriMinus);

		labelNoriTotalPrice = new JLabel("Total Price: ");
		labelNoriTotalPrice.setForeground(new Color(220, 20, 60));
		labelNoriTotalPrice.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelNoriTotalPrice.setBounds(240, 60, 100, 20);
		contentPane.add(labelNoriTotalPrice);

		labelNoriTotal = new JLabel("\u00A30");
		labelNoriTotal.setForeground(new Color(220, 20, 60));
		labelNoriTotal.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelNoriTotal.setBounds(310, 60, 20, 20);
		contentPane.add(labelNoriTotal);

		JButton buttonConfirm = new JButton("Confirm");
		buttonConfirm.setForeground(Color.WHITE);
		buttonConfirm.setBackground(new Color(205, 133, 63));
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page4.num = 1;
				if (labelNoriNum.getText().contentEquals("0")) {
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Thank you for your choice", "Thanks",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
					page4.init();
				}
			}
		});
		buttonConfirm.setBounds(220, 120, 80, 17);
		contentPane.add(buttonConfirm);
	}

	/**
	 * Judgment plus and minus button.
	 * 
	 * @param event ActionListener
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == buttonNoriPlus)
			increase(foodName, nowAddon);
		else
			decrease(foodName, nowAddon);
	}

	/**
	 * The type and quantity selected by the user. And determine whether the
	 * quantity {@code food} is within a reasonable range.
	 * 
	 * @param name food category
	 * @param food number of food
	 */
	private void increase(String name, int food) {
		if (food >= 3) {
			JOptionPane.showInternalMessageDialog(contentPane,
					"The food limit has been reached, \nyou cannot increase it anymore!", "notification",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			nowAddon++;
			if (foodName.equals("Nori")) {
				pg4.getDs().getOrder().setNoriadd(nowAddon);
			}
			if (foodName.equals("boiled egg")) {
				pg4.getDs().getOrder().setEggadd(nowAddon);
			}
			if (foodName.equals("Bamboo shoots")) {
				pg4.getDs().getOrder().setShootsadd(nowAddon);
			}
			if (foodName.equals("Chashu")) {
				pg4.getDs().getOrder().setChashuadd(nowAddon);
			}
			labelNoriNum.setText(nowAddon + "");
			labelNoriTotal.setText("\u00A3" + nowAddon);
		}
	}

	/**
	 * The type and quantity selected by the user. And determine whether the
	 * quantity {@code food} is within a reasonable range.
	 * 
	 * @param name food category
	 * @param food number of food
	 */
	private void decrease(String name, int food) {
		if (food <= 0) {
			JOptionPane.showInternalMessageDialog(contentPane,
					"The food limit has been reached, \nyou cannot decrease it anymore!", "notification",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			nowAddon--;
			if (foodName.equals("Nori")) {
				pg4.getDs().getOrder().setNoriadd(nowAddon);
			}
			if (foodName.equals("boiled egg")) {
				pg4.getDs().getOrder().setEggadd(nowAddon);
			}
			if (foodName.equals("Bamboo shoots")) {
				pg4.getDs().getOrder().setShootsadd(nowAddon);
			}
			if (foodName.equals("Chashu")) {
				pg4.getDs().getOrder().setChashuadd(nowAddon);
			}
			labelNoriNum.setText(nowAddon + "");
			labelNoriTotal.setText("\u00A3" + nowAddon);
		}
	}
}

/**
 * The {@code Display} class wraps a value of the primitive type {@code Display}
 * in an object. And this is the <code>display</code> page of the user
 * interface.
 * 
 * <p>
 * The entire page includes the background and buttons. Show user purchase
 * options.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class Display extends PageBase {

	/** Default serial version UID bar {@value} */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int currentProgress = 40;
	private JLabel labelSoup, labelNoodles, labelSpringonion, labelNori, labelChashu, labelBoiledegg;
	private JLabel labelSpiciness, labelEgg, labelShoots, labelChashuadd;
	private JLabel labelNoriadd;
	int num;

	/**
	 * Create the display frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public Display(DataSource ds, PageController pc) {
		super(ds, pc);
		this.num = 0;
		this.setTitle("Order display");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		JLabel panel = new JLabel("Your chooses:");
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel.setBounds(10, 10, 200, 17);
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
				if (num == 0 && labelNoriadd.getText().equals("More Nori: 0")
						&& labelEgg.getText().equals("More eggs: 0") && labelShoots.getText().equals("More shoots: 0")
						&& labelChashuadd.getText().equals("More Chashu: 0")&&checkStock()) {
					new RecDialog(Display.this).setVisible(true);
					num = 1;
				} else {
					int choose = JOptionPane.showConfirmDialog(null, "Do you want to submit the order?",
							"Wait for a moment", JOptionPane.YES_NO_OPTION);
					if (choose == 0) {
						pc.nextpage();
					}
				}
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
				if (currentProgress > 60) {
					currentProgress = 60;
				}
				progressBar.setValue(currentProgress);
			}
		}).start();

		labelSoup = new JLabel("Soup: ");
		labelSoup.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelSoup.setBounds(56, 30, 160, 30);
		contentPane.add(labelSoup);

		labelNoodles = new JLabel("Noodles: ");
		labelNoodles.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelNoodles.setBounds(56, 60, 160, 30);
		contentPane.add(labelNoodles);

		labelSpringonion = new JLabel("Springonion: ");
		labelSpringonion.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelSpringonion.setBounds(56, 90, 193, 30);
		contentPane.add(labelSpringonion);

		labelNori = new JLabel("Nori: ");
		labelNori.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelNori.setBounds(56, 120, 160, 30);
		contentPane.add(labelNori);

		labelChashu = new JLabel("Chashu: ");
		labelChashu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelChashu.setBounds(56, 150, 160, 30);
		contentPane.add(labelChashu);

		labelBoiledegg = new JLabel("Boiledegg: ");
		labelBoiledegg.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelBoiledegg.setBounds(56, 180, 160, 30);
		contentPane.add(labelBoiledegg);

		labelSpiciness = new JLabel("Spiciness:");
		labelSpiciness.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelSpiciness.setForeground(new Color(220, 20, 60));
		labelSpiciness.setBounds(240, 181, 100, 30);
		contentPane.add(labelSpiciness);

		labelNoriadd = new JLabel("Noriadd:");
		labelNoriadd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelNoriadd.setBounds(240, 61, 160, 30);
		contentPane.add(labelNoriadd);

		labelEgg = new JLabel("egg:");
		labelEgg.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelEgg.setBounds(240, 91, 150, 30);
		contentPane.add(labelEgg);

		labelShoots = new JLabel("shoots:");
		labelShoots.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelShoots.setBounds(240, 121, 150, 30);
		contentPane.add(labelShoots);

		labelChashuadd = new JLabel("Chashuadd:");
		labelChashuadd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelChashuadd.setBounds(240, 151, 150, 30);
		contentPane.add(labelChashuadd);

	}

	/**
	 * Set countdown timer. And set the button state.
	 */
	public void init() {
		Order nowOd = ds.getOrder();
		Config cfg = ds.getConfig();
		labelSoup.setText("Soup: " + cfg.getName("Soup", nowOd.getSoup()));
		labelNoodles.setText("Noodles: " + cfg.getName("Noodles", nowOd.getNoodles()));
		labelSpringonion.setText("Spring onion: " + cfg.getName("Spring onion", nowOd.getSpringonion()));
		labelNori.setText("Nori: " + cfg.changeBoolName(nowOd.getNori()));
		labelChashu.setText("Chashu: " + cfg.changeBoolName(nowOd.getChashu()));
		labelBoiledegg.setText("Boiledegg: " + cfg.changeBoolName(nowOd.getBoiledegg()));
		labelSpiciness.setText("Spiciness: " + nowOd.getSpiciness());
		labelNoriadd.setText("More Nori: " + nowOd.getNoriadd());
		labelEgg.setText("More eggs: " + nowOd.getEggadd());
		labelShoots.setText("More shoots: " + nowOd.getShootsadd());
		labelChashuadd.setText("More Chashu: " + nowOd.getChashuadd());
		setTimer(120);
	}
	
	private boolean checkStock() {
		Stock sck=ds.getStock();
		if(sck.getNoriadd()==0&&sck.getChashuadd()==0&&
				sck.getShootsadd()==0&&sck.getEggadd()==0) {
			return false;
		}
		return true;
	}
}
