package management.page;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import management.service.*;

/**
 * This is the page for modifying prices of different dishes.
 * 
 * <p>You can input prices you want to set and save your modification.
 * 
 * @author Xiaotian Wang
 *
 */
public class ModifyPrice extends JFrame{

	

	JButton btnSave = new JButton("Save");
	JButton btnCancel = new JButton("Cancel");
	

	private ModifyService ms = new ModifyService();
	
	private JLabel lblNori;
	private JLabel lblShoots;
	private JLabel lblRamen;
	private JLabel lblChashu;
	private JLabel lblEgg;
	
	private JTextField textField_ramen;
	private JTextField textField_egg;
	private JTextField textField_nori;
	private JTextField textField_chashu;
	private JTextField textField_shoots;
	private JLabel lblNewLabel;
	
	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyPrice window = new ModifyPrice();
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
	public ModifyPrice() {
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
		
		lblRamen = new JLabel("Ramen");
		lblRamen.setForeground(new Color(160, 82, 45));
		lblRamen.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblRamen.setBounds(139, 147, 120, 36);
		getContentPane().add(lblRamen);
		
		lblEgg = new JLabel("Extra Boiled Egg");
		lblEgg.setForeground(new Color(160, 82, 45));
		lblEgg.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblEgg.setBounds(28, 240, 233, 36);
		getContentPane().add(lblEgg);
		
		
		
		btnSave.setBackground(new Color(205, 133, 63));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 35));
		btnSave.setBounds(203, 480, 178, 46);
		getContentPane().add(btnSave);
		btnSave.addActionListener(new SaveListener());
		
		
		
		btnCancel.setBackground(new Color(205, 133, 63));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 35));
		btnCancel.setBounds(519, 480, 178, 46);
		getContentPane().add(btnCancel);
		btnCancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnCancel) {
				
				ModifyMenu window = new ModifyMenu();
				window.setVisible(true);
				dispose();
			}
				
		}});
	
		
		textField_ramen = new JTextField(""+ms.getOnePrice("Ramen"));
		textField_ramen.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_ramen.setBounds(280, 154, 90, 33);
		getContentPane().add(textField_ramen);
		textField_ramen.setColumns(10);
		
		
		
		textField_egg = new JTextField(""+ms.getOnePrice("Egg"));
		textField_egg.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_egg.setBounds(280, 247, 90, 34);
		getContentPane().add(textField_egg);
		textField_egg.setColumns(10);
		
		lblNori = new JLabel("Extra Nori");
		lblNori.setForeground(new Color(160, 82, 45));
		lblNori.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblNori.setBounds(448, 147, 156, 36);
		getContentPane().add(lblNori);
		
		textField_nori = new JTextField(""+ms.getOnePrice("Nori"));
		textField_nori.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_nori.setColumns(10);
		textField_nori.setBounds(641, 154, 90, 33);
		getContentPane().add(textField_nori);
		
		lblChashu = new JLabel("Extra Chashu");
		lblChashu.setForeground(new Color(160, 82, 45));
		lblChashu.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblChashu.setBounds(431, 245, 205, 36);
		getContentPane().add(lblChashu);
		
		textField_chashu = new JTextField(""+ms.getOnePrice("Chashu"));
		textField_chashu.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_chashu.setColumns(10);
		textField_chashu.setBounds(641, 247, 90, 33);
		getContentPane().add(textField_chashu);
		
		lblShoots = new JLabel("Bamboo Shoots");
		lblShoots.setForeground(new Color(160, 82, 45));
		lblShoots.setFont(new Font("Bell MT", Font.ITALIC, 30));
		lblShoots.setBounds(37, 348, 233, 41);
		getContentPane().add(lblShoots);
		
		textField_shoots = new JTextField(""+ms.getOnePrice("Shoots"));
		textField_shoots.setFont(new Font("Arial", Font.PLAIN, 20));
		textField_shoots.setColumns(10);
		textField_shoots.setBounds(280, 353, 90, 34);
		getContentPane().add(textField_shoots);
		
		lblNewLabel = new JLabel("MODIFY PRICE");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Bell MT", Font.BOLD, 40));
		lblNewLabel.setBounds(264, 24, 340, 47);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTip = new JLabel("<html>Tip: Price should be a positive number <br> with a maximum of two decimal places</html>");
		lblTip.setBackground(new Color(255, 255, 255));
		lblTip.setForeground(new Color(255, 0, 0));
		lblTip.setFont(new Font("Corbel", Font.PLAIN, 20));
		lblTip.setBounds(264, 69, 340, 68);
		getContentPane().add(lblTip);
	}
	
	
	/**
	 * This is a inner class implements {@code ActionListener}.
	 * 
	 * <p> When the "save" button is clicked, {@code actionPerformed} method will be invoked to
	 * save the modified prices.
	 * 
	 * @author Xiaotian Wang
	 *
	 */
	public class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnSave) {
				
			
				if (ms.checkPriceFormat(textField_ramen.getText())&&ms.checkPriceFormat(textField_egg.getText())
				&&ms.checkPriceFormat(textField_nori.getText())&&ms.checkPriceFormat(textField_chashu.getText())
				&&ms.checkPriceFormat(textField_shoots.getText())) {
					
					if (!(ms.checkPriceRange(textField_ramen.getText(), 99)&&ms.checkPriceRange(textField_egg.getText(), 99)
							&&ms.checkPriceRange(textField_nori.getText() ,99)&&ms.checkPriceRange(textField_chashu.getText() ,99)
							&&ms.checkPriceRange(textField_shoots.getText() ,99))) {
					
						int n = JOptionPane.showConfirmDialog(null, "The price you set is too high (>99) ! Are you sure to save?", "Confirm", JOptionPane.YES_NO_OPTION);
						if (n == JOptionPane.YES_OPTION) {
							ms.changePrice("Ramen", textField_ramen.getText());
							ms.changePrice("Egg", textField_egg.getText());
							ms.changePrice("Nori", textField_nori.getText());
							ms.changePrice("Chashu", textField_chashu.getText());
							ms.changePrice("Shoots", textField_shoots.getText());
							
							ms.savePrice();
						} 
					}else {
						int n = JOptionPane.showConfirmDialog(null, "Are you sure to save?", "Confirm", JOptionPane.YES_NO_OPTION);
						if (n == JOptionPane.YES_OPTION) {
							ms.changePrice("Ramen", textField_ramen.getText());
							ms.changePrice("Egg", textField_egg.getText());
							ms.changePrice("Nori", textField_nori.getText());
							ms.changePrice("Chashu", textField_chashu.getText());
							ms.changePrice("Shoots", textField_shoots.getText());
							
							ms.savePrice();
							ModifyMenu window = new ModifyMenu();
							window.setVisible(true);
							dispose();
							
						}
					}
				
				
				
				}else {
					JOptionPane.showMessageDialog(null, "Wrong number format! Input must be a positive number with a maximum of two decimal places");
				}
		}
	  }
	}
}
