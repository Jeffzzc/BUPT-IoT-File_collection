package management.page;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * This is the page showing a list of modification options.
 * 
 * <p>After choosing modify menu in the main menu, this page shows four options:
 * 1.Modify Price; 2.Modify Stock; 3.Modify Poster; 4.Modify Recommendation
 * 
 * @author Xiaotian Wang
 *
 */
public class ModifyMenu extends JFrame{

	JButton btnBack = new JButton("BACK");
	JButton btnModifyPrice = new JButton("MODIFY PRICE");
	JButton btnModifyStock = new JButton("MODIFY STOCK");
	private final JButton btnModifyPoster = new JButton("MODIFY POSTER");
	private final JButton btnModifyRecommendation = new JButton("MODIFY RECOMMENDATION");
	
	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyMenu window = new ModifyMenu();
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
	public ModifyMenu() {
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
		
		btnModifyPrice.setBackground(new Color(255, 255, 255));
		btnModifyPrice.setForeground(new Color(184, 134, 11));
		btnModifyPrice.setFont(new Font("Bell MT", Font.PLAIN, 25));
		btnModifyPrice.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnModifyPrice) {
				
				ModifyPrice window = new ModifyPrice();
				window.setVisible(true);
				setVisible(false);
			}
				
		}});
		btnModifyPrice.setBounds(405, 30, 402, 65);
		getContentPane().add(btnModifyPrice);
		
		
		btnModifyStock.setBackground(new Color(255, 255, 255));
		btnModifyStock.setForeground(new Color(128, 128, 128));
		btnModifyStock.setFont(new Font("Bell MT", Font.PLAIN, 25));
		btnModifyStock.setBounds(405, 133, 402, 65);
		getContentPane().add(btnModifyStock);
		
		
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(205, 133, 63));
		btnBack.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 35));
		btnBack.setBounds(630, 450, 151, 38);
		getContentPane().add(btnBack);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ModifyMenu.class.getResource("/management/totoro1.png")));
		lblNewLabel.setBounds(-40, 30, 402, 352);
		getContentPane().add(lblNewLabel);
		btnModifyPoster.setForeground(new Color(184, 134, 11));
		btnModifyPoster.setFont(new Font("Bell MT", Font.PLAIN, 25));
		btnModifyPoster.setBackground(Color.WHITE);
		btnModifyPoster.setBounds(405, 230, 402, 65);
		getContentPane().add(btnModifyPoster);
		
		
		btnModifyRecommendation.setForeground(Color.GRAY);
		btnModifyRecommendation.setFont(new Font("Bell MT", Font.PLAIN, 25));
		btnModifyRecommendation.setBackground(Color.WHITE);
		btnModifyRecommendation.setBounds(405, 330, 402, 65);
		getContentPane().add(btnModifyRecommendation);
		
		
		
		btnModifyStock.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnModifyStock) {
				
				ModifyStock window = new ModifyStock();
				window.setVisible(true);
				dispose();
			}
		}});
		
		btnBack.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnBack) {
				
				MainMenu window = new MainMenu();
				window.setVisible(true);
				dispose();
			}
				
		}});
		
		btnModifyPoster.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton)e.getSource();
				if (source==btnModifyPoster) {
					
					ModifyPicture window = new ModifyPicture();
					window.setVisible(true);
					dispose();
				}
			}});
		
		btnModifyRecommendation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton)e.getSource();
				if (source==btnModifyRecommendation) {
					
					ModifyRecAdd window = new ModifyRecAdd();
					window.setVisible(true);
					dispose();
				}
			}});
	}

}
