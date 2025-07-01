/**
 * Title      : BaseFrameAward.java
 * Description: This class is used to generate the competition, certificates and honors page.
 * Copyright  : Copyright (c) 2023/5/13
 * @author      Zixuan Wang & Zhendong Cui
 * @version     3.0
 */
package main.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class BaseFrameAward extends BaseFrame2{
    protected JPanel panel;
    protected JTable Graph;
    protected JButton add;
    protected String[][] tableData;
    protected int serialNum;
    protected String[] tableColumn;
    public BaseFrameAward() throws AWTException {
        super();

        print.setFont(new Font("Times New Roman", Font.BOLD, 25));
        print.setBounds(20, 140, 84, 40);
        contentPane.add(print);

        download.setFont(new Font("Times New Roman", Font.BOLD, 25));
        download.setBounds(150, 140, 122, 40);
        contentPane.add(download);

        search.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        search.setBounds(750, 140, 200, 40);
        search.setHorizontalAlignment(JLabel.RIGHT);
        contentPane.add(search);

        jtfSearch.setBounds(960, 140, 250, 40);
        contentPane.add(jtfSearch);

        query.setFont(new Font("Times New Roman", Font.BOLD, 25));
        query.setBounds(1230, 140, 89, 40);
        contentPane.add(query);

        panel = new JPanel(new BorderLayout());
    }

    public static class MyTableCellRenderer extends DefaultTableCellRenderer {
        /**
         *Description: This is the override for the JTable's background color.
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (row % 2 == 0) {
                setBackground(LIGHTBLUE);
            } else {
                setBackground(GREY);
            }
            setHorizontalAlignment(SwingConstants.CENTER);
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
/*    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BaseFrameAward frame = new BaseFrameAward();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}
