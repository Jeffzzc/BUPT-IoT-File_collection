package pages;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import domain.Config;
import domain.DataSource;
import domain.Order;
import domain.Payment;
import service.OrderService;
import service.PaymentService;

/**
 * The {@code End} class wraps a value of the primitive type {@code End} in an
 * object. And this is the <code>end</code> page of the user interface.
 * Automatically jump to the homepage {@link Welcome} after a period of time.
 * 
 * <p>
 * The entire page includes the background and buttons.
 * 
 * @author Ruisi Han
 * @version 5.0
 * @since JDK 11
 */
public class End extends PageBase implements Runnable {

	private JPanel contentPane;

	/** Default time */
	int time = 10;
	boolean start = false;
	JLabel labelTime;

	private OrderService os;
	private PaymentService ps;

	/**
	 * Only for testing
	 * @param args no need to set args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					End frame = new End();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Countdown thread.
	 */
	public void run() {
		while (start) {
			time--;
			labelTime.setText("" + time + " sec");
			labelTime.updateUI();
			if (time == 0) {
				pc.nowstop();
				pc.nostop = false;
				dispose();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public End() {
		this(null, null);
	}

	/**
	 * Create the end frame.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public End(DataSource ds, PageController pc) {
		super(ds, pc);
		ps = new PaymentService(DataSource.paymentfilename, ds);
		os = new OrderService(ds, DataSource.orderfilename);
		this.setTitle("End");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel panel = new JLabel("Your bill is being printed, please note to check.");
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.setBounds(15, 15, 350, 17);
		contentPane.add(panel);

		JLabel panel1 = new JLabel("Thank you for your patronage!");
		panel1.setForeground(new Color(160, 82, 45));
		panel1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel1.setBounds(15, 30, 350, 17);
		contentPane.add(panel1);

		labelTime = new JLabel("" + time + " sec");
		labelTime.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelTime.setForeground(new Color(220, 20, 60));
		labelTime.setBounds(370, 60, 60, 17);
		contentPane.add(labelTime);

		JButton button = new JButton("Return");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.nowstop();
				pc.nostop = false;
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(205, 133, 63));
		button.setBounds(280, 60, 80, 17);
		contentPane.add(button);

		JLabel img = new JLabel();
		img.setLocation(-90, 47);
		Icon icon = new ImageIcon("src/Loading.gif");
		img.setIcon(icon);
		img.setSize(580, 300);
		contentPane.add(img);

	}

	/**
	 * Load and initialize all choose.
	 */
	public void init() {
		try {
			Config conf=ds.getConfig();
			Order od = ds.getOrder();
			String orderId = od.getOrderID();
			String Soup = conf.getName("Soup", od.getSoup());
			String Noodles = conf.getName("Noodles", od.getNoodles());
			String Springonion = conf.getName("Spring onion", od.getSpringonion());
			String Nori = conf.changeBoolName(od.getNori());
			String Chashu = conf.changeBoolName(od.getChashu());
			String Boiledegg = conf.changeBoolName(od.getBoiledegg());
			String Noriadd = od.getNoriadd() + "";
			String egg = od.getEggadd() + "";
			String shoots = od.getShootsadd() + "";
			String Chashuadd = od.getChashuadd() + "";
			String spiciness = od.getSpiciness() + "";
			String inout = od.getInOrout();
			Payment py = ds.getPayment();
			String price = py.getTotalPrice() + "";
			String discount = py.getIsDiscount() == 0 ? "No" : "Yes";
			String method = py.getMethod();
			Date dt = new Date();
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
			String ctime = df2.format(py.getTime());
			String ptime = df2.format(dt);
			String memstr = "";
			if (ds.getMember() != null) {
				memstr += "MemberID: " + ds.getMember().getMemberID() + "\n";
				memstr += "Stamps: " + ds.getMember().getStamp() + "\n";
			} else {
				memstr += "Your are not our loyalty member! Join us! Get free meals!";
			}
			String[] content = { "-----------------Order-----------------", "Order ID: " + orderId, "Soup: " + Soup,
					"Noodles: " + Noodles, "Spring onion: " + Springonion, "Nori: " + Nori, "Chashu: " + Chashu,
					"Boiled egg: " + Boiledegg, "Extra noris: " + Noriadd, "Extra eggs: " + egg,
					"Bamboo shoots: " + shoots, "Extra chashu: " + Chashuadd, "Spiciness: Level" + spiciness,
					"In or out: " + inout, "-----------------Payment-----------------", "Price: " + price,
					"Method: " + method, "Discount: " + discount, "Created Time: " + ctime, "Payed Time: " + ptime,
					"-----------Member-----------------", memstr };

			File file = new File("ticket" + orderId + ".txt");
			OutputStream out = new FileOutputStream(file, true);
			for (int i = 0; i < content.length; i++) {
				out.write(content[i].getBytes());
				out.write('\n');
			}
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		os.saveOrder();
		ps.savePayment();
		startTime();
	}

	/**
	 * start time.
	 */
	public void startTime() {
		if (start) {
			return;
		} else {
			start = true;
			new Thread(this).start();
		}
	}
}
