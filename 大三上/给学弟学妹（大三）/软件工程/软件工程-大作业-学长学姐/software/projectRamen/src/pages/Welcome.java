package pages;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import domain.DataSource;
import service.OrderService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@code Welcome} class wraps a value of the primitive type {@code Welcome}
 * in an object. And this is the <code>starting</code> page of the user
 * interface.
 * 
 * <p>
 * The entire page includes the background and buttons. Click the button to hide
 * the Welcome page and enter other pages.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class Welcome extends PageBase {

	private JPanel contentPane;
	private JButton buttonStart;
	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Welcome() {
		this(null, null);
	}

	/**
	 * Create the welcome frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public Welcome(DataSource ds, PageController pc) {
		super(ds, pc);
		this.setTitle("Self-service Machine");
		setBackground(Color.ORANGE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();

		JLabel lblWelcomeToRamen = new JLabel("Welcome to Totoro Ramen Restaurant!");
		lblWelcomeToRamen.setForeground(new Color(160, 82, 45));
		lblWelcomeToRamen.setBackground(new Color(255, 255, 255));
		lblWelcomeToRamen.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		lblWelcomeToRamen.setBounds(28, 20, 368, 34);
		contentPane.add(lblWelcomeToRamen);

		JLabel imgRamen = new JLabel();
		imgRamen.setLocation(-380, 51);
		Icon icon = new ImageIcon("src/Ramen.png");
		imgRamen.setIcon(icon);
		imgRamen.setSize(822, 379);
		contentPane.add(imgRamen);
//		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		buttonStart = new JButton("Start");
		buttonStart.setForeground(Color.WHITE);
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderService os = new OrderService(ds, DataSource.orderfilename);
				os.newOrder();
				ds.getOrder().setChashu(0);
				ds.getOrder().setNori(0);
				ds.getOrder().setBoiledegg(0);
				ds.getOrder().setSpiciness(0);
				pc.nextpage();
//				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	
//				System.exit(0);
			}
		});
		buttonStart.setFont(new Font("Times New Roman", Font.BOLD, 18));
		buttonStart.setBackground(new Color(205, 133, 63));
		buttonStart.setBounds(175, 200, 80, 25);
		contentPane.add(buttonStart);
	}
}
