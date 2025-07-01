package pages;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import datautil.FrameTimer;
import domain.DataSource;

/**
 * The {@code PageBase} class wraps a value of the primitive type
 * {@code PageBase} in an object.
 * 
 * @author Tianyi Liu
 * @version 5.0
 * @since JDK 11
 */
public class PageBase extends JFrame {
	protected DataSource ds;
	protected PageController pc;
	protected JLabel timerLabel;
	protected Timer nowt;

	/**
	 * Store data source and page controller in variables in the class.
	 * 
	 * @param ds load data
	 * @param pc load page controller
	 */
	public PageBase(DataSource ds, PageController pc) {
		this.ds = ds;
		this.pc = pc;
	}

	/**
	 * Set the length and width of the GUI.
	 */
	public void resetPos() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimension = tk.getScreenSize();
		int width = dimension.width;
		int height = dimension.height;
		int x = (width - 450) / 2;
		int y = (height - 300) / 2;
		this.setLocation(x, y);
	}

	/**
	 * Initialize the {@code PageBase.init} method.
	 */
	public void init() {

	}

	/**
	 * Read the value of data source from DataSource method.
	 * 
	 * @return data source
	 */
	public DataSource getDs() {
		return ds;
	}

	/**
	 * Store data source in variables in the class.
	 * 
	 * @param ds data source
	 */
	public void setDs(DataSource ds) {
		this.ds = ds;
	}

	/**
	 * Read the value of page controller from PageController method.
	 * 
	 * @return page controller
	 */
	public PageController getPc() {
		return pc;
	}

	/**
	 * Store page controller in variables in the class.
	 * 
	 * @param pc page controller
	 */
	public void setPc(PageController pc) {
		this.pc = pc;
	}

	/**
	 * Start even and jump to the page when the countdown is zero.
	 * 
	 * @param time Countdown
	 */
	public void setTimer(int time) {
		if (timerLabel == null) {
			timerLabel = new JLabel(120 + "s");
			timerLabel.setBounds(10, 236, 58, 15);
			this.getContentPane().add(timerLabel);
		}
		timerLabel.setText(time + "s");
		FrameTimer ft = new FrameTimer(time, timerLabel, pc);
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			public void eventDispatched(AWTEvent event) {
				MouseEvent mv = (MouseEvent) event;
		        getContentPane().validate(); 
		        getContentPane().repaint(); 
				if (mv.getClickCount() > 0) {
					if (!(event.getSource() instanceof Component)) {
						return;
					}
					ft.mouseActionPerformed();
				}
			}
		}, AWTEvent.MOUSE_EVENT_MASK);
		nowt = new Timer(1000, ft.timeTaskPerformer);
		nowt.start();

	}

	/**
	 * Call stop method under conditions.
	 */
	public void clearwork() {
		if (nowt != null) {
			nowt.stop();
		}
	}
}
