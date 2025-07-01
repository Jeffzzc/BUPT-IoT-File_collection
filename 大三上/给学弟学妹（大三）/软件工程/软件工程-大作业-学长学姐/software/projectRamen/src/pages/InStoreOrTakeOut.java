package pages;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import domain.DataSource;

/**
 * The {@code InStoreOrTakeOur} class wraps a value of the primitive type
 * {@code InStoreOrTakeOur} in an object. And this is the
 * <code>in store or take our</code> page of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. There are two options
 * for users to choose.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class InStoreOrTakeOut extends PageBase {

	private JPanel contentPane;
	private String way = "";

	/** Default progress of progress bar {@value} */
	private static int currentProgress = 60;

	/**
	 * Create the in store or take out frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public InStoreOrTakeOut(DataSource ds, PageController pc) {
		super(ds, pc);
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
				if (way.equals("")) {
					JOptionPane.showInternalMessageDialog(contentPane,
							"You should choose a option before the next step!", "Wait for a moment",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					ds.getOrder().setInOrout(way);
					pc.nextpage();
				}
			}
		});
		button.setBounds(310, 217, 90, 17);
		contentPane.add(button);

		JLabel panel = new JLabel("Please choose in store or take out");
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.setBounds(10, 10, 250, 17);
		contentPane.add(panel);

		JLabel imgStore = new JLabel();
		imgStore.setLocation(70, 50);
		Icon icon = new ImageIcon("src/Store.png");
		imgStore.setIcon(icon);
		imgStore.setSize(100, 100);
		contentPane.add(imgStore);

		JLabel imgPackage = new JLabel();
		imgPackage.setLocation(70, 100);
		Icon icon_1 = new ImageIcon("src/Package.png");
		imgPackage.setIcon(icon_1);
		imgPackage.setSize(100, 100);
		contentPane.add(imgPackage);

		JButton buttonInStore = new JButton("In-store");
		buttonInStore.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonInStore.setBackground(new Color(255, 228, 225));
		buttonInStore.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonInStore.setBounds(130, 80, 130, 30);
		contentPane.add(buttonInStore);

		JButton buttonTakeOut = new JButton("Take-out");
		buttonTakeOut.setForeground(UIManager.getColor("JButton.darkShadow"));
		buttonTakeOut.setBackground(new Color(255, 228, 225));
		buttonTakeOut.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		buttonTakeOut.setBounds(130, 130, 130, 30);
		contentPane.add(buttonTakeOut);

		buttonInStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonInStore.setForeground(UIManager.getColor("JButton.black"));
				buttonInStore.setBackground(new Color(205, 92, 92));
				buttonInStore.setFont(new Font("Times New Roman", Font.BOLD, 14));
				buttonTakeOut.setForeground(UIManager.getColor("JButton.darkShadow"));
				buttonTakeOut.setBackground(new Color(255, 228, 225));
				buttonTakeOut.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				way = "In-store";
			}
		});

		buttonTakeOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonTakeOut.setForeground(UIManager.getColor("JButton.black"));
				buttonTakeOut.setBackground(new Color(205, 92, 92));
				buttonTakeOut.setFont(new Font("Times New Roman", Font.BOLD, 14));
				buttonInStore.setForeground(UIManager.getColor("JButton.darkShadow"));
				buttonInStore.setBackground(new Color(255, 228, 225));
				buttonInStore.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				way = "Take-out";
			}
		});

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(52, 220, 104, 14);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setValue(currentProgress);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);

		new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentProgress++;
				if (currentProgress > 80) {
					currentProgress = 80;
				}
				progressBar.setValue(currentProgress);
			}
		}).start();
	}

	/**
	 * Initial countdown. And automatically jump to the page at the end of the
	 * countdown.
	 */
	public void init() {
		setTimer(120);
	}

}
