package pages;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import domain.DataSource;
import domain.Order;
import service.PaymentService;
import javax.swing.border.LineBorder;

/**
 * The {@code OrderPage} class wraps a value of the primitive type
 * {@code OrderPage} in an object. And this is the <code>Order</code> page of
 * the user interface.
 * 
 * <p>
 * The entire page includes the background and buttons. Detailed display of
 * products (including ramen and add-ones) and prices purchased by users.
 * 
 * @author Shuming Fan
 * @version 5.0
 * @since JDK 11
 */
public class OrderPage extends PageBase {

	private PaymentService ps;
	private JPanel contentPane;

	/** Default progress of progress bar {@value} */
	private static int currentProgress = 80;
	private double totalPrice = 0;
	private JTable table;
	private JScrollPane scrollPane;
	private String[] columnNames = { "object", "unit price(\u00A3)", "quantity" };
	private Object[][] cells = new Object[5][3];
	private JLabel lblTotalPrice;

	/**
	 * Create the order page method.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 * 
	 * @param pc load page controller
	 * @param ds load data
	 */
	public OrderPage(DataSource ds, PageController pc) {
		super(ds, pc);
		this.ps = new PaymentService(DataSource.paymentfilename, ds);
		this.setTitle("Self-service Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton button = new JButton("Confirm");
		button.setBounds(310, 217, 90, 17);
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(205, 133, 63));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.changePage(15);
			}

		});
		contentPane.setLayout(null);
		contentPane.add(button);

		JButton buttonReturn = new JButton("Return");
		buttonReturn.setForeground(Color.WHITE);
		buttonReturn.setBackground(new Color(205, 133, 63));
		buttonReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.pastpage();
			}
		});
		buttonReturn.setBounds(213, 217, 85, 17);
		contentPane.add(buttonReturn);

		JLabel panel = new JLabel("Please check the order");
		panel.setBounds(10, 10, 250, 17);
		panel.setForeground(new Color(160, 82, 45));
		panel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(panel);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(52, 220, 104, 14);
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setValue(currentProgress);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);

		table = new JTable(cells, columnNames);
		table.setShowGrid(false);
		table.setGridColor(new Color(128, 128, 128));
		table.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		table.setBackground(new Color(255, 215, 0));
		table.setBounds(40, 40, 344, 151);
		contentPane.add(table, BorderLayout.CENTER);
		contentPane.add(table.getTableHeader(), BorderLayout.NORTH);
		table.setPreferredScrollableViewportSize(new Dimension(550, 100));

		table.getTableHeader().setBackground(new Color(255, 215, 0));
		table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));

		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBackground(new Color(255, 215, 0));
		scrollPane.getViewport().setBackground(new Color(255, 215, 0));

		lblTotalPrice = new JLabel("Total price: \u00A3" + totalPrice, JLabel.CENTER);
		lblTotalPrice.setBounds(37, 172, 353, 18);
		contentPane.add(lblTotalPrice);
		scrollPane.setSize(344, 118);
		scrollPane.setLocation(37, 44);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

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
	 * Load and initialize all items {@code columnNames} in {@code OrderPage.init}
	 * method.
	 */
	public void init() {
		setTimer(120);
		Order nowOd = ds.getOrder();
		ps.newPayment(null);
		ArrayList<Object> arr = new ArrayList<Object>();
		Object[] ramen = { "Ramen", 9.9, 1 };
		arr.add(ramen);
		totalPrice = ps.getPrice();
		int Noriadd = nowOd.getNoriadd();
		int egg = nowOd.getEggadd();
		int shoots = nowOd.getShootsadd();
		int Chashuadd = nowOd.getChashuadd();
		double noriaddPirce = ds.getConfig().getNoriaddPirce();
		double eggaddPirce = ds.getConfig().getEggaddPirce();
		double shootsaddPirce = ds.getConfig().getShootsaddPirce();
		double chashuaddPirce = ds.getConfig().getChashuaddPirce();
		if (Noriadd != 0) {
			Object[] row1 = { "Nori", noriaddPirce, Noriadd };
			arr.add(row1);
		}
		if (egg != 0) {
			Object[] row1 = { "Egg", eggaddPirce, egg };
			arr.add(row1);
		}
		if (shoots != 0) {
			Object[] row1 = { "Shoots", shootsaddPirce, shoots };
			arr.add(row1);
		}
		if (Chashuadd != 0) {
			Object[] row1 = { "Chashu", chashuaddPirce, Chashuadd };
			arr.add(row1);
		}
		for (int i = 0; i < arr.size(); i++) {
			cells[i] = (Object[]) arr.get(i);
		}

		Vector<Vector<Object>> data = new Vector<>();
		for (int i = 0; i < arr.size(); i++) {
			Vector<Object> temp = new Vector<>();
			for (int j = 0; j < 3; j++) {
				temp.add(cells[i][j]);
			}
			data.add(temp);
		}
		Vector<String> columns = new Vector<String>();
		for (int i = 0; i < 3; i++) {
			columns.add(columnNames[i]);
		}
		table.removeAll();
		DefaultTableModel dataModel = new DefaultTableModel(data, columns);
		table.setModel(dataModel);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		lblTotalPrice.setText(ds.getOrder().getInOrout() + "     Total price: \u00A3" + totalPrice);
	}
}
