package pages;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import domain.DataSource;

/**
 * The {@code Choice} class wraps a value of the primitive type {@code Choice}
 * in an object. And this is the <code>choice</code> page of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. Determine whether the
 * user chooses to log in, register or pay directly.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class Choice extends PageBase implements ActionListener {

	private JPanel contentPane;
	private JButton buttonRegister, buttonLogin, buttonPayDirectly, HelpButton;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Choice frame = new Choice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Choice() {
		this(null, null);
	}

	/**
	 * Create the choice frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public Choice(DataSource ds, PageController pc) {
		super(ds, pc);
		this.setTitle("Membership");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel panel = new JLabel("Do you have a membership number?");
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.setBounds(15, 15, 300, 17);
		contentPane.add(panel);

		buttonRegister = new JButton("Sign up");
		buttonRegister.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonRegister.setBackground(new Color(255, 228, 225));
		buttonRegister.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonRegister.setBounds(158, 113, 130, 30);
		buttonRegister.addActionListener(this);
		contentPane.add(buttonRegister);

		buttonLogin = new JButton("Sign In");
		buttonLogin.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonLogin.setBackground(new Color(255, 228, 225));
		buttonLogin.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonLogin.setBounds(158, 60, 130, 30);
		buttonLogin.addActionListener(this);
		contentPane.add(buttonLogin);

		buttonPayDirectly = new JButton("Pay Directly");
		buttonPayDirectly.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonPayDirectly.setBackground(new Color(255, 228, 225));
		buttonPayDirectly.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonPayDirectly.setBounds(158, 165, 130, 30);
		buttonPayDirectly.addActionListener(this);
		contentPane.add(buttonPayDirectly);

		JLabel imgLogin = new JLabel();
		imgLogin.setLocation(93, 60);
		Icon icon = new ImageIcon("src/Login.png");
		imgLogin.setIcon(icon);
		imgLogin.setSize(35, 35);
		contentPane.add(imgLogin);

		JLabel imgRegister = new JLabel();
		imgRegister.setLocation(93, 110);
		Icon icon1 = new ImageIcon("src/Register.png");
		imgRegister.setIcon(icon1);
		imgRegister.setSize(35, 35);
		contentPane.add(imgRegister);

		JLabel imgPayment = new JLabel();
		imgPayment.setLocation(93, 160);
		Icon icon2 = new ImageIcon("src/Payment.png");
		imgPayment.setIcon(icon2);
		imgPayment.setSize(35, 35);
		contentPane.add(imgPayment);

		JButton buttonReturn = new JButton("Return");
		buttonReturn.setForeground(Color.WHITE);
		buttonReturn.setBackground(new Color(205, 133, 63));
		buttonReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.changePage(5);
			}
		});
		buttonReturn.setBounds(342, 231, 80, 17);
		contentPane.add(buttonReturn);

		JLabel imgLoading = new JLabel();
		imgLoading.setLocation(275, 0);
		Icon icon3 = new ImageIcon("src/Loading2.gif");
		imgLoading.setIcon(icon3);
		imgLoading.setSize(455, 89);
		contentPane.add(imgLoading);

		HelpButton = new JButton("Help");
		HelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.changePage(14);
			}
		});
		HelpButton.setForeground(UIManager.getColor("JButton.black"));
		HelpButton.setBackground(new Color(255, 204, 153));
		HelpButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		HelpButton.setBounds(355, 8, 65, 23);
		contentPane.add(HelpButton);
	}

	/**
	 * Determine whether the user chooses to log in, register or pay directly.
	 * 
	 * @param event Action Listener
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == buttonRegister) {
			pc.changePage(8);
		} else if (source == buttonLogin) {
			pc.changePage(7);
		} else if (source == buttonPayDirectly) {
			pc.changePage(6);
		}
	}
	
	public void init() {
		setTimer(120);
	}
}
