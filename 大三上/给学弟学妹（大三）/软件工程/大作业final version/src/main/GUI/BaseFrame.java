/**
 * Title      : BaseFrame.java
 * Description: This class is used to generate the general widgets of all the GUI pages, which are help,setting,question,exit,title,line and background.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixu Song
 * @version     1.0
 */
package main.GUI;

import main.Control.ButtonControl;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame{
    protected JLabel background, title;
    static JPanel contentPane, line;
    protected JButton help;
    protected JButton setting;
    protected JButton exit;
    protected static JButton bottomButton;
    protected static Color LIGHTBLUE = new Color(0xffDBECFE);
    protected static Color WHITE = new Color(0xffEAE3EA);
    protected static Color GREY = new Color(0xffD0CAD8);
    protected static Color DARKBLUE = new Color(0xff9BAAC7);
    protected static Color PURPLE = new Color(0xff9A9CC3);
    JDialog dialog = new JDialog(this,"Instruction");

    public BaseFrame() throws AWTException {

        contentPane = new JPanel();
        contentPane.setLayout(null);

        help = new JButton(new ImageIcon("img/help.jpg"));
        help.setFont(new Font("Times New Roman", Font.BOLD, 25));
        help.setBounds(1215, 20, 77, 40);
        contentPane.add(help);
        help.addActionListener(new ButtonControl(help.getIcon()));

        setting = new JButton(new ImageIcon("img/setting.jpg"));
        setting.setFont(new Font("Times New Roman", Font.BOLD, 25));
        setting.setBounds(1200, 80, 107, 40);
        contentPane.add(setting);
        setting.addActionListener(new ButtonControl(setting.getIcon()));

        exit = new JButton(new ImageIcon("img/exit.jpg"));
        exit.setFont(new Font("Times New Roman", Font.BOLD, 25));
        exit.setBounds(1220, 140, 68, 40);
        contentPane.add(exit);
        exit.addActionListener(new ButtonControl(exit.getIcon()));

        title = new JLabel("Identity Check");
        title.setFont(new Font("Times New Roman", Font.BOLD, 70));
        title.setBounds(470, 210, 500, 70);
        contentPane.add(title);

        line = new JPanel();
        line.setBounds(0, 295, 1368, 3);
        line.setBackground(Color.BLACK);
        contentPane.add(line);

        background = new JLabel();
        background.setBounds(0, -20, 1368, 840);

        bottomButton = new JButton(new ImageIcon("img/question.jpg"));
        bottomButton.setBounds(0, 795, 1368, 38);
        bottomButton.setBorderPainted(false);
        contentPane.add(bottomButton);

    }
/*    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    BaseFrame frame = new BaseFrame();
                    // frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

 */
}
