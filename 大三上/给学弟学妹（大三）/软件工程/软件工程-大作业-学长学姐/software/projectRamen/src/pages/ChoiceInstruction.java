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
 * The {@code ChoiceInstruction} class wraps a value of the primitive type
 * {@code ChoiceInstruction} in an object. And this is the
 * <code>choice instruction</code> page of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. This is a page with a
 * countdown.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class ChoiceInstruction extends PageBase {
	private JPanel contentPane;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoiceInstruction frame = new ChoiceInstruction();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ChoiceInstruction() {
		this(null, null);
	}

	/**
	 * Create the choice instruction frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public ChoiceInstruction(DataSource ds, PageController pc) {
		super(ds, pc);
		this.setTitle("Self-service Machine");
		setBackground(Color.ORANGE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,  450, 325);
		setResizable(false);
		contentPane = new JPanel();
		timerLabel = new JLabel("120" + "s");
		timerLabel.setBounds(10, 236, 58, 15);
		timerLabel.setVisible(true);
		contentPane.add(timerLabel);
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.changePage(15);
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
		Icon icon = new ImageIcon("src/PaymentPage.png");
		img.setIcon(icon);
		img.setSize(450, 400);
		contentPane.add(img);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	/**
	 * Set countdown timer. And set the button state.
	 */
	public void init() {
		setTimer(240);
	}

}
