package pages;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import domain.DataSource;

/**
 * The {@code FixedPriceInstruction} class wraps a value of the primitive type
 * {@code FixedPriceInstruction} in an object. And this is the
 * <code>fixed Price instruction</code> page of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. This is a page with a
 * countdown.
 * 
 * @author Tianyi Liu
 * @version 5.0
 * @since JDK 11
 */
public class FixedPriceInstruction extends PageBase {
	private JPanel contentPane;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FixedPriceInstruction frame = new FixedPriceInstruction();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	FixedPriceInstruction() {
		this(null, null);
	}

	/**
	 * Create the fixed price instruction frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public FixedPriceInstruction(DataSource ds, PageController pc) {
		super(ds, pc);
		this.setTitle("Self-service Machine");
		setBackground(Color.ORANGE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);

		timerLabel = new JLabel("120" + "s");
		timerLabel.setBounds(10, 236, 58, 15);
		timerLabel.setVisible(true);
		contentPane.add(timerLabel);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.changePage(1);
			}
		});
		button.setBounds(402, 0, 48, 51);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setIconTextGap(0);
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		contentPane.add(button);

		JLabel img = new JLabel();
		img.setLocation(0, -55);
		Icon icon = new ImageIcon("src/FixedPrice.png");
		img.setIcon(icon);
		img.setSize(450, 400);
		contentPane.add(img);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
	}

	/**
	 * Initial countdown. And automatically jump to the page at the end of the
	 * countdown.
	 */
	public void init() {
		setTimer(240);
	}
}