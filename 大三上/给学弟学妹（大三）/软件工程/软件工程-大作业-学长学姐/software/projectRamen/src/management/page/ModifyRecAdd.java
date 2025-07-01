package management.page;

import java.awt.Color;
import java.awt.EventQueue;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import management.service.*;
import javax.swing.*;

/**
 * This is the page for modifying recommended add-on.
 * 
 * <p>You can input prices you want to set and save your modification.
 * 
 * @author Xiaotian Wang
 *
 */
public class ModifyRecAdd extends JFrame{

	
	private static final long serialVersionUID = 384922382942675766L;
	
	private JButton btnSave = new JButton("Save");
	private JButton btnCancel = new JButton("Cancel");
	
	private JRadioButton rdbtnNori = new JRadioButton("Extra Nori");
	private JRadioButton rdbtnShoots = new JRadioButton("Bamboo shoots");
	private JRadioButton rdbtnEgg = new JRadioButton("Extra boiled egg");
	private JRadioButton rdbtnChashu = new JRadioButton("Extra Chashu");
	private final ButtonGroup bg = new ButtonGroup();
	
	private JLabel lblTitle;

	private ModifyService ms = new ModifyService();
	
	
	private String selected = ms.getRec().get("addon");
	
	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyRecAdd window = new ModifyRecAdd();
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
	public ModifyRecAdd() {
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
		
		
		btnSave.setBackground(new Color(205, 133, 63));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 35));
		btnSave.setBounds(155, 480, 178, 46);
		getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton)e.getSource();
				if (source==btnSave) {
					int n = JOptionPane.showConfirmDialog(null, "Are you sure to save?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						ms.changeRec("addon", selected);
						ms.saveRec();
						ModifyMenu window = new ModifyMenu();
						window.setVisible(true);
						dispose();
					}
				}
			}
		});
		
		
		
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
		
		lblTitle = new JLabel("Choose recommended add-on:");
		lblTitle.setForeground(new Color(128, 0, 0));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setBounds(43, 39, 472, 62);
		getContentPane().add(lblTitle);
		
		
		rdbtnNori.setForeground(new Color(139, 69, 19));
		rdbtnNori.setBackground(new Color(240, 220, 140));
		rdbtnNori.setFont(new Font("Bell MT", Font.ITALIC, 30));
		rdbtnNori.setBounds(155, 170, 245, 55);
		getContentPane().add(rdbtnNori);
		rdbtnNori.addActionListener(new RdbtnListener());
		checkStock("Extra Nori",rdbtnNori);
		
		rdbtnShoots.setForeground(new Color(160, 82, 45));
		rdbtnShoots.setBackground(new Color(240, 220, 140));
		rdbtnShoots.setFont(new Font("Bell MT", Font.ITALIC, 30));
		rdbtnShoots.setBounds(155, 298, 260, 62);
		getContentPane().add(rdbtnShoots);
		rdbtnShoots.addActionListener(new RdbtnListener());
		checkStock("Bamboo shoots",rdbtnShoots);
		
		
		rdbtnEgg.setForeground(new Color(160, 82, 45));
		rdbtnEgg.setBackground(new Color(240, 220, 140));
		rdbtnEgg.setFont(new Font("Bell MT", Font.ITALIC, 30));
		rdbtnEgg.setBounds(508, 170, 260, 55);
		getContentPane().add(rdbtnEgg);
		rdbtnEgg.addActionListener(new RdbtnListener());
		checkStock("Extra boiled egg",rdbtnEgg);
		
		
		rdbtnChashu.setForeground(new Color(160, 82, 45));
		rdbtnChashu.setBackground(new Color(240, 220, 140));
		rdbtnChashu.setFont(new Font("Bell MT", Font.ITALIC, 30));
		rdbtnChashu.setBounds(519, 301, 236, 62);
		getContentPane().add(rdbtnChashu);
		rdbtnChashu.addActionListener(new RdbtnListener());
		checkStock("Extra Chashu",rdbtnChashu);
		
		
		bg.add(rdbtnNori);
		bg.add(rdbtnShoots);
		bg.add(rdbtnEgg);
		bg.add(rdbtnChashu);
		
		switch (selected) {
			case("Extra Nori"):
				rdbtnNori.setSelected(true);
				break;
			case("Bamboo shoots"):
				rdbtnShoots.setSelected(true);
				break;
			case("Extra boiled egg"):
				rdbtnEgg.setSelected(true);
				break;
			case("Extra Chashu"):
				rdbtnChashu.setSelected(true);
				break;
		}
		
		
		JLabel lblNoriImg = new JLabel("");
		lblNoriImg.setIcon(new ImageIcon(ModifyRecAdd.class.getResource("/management/Nori.png")));
		lblNoriImg.setBounds(79, 170, 58, 65);
		getContentPane().add(lblNoriImg);
		
		JLabel lblEggImg = new JLabel("");
		lblEggImg.setIcon(new ImageIcon(ModifyRecAdd.class.getResource("/management/Egg.png")));
		lblEggImg.setBounds(444, 170, 58, 65);
		getContentPane().add(lblEggImg);
		
		JLabel lblShootsImg = new JLabel("");
		lblShootsImg.setIcon(new ImageIcon(ModifyRecAdd.class.getResource("/management/Shoots.png")));
		lblShootsImg.setBounds(79, 298, 58, 65);
		getContentPane().add(lblShootsImg);
		
		JLabel lblChashuImg = new JLabel("");
		lblChashuImg.setIcon(new ImageIcon(ModifyRecAdd.class.getResource("/management/Chashu.png")));
		lblChashuImg.setBounds(444, 295, 58, 65);
		getContentPane().add(lblChashuImg);
//		textField_ramen.setText(""+ms.getOnePrice("egg"));
	}
	
	/**
	 * This method is used to initialize the radio button.
	 * 
	 * @param name the name of the add-on.
	 * @param rdbtn the corresponding radio button you want to initialize.
	 */
	private void checkStock(String name, JRadioButton rdbtn) {
		int stock = ms.getOneStock(name);
		if (stock == 0) {
			rdbtn.setEnabled(false);
		}
	}
	
	/**
	 * This is a inner class implements {@code ActionListener}.
	 * 
	 * <p> When a radio button is selected, {@code actionPerformed} method will be invoked to
	 * change the value of {@code selected} attribute.
	 * 
	 * @author Xiaotian Wang
	 *
	 */
	class RdbtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JRadioButton temp=(JRadioButton)e.getSource();
            if(temp.isSelected()){
                selected =temp.getText();
            }
		}
	}
	

}
