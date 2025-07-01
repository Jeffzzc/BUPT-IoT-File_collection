/**
 * Title      : BaseFrame2.java
 * Description: This class is used to generate the general widgets for other GUI pages, which are print, download, back and home, and two methods for them.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixu Song
 * @version     1.0
 */
package main.GUI;

import main.Control.ButtonControl;

import javax.swing.*;
import java.awt.*;

public class BaseFrame2 extends BaseFrame{
    protected JButton back,home,print,download,query;
    protected JLabel search;
    protected JTextField jtfSearch;
    public BaseFrame2() throws AWTException {
        super();
        setting.setBounds(20,50,107,40);
        help.setBounds(150,50,77,40);
        exit.setBounds(1250,50,68,40);
        title.setBounds(470,20,600,80);
        line.setBounds(0,110,1368,3);

        print = new JButton(new ImageIcon("img/print.jpg"));
        download = new JButton(new ImageIcon("img/download.jpg"));
        query = new JButton(new ImageIcon("img/query.jpg"));
        search = new JLabel();
        jtfSearch = new JTextField();

        back = new JButton();
        back.setBounds(20,750,98,40);
        contentPane.add(back);

        home = new JButton(new ImageIcon("img/home.jpg"));
        home.setBounds(1230,750,98,40);
        contentPane.add(home);
        home.addActionListener(new ButtonControl(home.getIcon()));

    }

    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BaseFrame2 frame = new BaseFrame2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}
