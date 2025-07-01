/**
 * Title      : Certificates.java
 * Description: This class is used to draw the Certificates page.
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

public class Certificates extends BaseFrameAward{
    public static JFrame frameCertificate;
    public Certificates() throws AWTException {
        super();
        title.setText("Certificates");
        search.setText("Certificates name");
        back.setIcon(new ImageIcon("img/back3.jpg"));
        back.addActionListener(new ButtonControl(back.getIcon()));

        frameCertificate=new JFrame("Learning Journey");
        frameCertificate.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameCertificate.setBounds(0,0,1368,864);
        frameCertificate.setVisible(false);

        panel.setBounds(20, 220, 1200, 500);
        tableData = new String[][]{{"CET-4 (College English Test Band 4)","550/PASS"},
        {"CET-6 (College English Test Band 6)","560/PASS"},
        {"IELTS (International English Language Testing System)","7"},
        {"National Computer Rank Examination Grade 2","80/PASS"},};
        tableColumn = new String[]{"Competition name", "Grade&State"};
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
        jtfSearch.setText("Competition name");
        ButtonControl b = new ButtonControl();
        Graph = b.QueryFunction(Graph,query,serialNum,jtfSearch,tableData,tableColumn);

        //Create the table renderer
        MyTableCellRenderer renderer = new MyTableCellRenderer();

        for (int i = 0; i < tableColumn.length; i++) {
            TableColumn column = Graph.getColumn(tableColumn[i]);
            column.setCellRenderer(renderer);
        }
        background.setIcon(new ImageIcon("img/background3.jpg"));
        contentPane.add(background);
        print.setIcon(new ImageIcon("img/CertificatesPrint.jpg"));
        print.addActionListener(new ButtonControl(print.getIcon()));
        download.setIcon(new ImageIcon("img/CertificatesDownload.jpg"));
        download.addActionListener(new ButtonControl(download.getIcon()));

        frameCertificate.add(contentPane);

        bottomButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dialog.setBounds(500, 300, 400, 200);
                JPanel panel = new JPanel(new GridLayout(4, 1));

                JLabel title = new JLabel("Certificate Page");
                JLabel conetent_1 = new JLabel("Provided functions: ");
                JLabel conetent_2 = new JLabel("1. check the certificate you have got");
                panel.add(title);
                panel.add(conetent_1);
                panel.add(conetent_2);

                dialog.add(panel);
                dialog.setVisible(true);
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

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    Certificates frame = new Certificates();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
