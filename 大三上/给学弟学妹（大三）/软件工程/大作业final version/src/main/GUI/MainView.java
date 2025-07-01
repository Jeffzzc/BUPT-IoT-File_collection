/**
 * Title      : MainView.java
 * Description: This class is run the hole program.
 * Copyright  : Copyright (c) 2023/5/23
 * @author      Zixu Song & Zhendong Cui
 * @version     5.0
 */
package main.GUI;

import java.awt.*;

public class MainView {

    public static BaseFrameCourse c;
    public static BaseFrameCourse ip;
    public static BaseFrameCourse dsp;
    public static BaseFrameCourse math;
    public static BaseFrameCourse media;
    public static BaseFrameCourse java;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    LoginPageView login = new LoginPageView();
                    LoginPageView.frameLogin.setVisible(true);
                    ChangingPwdView changingPwdView = new ChangingPwdView();
                    SettingPage settingPage = new SettingPage();
                    HelpPage helpPage = new HelpPage();
                    c=new BaseFrameCourse(2);
                    ip=new BaseFrameCourse(0);
                    java=new BaseFrameCourse(1);
                    media=new BaseFrameCourse(3);
                    dsp=new BaseFrameCourse(4);
                    math=new BaseFrameCourse(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
