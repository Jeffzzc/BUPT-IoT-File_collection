/**
 * Title      : Honors.java
 * Description: This class is used to draw the Honors page.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixuan Wang
 * @version     4.0
 */
package main.GUI;

import main.Control.ButtonControl;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Honors extends BaseFrameAward{
    public static JFrame frameHonor;
    public Honors() throws AWTException {
        super();
        title.setText("Honors");
        search.setText("Honors name");
        back.setIcon(new ImageIcon("img/back3.jpg"));
        back.addActionListener(new ButtonControl(back.getIcon()));

        frameHonor=new JFrame("Learning Journey");
        frameHonor.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameHonor.setBounds(0,0,1368,864);
        frameHonor.setVisible(false);

        panel.setBounds(20, 220, 1300, 500);
        tableData = new String[][] {{"National Scholarships","2020/21"},
        {"University first-class scholarship","2021/22"},
        {"Merit Student","2021/22"},};
        tableColumn = new String[] {"Scholarship name", "Get time"};
        Graph = new JTable(tableData,tableColumn);
        Graph.setBounds(20, 220, 1300, 500);
        
        JTableHeader graphHeader = Graph.getTableHeader();
        graphHeader.setFont(new Font("Times New Roman", Font.BOLD, 16));
        graphHeader.setForeground(new Color(0, 0, 0));
        Graph.setRowHeight(50);
        Graph.setFont(new Font("Times New Roman", Font.BOLD, 16));
        Graph.setPreferredScrollableViewportSize(new Dimension(1,1));
        JScrollPane scrollPane = new JScrollPane(Graph);
        panel.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panel);
        jtfSearch.setText("Scholarship name");

        ButtonControl b = new ButtonControl();
        Graph = b.QueryFunction(Graph,query,serialNum,jtfSearch,tableData,tableColumn);

        print.setIcon(new ImageIcon("img/HonorsPrint.jpg"));
        print.addActionListener(new ButtonControl(print.getIcon()));
        download.setIcon(new ImageIcon("img/HonorsDownload.jpg"));
        download.addActionListener(new ButtonControl(download.getIcon()));

        //Create the table renderer
        MyTableCellRenderer renderer = new MyTableCellRenderer();

        for (int i = 0; i < tableColumn.length; i++) {
            TableColumn column = Graph.getColumn(tableColumn[i]);
            column.setCellRenderer(renderer);
        }

        background.setIcon(new ImageIcon("img/background3.jpg"));
        contentPane.add(background);
        frameHonor.add(contentPane);

        bottomButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dialog.setBounds(500, 300, 400, 200);
                JPanel panel = new JPanel(new GridLayout(4, 1));

                JLabel title = new JLabel("Honors Page");
                JLabel conetent_1 = new JLabel("Provided functions: ");
                JLabel conetent_2 = new JLabel(" 1. show the honors you have got");
                panel.add(title);
                panel.add(conetent_1);
                panel.add(conetent_2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dialog.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

        });
    }


/*    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Honors frame = new Honors();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}
