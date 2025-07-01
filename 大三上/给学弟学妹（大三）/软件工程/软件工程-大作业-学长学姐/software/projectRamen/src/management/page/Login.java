package management.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;


import management.service.*;

/**
 * This is the login page of the management system.
 * 
 * <p>On this page, the restaurant manager inputs username and password to log in.
 * 
 * @author Xiaotian Wang
 *
 */
public class Login extends JFrame {

	private static final long serialVersionUID = 1600126518534762484L;
	
	private JTextField jtUsername;
	private JTextField jtPassword;
	private JPasswordField jpPassword;
	
	private JButton btnLogIn = new JButton("LOGIN");
	private JButton btnPdview = new JButton("");
	
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblTitle;
	private JLabel lblTotoro;
	
	private ImageIcon closeImg = new ImageIcon(Login.class.getResource("/management/Close.png"));
	private ImageIcon openImg = new ImageIcon(Login.class.getResource("/management/Open.png"));
	private ImageIcon welcomeImg = new ImageIcon(Login.class.getResource("/management/totoro2.png"));
	
	
	private int pdStatus = 0;
	
	private AccountService as = new AccountService();
	
	

	/**
	 * Create the application.
	 */
	public Login() {
		getContentPane().setBackground(new Color(240, 220, 140));
		getContentPane().setForeground(new Color(0, 0, 0));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 32));
		lblUsername.setBounds(406, 134, 164, 55);
		getContentPane().add(lblUsername);
		
		jtUsername = new JTextField();
		jtUsername.setForeground(Color.BLACK);
		jtUsername.setBounds(406, 191, 318, 39);
		getContentPane().add(jtUsername);
		jtUsername.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 32));
		lblPassword.setBounds(409, 253, 187, 28);
		getContentPane().add(lblPassword);
		
		//password can be viewed
		jtPassword = new JTextField();
		jtPassword.setVisible(false);
		jtPassword.setForeground(Color.BLACK);
		jtPassword.setBounds(406, 291, 318, 39);
		getContentPane().add(jtPassword);
		jtPassword.setColumns(20);
		
		
		//password can't be viewed		
		jpPassword = new JPasswordField();
		jpPassword.setColumns(20);
		jpPassword.setBounds(406, 291, 318, 39);
		jpPassword.setForeground(Color.BLACK);
		getContentPane().add(jpPassword);
		
		
		btnLogIn.addActionListener(new LoginListener());
		btnLogIn.setBackground(new Color(205, 133, 63));
		btnLogIn.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 32));
		btnLogIn.setBounds(550, 418, 174, 45);
		btnLogIn.setForeground(new Color(255, 255, 255));
		getContentPane().add(btnLogIn);
		
		lblTitle = new JLabel("Management   System");
		lblTitle.setForeground(new Color(102, 51, 0));
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblTitle.setBounds(181, 27, 543, 70);
		getContentPane().add(lblTitle);
		
		lblTotoro = new JLabel("");
		lblTotoro.setIcon(welcomeImg);
		lblTotoro.setBounds(-63, 59, 459, 390);
		getContentPane().add(lblTotoro);
		
		
		btnPdview.setBorderPainted(false);
		btnPdview.setBackground(new Color(240, 220, 140));
		btnPdview.setIcon(closeImg);
		btnPdview.setBounds(737, 291, 81, 39);
		getContentPane().add(btnPdview);
		btnPdview.addActionListener(new PdviewListener());
		
	
		

	}
	
	/**
	 * This is a inner class implements {@code ActionListener}.
	 * 
	 * <p> When login button is clicked, {@code actionPerformed} method will be invoked to
	 * check the correctness of username and password.
	 * 
	 * @author Xiaotian Wang
	 *
	 */
	public class LoginListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnLogIn) {
				
				String password="";
				
				if (pdStatus==0) {
					password = jpPassword.getText();
				}else {
					password = jtPassword.getText();
				}
				
				
				if (as.loginAccount(jtUsername.getText(), password)==1) {
					
					MainMenu window = new MainMenu();
					window.setVisible(true);
					Login.this.dispose();

				}else {
					JOptionPane.showMessageDialog(null, "Wrong username or password!"); 
				}
			}
		}
	}
	
	/**
	 * This is a inner class implements {@code ActionListener}.
	 * 
	 * <p> When the "eye" image is clicked, {@code actionPerformed} method will be invoked to
	 * change the state of whether the password is visible.
	 * 
	 * @author Xiaotian Wang
	 *
	 */
	public class PdviewListener implements ActionListener{
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnPdview) {
				if (pdStatus==0) {
					btnPdview.setIcon(openImg);
					jpPassword.setVisible(false);
					jtPassword.setText(jpPassword.getText());
					jtPassword.setVisible(true);
					pdStatus++;
				}else {
					btnPdview.setIcon(closeImg);
					jtPassword.setVisible(false);
					jpPassword.setText(jtPassword.getText());
					jpPassword.setVisible(true);
					pdStatus--;
				}
			}
		}
	}
	

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main (String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
