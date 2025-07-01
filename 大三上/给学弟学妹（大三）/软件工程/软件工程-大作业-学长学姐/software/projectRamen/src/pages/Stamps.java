package pages;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import domain.DataSource;
import service.MemberService;

/**
 * The {@code Stamps} class wraps a value of the primitive type {@code Stamps}
 * in an object. And this is the <code>Stamps</code> page of the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. This is the stamp
 * display interface after member login. A stamp can be added
 * for every bowl of ramen order. Every ten stamps can be exchanged for a free
 * bowl of ramen (without additives).
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class Stamps extends PageBase {

	private JPanel contentPane;
	private int num;
	private JButton buttonReturn;
	private static Stamps frame;
	private ArrayList<JLabel> jlist;
	private MemberService ms;
	private JLabel panelNum;
	private JButton buttonCollect;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Stamps();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Stamps() {
		this(null, null);
	}

	/**
	 * Create the stamps frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public Stamps(DataSource ds, PageController pc) {
		super(ds, pc);
		jlist = new ArrayList<JLabel>();
		ms = new MemberService(DataSource.memberfilename, ds);
		this.setVisible(false);
		this.setTitle("Stamps Display");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		initList();

		JLabel panelStamps = new JLabel("Unredeemed Stamp");
		panelStamps.setForeground(new Color(160, 82, 45));
		panelStamps.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panelStamps.setBounds(15, 15, 180, 17);
		contentPane.add(panelStamps);

		JLabel panelRate = new JLabel("Collection Completion Rate:");
		panelRate.setForeground(new Color(255, 255, 255));
		panelRate.setFont(new Font("Times New Roman", Font.BOLD, 13));
		panelRate.setBounds(180, 25, 180, 17);
		contentPane.add(panelRate);

		panelNum = new JLabel(num + "/10");
		panelNum.setBackground(new Color(218, 165, 32));
		panelNum.setForeground(new Color(220, 20, 60));
		panelNum.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panelNum.setBounds(350, 25, 79, 17);
		contentPane.add(panelNum);

		buttonReturn = new JButton("Return");
		buttonReturn.setForeground(Color.WHITE);
		buttonReturn.setBackground(new Color(205, 133, 63));
		buttonReturn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		buttonReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedx(e);
			}
		});
		buttonReturn.setBounds(79, 217, 84, 17);
		contentPane.add(buttonReturn);

		buttonCollect = new JButton("Pay and collect one stamp");
		buttonCollect.setForeground(Color.WHITE);
		buttonCollect.setBackground(new Color(205, 133, 63));
		buttonCollect.setFont(new Font("Times New Roman", Font.BOLD, 12));
		buttonCollect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedx(e);
			}
		});
		buttonCollect.setBounds(209, 217, 201, 17);
		contentPane.add(buttonCollect);

		JLabel picture = new JLabel() {
			public void paint(Graphics gr) {
				Graphics2D g = (Graphics2D) gr;
				g.setBackground(Color.orange);
				Stroke dash = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.5f,
						new float[] { 5, 5, }, 0f);
				g.setStroke(dash);

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 5; j++) {
						g.setColor(Color.BLACK);
						g.drawRect(30 + 75 * j, 60 + 80 * i, 50, 50);
					}
				}
//				
			}
		};
		picture.setBounds(5, 5, 434, 250);
		contentPane.add(picture);

		JPanel panelPhoto = new JPanel();
		panelPhoto.setBackground(new Color(255, 228, 196));
		panelPhoto.setBounds(20, 50, 390, 155);
		contentPane.add(panelPhoto);
	}

	/**
	 * Load and initialize all stamps. set one write to visible according to the
	 * number of users in the database and one write to invisible.
	 */
	private void initList() {
		Stamps1();
		Stamps2();
		Stamps3();
		Stamps4();
		Stamps5();
		Stamps6();
		Stamps7();
		Stamps8();
		Stamps9();
		Stamps10();
	}

	/**
	 * Load the first stamp image in the right place.
	 */
	private void Stamps1() {
		JLabel img = new JLabel();
		jlist.add(img);
		img.setVisible(false);
		img.setLocation(35, 65);
		Icon icon = new ImageIcon("src/Stamps1.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * Load the second stamp image in the right place.
	 */
	private void Stamps2() {
		JLabel img = new JLabel();
		img.setLocation(110, 65);
		jlist.add(img);
		img.setVisible(false);
		Icon icon = new ImageIcon("src/Stamps2.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * Load the third stamp image in the right place.
	 */
	private void Stamps3() {
		JLabel img = new JLabel();
		img.setLocation(185, 65);
		jlist.add(img);
		img.setVisible(false);
		Icon icon = new ImageIcon("src/Stamps3.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * Load the fourth stamp image in the right place.
	 */
	private void Stamps4() {
		JLabel img = new JLabel();
		jlist.add(img);
		img.setVisible(false);
		img.setLocation(260, 65);
		Icon icon = new ImageIcon("src/Stamps4.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * Load the fifth stamp image in the right place.
	 */
	private void Stamps5() {
		JLabel img = new JLabel();
		jlist.add(img);
		img.setVisible(false);
		img.setLocation(335, 65);
		Icon icon = new ImageIcon("src/Stamps5.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * Load the sixth stamp image in the right place.
	 */
	private void Stamps6() {
		JLabel img = new JLabel();
		jlist.add(img);
		img.setVisible(false);
		img.setLocation(35, 145);
		Icon icon = new ImageIcon("src/Stamps6.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * Load the seventh stamp image in the right place.
	 */
	private void Stamps7() {
		JLabel img = new JLabel();
		jlist.add(img);
		img.setVisible(false);
		img.setLocation(110, 145);
		Icon icon = new ImageIcon("src/Stamps7.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * Load the eighth stamp image in the right place.
	 */
	private void Stamps8() {
		JLabel img = new JLabel();
		jlist.add(img);
		img.setVisible(false);
		img.setLocation(185, 145);
		Icon icon = new ImageIcon("src/Stamps8.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * Load the ninth stamp image in the right place.
	 */
	private void Stamps9() {
		JLabel img = new JLabel();
		jlist.add(img);
		img.setVisible(false);
		img.setLocation(260, 145);
		Icon icon = new ImageIcon("src/Stamps9.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * Load the tenth stamp image in the right place.
	 */
	private void Stamps10() {
		JLabel img = new JLabel();
		jlist.add(img);
		img.setVisible(false);
		img.setLocation(335, 145);
		Icon icon = new ImageIcon("src/Stamps10.png");
		img.setIcon(icon);
		img.setSize(50, 50);
		contentPane.add(img);
	}

	/**
	 * <p>
	 * Including two choices of Return button and Collect button. If
	 * {@code Stamps.num} less that 10, then the {@code JOptionPane} will refuse to
	 * redeem. else there will be redemption tips.
	 * 
	 * @param event JButton listener
	 */
	public void actionPerformedx(ActionEvent event) {
		Object source = event.getSource();
		if (source == buttonReturn) {
			pc.changePage(6);
		} else if (source == buttonCollect)
			if (num < 10) {
				JOptionPane.showInternalMessageDialog(contentPane, "You will get a new stamp after paying",
						"Wait for a moment", JOptionPane.INFORMATION_MESSAGE);
				ms.addStamp();
				pc.changePage(11);
			} else {
				int choose = JOptionPane.showConfirmDialog(null, "Would you want to redeem for a free meal?",
						"Wait for a moment", JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					ms.useStamp();
					ds.getPayment().setIsDiscount(1);
					ds.getPayment().setTotalPrice(0.0);
					ds.getPayment().setMethod("Stamp");
					pc.changePage(11);
				}
			}
	}

	/**
	 * Initialize {@code panelNum}. And the same number as the stamp visible.
	 * Initialize {@code buttonCollect}.
	 */
	public void init() {
		num = ms.getStamp();
		panelNum.setText(num + "/10");
		if (num < 10)
			buttonCollect.setText("Pay and collect one stamp");
		else
			buttonCollect.setText("Redeem Now!");
		for (int i = 0; i < num; i++) {
			jlist.get(i).setVisible(true);
		}
		setTimer(120);
	}
}
