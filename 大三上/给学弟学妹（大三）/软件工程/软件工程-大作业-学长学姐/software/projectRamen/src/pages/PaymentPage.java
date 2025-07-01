package pages;


import javax.swing.JButton;
import java.awt.Color;
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

import domain.DataSource;
import service.MemberService;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
/**
 * The {@code PaymentPage} class wraps a value of the primitive type {@code PaymentPage} in an object. 
 * And this is the <code>Payment</code> page of the user interface.
 * 
 * <p> The entire page includes the background and buttons. 
 * This page can provide users with two payment method options.
 * There are two buttons for Card and Cash.
 * 
 * @author Shuming Fan
 * @version 5.0
 * @since JDK 11
 */
public class PaymentPage extends PageBase {

	private JPanel contentPane;
	private String payment = "";
	private static int currentProgress = 80;
	private JLabel lblWelcome;
	private MemberService ms;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentPage frame = new PaymentPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	PaymentPage() {
		this(null, null);
	}

	/**
	 * Create the frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public PaymentPage(DataSource ds, PageController pc) {
		super(ds, pc);
		ms = new MemberService(DataSource.memberfilename, ds);
		this.setTitle("Self-service Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("Next Step");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(205, 133, 63));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (payment.equals("")) {
					JOptionPane.showInternalMessageDialog(contentPane,
							"You should choose a option before the next step!", "Wait for a moment",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					ds.getPayment().setMethod(payment);
					if (ds.getMember() != null) {
						pc.changePage(10);
					} else {
						pc.changePage(11);
					}
				}
			}
		});
		button.setBounds(310, 217, 90, 17);
		contentPane.add(button);

		JButton buttonReturn = new JButton("Return");
		buttonReturn.setForeground(Color.WHITE);
		buttonReturn.setBackground(new Color(205, 133, 63));
		buttonReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.changePage(15);
			}
		});
		buttonReturn.setBounds(213, 217, 85, 17);
		contentPane.add(buttonReturn);

		JLabel panel = new JLabel("Please choose the method of payment");
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.setBounds(10, 10, 302, 17);
		contentPane.add(panel);

		JButton btnCard = new JButton("Card");
		btnCard.setForeground(SystemColor.controlDkShadow);
		btnCard.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCard.setBackground(new Color(255, 228, 225));
		btnCard.setBounds(130, 130, 130, 30);
		contentPane.add(btnCard);

		JButton btnCash = new JButton("Cash");
		btnCash.setForeground(SystemColor.controlDkShadow);
		btnCash.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCash.setBackground(new Color(255, 228, 225));
		btnCash.setBounds(130, 80, 130, 30);
		contentPane.add(btnCash);

		btnCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCard.setForeground(UIManager.getColor("JButton.black"));
				btnCard.setBackground(new Color(205, 92, 92));
				btnCard.setFont(new Font("Times New Roman", Font.BOLD, 14));
				btnCash.setForeground(UIManager.getColor("JButton.darkShadow"));
				btnCash.setBackground(new Color(255, 228, 225));
				btnCash.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				payment = "Card";
			}
		});
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCash.setForeground(UIManager.getColor("JButton.black"));
				btnCash.setBackground(new Color(205, 92, 92));
				btnCash.setFont(new Font("Times New Roman", Font.BOLD, 14));
				btnCard.setForeground(UIManager.getColor("JButton.darkShadow"));
				btnCard.setBackground(new Color(255, 228, 225));
				btnCard.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				payment = "Cash";
			}
		});

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(52, 220, 104, 14);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setValue(currentProgress);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);

		JLabel labelCash = new JLabel("");
		labelCash.setIcon(new ImageIcon("src/cash.png"));
		labelCash.setBounds(76, 74, 40, 36);
		contentPane.add(labelCash);

		JLabel labelCard = new JLabel("");
		labelCard.setIcon(new ImageIcon("src/card.png"));
		labelCard.setBounds(70, 130, 72, 41);
		contentPane.add(labelCard);

		lblWelcome = new JLabel("Welcome, distinguished member:");
		lblWelcome.setForeground(new Color(204, 0, 51));
		lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblWelcome.setBounds(10, 37, 302, 17);
		lblWelcome.setVisible(false);
		contentPane.add(lblWelcome);

		new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentProgress++;
				if (currentProgress > 100) {
					currentProgress = 100;
				}
				progressBar.setValue(currentProgress);
			}
		}).start();
	}

	/**
	 * <p>
	 * Popup to remind personal information. There is a countdown timer. If there is
	 * no operation of clicking the button within the specified time, it will
	 * automatically jump.
	 */
	public void init() {
		if (ds.getMember() != null) {
			lblWelcome.setText(
					"Welcome, distinguished member:" + ms.getMember().getfName() + " " + ms.getMember().getsName());
			lblWelcome.setVisible(true);
		} else {
			lblWelcome.setVisible(false);
		}
		setTimer(120);
	}
}
