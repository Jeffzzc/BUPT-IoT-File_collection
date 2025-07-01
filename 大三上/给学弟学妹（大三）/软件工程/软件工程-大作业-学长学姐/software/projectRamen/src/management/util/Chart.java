package management.util;

import management.domain.*;
import management.service.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

/**
 * This class is used to generate last week sale chart.
 * 
 * @author Xiaotian Wang
 *
 */
public class Chart {
	
	private String chart_path = "";
	
	/**
	 * Initialize Chart
	 * 
	 * @param chart_path chart save path
	 */
	public Chart(String chart_path) {
		this.chart_path = chart_path;
	}
	
	
	/**
	 * Generate bar chart and save it.
	 */
	 public void makeBarChart() {

		 double[][] data = getLastWeekSale(); 
		 String[] rowKeys = { "Sale (\u00A3)" };
		 String[] columnKeys = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		 CategoryDataset dataset = getBarData(data, rowKeys, columnKeys);
		 createBarChart(dataset, "", "", "Last Week Sale", "Last Week Sale.png");
		 }
		 
	 /**
	  * Get last week sales data 
	  *
	  * @return last week sales data (a {@code double[][]})
	  */
	 private double[][] getLastWeekSale() {
		 StatsService ss = new StatsService();
		 ss.doStats("orderlist.csv", "paymentlist.csv",7);
		 return ss.getStats().getLastWeekSale();
		 
	 }
		 
	 /**
	  * 
	  *
	  *@param dataset
	  *            
	  * @param xName
	  *            
	  * @param yName
	  *           
	  * @param chartTitle
	  *           
	  * @param charName
	  *            
	  * @return chartName
	  */
	 
	 /**
	  * Create barchart 
	  * 
	  * 
	  * @param dataset dataset
	  * @param xName x axis label
	  * @param yName y axis label
	  * @param chartTitle chart title
	  * @param charName chart file name
	  * @return chart file name if successful; null if not
	  */
	private String createBarChart(CategoryDataset dataset, String xName,
			   String yName, String chartTitle, String charName) {
			  JFreeChart chart = ChartFactory.createBarChart(chartTitle, 
			    xName, 
			    yName, 
			    dataset, 
			    PlotOrientation.VERTICAL, 
			    true, 
			    false, 
			    false 
			    );
			  Font labelFont = new Font("Arial", Font.PLAIN, 11);
			  chart.setTextAntiAlias(false);
			  chart.setBackgroundPaint(Color.white);
			  
			  // create plot
			  CategoryPlot plot = chart.getCategoryPlot();
			 
			  
			  plot.setRangeGridlinesVisible(true);
			  plot.setRangeGridlinePaint(Color.gray);
			  
			  
			  NumberAxis vn = (NumberAxis) plot.getRangeAxis();
			  
			  DecimalFormat df = new DecimalFormat("#0.00");
			  vn.setNumberFormatOverride(df); 
			  
			  
			  // x axis
			  CategoryAxis domainAxis = plot.getDomainAxis();
			  domainAxis.setLabelFont(labelFont);
			  domainAxis.setTickLabelFont(labelFont);
			 
			  domainAxis.setMaximumCategoryLabelWidthRatio(3.0f);
			  
			  domainAxis.setCategoryMargin(0.5);
			  domainAxis.setLowerMargin(0.1);
			  domainAxis.setUpperMargin(0.1);
	
			  plot.setDomainAxis(domainAxis);
			  plot.setBackgroundPaint(new Color(255, 255, 255));

			  // y axis
			  ValueAxis rangeAxis = plot.getRangeAxis();
			  rangeAxis.setLabelFont(labelFont);
			  rangeAxis.setTickLabelFont(labelFont);
			  
			  rangeAxis.setUpperMargin(0.15);
			  rangeAxis.setLowerMargin(0.15);
			  plot.setRangeAxis(rangeAxis);

			  
			  // bar renderer
			  BarRenderer renderer = new BarRenderer();
			  
			  renderer.setMaximumBarWidth(0.05);
			  renderer.setMinimumBarLength(0.2);
			  renderer.setBaseOutlinePaint(Color.BLACK);
			  renderer.setDrawBarOutline(true);

			  //bar color
			  renderer.setSeriesPaint(0, new Color(0, 255, 128));

	
			  renderer.setItemMargin(0.15);
			  renderer.setIncludeBaseInRange(true);
			  renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			  renderer.setBaseItemLabelsVisible(true);

			  plot.setRenderer(renderer);
			
			  plot.setForegroundAlpha(1.0f);

			  FileOutputStream fos_jpg = null;
			  try {
			   isChartPathExist(chart_path);
			   String chartName = chart_path + charName;
			   fos_jpg = new FileOutputStream(chartName);
			   ChartUtilities.writeChartAsPNG(fos_jpg, chart, 500, 500, true, 10);
			   return chartName;
			  } catch (Exception e) {
			   e.printStackTrace();
			   return null;
			  } finally {
			   try {
			    fos_jpg.close();
			   } catch (Exception e) {
			    e.printStackTrace();
			   }
			  }
			 }
	 
	 /**
	  * Transform raw date into chart dataset
	  * 
	  * @param data raw data
	  * @param rowKeys row data key
	  * @param columnKeys column data key
	  * @return chart dataset
	  */
	 private CategoryDataset getBarData(double[][] data, String[] rowKeys,
			   String[] columnKeys) {
			  return DatasetUtilities
			    .createCategoryDataset(rowKeys, columnKeys, data);

			 }
	 
	 /**
	  * Check if the chart path exists, make it if not 
	  * 
	  * @param chartPath chart file path
	  */
	 private void isChartPathExist(String chartPath) {
		  File file = new File(chartPath);
		  if (!file.exists()) {
		   file.mkdirs();
		  }
		 }


}
