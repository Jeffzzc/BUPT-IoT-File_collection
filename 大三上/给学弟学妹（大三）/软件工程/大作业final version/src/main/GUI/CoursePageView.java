/**
 * Title      : CoursePageView.java
 * Description: This class is used to draw the Course list page.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixuan Wang & Zhendong Cui
 * @version     5.0
 */
package main.GUI;

import main.Control.ButtonControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CoursePageView extends BaseFrame2{
    private JButton ip,java,c,media,dsp,math;
    private JComboBox cmb;
    public static JFrame frameCourse;
    public CoursePageView() throws AWTException {
        super();
        title.setText("Course Details");
        back.setIcon(new ImageIcon("img/back.jpg"));
        back.addActionListener(new ButtonControl(back.getIcon()));
        frameCourse=new JFrame("Learning Journey");
        frameCourse.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameCourse.setBounds(0,0,1368,864);
        frameCourse.setVisible(false);

        //Add Module button
        ip = new JButton(new ImageIcon("img/ip.jpg"));
        ip.setBounds(150,220,300,220);
        contentPane.add(ip);
        ip.addActionListener(new ButtonControl(ip.getIcon()));

        java = new JButton(new ImageIcon("img/java.jpg"));
        java.setBounds(520,220,300,220);
        contentPane.add(java);
        java.addActionListener(new ButtonControl(java.getIcon()));

        c = new JButton(new ImageIcon("img/c.jpg"));
        c.setBounds(890,220,300,220);
        contentPane.add(c);
        c.addActionListener(new ButtonControl(c.getIcon()));

        media = new JButton(new ImageIcon("img/media.jpg"));
        media.setBounds(150,500,300,220);
        contentPane.add(media);
        media.addActionListener(new ButtonControl(media.getIcon()));

        dsp = new JButton(new ImageIcon("img/dsp.jpg"));
        dsp.setBounds(520,500,300,220);
        contentPane.add(dsp);
        dsp.addActionListener(new ButtonControl(dsp.getIcon()));

        math = new JButton(new ImageIcon("img/math.jpg"));
        math.setBounds(890,500,300,220);
        contentPane.add(math);
        math.addActionListener(new ButtonControl(math.getIcon()));

        background.setIcon(new ImageIcon("img/background2.jpg"));
        contentPane.add(background);
        frameCourse.add(contentPane);

        bottomButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                dialog.setBounds(500, 300, 400, 200);
                JPanel panel = new JPanel(new GridLayout(4, 1));

                JLabel title = new JLabel("Course Page");
                JLabel conetent_1 = new JLabel("Provided functions: ");
                JLabel conetent_2 = new JLabel("1. view the course schedule. 2. sort by keywords.");
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
//                    CoursePageView frame = new CoursePageView();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
