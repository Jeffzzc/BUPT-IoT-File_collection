/**
 * Title      : Competition.java
 * Description: This class is used to draw the Competition page.
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


public class Competition extends BaseFrameAward{
    public static JFrame frameCompetition;
    private JScrollPane scrollPane;
    private JTableHeader graphHeader;
    public Competition() throws AWTException {
        super();
        title.setText("Competition");
        search.setText("Competition name");
        back.setIcon(new ImageIcon("img/back3.jpg"));
        back.addActionListener(new ButtonControl(back.getIcon()));

        frameCompetition=new JFrame("Learning Journey");
        frameCompetition.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameCompetition.setBounds(0,0,1368,864);
        frameCompetition.setVisible(false);

        panel.setBounds(20, 220, 1300, 500);
        tableData = new String[][]{{"Mathematical Contest In Modeling", "Honorable Mention", "2021"},
                {"The Chinese English Competitions", "The second prize", "2022"}};
        tableColumn = new String[]{"Competition name", "Award", "Get time"};
        Graph = new JTable(tableData,tableColumn);
        Graph.setBounds(20, 220, 1300, 500);

        graphHeader = Graph.getTableHeader();
        graphHeader.setFont(new Font("Times New Roman", Font.BOLD, 15));
        graphHeader.setForeground(PURPLE);
        Graph.setRowHeight(45);
        Graph.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        Graph.setPreferredScrollableViewportSize(new Dimension(1,1));
        scrollPane = new JScrollPane(Graph);
        panel.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(panel);
        jtfSearch.setText("Competition name");
        ButtonControl b = new ButtonControl();
        Graph = b.QueryFunction(Graph,query,serialNum,jtfSearch,tableData,tableColumn);

        //Create the table renderer
        BaseFrameAward.MyTableCellRenderer renderer = new BaseFrameAward.MyTableCellRenderer();

        for (int i = 0; i < tableColumn.length; i++) {
            TableColumn column = Graph.getColumn(tableColumn[i]);
            column.setCellRenderer(renderer);
        }

        print.setIcon(new ImageIcon("img/CompetitionPrint.jpg"));
        print.addActionListener(new ButtonControl(print.getIcon()));
        download.setIcon(new ImageIcon("img/CompetitionDownload.jpg"));
        download.addActionListener(new ButtonControl(download.getIcon()));

        background.setIcon(new ImageIcon("img/background3.jpg"));
        contentPane.add(background);
        frameCompetition.add(contentPane);

        bottomButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dialog.setBounds(500, 300, 400, 200);
                JPanel panel = new JPanel(new GridLayout(4, 1));

                JLabel title = new JLabel("Competition Page");
                JLabel conetent_1 = new JLabel("Provided functions: ");
                JLabel conetent_2 = new JLabel("1. check the competitions you have won.");
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

/*    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Competition frame = new Competition();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}
