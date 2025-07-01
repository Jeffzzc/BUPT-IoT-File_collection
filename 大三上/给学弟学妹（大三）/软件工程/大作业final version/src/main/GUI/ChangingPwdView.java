/**
 * Title      : ChangingPwdView.java
 * Description: This class is used to generate the password changing page for you to change your password.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixu Song
 * @version     4.0
 */
package main.GUI;

import main.Control.ButtonControl;

import javax.swing.*;
import java.awt.*;

public class ChangingPwdView extends JFrame{
    public static JFrame frameChanging;
    public static JLabel label;
    private JLabel label1,label4,label2,label3,background;
    public static JTextField jtf3;
    public static JTextField jtf1;
    public static JTextField jtf2;
    protected JButton button1,button2;
    public JPanel panel;
    /**
     * Description: Create the changing password frame.
     */
    public ChangingPwdView() throws AWTException {
        frameChanging=new JFrame("Changing password");
        frameChanging.setBounds(520,220,400, 350);
        frameChanging.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameChanging.setVisible(false);

        panel = new JPanel();
        panel.setLayout(null);

        label1= new JLabel("Changing Password");
        label1.setBounds(0,20,400,45);
        label1.setFont(new Font("Times New Roman", Font.BOLD, 40));
        label1.setForeground(BaseFrame.PURPLE);
        label1.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label1);

        label = new JLabel("New password aren't the same as check password!");
        label.setFont(new Font("Times New Roman", Font.BOLD, 17));
        label.setForeground(Color.RED);
        label.setBounds(0, 75, 400, 20);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVisible(false);
        panel.add(label);

        label4 = new JLabel("Account number:");
        label4.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label4.setBounds(10, 110, 190, 30);
        panel.add(label4);

        jtf3 = new JTextField();
        jtf3.setBounds(205, 110, 175, 30);
        panel.add(jtf3);

        label2 = new JLabel("New password:");
        label2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label2.setBounds(10, 160, 190, 30);
        panel.add(label2);

        jtf1 = new JTextField();
        jtf1.setBounds(205, 160, 175, 30);
        panel.add(jtf1);

        label3 = new JLabel("Check password:");
        label3.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label3.setBounds(10, 210, 200, 30);
        panel.add(label3);

        jtf2 = new JTextField();
        jtf2.setBounds(205, 210, 175, 30);
        panel.add(jtf2);

        button1= new JButton("Confirm");
        button1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        button1.setBounds(10, 260, 150, 30);
        panel.add(button1);

        button2= new JButton("Cancel");
        button2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        button2.setBounds(228, 260, 150, 30);
        panel.add(button2);

        background = new JLabel(new ImageIcon("img/background4.jpg"));
        background.setBounds(0,-20,1368,840);
        panel.add(background);
        frameChanging.add(panel);

        button1.addActionListener(new ButtonControl());
        button2.addActionListener(new ButtonControl());
    }
/*    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ChangingPwdView frame1 = new ChangingPwdView();
                    //frame1.setVisible(true);
                    //frame1.requestFocusInWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}
