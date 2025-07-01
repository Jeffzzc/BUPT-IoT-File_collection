/**
 * Title      : LoginPageView.java
 * Description: This class is used to generate the first page you run the programme, which is used to check your identity.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixu Song
 * @version     4.0
 */
package main.GUI;

import main.Control.ButtonControl;

import java.awt.*;
import javax.swing.*;

public class LoginPageView extends BaseFrame1 {
    public static JFrame frameLogin;
    public static JTextField jtfStudentID;
    public static JTextField jtfPwd;
    public static JLabel label9;
    private JLabel studentID,password,label5,label6,label7,label8;
    private JButton login,button;
    /**
     * Description: Create the login frame.
     */
    public LoginPageView() throws AWTException {
        super();
        frameLogin=new JFrame("Learning Journey");
        frameLogin.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameLogin.setBounds(0,0,1368,864);
        frameLogin.setVisible(false);

        label5= new JLabel("This page is restrict.");
        label5.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label5.setBounds(590, 320, 500, 25);
        contentPane.add(label5);

        label6= new JLabel("Please enter your student ID number and password below to log in.");
        label6.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label6.setBounds(330, 350, 1000, 25);
        contentPane.add(label6);

        label9 = new JLabel("Your ID and password don't match!!!");
        label9.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label9.setForeground(Color.RED);
        label9.setBounds(520, 430, 500, 30);
        label9.setVisible(false);
        contentPane.add(label9);

        studentID = new JLabel("Student ID:");
        studentID.setFont(new Font("Times New Roman", Font.BOLD, 40));
        studentID.setBounds(420, 470, 300, 40);
        contentPane.add(studentID);

        jtfStudentID = new JTextField();
        jtfStudentID.setBounds(650, 470, 300, 40);
        contentPane.add(jtfStudentID);

        password = new JLabel("Password:");
        password.setFont(new Font("Times New Roman", Font.BOLD, 40));
        password.setBounds(420, 550, 300, 40);
        contentPane.add(password);

        jtfPwd = new JTextField();
        jtfPwd.setBounds(650, 550, 300, 40);
        contentPane.add(jtfPwd);

        login = new JButton("Login");
        login.setFont(new Font("Times New Roman", Font.BOLD, 30));
        login.setBounds(630, 640, 150, 50);
        contentPane.add(login);
        login.addActionListener(new ButtonControl());

        label7= new JLabel("Forget or want to change your password?");
        label7.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label7.setForeground(Color.RED);
        label7.setBounds(410, 720, 450, 30);
        contentPane.add(label7);

        button= new JButton("Click here");
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Times New Roman", Font.BOLD, 25));
        button.setForeground(Color.BLUE);
        button.setBounds(830, 720, 200, 30);
        contentPane.add(button);
        button.addActionListener(new ButtonControl());

        label8= new JLabel("All rights are reserved.");
        label8.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label8.setBounds(600, 760, 500, 30);
        contentPane.add(label8);

        background.setIcon(new ImageIcon("img/login.jpg"));
        contentPane.add(background);

        frameLogin.add(contentPane);
    }
/*    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPageView frame1 = new LoginPageView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}