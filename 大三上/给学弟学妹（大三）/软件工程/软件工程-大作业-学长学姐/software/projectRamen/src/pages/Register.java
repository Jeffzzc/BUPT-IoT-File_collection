package pages;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import domain.DataSource;
import service.MemberService;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * The {@code Register} class wraps a value of the primitive type
 * {@code Register} in an object. And this is the <code>Registration</code> page
 * of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. This page will collect
 * user-related information to complete the registration. Including First name
 * {@link txtFirstName}, Surname {@link txtSurname}, Email {@link txtPhone},
 * Phone number {@link txtPhone} and automatically generated Captcha
 * {@link txtCaptcha}.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class Register extends PageBase implements Runnable {

	private MemberService ms;
	private JPanel contentPane;
	private JTextField txtFirstName, txtSurname, txtEmail, txtPhone, txtCaptcha;
	private JLabel imgWrong1, imgCorrect1, imgWrong2, imgCorrect2, imgWrong3, imgCorrect3, imgWrong4, imgCorrect4;
	private JLabel Tip1, Tip2, Tip3, Tip4;
	private JButton refresh = new JButton("Refresh");
	private JLabel Btn = new JLabel("123456", JLabel.CENTER);
	private Thread thread;
	private JLabel lblNewLabelChoice;
	private boolean is_ok;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Register() {
		this(null, null);
	}

	/**
	 * Create the register frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public Register(DataSource ds, PageController pc) {
		super(ds, pc);
		this.ms = new MemberService(DataSource.memberfilename, ds);
		this.setTitle("Sigh Up");
		this.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		imgCorrect1 = new JLabel();
		imgWrong1 = new JLabel();
		imgWrong1.setLocation(270, 40);
		imgCorrect1.setLocation(270, 40);

		imgWrong2 = new JLabel();
		imgWrong2.setLocation(270, 80);
		imgCorrect2 = new JLabel();
		imgCorrect2.setLocation(270, 80);

		imgWrong3 = new JLabel();
		imgWrong3.setLocation(270, 120);
		imgCorrect3 = new JLabel();
		imgCorrect3.setLocation(270, 120);

		imgWrong4 = new JLabel();
		imgWrong4.setLocation(270, 160);
		imgCorrect4 = new JLabel();
		imgCorrect4.setLocation(270, 160);

		Icon icon = new ImageIcon("src/Wrong.png");
		imgWrong1.setIcon(icon);
		imgWrong1.setSize(50, 50);
		contentPane.add(imgWrong1);

		icon = new ImageIcon("src/Correct.png");
		imgCorrect1.setIcon(icon);
		imgCorrect1.setSize(40, 40);
		contentPane.add(imgCorrect1);

		icon = new ImageIcon("src/Wrong.png");
		imgWrong2.setIcon(icon);
		imgWrong2.setSize(50, 50);
		contentPane.add(imgWrong2);
		is_ok = false;

		icon = new ImageIcon("src/Correct.png");
		imgCorrect2.setIcon(icon);
		imgCorrect2.setSize(40, 40);
		contentPane.add(imgCorrect2);

		icon = new ImageIcon("src/Correct.png");
		imgCorrect3.setIcon(icon);
		imgCorrect3.setSize(40, 40);
		contentPane.add(imgCorrect3);

		icon = new ImageIcon("src/Wrong.png");
		imgWrong3.setIcon(icon);
		imgWrong3.setSize(50, 50);
		contentPane.add(imgWrong3);

		icon = new ImageIcon("src/Correct.png");
		imgCorrect4.setIcon(icon);
		imgCorrect4.setSize(40, 40);
		contentPane.add(imgCorrect4);

		icon = new ImageIcon("src/Wrong.png");
		imgWrong4.setIcon(icon);
		imgWrong4.setSize(50, 50);
		contentPane.add(imgWrong4);

		JLabel panel = new JLabel("Type in your information. ");
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.setBounds(15, 5, 183, 17);
		contentPane.add(panel);

		JLabel labFirstName = new JLabel("First name:");
		labFirstName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labFirstName.setForeground(new Color(220, 20, 60));
		labFirstName.setBounds(30, 50, 88, 25);
		this.getContentPane().add(labFirstName);

		txtFirstName = new JTextField(20);
		txtFirstName.setBounds(140, 50, 120, 25);
		txtFirstName.setText("");
		this.getContentPane().add(txtFirstName);

		JLabel labSurname = new JLabel("Surname:");
		labSurname.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labSurname.setForeground(new Color(220, 20, 60));
		labSurname.setBounds(30, 90, 70, 25);
		this.getContentPane().add(labSurname);

		txtSurname = new JTextField(20);
		txtSurname.setBounds(140, 90, 120, 25);
		txtSurname.setText("");
		this.getContentPane().add(txtSurname);

		JLabel labEmail = new JLabel("Email:");
		labEmail.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labEmail.setForeground(new Color(0, 0, 0));
		labEmail.setBounds(30, 130, 70, 25);
		this.getContentPane().add(labEmail);

		txtEmail = new JTextField(20);
		txtEmail.setBounds(140, 130, 120, 25);
		txtEmail.setText("");
		this.getContentPane().add(txtEmail);

		JLabel labPhoneNumber = new JLabel("Phone Number:");
		labPhoneNumber.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labPhoneNumber.setForeground(new Color(0, 0, 0));
		labPhoneNumber.setBounds(30, 170, 130, 25);
		this.getContentPane().add(labPhoneNumber);

		txtPhone = new JTextField(20);
		txtPhone.setBounds(140, 170, 120, 25);
		txtPhone.setText("");
		this.getContentPane().add(txtPhone);

		JLabel labCaptcha = new JLabel("Captcha:");
		labCaptcha.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labCaptcha.setForeground(new Color(0, 0, 0));
		labCaptcha.setBounds(30, 210, 60, 25);
		this.getContentPane().add(labCaptcha);

		txtCaptcha = new JTextField(20);
		txtCaptcha.setBounds(110, 210, 60, 25);
		this.getContentPane().add(txtCaptcha);

		Tip1 = new JLabel("Tip: Can be empty");
		Tip1.setFont(new Font("Times New Roman", Font.BOLD, 10));
		Tip1.setForeground(new Color(255, 255, 255));
		Tip1.setBounds(310, 130, 120, 21);
		contentPane.add(Tip1);

		Tip2 = new JLabel("Tip: Can be empty");
		Tip2.setFont(new Font("Times New Roman", Font.BOLD, 10));
		Tip2.setForeground(new Color(255, 255, 255));
		Tip2.setBounds(310, 170, 120, 21);
		contentPane.add(Tip2);

		Tip3 = new JLabel("Tip: It can not be empty");
		Tip3.setFont(new Font("Times New Roman", Font.BOLD, 10));
		Tip3.setForeground(new Color(255, 255, 255));
		Tip3.setBounds(310, 50, 120, 21);
		contentPane.add(Tip3);

		Tip4 = new JLabel("Tip: It can not be empty");
		Tip4.setFont(new Font("Times New Roman", Font.BOLD, 10));
		Tip4.setForeground(new Color(255, 255, 255));
		Tip4.setBounds(310, 90, 120, 21);
		contentPane.add(Tip4);

		String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer stringbuffer = new StringBuffer();
		int len = KeyString.length();
		for (int i = 0; i < 6; i++) {
			stringbuffer.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
		}

		String x = stringbuffer.toString();
		String y = String.valueOf(x);
		Btn.setText(y);
		Btn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Btn.setForeground(Color.BLUE);
		Btn.setBounds(190, 195, 60, 25);
		contentPane.add(Btn);
		refresh.setBackground(new Color(240, 128, 128));

		refresh.setFont(new Font("Times New Roman", Font.BOLD, 12));
		refresh.setForeground(new Color(0, 0, 0));
		refresh.setBounds(180, 220, 75, 17);
		contentPane.add(refresh);

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
				StringBuffer sb = new StringBuffer();
				int len = KeyString.length();
				for (int i = 0; i < 6; i++) {
					sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
				}
				String x = sb.toString();
				String y = String.valueOf(x);
				Btn.setText(y);
			}
		});

		JButton buttonReturn = new JButton("Return");
		buttonReturn.setForeground(Color.WHITE);
		buttonReturn.setBackground(new Color(205, 133, 63));
		buttonReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thread.interrupt();
				pc.changePage(15);
			}
		});
		buttonReturn.setBounds(260, 220, 75, 17);
		contentPane.add(buttonReturn);

		JButton buttonNextStep = new JButton("Next Step");
		buttonNextStep.setForeground(Color.WHITE);
		buttonNextStep.setBackground(new Color(205, 133, 63));
		buttonNextStep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
				}
				if (txtCaptcha.getText().toLowerCase().equals(Btn.getText().toLowerCase()) && is_ok) {
					if (ms.memberReg(txtFirstName.getText(), txtSurname.getText(), txtEmail.getText(),
							txtPhone.getText())) {
						thread.interrupt();
						JOptionPane.showConfirmDialog(null, "Registration success!", "Results feedback",
								JOptionPane.CLOSED_OPTION);
						pc.changePage(9);
						ms.printTicket();
						return;
					}
				} 
				if (!txtCaptcha.getText().toLowerCase().equals(Btn.getText().toLowerCase())){
					JOptionPane.showConfirmDialog(null, "Verification code error, please try again!", "Results feedback",
							JOptionPane.CLOSED_OPTION);
				} else {
					JOptionPane.showConfirmDialog(null, "Incorrect input information, please try again!", "Results feedback",
							JOptionPane.CLOSED_OPTION);
				}

			}
		});
		buttonNextStep.setBounds(340, 220, 90, 17);
		contentPane.add(buttonNextStep);

		lblNewLabelChoice = new JLabel("You need to fill in at least one Phone number or Email ");
		lblNewLabelChoice.setBounds(30, 17, 406, 36);
		contentPane.add(lblNewLabelChoice);
	}

	/**
	 * Always verify the correctness of the input. And display the corresponding
	 * errors and correct pictures in run method.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
			is_ok = true;
			if (!isName(txtFirstName.getText())) {
				imgWrong1.setVisible(true);
				imgCorrect1.setVisible(false);
				is_ok = false;
			} else {
				imgCorrect1.setVisible(true);
				imgWrong1.setVisible(false);
			}
			if (!isName(txtSurname.getText())) {
				imgWrong2.setVisible(true);
				imgCorrect2.setVisible(false);
				is_ok = false;
			} else {
				imgCorrect2.setVisible(true);
				imgWrong2.setVisible(false);
			}
			boolean email = isEmail(txtEmail.getText()), phone = isPhone(txtPhone.getText());
			boolean emptyemail = txtEmail.getText().equals("");
			boolean emptyphone = txtPhone.getText().equals("");
			if ((email && phone) || (emptyphone && email) || (emptyemail && phone)
					|| (email && !emptyphone && !phone)) {
				imgCorrect3.setVisible(true);
				imgWrong3.setVisible(false);
			} else {
				imgWrong3.setVisible(true);
				imgCorrect3.setVisible(false);
				is_ok = false;
			}

			if ((email && phone) || (emptyemail && phone) || (emptyphone && email)
					|| (phone && !emptyemail && !email)) {
				imgCorrect4.setVisible(true);
				imgWrong4.setVisible(false);
			} else {
				imgWrong4.setVisible(true);
				imgCorrect4.setVisible(false);
				is_ok = false;
			}
		}
	}

	/**
	 * There are some requirement in isName method.
	 * 
	 * @param charaString the name input
	 * @return whether it reach the requirement
	 */
	private static boolean isName(String charaString) {
		if (charaString == null)
			return false;
		if (charaString.equals(""))
			return false;
		if (charaString.length() > 10)
			return false;
		return charaString.matches("^[a-zA-Z]*");
	}

	/**
	 * There are some requirement in isEmail method.
	 * 
	 * @param string the Email input
	 * @return whether it reach the requirement
	 */
	private static boolean isEmail(String string) {
		if (string == null)
			return false;
		String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p;
		Matcher m;
		p = Pattern.compile(regEx1);
		m = p.matcher(string);
		if (m.matches()) {
			return true;
		} else
			return false;
	}

	/**
	 * There are some requirement in isPhone method.
	 * 
	 * @param phone the phone number input
	 * @return whether it reach the requirement
	 */
	private static boolean isPhone(String phone) {
		String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
		if (phone.length() != 11) {
			return false;
		} else {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phone);
			return m.matches();
		}
	}

	/**
	 * Initialize the thread in {@code Register.init} method.
	 */
	public void init() {
		thread = new Thread(this);
		thread.start();
		setTimer(300);
	}
}
