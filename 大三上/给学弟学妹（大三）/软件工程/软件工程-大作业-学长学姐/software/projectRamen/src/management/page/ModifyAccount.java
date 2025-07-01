package management.page;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import management.page.Login.PdviewListener;
import management.service.*;
import java.awt.Color;
import javax.swing.JPasswordField;

/**
 * This is the page for modifying administrator account.
 * 
 * <p>There is only one administrator account in this system. You can modify its
 * name and password in this page. The username and password should follow the format
 * requirement.  
 * 
 * @author Xiaotian Wang
 *
 */
public class ModifyAccount extends JFrame{

	private static final long serialVersionUID = -5553898156765718347L;
	
	private JTextField textField_username;
	private JTextField textField_password;
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnPdview = new JButton("");
	
	private AccountService as = new AccountService();
	
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblPrompt_1;
	private JLabel lblPrompt_2;
	
	private JPasswordField passwordField;
	
	private ImageIcon closeImg = new ImageIcon(Login.class.getResource("/management/Close.png"));
	private ImageIcon openImg = new ImageIcon(Login.class.getResource("/management/Open.png"));
	
	private int pdStatus = 0;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyAccount window = new ModifyAccount();
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
	public ModifyAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		getContentPane().setBackground(new Color(240, 220, 140));
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		textField_username = new JTextField(as.getAccount().getName());
		textField_username.setBounds(337, 169, 277, 37);
		getContentPane().add(textField_username);
		textField_username.setColumns(10);
		
		textField_password = new JTextField(as.getAccount().getPassword());
		textField_password.setBounds(335, 254, 279, 37);
		getContentPane().add(textField_password);
		textField_password.setColumns(10);
		textField_password.setVisible(false);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 35));
		lblUsername.setBounds(136, 165, 190, 28);
		getContentPane().add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 35));
		lblPassword.setBounds(136, 254, 178, 37);
		getContentPane().add(lblPassword);
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(205, 133, 63));
		btnSave.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 30));
		btnSave.setBounds(211, 391, 157, 50);
		getContentPane().add(btnSave);
		btnSave.addActionListener(new SaveListener());
		
		
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(205, 133, 63));
		btnCancel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 30));
		btnCancel.setBounds(492, 391, 178, 50);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnCancel) {
				
				MainMenu window = new MainMenu();
				window.setVisible(true);
				dispose();
			}
				
		}});
		
		lblPrompt_1 = new JLabel("3-10 letters or numbers");
		lblPrompt_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPrompt_1.setForeground(Color.RED);
		lblPrompt_1.setBounds(337, 216, 247, 28);
		getContentPane().add(lblPrompt_1);
		
		lblPrompt_2 = new JLabel("6-18 letters or numbers");
		lblPrompt_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPrompt_2.setForeground(Color.RED);
		lblPrompt_2.setBounds(335, 298, 233, 28);
		getContentPane().add(lblPrompt_2);
		
		passwordField = new JPasswordField(as.getAccount().getPassword());
		passwordField.setBounds(335, 254, 279, 37);
		getContentPane().add(passwordField);
		
		
		btnPdview.setBorderPainted(false);
		btnPdview.setBackground(new Color(240, 220, 140));
		btnPdview.setBounds(641, 254, 81, 39);
		btnPdview.setIcon(closeImg);
		btnPdview.addActionListener(new PdviewListener());
		getContentPane().add(btnPdview);
	}
	
	public class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnSave) {
				
				String password="";
				
				if (pdStatus==0) {
					password = passwordField.getText();
				}else {
					password = textField_password.getText();
				}
				
				
				if (as.checkAccountName(textField_username.getText())&&
						as.checkAccountPassword(password)) {
					
					int n = JOptionPane.showConfirmDialog(null, "Are you sure to save?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
					
						as.changeAccountName(textField_username.getText());
						as.changeAccountPassword(password);
						as.saveAccount();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Wrong username or password format!"); 
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
					passwordField.setVisible(false);
					textField_password.setText(passwordField.getText());
					textField_password.setVisible(true);
					pdStatus++;
				}else {
					btnPdview.setIcon(closeImg);
					textField_password.setVisible(false);
					passwordField.setText(textField_password.getText());
					passwordField.setVisible(true);
					pdStatus--;
				}
			}
		}
	}
}
