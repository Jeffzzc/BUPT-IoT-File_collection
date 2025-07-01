package pages;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import domain.DataSource;
import service.MemberService;
import javax.swing.JButton;

/**
 * The {@code Login} class wraps a value of the primitive type {@code Login} in
 * an object. And this is the <code>log in</code> page of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. Users can log in by
 * entering the correct password.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class Login extends PageBase implements ActionListener {

	private MemberService ms;
	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JButton btnNewButton;
	private JLabel imgClose = new JLabel();
	private JLabel imgOpen = new JLabel();
	private JTextField txt;
	private String num;
	private int click = 0;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		this(null, null);
	}

	/**
	 * Create the Log in frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public Login(DataSource ds, PageController pc) {
		super(ds, pc);
		this.setTitle("Login");
		ms = new MemberService(DataSource.memberfilename, ds);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel panel = new JLabel("Login");
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel.setBounds(180, 25, 180, 25);
		contentPane.add(panel);

		JLabel panel1 = new JLabel("Membership");
		panel1.setForeground(new Color(255, 255, 255));
		panel1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel1.setBounds(200, 80, 180, 17);
		contentPane.add(panel1);

		txtPassword = new JPasswordField(20);
		txtPassword.setBounds(130, 135, 140, 25);
		txtPassword.addActionListener(this);
		this.getContentPane().add(txtPassword);

		JLabel img = new JLabel();
		img.setLocation(120, 60);
		Icon icon = new ImageIcon("src/Membership.png");
		img.setIcon(icon);
		img.setSize(60, 60);
		contentPane.add(img);

		imgClose.setLocation(300, 120);
		Icon icon1 = new ImageIcon("src/Close.png");
		imgClose.setIcon(icon1);
		imgClose.setSize(40, 40);
		imgClose.setVisible(true);
		contentPane.add(imgClose);

		imgOpen.setLocation(305, 120);
		Icon icon2 = new ImageIcon("src/Open.png");
		imgOpen.setIcon(icon2);
		imgOpen.setSize(40, 40);
		imgOpen.setVisible(false);
		contentPane.add(imgOpen);

		btnNewButton = new JButton("");
		btnNewButton.setBounds(303, 120, 40, 40);
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setOpaque(false);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);

		txt = new JTextField(20);
		txt.setBounds(130, 135, 140, 25);
		txt.addActionListener(this);
		txt.setVisible(false);
		this.getContentPane().add(txt);

		JButton buttonReturn = new JButton("Return");
		buttonReturn.setForeground(Color.WHITE);
		buttonReturn.setBackground(new Color(205, 133, 63));
		buttonReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.changePage(15);
			}
		});
		buttonReturn.setBounds(220, 217, 80, 17);
		contentPane.add(buttonReturn);

		JButton buttonNextStop = new JButton("Next Step");
		buttonNextStop.setForeground(Color.WHITE);
		buttonNextStop.setBackground(new Color(205, 133, 63));
		buttonNextStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nowpass = "";
				if (txtPassword.isVisible()) {
					nowpass = new String(txtPassword.getPassword());
				} else {
					nowpass = new String(txt.getText());
				}
				boolean res = ms.memberLogin(nowpass);
				if (res) {
					JOptionPane.showInternalMessageDialog(contentPane, "Welcome, " + ds.getMember().getfName(),
							"Welcome", JOptionPane.INFORMATION_MESSAGE);
					pc.changePage(6);
				} else {
					JOptionPane.showInternalMessageDialog(contentPane, "Please input the right ID", "Wrong Member ID",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		buttonNextStop.setBounds(330, 217, 90, 17);
		contentPane.add(buttonNextStop);
	}

	/**
	 * <p>
	 * There are two states of visible and invisible. The default option is
	 * invisible. Odd number of button clicks are visible options. On the contrary,
	 * the even-click button is an invisible option
	 * 
	 * @param event JButton listener
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		click++;
		if (source == btnNewButton) {
			if (click % 2 != 0) {
				num = new String(txtPassword.getPassword());
				imgClose.setVisible(false);
				imgOpen.setVisible(true);
				txtPassword.setVisible(false);
				txt.setVisible(true);
				txt.setText(num);
			} else {
				num = txt.getText();
				imgClose.setVisible(true);
				imgOpen.setVisible(false);
				txt.setVisible(false);
				txtPassword.setVisible(true);
				txtPassword.setText(num);
			}
		}
	}

	/**
	 * Initial countdown. And automatically jump to the page at the end of the
	 * countdown.
	 */
	public void init() {
		setTimer(160);
		txtPassword.setText("");
		txt.setText("");
	}
}
