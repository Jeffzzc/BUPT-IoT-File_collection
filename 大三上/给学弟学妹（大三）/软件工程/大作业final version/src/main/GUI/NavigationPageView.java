/**
 * Title      : NavigationPageView.java
 * Description: This class is used to draw the main page after student login.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixuan Wang & Zhendong Cui
 * @version     4.0
 */
package main.GUI;

import main.Control.ButtonControl;
import main.Entity.Student;
import main.Read.DataReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NavigationPageView extends BaseFrame1{
    public static JFrame frameNavi;
    private JButton grade,courseInfo,schedule,awards;
    private JLabel studentName,studentID,studentClass,studentPicture;
    public NavigationPageView() throws AWTException {
        super();
        frameNavi=new JFrame("Learning Journey");
        frameNavi.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameNavi.setBounds(0,0,1368,864);
        frameNavi.setVisible(false);
        DataReader.initialize();
        Student student = DataReader.select(ButtonControl.currentStudent);
        title.setText("Student System");
        studentPicture = new JLabel(new ImageIcon("img/me.jpg"));
        studentPicture.setBounds(50,320,300,300);
        contentPane.add(studentPicture);
        studentName = new JLabel("  Name:"+student.studentName);
        studentName.setOpaque(true);
        studentName.setBackground(Color.WHITE);
        studentName.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        studentName.setFont(new Font("Times New Roman", Font.BOLD, 20));
        studentName.setBounds(50, 640, 300, 40);
        contentPane.add(studentName);

        studentID = new JLabel("  ID:"+student.studentID);
        studentID.setOpaque(true);
        studentID.setBackground(Color.WHITE);
        studentID.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        studentID.setFont(new Font("Times New Roman", Font.BOLD, 20));
        studentID.setBounds(50, 690, 300, 40);
        contentPane.add(studentID);

        studentClass = new JLabel("  Class: 2020215101");
        studentClass.setOpaque(true);
        studentClass.setBackground(Color.WHITE);
        studentClass.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        studentClass.setFont(new Font("Times New Roman", Font.BOLD, 20));
        studentClass.setBounds(50, 740, 300, 40);
        contentPane.add(studentClass);

        grade = new JButton(new ImageIcon("img/statistic.jpg"));
        grade.setBounds(550, 320, 300, 200);
        contentPane.add(grade);
        grade.addActionListener(new ButtonControl(grade.getIcon()));

        schedule = new JButton(new ImageIcon("img/syllabus.jpg"));
        schedule.setBounds(950, 320, 300, 200);
        contentPane.add(schedule);
        schedule.addActionListener(new ButtonControl(schedule.getIcon()));

        courseInfo = new JButton(new ImageIcon("img/homework.jpg"));
        courseInfo.setBounds(550, 560, 300, 200);
        contentPane.add(courseInfo);
        courseInfo.addActionListener(new ButtonControl(courseInfo.getIcon()));

        awards = new JButton(new ImageIcon("img/news.jpg"));
        awards.setBounds(950, 560, 300, 200);
        contentPane.add(awards);
        awards.addActionListener(new ButtonControl(awards.getIcon()));

        background.setIcon(new ImageIcon("img/background4.jpg"));
        contentPane.add(background);
        frameNavi.add(contentPane);

        bottomButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dialog.setBounds(500, 300, 400, 200);
                JPanel panel = new JPanel(new GridLayout(4, 1));

                JLabel title = new JLabel("Navigation Page");
                JLabel conetent_1 = new JLabel("Provided functions: ");
                JLabel conetent_2 = new JLabel("1. navigate to other pages.");
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
    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NavigationPageView frame = new NavigationPageView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}
