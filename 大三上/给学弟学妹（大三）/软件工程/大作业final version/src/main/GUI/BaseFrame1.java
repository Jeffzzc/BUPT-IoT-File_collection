/**
 * Title      : BaseFrame1.java
 * Description: This class is used to generate the general widgets for login and navigation GUI pages, which are the icon and name of BUTP and QM.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixu Song
 * @version     1.0
 */

package main.GUI;

import javax.swing.*;
import java.awt.*;

public class BaseFrame1 extends BaseFrame{
    protected JLabel label1,label2,label3,label4;
    public BaseFrame1() throws AWTException {
        super();
        label1 = new JLabel(new ImageIcon("img/bupt.png"));
        label1.setBounds(20,20,80,80);
        contentPane.add(label1);

        label2 = new JLabel("Beijing University of Posts and Telecommunications");
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label2.setBounds(110,60,500,20);
        contentPane.add(label2);

        label3 = new JLabel(new ImageIcon("img/qm.png"));
        label3.setBounds(20,120,80,80);
        contentPane.add(label3);

        label4 = new JLabel("Queen Mary University of London");
        label4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label4.setBounds(110,160,500,20);
        contentPane.add(label4);
    }
    
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    BaseFrame1 frame = new BaseFrame1();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
}
