/**
 * Title      : SettingPage.java
 * Description: This class represents the Setting Page in the GUI.
 *              It allows users to customize the application settings, such as enabling background music.
 *              The settings can be saved and applied.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Rongxunan Zhu
 * @version     2.0
 */
package main.GUI;

import main.Control.ButtonControl;

import javax.swing.*;
import java.awt.*;

public class SettingPage extends JFrame {
    public static JFrame frameSetting;
    private JPanel contentPane, optionPanel;
    private JLabel titleLabel,background;
    private JButton saveButton;
    public static JCheckBox musicCheckBox;
    Color settingback = new Color(227, 240, 249); // Defining new colours

    public SettingPage() throws AWTException {
        frameSetting = new JFrame("Settings");
        frameSetting.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frameSetting.setBounds(520, 220, 400, 350);
        frameSetting.setVisible(false);

        contentPane = new JPanel(null); // Use of absolute layout
        contentPane.setBackground(settingback); // Set position and size
        setContentPane(contentPane);

        titleLabel = new JLabel("Settings");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setForeground(BaseFrame.PURPLE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 20, 400, 45); // Set the background colour
        contentPane.add(titleLabel);

        optionPanel = new JPanel();
        optionPanel.setBounds(0, 100, 385, 45);
        musicCheckBox = new JCheckBox("Enable Background Music");
        musicCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
        musicCheckBox.setForeground(Color.BLACK);
        optionPanel.setBackground(settingback);
        musicCheckBox.setBackground(settingback);
        optionPanel.add(musicCheckBox);
        contentPane.add(optionPanel);

        saveButton = new JButton("Save");
        saveButton.setBounds(145, 195, 100, 30);
        saveButton.addActionListener(new ButtonControl());
        contentPane.add(saveButton);
        background = new JLabel(new ImageIcon("img/background4.jpg"));
        background.setBounds(0,-20,1368,840);
        contentPane.add(background);
        frameSetting.add(contentPane);

        setLocationRelativeTo(null); //Centered display
    }

    /* Test code */
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
