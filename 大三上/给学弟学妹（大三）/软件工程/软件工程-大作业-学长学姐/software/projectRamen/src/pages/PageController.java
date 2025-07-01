package pages;

import java.util.ArrayList;
import javax.swing.JFrame;
import domain.DataSource;

/**
 * The {@code PageController} class wraps a value of the primitive type
 * {@code PageController} in an object. And this is the
 * <code>PageController</code> page of the user interface.
 * 
 * <p>
 * The class automatically loads all pages and renders them in order.
 * 
 * @author Shuming Fan
 * @version 5.0
 * @since JDK 11
 */
public class PageController {
	public int nowPage;
	private DataSource ds;
	ArrayList<PageBase> pageArray = new ArrayList<PageBase>();
	public volatile boolean nostop;

	/**
	 * Create the page controller.
	 * <p>
	 * Need to load relevant data information before page visualization.
	 *
	 * @param ds load data
	 */
	public PageController(DataSource ds) {
		nostop = true;
		this.ds = ds;
		pageArray.add(new Welcome(ds, this));// 0
		pageArray.add(new FixedPrice(ds, this));// 1
		pageArray.add(new AddOns(ds, this));// 2
		pageArray.add(new Display(ds, this));// 3
		pageArray.add(new InStoreOrTakeOut(ds, this));// 4
		pageArray.add(new OrderPage(ds, this));// 5
		pageArray.add(new PaymentPage(ds, this));// 6
		pageArray.add(new Login(ds, this));// 7
		pageArray.add(new Register(ds, this));// 8
		pageArray.add(new SixDigitsCode(ds, this));// 9
		pageArray.add(new Stamps(ds, this));// 10
		pageArray.add(new End(ds, this));// 11
		pageArray.add(new FixedPriceInstruction(ds, this));// 12
		pageArray.add(new AddonsInstruction(ds, this));// 13
		pageArray.add(new ChoiceInstruction(ds, this));// 14
		pageArray.add(new Choice(ds, this));// 15
		nowPage = 0;
		changePage(0);
	}

	/**
	 * Add pages to pagecontroller
	 *
	 *
	 * @param e the page need to be added
	 */
	public void addPage(PageBase e) {
		pageArray.add(e);
	}

	/**
	 * Jumps to the specified page
	 * <p>
	 * This method initializes the page automatically
	 *
	 * @param pageNumber page number
	 */
	public void changePage(int pageNumber) {
		pageArray.get(nowPage).setVisible(false);
		pageArray.get(nowPage).clearwork();
		nowPage = pageNumber;
		pageArray.get(nowPage).init();
		pageArray.get(nowPage).resetPos();
		pageArray.get(nowPage).setVisible(true);
	}

	/**
	 * Get the current page number
	 *
	 * @return now current page number
	 */
	public int getNowPage() {
		return nowPage;
	}

	/**
	 * Get the page from the controller.
	 *
	 * @param pageNum the page number
	 * @return page the page
	 */
	public JFrame getPage(int pageNum) {
		return pageArray.get(pageNum);
	}

	/**
	 * Switch to the next page
	 */
	public void nextpage() {
		changePage(nowPage + 1);
	}

	/**
	 * Switch to the past page
	 */
	public void pastpage() {
		changePage(nowPage - 1);
	}

	/**
	 * Close all pages
	 */
	public void nowstop() {
		pageArray.get(nowPage).dispose();
		nostop = false;
	}

}
