package pages;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import domain.DataSource;
import service.MemberService;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The {@code SixDigitsCode} class wraps a value of the primitive type
 * {@code SixDigitsCode} in an object. And this is the
 * <code>Successful Registration</code> page of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. This page prompts the
 * user to register successfully. And send the membership code to user and
 * printed registration ticket.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class SixDigitsCode extends PageBase {

	private JPanel contentPane;
	private MemberService ms;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SixDigitsCode frame = new SixDigitsCode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SixDigitsCode() {
		this(null, null);
	}

	/**
	 * Create the frame. Need to load relevant data information before page
	 * visualization. If the return button has been clicked, the page will
	 * automatically jump to the first page.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public SixDigitsCode(DataSource ds, PageController pc) {
		super(ds, pc);
		ms = new MemberService(DataSource.memberfilename, ds);
		this.setTitle("Six Digits Code");
		this.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel panelMembership = new JLabel("Your membership code has been sent to you, ");
		panelMembership.setForeground(new Color(160, 82, 45));
		panelMembership.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panelMembership.setBounds(15, 15, 350, 17);
		contentPane.add(panelMembership);

		JLabel panelTicket = new JLabel("and your registration ticket has been printed, please check.");
		panelTicket.setForeground(new Color(160, 82, 45));
		panelTicket.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panelTicket.setBounds(15, 30, 400, 17);
		contentPane.add(panelTicket);

		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ms.logout();
				JOptionPane.showConfirmDialog(null,
						"If you have received the code, please select 'sign in' the following page!", "Hint",
						JOptionPane.CLOSED_OPTION);
				pc.changePage(15);
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(205, 133, 63));
		button.setBounds(350, 60, 80, 17);
		contentPane.add(button);

		JLabel img = new JLabel();
		img.setLocation(-180, 60);
		Icon icon = new ImageIcon("src/Loading1.gif");
		img.setIcon(icon);
		img.setSize(692, 300);
		contentPane.add(img);
	}

	/**
	 * Initialize the countdown timer. If their is no operation after
	 * 120 seconds, it will automatically jump to the page.
	 */
	public void init() {
		setTimer(120);
	}

}
