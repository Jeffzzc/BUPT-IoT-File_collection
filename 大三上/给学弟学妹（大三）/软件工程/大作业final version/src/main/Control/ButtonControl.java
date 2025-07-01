/**
 * Title      : ButtonControl.java
 * Description: This class is used to control the actions happened in other GUI pages and perform some reactions.
 * Copyright  : Copyright (c) 2023/5/25
 * @author      Zixu Song & Zhendong Cui
 * @version     6.0
 */

package main.Control;

import main.Entity.Student;
import main.GUI.*;
import main.Read.DataReader;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static main.GUI.MainView.*;
import static main.GUI.SettingPage.musicCheckBox;

public class ButtonControl implements ActionListener{
    private String ID,password,iconName;
    public  Icon icon;
    public static String currentStudent;
    private Clip settingClip;
    public Robot robot = new Robot();
    public String[][] showTableData;
    public String input;
    public JTable Graph;
    public int serialNumB;

    public ButtonControl() throws AWTException {}

    public ButtonControl(Icon icon) throws AWTException {
        this.icon = icon;
    }

    /**
     *Description: This is the override actionPerformed function to listen the event performed by the other pages' button.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ID = LoginPageView.jtfStudentID.getText();
        password = LoginPageView.jtfPwd.getText();
        DataReader.initialize();
        String buttonName=e.getActionCommand();
        if(icon!=null){
            iconName=icon.toString();
        }
        System.out.println(iconName);
        if(buttonName.equals("Login")){
            try {
                LoginFunction();
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        } else if (buttonName.equals("Click here")) {
            ChangingPwdView.frameChanging.setVisible(true);
        } else if (buttonName.equals("Cancel")) {
            ChangingPwdView.frameChanging.setVisible(false);
        } else if (buttonName.equals("Confirm")) {
            ChangePWFunction();
        } else if (buttonName.equals("Save")){
            saveSettings();
        } else if (iconName.equals("img/GradeDownload.jpg")){
            DownloadFunction(30, 270, 1300, 400,"grade");
        } else if (iconName.equals("img/GradePrint.jpg")) {
            PrintFunction(30, 270, 1300, 400);
        } else if (iconName.equals("img/CertificatesDownload.jpg")){
            DownloadFunction(25, 245, 1200, 500,"certificates");
        } else if (iconName.equals("img/CertificatesPrint.jpg")) {
            PrintFunction(25, 245, 1200, 500);
        } else if (iconName.equals("img/CompetitionDownload.jpg")){
            DownloadFunction(25, 245, 1300, 500,"competition");
        } else if (iconName.equals("img/CompetitionPrint.jpg")) {
            PrintFunction(25, 245, 1300, 500);
        } else if (iconName.equals("img/HonorsDownload.jpg")){
            DownloadFunction(25, 245, 1300, 500,"honors");
        } else if (iconName.equals("img/HonorsPrint.jpg")) {
            PrintFunction(25, 245, 1300, 500);
        } else if (iconName.equals("img/exit.jpg")) {
            System.exit(0);
        } else if (iconName.equals("img/setting.jpg")) {
            SettingPage.frameSetting.setVisible(true);
        } else if (iconName.equals("img/help.jpg")) {
            HelpPage.frameHelp.setVisible(true);
        } else if (iconName.equals("img/news.jpg")) {
            AwardPageView.frameAward.setVisible(true);
            NavigationPageView.frameNavi.setVisible(false);
        } else if (iconName.equals("img/statistic.jpg")) {
            GradePageView.frameGrade.setVisible(true);
            NavigationPageView.frameNavi.setVisible(false);
        } else if (iconName.equals("img/syllabus.jpg")) {
            SchedulePageView.frameSchedule.setVisible(true);
            NavigationPageView.frameNavi.setVisible(false);
        } else if (iconName.equals("img/homework.jpg")) {
            CoursePageView.frameCourse.setVisible(true);
            NavigationPageView.frameNavi.setVisible(false);
        } else if (iconName.equals("img/home.jpg")) {
            NavigationPageView.frameNavi.setVisible(true);
            SchedulePageView.frameSchedule.setVisible(false);
            CoursePageView.frameCourse.setVisible(false);
            GradePageView.frameGrade.setVisible(false);
            AwardPageView.frameAward.setVisible(false);
            Certificates.frameCertificate.setVisible(false);
            Competition.frameCompetition.setVisible(false);
            Honors.frameHonor.setVisible(false);
        } else if (iconName.equals("img/competition.jpg")) {
            Competition.frameCompetition.setVisible(true);
            AwardPageView.frameAward.setVisible(false);
        } else if (iconName.equals("img/certificate.jpg")) {
            Certificates.frameCertificate.setVisible(true);
            AwardPageView.frameAward.setVisible(false);
        } else if (iconName.equals("img/honor.jpg")) {
            Honors.frameHonor.setVisible(true);
            AwardPageView.frameAward.setVisible(false);
        } else if(iconName.equals("img/ip.jpg")){
            ip.frameBaseCourse.setVisible(true);
            CoursePageView.frameCourse.setVisible(false);
        } else if(iconName.equals("img/java.jpg")){
            java.frameBaseCourse.setVisible(true);
            CoursePageView.frameCourse.setVisible(false);
        }  else if(iconName.equals("img/media.jpg")){
            media.frameBaseCourse.setVisible(true);
            CoursePageView.frameCourse.setVisible(false);
        }  else if(iconName.equals("img/dsp.jpg")){
            dsp.frameBaseCourse.setVisible(true);
            CoursePageView.frameCourse.setVisible(false);
        }  else if(iconName.equals("img/math.jpg")){
            math.frameBaseCourse.setVisible(true);
            CoursePageView.frameCourse.setVisible(false);
        }  else if(iconName.equals("img/c.jpg")){
            c.frameBaseCourse.setVisible(true);
            CoursePageView.frameCourse.setVisible(false);
        } else if (iconName.equals("img/back2.jpg")) {
            CoursePageView.frameCourse.setVisible(true);
            ip.frameBaseCourse.setVisible(false);
            c.frameBaseCourse.setVisible(false);
            dsp.frameBaseCourse.setVisible(false);
            math.frameBaseCourse.setVisible(false);
            media.frameBaseCourse.setVisible(false);
            java.frameBaseCourse.setVisible(false);
        } else if (iconName.equals("img/back.jpg")) {
            NavigationPageView.frameNavi.setVisible(true);
            AwardPageView.frameAward.setVisible(false);
            CoursePageView.frameCourse.setVisible(false);
            GradePageView.frameGrade.setVisible(false);
            SchedulePageView.frameSchedule.setVisible(false);
        } else if (iconName.equals("img/back3.jpg")) {
            AwardPageView.frameAward.setVisible(true);
            Certificates.frameCertificate.setVisible(false);
            Competition.frameCompetition.setVisible(false);
            Honors.frameHonor.setVisible(false);
        } else if (iconName.equals("img/")) {

        }
    }

    /**
     *Description: This is the function to handle the login request.
     */
    public void LoginFunction() throws AWTException {
        Student student = DataReader.select(ID);
        if(student!=null && student.password.equals(password)){
            currentStudent = ID;
            System.out.println(currentStudent);
            NavigationPageView navigationPageView = new NavigationPageView();
            NavigationPageView.frameNavi.setVisible(true);
            LoginPageView.frameLogin.setVisible(false);
            AwardPageView awardPageView = new AwardPageView();

            try {
                Certificates certificates = new Certificates();
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
            SchedulePageView schedulePageView = new SchedulePageView();
            try {
                Competition competition = new Competition();
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
            CoursePageView coursePageView = new CoursePageView();
            try {
                Honors honors = new Honors();
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
            try {
                GradePageView gradePageView = new GradePageView();
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        }else{
            LoginPageView.label9.setVisible(true);
        }
        LoginPageView.jtfStudentID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                LoginPageView.label9.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
        LoginPageView.jtfPwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                LoginPageView.label9.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
    }

    /**
     *Description: This is the function to handle the change password operation.
     */
    public void ChangePWFunction(){
        String csvFile = "./data/data.csv";
        BufferedReader br = null;
        BufferedWriter bw = null;
        String line = "";
        String csvSplitBy = ",";
        if (ChangingPwdView.jtf1.getText().equals(ChangingPwdView.jtf2.getText())) {
            ArrayList<String> update = new ArrayList<>();
            try {
                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(csvSplitBy);
                    if (data[1].equals(ChangingPwdView.jtf3.getText())) {
                        update.add(data[0] + "," + data[1] + "," + ChangingPwdView.jtf1.getText() + "," + data[3] + "," + data[4] + "," + data[5] + "\r\n");
                    } else {
                        update.add(line + "\r\n");
                    }
                }
                br.close();

                bw = new BufferedWriter(new FileWriter(csvFile));
                for (int i = 0; i < update.size(); i++) {
                    bw.write(update.get(i));
                }
                bw.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
            ChangingPwdView.frameChanging.setVisible(false);
        }else {
            ChangingPwdView.label.setVisible(true);
        }
        ChangingPwdView.jtf1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ChangingPwdView.label.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });

        ChangingPwdView.jtf2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ChangingPwdView.label.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {}
        });
    }

    /**
     *Description: This is the function to get the screen capture.
     */
    public void PrintFunction(int x,int y,int w,int h){
        BufferedImage bfImage = robot.createScreenCapture(new Rectangle(x, y, w, h));
        JFrame frame = new JFrame("print preview");
        //设置在屏幕的位置
        frame.setLocation(10,100);
        frame.setSize(w+25,h+100);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(new JLabel(new ImageIcon(bfImage)));
        JLabel tip = new JLabel("Do you want to print this table?");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton a = new JButton("Yes");
        JButton b =new JButton("No");
        panel.add(a);
        panel.add(b);
        frame.add(tip);
        frame.add(panel);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.setVisible(true);

        b.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        a.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame("print success!");
                //设置在屏幕的位置
                frame2.setLocation(10,100);
                frame2.setSize(300,70);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JLabel inform = new JLabel("print success!");
                frame2.add(inform);
                frame2.setLayout(new FlowLayout(FlowLayout.CENTER));
                frame2.setVisible(true);
                frame.setVisible(false);
            }
        });
    }

    /**
     *Description: This is the function to download the screen capture.
     */
    public void DownloadFunction(int x,int y,int w,int h,String fN){
        BufferedImage image = robot.createScreenCapture(new Rectangle(x,y,w,h));
        screenShotAsFile(image, "./download", fN, "png");
        JFrame successful = new JFrame("download successfully!");
        successful.setLocation(500,400);
        successful.setSize(300,70);
        successful.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel inform = new JLabel("download successfully!");
        successful.add(inform);
        successful.setLayout(new FlowLayout(FlowLayout.CENTER));
        successful.setVisible(true);
    }

    /**
     * Determine if music is playing
     *
     * @return true means the music is playing, false means the music is not playing
     */
    public boolean isBackgroundMusicPlaying() {
        if (settingClip != null) {
            return settingClip.isRunning();
        }
        return false;
    }

    /**
     * Operates accordingly to the user's choice of whether to play music or not
     *
     */
    public void saveSettings() {
        boolean enableMusic = musicCheckBox.isSelected();
        if (enableMusic) {
            if (!isBackgroundMusicPlaying()) {
                playBackgroundMusic("./music/Summer.wav");
            }
        } else {
            stopBackgroundMusic();
        }
        SettingPage.frameSetting.setVisible(false);
    }

    /**
     * Play music from the appropriate path
     * @param filePath Audio path
     */
    public void playBackgroundMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            settingClip = AudioSystem.getClip();
            settingClip.open(audioStream);
            settingClip.loop(Clip.LOOP_CONTINUOUSLY);
            settingClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop music playing
     */
    public void stopBackgroundMusic() {
        if (settingClip != null && settingClip.isRunning()) {
            settingClip.stop();
            settingClip.close();
        }
    }

    /**
     * This function is to handle the query and update the JTable.
     * @param graph old table
     * @param query query button action.
     * @param jtfSearch get the query string.
     * @param tableData old data
     * @param tableColumn get table column string
     * @return new graph table
     */
    public JTable QueryFunction(JTable graph, JButton query, int serialNum, JTextField jtfSearch, String[][] tableData, String[] tableColumn){
        serialNumB =serialNum;
        Graph = graph;
        jtfSearch.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent a)
            {
                a.getSource();
                JTextField textField = (JTextField) a.getSource();//强制转换coercion
                input = textField.getText();//see the source code ,it will return a String
            }
        });

        query.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showTableData = tableData.clone();
                serialNumB = showTableData.length;
                if(!input.equals("Competition name")&&!input.equals("")){
                    for(int i = serialNumB - 1; i>=0; i--){
                        if(showTableData[i][0].equals(input)){
                            showTableData[0] = showTableData[i];
                            showTableData = Arrays.copyOf(showTableData,1);
                        }
                    }
                    serialNumB = 1;
                }
                DefaultTableModel tabModel = new DefaultTableModel(showTableData,tableColumn);
                Graph.setModel(tabModel);
                Graph.setEnabled(true);

                BaseFrameAward.MyTableCellRenderer renderer = new BaseFrameAward.MyTableCellRenderer();

                // 遍历表格的每一列，分别给每一列设置单元格渲染器
                for (int i = 0; i < tableColumn.length; i++) {
                    // 根据 列名 获取 表格列
                    TableColumn column = Graph.getColumn(tableColumn[i]);
                    // 设置 表格列 的 单元格渲染器
                    column.setCellRenderer(renderer);
                }
            }
        });

        return Graph;
    }

    /**
     * Description: this method help users to print the table they query in the system
     * @param x the begin position of print in x axis
     * @param y the begin position of print in y axis
     * @param width the width of print picture
     * @param height the height of print picture
     * */
    public BufferedImage getScreenShot(int x, int y, int width, int height) {
        BufferedImage bfImage = null;
        try {
            Robot robot = new Robot();
            bfImage = robot.createScreenCapture(new Rectangle(x, y, width, height));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return bfImage;
    }

    /**
     * Description: this method help users to download their print
     * @param image the print picture which is generated by clicking the print button
     * @param savePath the save path of the picture
     * @param fileName the save name of the picture
     * @param format the format of the picture, such as .png, .jpg
     * */
    public void screenShotAsFile(BufferedImage image, String savePath, String fileName, String format) {
        try {
            Robot robot = new Robot();
            File path = new File(savePath);
            File file = new File(path, fileName+ "." + format);
            ImageIO.write(image, format, file);
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
