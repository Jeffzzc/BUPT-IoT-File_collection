package management.page;

import java.awt.Color;
import java.awt.EventQueue;



import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import management.service.*;

import javax.swing.filechooser.*;

/**
 * This is the page for modifying poster of self-ordering system.
 * 
 * <p>You can choose a picture from your computer.
 * 
 * @author Xiaotian Wang
 *
 */
public class ModifyPicture extends JFrame{


	private static final long serialVersionUID = 4378105798913071147L;
	
	JButton btnSave = new JButton("Save");
	JButton btnCancel = new JButton("Cancel");
	JButton btnChoose = new JButton("OPEN");

	private ModifyService ms = new ModifyService();
	
	private JTextField textField_Path =  new JTextField(ms.getRec().get("poster"));
	private JLabel lblPicture = new JLabel("");
	
	
	private JFileChooser fileChooser = new JFileChooser();
	private File selectedFile;
	
	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyPicture window = new ModifyPicture();
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
	public ModifyPicture() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		FileFilter filter = new FileNameExtensionFilter("JPG/JPEG/GIF/PNG", "JPG", "JPEG", "GIF", "PNG");
		fileChooser.setFileFilter(filter);
		
		getContentPane().setBackground(new Color(240, 220, 140));
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
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
		
		JLabel lblCurrentPath = new JLabel("Current Path:");
		lblCurrentPath.setFont(new Font("Arial", Font.PLAIN, 30));
		lblCurrentPath.setForeground(new Color(210, 105, 30));
		lblCurrentPath.setBounds(29, 31, 190, 57);
		getContentPane().add(lblCurrentPath);
		
		
		textField_Path.setBounds(222, 41, 459, 36);
		getContentPane().add(textField_Path);
		textField_Path.setColumns(10);
		
		
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChoose.setForeground(Color.WHITE);
		btnChoose.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 30));
		btnChoose.setBackground(new Color(205, 133, 63));
		btnChoose.setBounds(698, 34, 151, 46);
		getContentPane().add(btnChoose);
		ImageIcon icon = new ImageIcon(ms.getRec().get("poster"));
		icon.setImage(icon.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
		lblPicture.setIcon(icon);
		btnChoose.addActionListener(new ChooseFileListener());
		
		lblPicture.setBounds(197, 101, 500, 335);
		getContentPane().add(lblPicture);
		btnCancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnCancel) {
				
				ModifyMenu window = new ModifyMenu();
				window.setVisible(true);
				dispose();
			}
				
		}});

	}
	
	/**
	 * This is a inner class implements {@code ActionListener}.
	 * 
	 * <p> When the "open" button is clicked, {@code actionPerformed} method will be invoked to
	 * open a file system for choosing picture.
	 * 
	 * @author Xiaotian Wang
	 *
	 */
	public class ChooseFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnChoose) {

				int i = fileChooser.showOpenDialog(getContentPane());
				
			
				if (i == JFileChooser.APPROVE_OPTION) {
					
					selectedFile = fileChooser.getSelectedFile();
					ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
					 icon.setImage(icon.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
					
					lblPicture.setIcon(icon);
					textField_Path.setText(selectedFile.getAbsolutePath());
					
				}
			
			} 
				
				
				
		}
	  }
	
	
	
	/**
	 * This is a inner class implements {@code ActionListener}.
	 * 
	 * <p> When the "save" button is clicked, {@code actionPerformed} method will be invoked to
	 * save the path of the poster.
	 * 
	 * @author Xiaotian Wang
	 *
	 */
	public class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			if (source==btnSave) {
				int n = JOptionPane.showConfirmDialog(null, "Are you sure to save?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					
					if (selectedFile!=null) {
						ms.changeRec("poster", selectedFile.getAbsolutePath());
						ms.savePoster();
					}
					ModifyMenu window = new ModifyMenu();
					window.setVisible(true);
					dispose();
				}
			}
			
		} 
						
	}
	
	
}
	

