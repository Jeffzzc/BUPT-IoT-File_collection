/**
 * Title      : HelpPage.java
 * Description: This class is used to draw the Help Dialog.
 * Copyright  : Copyright (c) 2023/5/13
 * @author      Zhendong Cui
 * @version     1.0
 */
package main.GUI;

import java.awt.*;
import javax.swing.*;

public class HelpPage extends JFrame {
    public static JFrame frameHelp;
    private JPanel contentPane;
    private JLabel titleLabel,textLabel;
    Color settingback = new Color(227, 240, 249);

    public HelpPage() {
        frameHelp = new JFrame("Help");
        frameHelp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameHelp.setBounds(520, 220, 400, 350);
        frameHelp.setVisible(false);

        contentPane = new JPanel(null);
        contentPane.setBackground(settingback);
        setContentPane(contentPane);

        titleLabel = new JLabel("Help");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setForeground(BaseFrame.PURPLE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 20, 400, 45);
        contentPane.add(titleLabel);

        textLabel = new JLabel("Administrator Phone Number: 13513382059");
        textLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        textLabel.setForeground(Color.black);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setBounds(0, 100, 400, 150);
        contentPane.add(textLabel);

        frameHelp.add(contentPane);
        setLocationRelativeTo(null);
    }

//    public static void main(String[] args) {
//        /*
//         * SwingUtilities.invokeLater(new Runnable() {
//         * public void run() {
//         * new SettingPage();
//         * }
//         * });
//         */
//        SettingPage settingPage = new SettingPage();
//        SettingPage.frameSetting.setVisible(true);
//    }
}
