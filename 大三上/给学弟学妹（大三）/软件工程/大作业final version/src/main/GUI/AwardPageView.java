/**
 * Title      : AwardPageView.java
 * Description: This class is used to generate the award page for you to choose to see your honors and awards you have gained.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixu Song
 * @version     1.0
 */

package main.GUI;

import main.Control.ButtonControl;

import javax.swing.*;
import java.awt.*;

public class AwardPageView extends BaseFrame2{
    public static JFrame frameAward;
    private JButton competition,certificate,honor;
    
    public AwardPageView() throws AWTException {
        super();
        title.setText("Honors & Awards");
        back.setIcon(new ImageIcon("img/back.jpg"));
        back.addActionListener(new ButtonControl(back.getIcon()));

        frameAward=new JFrame("Learning Journey");
        frameAward.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameAward.setBounds(0,0,1368,864);
        frameAward.setVisible(false);

        //添加竞赛板块
        competition = new JButton(new ImageIcon("img/competition.jpg"));
        competition.setBounds(150,300,300,220);
        contentPane.add(competition);
        competition.addActionListener(new ButtonControl(competition.getIcon()));
        //添加证书板块
        certificate = new JButton(new ImageIcon("img/certificate.jpg"));
        certificate.setBounds(520,300,300,220);
        contentPane.add(certificate);
        certificate.addActionListener(new ButtonControl(certificate.getIcon()));
        //添加荣誉板块
        honor = new JButton(new ImageIcon("img/honor.jpg"));
        honor.setBounds(890,300,300,220);
        contentPane.add(honor);
        honor.addActionListener(new ButtonControl(honor.getIcon()));

        background.setIcon(new ImageIcon("img/background3.jpg"));
        contentPane.add(background);
        frameAward.add(contentPane);
    }
    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AwardPageView frame = new AwardPageView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
}
